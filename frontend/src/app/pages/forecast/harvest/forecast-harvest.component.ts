import {Component, OnInit} from "@angular/core";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ForecastHarvestForm} from "./forecast-harvest.interface";
import {CropService} from "../../../services/crop.service";
import {CropCategoryModel} from "../../crop/crop-category.model";
import {CropModel} from "../../crop/crop.model";
import {CropVarietyModel} from "../../crop/crop-variety.model";
import {IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts} from "angular-2-dropdown-multiselect";
import {ParcelService} from "../../../services/parcel.service";

@Component({
    selector: 'az-forecast-harvest',
    templateUrl: './forecast-harvest.component.html'
})

export class ForecastHarvestComponent implements OnInit {

    private form: FormGroup;

    private parcels: IMultiSelectOption[] = [];
    private categoryModels: CropCategoryModel[] = [];
    private cropModels: CropModel[] = [];
    private varietyModels: CropVarietyModel[] = [];

    public parcelControlSettings: IMultiSelectSettings = {
        checkedStyle: 'fontawesome',
        buttonClasses: 'btn btn-secondary btn-block',
        dynamicTitleMaxItems: 3,
        displayAllSelectedText: true,
        showCheckAll: true,
        showUncheckAll: true
    };

    public parcelControlTexts: IMultiSelectTexts = {
        checkAll: 'Select all',
        uncheckAll: 'Unselect all',
        checked: 'item selected',
        checkedPlural: 'items selected',
        defaultTitle: 'Select parcels',
        allSelected: 'All selected',
    };

    constructor(private formBuilder: FormBuilder,
                private cropService: CropService,
                private parcelService: ParcelService) {
    }

    ngOnInit() {
        this.buildForm();
        this.setupParcels();
        this.setupCropCategories();
        // this.findCrops(1);
        // this.findVarieties(1);
    }

    private buildForm() {
        this.form = this.formBuilder.group({
            parcels: [null, Validators.required],
            name: [null, Validators.compose([Validators.required, Validators.maxLength(256)])],
            cropCategoryId: [null, Validators.required],
            cropId: [null, Validators.required],
            cropVarietyId: [null],
            description: [null, Validators.compose([Validators.required, Validators.maxLength(1024)])],
        });
    }

    private setupParcels() {
        this.parcelService.find().subscribe(models => {
            this.parcels = models.map((model) => {
                return {
                    id: model.id,
                    name: model.name
                };
            });
        });
    }

    private setupCropCategories() {
        this.cropService.findCategories().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.categoryModels = payloadModel.payload;
        });
    }

    private onCropCategoryChange() {
        let cropCategoryId = this.form.controls['cropCategoryId'].value;
        this.setupCrops(cropCategoryId);
        this.cropModels = [];
        this.varietyModels = [];
        this.form.controls['cropId'].setValue(null);
        this.form.controls['cropVarietyId'].setValue(null);
    }

    private setupCrops(categoryId: number) {
        this.cropService.findCrops(categoryId).subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.cropModels = payloadModel.payload;
        });
    }

    private onCropChange() {
        let cropId = this.form.controls['cropId'].value;
        this.setupVarieties(cropId);
        this.varietyModels = [];
        this.form.controls['cropVarietyId'].setValue(null);
    }

    private setupVarieties(cropId: number) {
        this.cropService.findVarieties(cropId).subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.varietyModels = payloadModel.payload;
        });
    }

    onSubmit({value, valid}: { value: ForecastHarvestForm, valid: boolean }) {
        console.log(value, valid);
    }

}
