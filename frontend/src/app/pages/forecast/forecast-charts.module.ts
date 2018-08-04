import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';
import 'chart.js/dist/Chart.js';
import { DirectivesModule } from '../../theme/directives/directives.module';
import {ForecastHarvestChartsComponent} from './harvest/forecast-harvest-charts.component';
import {ChartsModule} from "ng2-charts";

export const routes = [
  { path: '', redirectTo: 'forecasting', pathMatch: 'full' },
  { path: 'harvesting', component: ForecastHarvestChartsComponent,  data: { breadcrumb: 'harvesting' }  }
];

@NgModule({
  imports: [
    CommonModule,
    ChartsModule,
    DirectivesModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    ForecastHarvestChartsComponent
  ]
})

export class ForecastChartsModule { }
