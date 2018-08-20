import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {CropService} from '../../../services/crop.service';
import {CropCategoryModel} from '../../manage-crops/crop-variety/crop-category.model';
import {CropModel} from '../../manage-crops/crop-variety/crop.model';
import {CropVarietyModel} from '../../manage-crops/crop-variety/crop-variety.model';
import {IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts} from 'angular-2-dropdown-multiselect';
import {ParcelService} from '../../../services/parcel.service';
import {LangService} from '../../../services/lang.service';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';
import {ForecastService} from '../../../services/forecast.service';
import {ForecastModel} from '../forecast.model';
import {ForecastSnapshotModel} from '../forecast-snapshot.model';
import {ForecastHarvestModel} from '../forecast-harvest.model';

@Component({
    selector: 'app-forecast-form',
    templateUrl: './forecast-form.component.html'
})
export class ForecastFormComponent implements OnInit {

    private form: FormGroup;
    private formSubmitted = false;

    private model: ForecastModel;

    private area: number;
    private parcelMap;
    private parcels: IMultiSelectOption[];
    private categories: CropCategoryModel[] = [];
    private crops: CropModel[] = [];
    private cropVarieties: CropVarietyModel[] = [];

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

    labelSaved: string;
    labelRemoved: string;
    labelValidationFail: string;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder,
                private forecastService: ForecastService,
                private cropService: CropService,
                private parcelService: ParcelService,
                private langService: LangService,
                private toastrService: ToastrService) {
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

        this.setupLabels();
    }


    private setupLabels() {
        this.langService.get(Messages.SAVED).subscribe((message) => this.labelSaved = message);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe((message) => this.labelValidationFail = message);
        this.langService.get(Messages.REMOVED).subscribe((message) => this.labelRemoved = message);
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

        this.setupParcels();

        this.setupCropCategories();
        if (this.model.cropCategoryId != null) {
            this.setupCrops(this.model.cropCategoryId);
            if (this.model.cropVarietyId != null) {
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

    private setupCropCategories() {
        this.cropService.findCategories().subscribe(payloadModel => {
            const status = payloadModel['status'];
            const message = payloadModel['message'];
            this.categories = payloadModel['payload'];
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
            this.crops = payloadModel.payload;
        });
    }

    public onCropChange() {
        const cropId = this.form.controls['cropId'].value;
        this.setupCropVarieties(cropId);
        this.cropVarieties = [];
        this.form.controls['cropVarietyId'].setValue(null);
    }

    private setupCropVarieties(cropId: number) {
        this.cropService.findVarieties(cropId).subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.cropVarieties = payloadModel.payload;
        });
    }

    onSubmit() {
        this.formSubmitted = true;

        if (!this.form.valid) {
            this.toastrService.warning(this.labelValidationFail);
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
            this.toastrService.success(this.labelSaved);
        });
    }

    onRemove() {
        this.forecastService.remove(this.model.id).subscribe(() => {
            this.toastrService.success(this.labelRemoved);
            this.router.navigate(['/pages/forecasting']);
        });
    }

}
