import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { CropListComponent } from './crop/list/crop-list.component';
import {CropFormComponent} from './crop/form/crop-form.component';
import { CropVarietyTreeComponent } from './crop-variety/tree/crop-variety-tree.component';
import { CropVarietyListComponent } from './crop-variety/list/crop-variety-list.component';
import { CropVarietyComponent } from './crop-variety/form/crop-variety-form.component';

const routes: Routes = [
    {path: '', component: CropListComponent},
    {path: 'crop-variety-tree', component: CropVarietyTreeComponent, pathMatch: 'full', data: {breadcrumb: 'Crop & Varieties'}},
    {path: 'crop-variety-list', component: CropVarietyListComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Varieties'}},
    {path: 'crop-variety-list/:id', component: CropVarietyComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Variety'}},
    {path: ':id', component: CropFormComponent, data: {breadcrumb: 'Crop Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageCropsRoutingModule {
}
