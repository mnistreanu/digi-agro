import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {ImageRendererComponent} from '../../modules/aggrid/image-renderer/image-renderer.component';
import {ImageRendererModule} from '../../modules/aggrid/image-renderer/image-renderer.module';
import {PinnedRowRendererModule} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.module';
import {PinnedRowRendererComponent} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {NguiAutoCompleteModule} from '@ngui/auto-complete';
import {CropPlannerComponent} from './crop-planner/list/crop-planner.component';
import {ManageHarvestComponent} from './manage-harvest/list/manage-harvest.component';
import {ParcelSeasonListComponent} from '../parcels/parcel-season-list/parcel-season-list.component';

export const routes = [
    {path: 'field-diagnosis-list', component: ParcelSeasonListComponent, data: {breadcrumb: 'Field diagnosis list'}},
    {path: 'manage-harvest', component: ManageHarvestComponent, data: {breadcrumb: 'Manage Harvest'}},
    {path: 'crop-planner', component: CropPlannerComponent, data: {breadcrumb: 'Crop planner'}},
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        NguiAutoCompleteModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,
        DirectivesModule,
        FormErrorBlockModule,
        ImageRendererModule,
        PinnedRowRendererModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent, ImageRendererComponent, PinnedRowRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        ParcelSeasonListComponent,
        CropPlannerComponent,
        ManageHarvestComponent
    ],
    providers: []
})

export class FarmlandModule {
}

