import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NguiMapModule} from '@ngui/map';
import {AgGridModule} from 'ag-grid-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ParcelListComponent} from './parcel-list/parcel-list.component';
import {ParcelMapComponent} from './parcel-map/parcel-map.component';
import {Constants} from '../../common/constants';
import { ParcelComponent } from './parcel/parcel.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import { ParcelMapEditorComponent } from './parcel-map-editor/parcel-map-editor.component';

export const routes = [
    {path: '', component: ParcelListComponent, pathMatch: 'full'},
    {path: ':id', component: ParcelComponent},
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent]),
        NguiMapModule.forRoot({apiUrl: Constants.GOOGLE_MAP_API}),
        RouterModule.forChild(routes),
    ],
    declarations: [ParcelListComponent, ParcelMapComponent, ParcelComponent, ParcelMapEditorComponent]
})
export class ParcelModule {
}
