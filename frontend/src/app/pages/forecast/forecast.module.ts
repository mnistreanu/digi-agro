import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import 'chart.js/dist/Chart.js';
import { DirectivesModule } from '../../theme/directives/directives.module';
import {ForecastHarvestChartsComponent} from './harvest/forecast-harvest-charts.component';
import {ForecastHarvestComponent} from './harvest/forecast-harvest.component';
import {ChartsModule} from "ng2-charts";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from "angular-2-dropdown-multiselect";
import {ForecastHarvestFactorComponent} from "./harvest/forecast-harvest-factor.component";

export const routes = [
  { path: '', redirectTo: 'forecasting', pathMatch: 'full' },
  { path: 'harvesting-form', component: ForecastHarvestComponent,  data: { breadcrumb: 'harvesting-form' }},
  { path: 'harvesting-factor', component: ForecastHarvestFactorComponent,  data: { breadcrumb: 'harvesting-factor' }},
  { path: 'harvesting', component: ForecastHarvestChartsComponent,  data: { breadcrumb: 'harvesting' }},
  { path: 'costs-form', component: ForecastHarvestComponent,  data: { breadcrumb: 'costs-form' }},
  { path: 'costs', component: ForecastHarvestChartsComponent,  data: { breadcrumb: 'costs' }}
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
    RouterModule.forChild(routes)
  ],
  declarations: [
    ForecastHarvestChartsComponent, ForecastHarvestComponent, ForecastHarvestFactorComponent
  ]
})

export class ForecastModule { }
