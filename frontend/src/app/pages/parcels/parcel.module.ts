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
import { ParcelInfoFormComponent } from './parcel-info-form/parcel-info-form.component';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {ParcelCropFormComponent} from './parcel-crop-form/parcel-crop-form.component';

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
        FormErrorBlockModule,
        DirectivesModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent]),
        NguiMapModule.forRoot({apiUrl: Constants.GOOGLE_MAP_API}),
        RouterModule.forChild(routes),
    ],
    declarations: [ParcelListComponent, ParcelMapComponent, ParcelComponent,
        ParcelMapEditorComponent, ParcelInfoFormComponent, ParcelCropFormComponent]
})
export class ParcelModule {
}
