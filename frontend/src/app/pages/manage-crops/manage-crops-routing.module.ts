import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import { CropsComponent } from './crops/crops.component';
//import {TenantComponent} from './tenant/tenant.component';

const routes: Routes = [
    {path: '', component: CropsComponent},
    //{path: 'tenant/:id', component: TenantComponent, data: {breadcrumb: 'Tenant Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageCropsRoutingModule {
}
