import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NguiMapModule} from '@ngui/map';
import {AgGridModule} from 'ag-grid-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {WeatherHistoryComponent} from './history/weather-history.component';

export const routes = [
    {path: '', redirectTo: 'weather', pathMatch: 'full'},
    {path: 'history', component: WeatherHistoryComponent, data: {breadcrumb: 'history'}},
    {path: 'forecast', component: WeatherHistoryComponent, data: {breadcrumb: 'forecast'}}
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        AgGridModule.withComponents([]),
        NguiMapModule.forRoot({apiUrl: 'https://maps.google.com/maps/api/js?key=AIzaSyC_u-ujFg1xhXlOnOV0GyptTlory-KvlFY'}),
        RouterModule.forChild(routes)
    ],
    declarations: [WeatherHistoryComponent]
})
export class WeatherModule {
}
