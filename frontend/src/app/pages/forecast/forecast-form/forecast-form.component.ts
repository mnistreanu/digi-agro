import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CropService} from '../../../services/crop/crop.service';
import {CropCategoryModel} from '../../manage-crops/crop-category.model';
import {CropModel} from '../../manage-crops/crop/crop.model';
import {CropVarietyModel} from '../../manage-crops/crop-variety.model';
import {IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts} from 'angular-2-dropdown-multiselect';
import {ParcelService} from '../../../services/parcel.service';
import {LangService} from '../../../services/lang.service';
import {Messages} from '../../../common/messages';
import {ForecastService} from '../../../services/forecast.service';
import {ForecastModel} from '../forecast.model';
import {ForecastSnapshotModel} from '../forecast-snapshot.model';
import {ForecastHarvestModel} from '../forecast-harvest.model';
import {AlertService} from '../../../services/alert.service';
import {CropCategoryService} from '../../../services/crop/crop-category.service';
import {CropVarietyService} from '../../../services/crop/crop-variety.service';
import {FieldMapper} from '../../../common/field.mapper';

@Component({
    selector: 'app-forecast-form',
    templateUrl: './forecast-form.component.html'
})
export class ForecastFormComponent implements OnInit {

    form: FormGroup;
    formSubmitted = false;

    model: ForecastModel;

    private area: number;
    private parcelMap;
    private parcels: IMultiSelectOption[];
    private categories: CropCategoryModel[] = [];
    private crops: CropModel[] = [];
    private cropVarieties: CropVarietyModel[] = [];

    private harvestAmount;
    private unitPrice;

    public parcelControlSettings: IMultiSelectSettings = {
        checkedStyle: 'fontawesome',
        buttonClasses: 'btn btn-secondary btn-block',
        dynamicTitleMaxItems: 3,
        displayAllSelectedText: true,
        showCheckAll: true,
        showUncheckAll: true
    };

    public parcelControlTexts: IMultiSelectTexts = {
        checkAll: this.langService.instant(Messages.SELECT_ALL),
        uncheckAll: this.langService.instant(Messages.UNSELECT_ALL),
        checked: this.langService.instant(Messages.ITEM_SELECTED),
        checkedPlural: this.langService.instant(Messages.ITEMS_SELECTED),
        defaultTitle: this.langService.instant(Messages.PLEASE_SELECT) + ' ' + this.langService.instant(Messages.THE_PARCELS),
        allSelected: this.langService.instant(Messages.ALL_SELECTED),
    };


    constructor(private router: Router,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder,
                private forecastService: ForecastService,
                private cropService: CropService,
                private cropVarietyService: CropVarietyService,
                private cropCategoryService: CropCategoryService,
                private parcelService: ParcelService,
                private langService: LangService,
                private alertService: AlertService) {
    }

    ngOnInit() {

        this.route.params.subscribe(params => {
            const id = params['id'];
            if (id == -1) {
                this.prepareNew();
            }
            else {
                this.setupModel(id);
            }
        });
    }

    private prepareNew() {
        this.model = new ForecastModel();
        this.model.snapshot = new ForecastSnapshotModel();
        const initialHarvest = new ForecastHarvestModel();
        initialHarvest.createdAt = new Date();
        initialHarvest.factorName = this.langService.instant('forecast.initial-forecast');
        initialHarvest.quantity = 0;
        this.model.snapshot.harvests = [initialHarvest];
        this.buildForm();
    }

    private setupModel(id) {
        this.forecastService.findOne(id).subscribe(data => {
            this.model = data;
            this.buildForm();
        });
    }

