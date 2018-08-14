import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { CropsComponent } from './crops/crops.component';
import {CropComponent} from './crop/crop.component';

const routes: Routes = [
    {path: '', component: CropsComponent},
    {path: ':id', component: CropComponent, data: {breadcrumb: 'Crop Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageCropsRoutingModule {
}
