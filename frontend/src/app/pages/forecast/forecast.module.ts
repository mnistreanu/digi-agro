import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import 'chart.js/dist/Chart.js';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ForecastChartsComponent} from './charts/forecast-charts.component';
import {ForecastFormComponent} from './forecast-form/forecast-form.component';
import {ChartsModule} from 'ng2-charts';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {HarvestFactorComponent} from './harvest-factor/harvest-factor.component';
import {AgGridModule} from 'ag-grid-angular';
import {ForecastListComponent} from './forecast-list/forecast-list.component';
import { ForecastSummaryComponent } from './forecast-form/forecast-summary/forecast-summary.component';

export const routes = [
    {path: '', component: ForecastListComponent, pathMatch: 'full'},
    {path: 'harvesting/:id', component: ForecastFormComponent, data: {breadcrumb: 'form'}},

    {path: 'charts', component: ForecastChartsComponent, data: {breadcrumb: 'charts'}},
    {path: 'costs-form', component: ForecastFormComponent, data: {breadcrumb: 'costs-form'}},
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
        AgGridModule.withComponents([]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        ForecastChartsComponent,
        ForecastFormComponent,
        HarvestFactorComponent,
        ForecastListComponent,
        ForecastSummaryComponent
    ]
})

export class ForecastModule {
}
