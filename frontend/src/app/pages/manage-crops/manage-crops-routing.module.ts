import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { CropsComponent } from './crops/crops.component';
import {CropComponent} from './crop/crop.component';
import { CropVarietyTreeComponent } from './crop-variety-tree/crop-variety-tree.component';
import { CropVarietiesListComponent } from './crop-varieties/crop-varieties-list.component';
import { CropVarietyComponent } from './crop-variety/crop-variety.component';

const routes: Routes = [
    {path: '', component: CropsComponent},
    {path: 'crop-variety-tree', component: CropVarietyTreeComponent, pathMatch: 'full', data: {breadcrumb: 'Crop & Varieties'}},
    {path: 'crop-varieties', component: CropVarietiesListComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Varieties'}},
    {path: 'crop-varieties/:id', component: CropVarietyComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Variety'}},
    {path: ':id', component: CropComponent, data: {breadcrumb: 'Crop Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageCropsRoutingModule {
}
