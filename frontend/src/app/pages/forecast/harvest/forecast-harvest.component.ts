import {Component, ViewEncapsulation, OnInit, NgModule} from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ForecastHarvestForm } from './forecast-harvest.interface';
import {CropService} from "../../../services/crop.service";
import {CropCategoryModel} from "../../crop/crop-category.model";
import {CropModel} from "../../crop/crop.model";
import {CropVarietyModel} from "../../crop/crop-variety.model";
import { IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts } from 'angular-2-dropdown-multiselect';
import {ParcelService} from "../../../services/parcel.service";
import {ParcelModel} from "../../telemetry/parcel.model";

@Component({
    selector: 'az-forecast-harvest',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './forecast-harvest.component.html'
})

export class ForecastHarvestComponent implements OnInit  {
    myForm: FormGroup;
    private categoryModels : CropCategoryModel[];
    private cropModels : CropModel[];
    private varietyModels : CropVarietyModel[];
    public parcelControlOptions: IMultiSelectOption[];

    public parcelIdModel: number[];
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


    public parcelControlOptions: IMultiSelectOption[] = [
        { id: 1, name: 'Sweden'},
        { id: 2, name: 'Norway' },
        { id: 3, name: 'Canada' },
        { id: 4, name: 'USA' }
    ];

    constructor(private formBuilder: FormBuilder,
                private cropService: CropService,
                private parcelService: ParcelService
    ) {

    }

    ngOnInit() {
        this.myForm = this.formBuilder.group({
            name: ['', Validators.compose([Validators.required,  Validators.maxLength(256)])],
            cropCategoryId: ['', Validators.required],
            cropId: ['', Validators.required],
            cropVarietyId: [''],
            description: ['', Validators.compose([Validators.required,  Validators.maxLength(1024)])],
        });

//        this.findParcels();
        this.findCropCategories();
        this.findCrops(1);
        this.findVarieties(1);
    }

    private findParcels() {
        this.parcelService.find().subscribe(parcelModels => {
            // let status = payloadModel.status;
            // let message = payloadModel.message;
            // this.parcelModels = payloadModel.payload;
            debugger;

            parcelModels.forEach((model) => {
                let option : IMultiSelectOption;
                option.id = model.id;
                option.name = model.name;
                this.parcelControlOptions.push(option);
            });
        })
    }

    private findCropCategories() {
        this.cropService.findCategories().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.categoryModels = payloadModel.payload;
        })
    }

    private findCrops(categoryId: number) {
        this.cropService.findCrops(categoryId).subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.cropModels = payloadModel.payload;
        })
    }

    private findVarieties(cropId: number) {
        this.cropService.findVarieties(cropId).subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.varietyModels = payloadModel.payload;
        })
    }

    onSubmit({ value, valid }: { value: ForecastHarvestForm, valid: boolean }) {
        console.log(value, valid);
    }

}
