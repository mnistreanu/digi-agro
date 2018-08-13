import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import 'chart.js/dist/Chart.js';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ForecastChartsComponent} from './charts/forecast-charts.component';
import {ForecastHarvestComponent} from './harvest-form/forecast-harvest-form.component';
import {ChartsModule} from 'ng2-charts';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {ForecastHarvestFactorComponent} from './harvest-factor/forecast-harvest-factor.component';

export const routes = [
    {path: '', redirectTo: 'forecasting', pathMatch: 'full'},
    {path: 'harvesting-form', component: ForecastHarvestComponent, data: {breadcrumb: 'harvesting-form'}},
    {path: 'harvesting-factor', component: ForecastHarvestFactorComponent, data: {breadcrumb: 'harvesting-factor'}},
    {path: 'charts', component: ForecastChartsComponent, data: {breadcrumb: 'charts'}},
    {path: 'costs-form', component: ForecastHarvestComponent, data: {breadcrumb: 'costs-form'}},
    {path: 'costs', component: ForecastChartsComponent, data: {breadcrumb: 'costs'}}
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,
        ChartsModule,
        DirectivesModule,
        FormErrorBlockModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        ForecastChartsComponent, ForecastHarvestComponent, ForecastHarvestFactorComponent
    ]
})

export class ForecastModule {
}
