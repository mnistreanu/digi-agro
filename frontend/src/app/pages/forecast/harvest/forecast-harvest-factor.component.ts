import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ForecastHarvestForm} from './forecast-harvest.interface';
import {CropService} from '../../../services/crop.service';
import {CropCategoryModel} from '../../crop/crop-category.model';
import {CropModel} from '../../crop/crop.model';
import {CropVarietyModel} from '../../crop/crop-variety.model';
import {ParcelService} from '../../../services/parcel.service';

@Component({
    selector: 'app-forecast-harvest-factor',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './forecast-harvest-factor.component.html'
})
export class ForecastHarvestFactorComponent implements OnInit {
    myForm: FormGroup;
    private categoryModels: CropCategoryModel[];
    private cropModels: CropModel[];
    private varietyModels: CropVarietyModel[];


    constructor(private formBuilder: FormBuilder,
                private cropService: CropService,
                private parcelService: ParcelService) {

    }

    ngOnInit() {
        this.myForm = this.formBuilder.group({
            name: ['', Validators.compose([Validators.required, Validators.maxLength(256)])],
            cropCategoryId: ['', Validators.required],
            cropId: ['', Validators.required],
            cropVarietyId: [''],
            description: ['', Validators.compose([Validators.required, Validators.maxLength(1024)])],
        });

        this.findCropCategories();
        this.findCrops(1);
        this.findVarieties(1);
    }


    private findCropCategories() {
        this.cropService.findCategories().subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.categoryModels = payloadModel.payload;
        });
    }

    private findCrops(categoryId: number) {
        this.cropService.findCrops(categoryId).subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.cropModels = payloadModel.payload;
        });
    }

    private findVarieties(cropId: number) {
        this.cropService.findVarieties(cropId).subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.varietyModels = payloadModel.payload;
        });
    }

    onSubmit({value, valid}: { value: ForecastHarvestForm, valid: boolean }) {
        console.log(value, valid);
    }

}
