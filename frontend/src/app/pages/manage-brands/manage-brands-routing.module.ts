import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {BrandListComponent} from './brand-list/brand-list.component';
import {BrandComponent} from './brand/brand.component';

const routes: Routes = [
    {path: '', component: BrandListComponent},
    {path: 'brand/:id', component: BrandComponent, data: {breadcrumb: 'Brand Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageBrandsRoutingModule {
}
