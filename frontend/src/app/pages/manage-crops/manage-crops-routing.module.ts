import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { CropsComponent } from './crops/crops.component';
import {CropComponent} from './crop/crop.component';
import { CropVarietyComponent } from './crop-variety/crop-variety.component';

const routes: Routes = [
    {path: '', component: CropsComponent},
    {path: 'crop-varieties', component: CropVarietyComponent, pathMatch: 'full', data: {breadcrumb: 'Crop Varieties'}},
    {path: ':id', component: CropComponent, data: {breadcrumb: 'Crop Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageCropsRoutingModule {
}
