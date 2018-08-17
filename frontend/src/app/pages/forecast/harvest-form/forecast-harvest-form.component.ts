import {Component, OnInit} from '@angular/core';
import {Router} from '@angular/router';
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

@Component({
    selector: 'app-forecast-harvest',
    templateUrl: './forecast-harvest-form.component.html'
})

export class ForecastHarvestComponent implements OnInit {

    private form: FormGroup;
    private formSubmitted = false;

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
    labelValidationFail: string;

    constructor(private router: Router,
                private formBuilder: FormBuilder,
                private forecastService: ForecastService,
                private cropService: CropService,
                private parcelService: ParcelService,
                private langService: LangService,
                private toastrService: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.buildForm();
        this.setupParcels();
        this.setupCropCategories();
    }


    private setupLabels() {
        this.langService.get(Messages.SAVED).subscribe((message) => this.labelSaved = message);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe((message) => this.labelValidationFail = message);
    }

    private buildForm() {
        this.form = this.formBuilder.group({
            parcels: [null, Validators.required],
            name: [null, Validators.compose([Validators.required, Validators.maxLength(256)])],
            harvestingYear: [null, Validators.required],
            cropCategoryId: [null, Validators.required],
            cropId: [null, Validators.required],
            cropVarietyId: [null],
            description: [null, Validators.maxLength(1024)],
            unitPrice: [null, Validators.required],
            currency: [null, Validators.required],
            unitOfMeasure: [null, Validators.required],
            quantityHectar: [null, Validators.required]
        });
    }

    private setupParcels() {
        this.area = 0;
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
        });
    }

    public onParcelChange() {
        this.area = 0;
        const selectedParcels = this.form.controls['parcels'].value;
        selectedParcels.forEach(parcelId => {
            this.area += this.parcelMap[parcelId].area;
        });
    }

    private setupCropCategories() {
        this.cropService.findCategories().subscribe(payloadModel => {
            const status = payloadModel['status'];
            const message = payloadModel['message'];
            this.categories = payloadModel['payload'];
        });
    }

    private onCropCategoryChange() {
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

    private onCropChange() {
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

        const forecastModel = new ForecastModel();
        Object.assign(forecastModel, this.form.value);

        this.formSubmitted = false;

        this.forecastService.save(forecastModel).subscribe(() => {
            this.toastrService.success(this.labelSaved);
            this.router.navigate(['/pages/forecasting/harvesting-factor']);
        });
    }

}
