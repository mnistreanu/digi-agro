import {Component, ViewEncapsulation, OnInit, NgModule} from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MyForm } from './forecast-harvest.interface';
import {CropService} from "../../../services/crop.service";
import {CropCategoryModel} from "../../crop/crop-category.model";
import {CropModel} from "../../crop/crop.model";
import {CropVarietyModel} from "../../crop/crop-variety.model";

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

    constructor(private formBuilder: FormBuilder,
                private cropService: CropService
    ) {

    }

    ngOnInit() {
        this.myForm = this.formBuilder.group({
            simple: ['', Validators.required],
        });

        this.findCropCategories();
        this.findCrops(null);
        this.findVarieties(null);
    }

    private findCropCategories() {
        this.cropService.findCategories().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.categoryModels = payloadModel.payload;
        })
    }

    private findCrops(categoryId: number) {
        this.cropService.findCrops().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.cropModels = payloadModel.payload;
        })
    }

    private findVarieties(cropId: number) {
        this.cropService.findVarieties().subscribe(payloadModel => {
            let status = payloadModel.status;
            let message = payloadModel.message;
            this.varietyModels = payloadModel.payload;
        })
    }

    onSubmit({ value, valid }: { value: MyForm, valid: boolean }) {
        console.log(value, valid);
    }

}
