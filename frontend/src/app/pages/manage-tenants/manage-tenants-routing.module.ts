import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {TenantListComponent} from "./tenant-list/tenant-list.component";
import {TenantComponent} from "./tenant/tenant.component";

const routes: Routes = [
  { path: '', component: TenantListComponent },
  { path: 'tenant/:id', component: TenantComponent, data: { breadcrumb: 'Tenant Form' }}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageTenantsRoutingModule { }
