import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {TranslateModule} from '@ngx-translate/core';
import {DatatableComponent, NgxDatatableModule} from '@swimlane/ngx-datatable';
import { CropListComponent } from './crop/list/crop-list.component';
import { CropFormComponent } from './crop/form/crop-form.component';
import { CropVarietyTreeComponent } from './crop-variety/tree/crop-variety-tree.component';
import { CropVarietyListComponent } from './crop-variety/list/crop-variety-list.component';
import { CropVarietyFormComponent } from './crop-variety/form/crop-variety-form.component';
import {CropSeasonListComponent} from './crop-season/list/crop-season-list.component';
import {CropSeasonFormComponent} from './crop-season/form/crop-season-form.component';


const routes: Routes = [
    {path: 'crop-list', component: CropListComponent},
    {path: 'crop-list/:id', component: CropFormComponent, data: {breadcrumb: 'Crop Form'}},
    {path: 'crop-variety-tree', component: CropVarietyTreeComponent, pathMatch: 'full', data: {breadcrumb: 'Crop & Varieties'}},
    {path: 'crop-variety-list', component: CropVarietyListComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Varieties'}},
    {path: 'crop-variety-list/:id', component: CropVarietyFormComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Variety'}},
    {path: 'crop-season-list', component: CropSeasonListComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Seasons'}},
    {path: 'crop-season-list/:id', component: CropSeasonFormComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Season'}},
];

@NgModule({
    imports: [
        RouterModule.forChild(routes),
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        NgxDatatableModule,
        EditRendererModule,
        TranslateModule,
        CommonModule,
        FormsModule,
        DirectivesModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [CropListComponent, CropFormComponent,
                   CropVarietyTreeComponent, CropVarietyListComponent, CropVarietyFormComponent,
                   CropSeasonListComponent, CropSeasonFormComponent]
})
export class ManageCropsModule {
}
