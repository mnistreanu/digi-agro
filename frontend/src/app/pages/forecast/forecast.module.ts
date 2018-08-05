import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import 'chart.js/dist/Chart.js';
import { DirectivesModule } from '../../theme/directives/directives.module';
import {ForecastHarvestChartsComponent} from './harvest/forecast-harvest-charts.component';
import {ForecastHarvestComponent} from './harvest/forecast-harvest.component';
import {ChartsModule} from "ng2-charts";

export const routes = [
  { path: '', redirectTo: 'forecasting', pathMatch: 'full' },
  { path: 'harvesting-form', component: ForecastHarvestComponent,  data: { breadcrumb: 'harvesting-form' }},
  { path: 'harvesting', component: ForecastHarvestChartsComponent,  data: { breadcrumb: 'harvesting' }},
  { path: 'costs-form', component: ForecastHarvestComponent,  data: { breadcrumb: 'costs-form' }},
  { path: 'costs', component: ForecastHarvestChartsComponent,  data: { breadcrumb: 'costs' }}
];

@NgModule({
  imports: [
    CommonModule,
    ChartsModule,
    DirectivesModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    ForecastHarvestChartsComponent, ForecastHarvestComponent
  ]
})

export class ForecastModule { }
