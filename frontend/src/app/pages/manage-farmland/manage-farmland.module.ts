import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NguiMapModule} from '@ngui/map';
import {AgGridModule} from 'ag-grid-angular';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ParcelListComponent} from './parcel-list/parcel-list.component';
import {Constants} from '../../common/constants';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {ParcelMapEditorComponent} from './parcel/parcel-map-editor/parcel-map-editor.component';
import {ParcelGeneralFormComponent} from './parcel/parcel-general-form/parcel-general-form.component';
import {ParcelCropFormComponent} from './parcel/parcel-crop-form/parcel-crop-form.component';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {ParcelSoilFormComponent} from './parcel/parcel-soil-form/parcel-soil-form.component';
import {ParcelDiagnosisListComponent} from './parcel-diagnosis/parcel-diagnosis-list/parcel-diagnosis-list.component';
import {ParcelDiagnosisFormComponent} from './parcel-diagnosis/parcel-diagnosis-form/parcel-diagnosis-form.component';
import {ManageHarvestComponent} from './manage-harvest/list/manage-harvest.component';
import {CropPlannerComponent} from './crop-planner/list/crop-planner.component';
import {ParcelComponent} from './parcel/parcel.component';
import {ParcelsMapComponent} from './parcels-map/parcels-map.component';

export const routes = [
    {path: 'parcels', component: ParcelListComponent, pathMatch: 'full'},
    {path: 'parcels/:id', component: ParcelComponent},
    {path: 'parcels-map', component: ParcelsMapComponent, data: {breadcrumb: 'Parcels map'}},
    {path: 'field-diagnosis-list', component: ParcelDiagnosisListComponent, data: {breadcrumb: 'Field diagnosis list'}},
    {path: 'field-diagnosis-list/:id', component: ParcelDiagnosisFormComponent},
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
        ParcelsMapComponent,
        ParcelComponent,
        ParcelMapEditorComponent,
        ParcelGeneralFormComponent,
        ParcelSoilFormComponent,
        ParcelCropFormComponent,
        ParcelDiagnosisListComponent,
        ParcelDiagnosisFormComponent,
        CropPlannerComponent,
        ManageHarvestComponent
    ]
})
export class ManageFarmlandModule {
}
