import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
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
