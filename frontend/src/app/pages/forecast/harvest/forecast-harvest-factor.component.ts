import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ForecastHarvestForm} from './forecast-harvest.interface';
import {CropService} from '../../../services/crop.service';
import {CropCategoryModel} from '../../crop/crop-category.model';
import {CropModel} from '../../crop/crop.model';
import {CropVarietyModel} from '../../crop/crop-variety.model';
import {ParcelService} from '../../../services/parcel.service';
import {ForecastService} from "../../../services/forecast.service";
import {ForecastModel} from "../forecast.model";

@Component({
    selector: 'app-forecast-harvest-factor',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './forecast-harvest-factor.component.html'
})
export class ForecastHarvestFactorComponent implements OnInit {
    myForm: FormGroup;
    forecastModels: ForecastModel[];

    constructor(private formBuilder: FormBuilder,
                private forecastService: ForecastService) {

    }

    ngOnInit() {
        this.findForecasts(1);
    }

    private findForecasts(tenantId: number) {
        this.forecastService.findForecasts(tenantId).subscribe(payloadModel => {
            const status = payloadModel.status;
            const message = payloadModel.message;
            this.forecastModels = payloadModel.payload;
        });
    }


}
