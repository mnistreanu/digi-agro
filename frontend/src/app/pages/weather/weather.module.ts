import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NguiMapModule} from '@ngui/map';
import {AgGridModule} from 'ag-grid-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {WeatherHistoryComponent} from './history/weather-history.component';
import {CustomImageRendererModule} from '../../modules/aggrid/custom-image-renderer/custom-image-renderer.module';
import {CustomImageRendererComponent} from '../../modules/aggrid/custom-image-renderer/custom-image-renderer.component';
import {Constants} from '../../common/constants';

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
        CustomImageRendererModule,
        AgGridModule.withComponents([CustomImageRendererComponent]),
        NguiMapModule.forRoot({apiUrl: Constants.GOOGLE_MAP_API}),
        RouterModule.forChild(routes)
    ],
    declarations: [WeatherHistoryComponent]
})
export class WeatherModule {
}
