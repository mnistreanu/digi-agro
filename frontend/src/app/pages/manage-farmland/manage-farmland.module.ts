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
import {ParcelComponent} from './parcel/parcel.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {ParcelMapEditorComponent} from './parcel-map-editor/parcel-map-editor.component';
import {ParcelInfoFormComponent} from './parcel-info-form/parcel-info-form.component';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {ParcelSeasonFormComponent} from './parcel-season-form/parcel-season-form.component';
import {ManageHarvestComponent} from './manage-harvest/list/manage-harvest.component';
import {CropPlannerComponent} from './crop-planner/list/crop-planner.component';
import {ParcelSeasonListComponent} from './parcel-season-list/parcel-season-list.component';

export const routes = [
    {path: 'parcels', component: ParcelListComponent, pathMatch: 'full'},
    {path: 'parcels/:id', component: ParcelComponent},
    {path: 'field-diagnosis-list', component: ParcelSeasonListComponent, data: {breadcrumb: 'Field diagnosis list'}},
    {path: 'manage-harvest', component: ManageHarvestComponent, data: {breadcrumb: 'Manage Harvest'}},
    {path: 'crop-planner', component: CropPlannerComponent, data: {breadcrumb: 'Crop planner'}},
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
    declarations: [
        ParcelListComponent,
        ParcelMapComponent,
        ParcelComponent,
        ParcelMapEditorComponent,
        ParcelInfoFormComponent,
        ParcelSeasonFormComponent,
        ParcelSeasonListComponent,
        CropPlannerComponent,
        ManageHarvestComponent
    ]
})
export class ManageFarmlandModule {
}