    private buildForm() {
        this.form = this.formBuilder.group({
            name: [this.model.name, Validators.compose([Validators.required, Validators.maxLength(256)])],
            description: [this.model.description, Validators.maxLength(1024)],
            cropCategoryId: [this.model.cropCategoryId, Validators.required],
            cropId: [this.model.cropId, Validators.required],
            cropVarietyId: [this.model.cropVarietyId],
            harvestingYear: [this.model.harvestingYear, Validators.required],
            parcels: [this.model.snapshot.parcels, Validators.required],
            unitOfMeasure: [this.model.snapshot.unitOfMeasure, Validators.required],
            currency: [this.model.snapshot.currency, Validators.required],
            unitPrice: [this.model.snapshot.unitPrice, Validators.required]
        });

        this.unitPrice = this.model.snapshot.unitPrice;

        this.setupParcels();

        this.setupCropCategories();
        if (this.model.cropCategoryId != null) {
            this.setupCrops(this.model.cropCategoryId);
            if (this.model.cropId != null) {
                this.setupCropVarieties(this.model.cropId);
            }
        }
    }

    private setupParcels() {
        this.parcelService.find().subscribe(models => {
            this.parcelMap = {};
            this.parcels = [];
            models.forEach((model) => {
                this.parcelMap[model.id] = model;
                this.parcels.push({
                    id: model.id,
                    name: model.name
                });
            });
            this.calcArea(this.model.snapshot.parcels);
        });
    }

    public onParcelChange() {
        const selectedParcels = this.form.controls['parcels'].value;
        this.calcArea(selectedParcels);
    }

    private calcArea(parcels) {
        if (parcels) {
            this.area = parcels.reduce((prevValue, parcelId) => prevValue + this.parcelMap[parcelId].area, 0);
        }
        else {
            this.area = 0;
        }
    }

    public onUnitPriceChange() {
        this.unitPrice = this.form.controls['unitPrice'].value;
    }

    private setupCropCategories() {
        this.cropCategoryService.find().subscribe(models => {
            this.categories = models;
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            this.categories.forEach(model => {
                model['name'] = model[nameField];
            });
        });
    }

    public onCropCategoryChange() {
        const cropCategoryId = this.form.controls['cropCategoryId'].value;
        this.setupCrops(cropCategoryId);
        this.crops = [];
        this.cropVarieties = [];
        this.form.controls['cropId'].setValue(null);
        this.form.controls['cropVarietyId'].setValue(null);
    }

    private setupCrops(categoryId: number) {
        this.cropService.findCrops(categoryId).subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            this.crops = payloadModel.payload;
            this.crops.forEach(model => {
                model['name'] = model[nameField];
            });
        });
    }

    public onCropChange() {
        const cropId = this.form.controls['cropId'].value;
        this.setupCropVarieties(cropId);
        this.cropVarieties = [];
        this.form.controls['cropVarietyId'].setValue(null);
    }

    private setupCropVarieties(cropId: number) {
        this.cropVarietyService.find(cropId).subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            const mapper = new FieldMapper(this.langService.getLanguage());
            const nameField = mapper.get('name');
            this.cropVarieties = payloadModel.payload;
            this.cropVarieties.forEach(model => {
                model['name'] = model[nameField];
            });
        });
    }

    registerHarvestAmount(amount) {
        this.harvestAmount = amount;
    }

    onSubmit() {
        this.formSubmitted = true;

        if (!this.form.valid) {
            this.alertService.validationFailed();
            return;
        }

        const formValue = this.form.value;
        this.model.name = formValue.name;
        this.model.description = formValue.description;
        this.model.cropCategoryId = formValue.cropCategoryId;
        this.model.cropId = formValue.cropId;
        this.model.cropVarietyId = formValue.cropVarietyId;
        this.model.harvestingYear = formValue.harvestingYear;
        this.model.snapshot.parcels = formValue.parcels;
        this.model.snapshot.unitOfMeasure = formValue.unitOfMeasure;
        this.model.snapshot.currency = formValue.currency;
        this.model.snapshot.unitPrice = formValue.unitPrice;

        this.formSubmitted = false;

        this.forecastService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    onRemove() {
        this.forecastService.remove(this.model.id).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['/pages/forecasting']);
        });
    }

}
