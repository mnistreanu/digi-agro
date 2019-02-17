import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NguiMapModule} from '@ngui/map';
import {ChartsModule} from 'ng2-charts';
import {AgGridModule} from 'ag-grid-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ImageRendererModule} from '../../modules/aggrid/image-renderer/image-renderer.module';
import {ImageRendererComponent} from '../../modules/aggrid/image-renderer/image-renderer.component';
import {Constants} from '../../common/constants';
import {WeatherForecastComponent} from './forecast/weather-forecast.component';
import {WeatherHistoryComponent} from './history/weather-history.component';
import 'chart.js/dist/Chart.js';

export const routes = [
    {path: '', redirectTo: 'weather', pathMatch: 'full'},
    {path: 'forecast', component: WeatherForecastComponent, data: {breadcrumb: 'forecast'}},
    {path: 'history', component: WeatherHistoryComponent, data: {breadcrumb: 'history'}}
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        ChartsModule,
        ImageRendererModule,
        AgGridModule.withComponents([ImageRendererComponent]),
        NguiMapModule.forRoot({apiUrl: Constants.GOOGLE_MAP_API}),
        RouterModule.forChild(routes)
    ],
    declarations: [WeatherForecastComponent, WeatherHistoryComponent]
})
export class WeatherModule {
}
