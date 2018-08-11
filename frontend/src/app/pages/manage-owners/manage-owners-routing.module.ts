import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {OwnerListComponent} from './owner-list/owner-list.component';
import {OwnerComponent} from './owner/owner.component';

const routes: Routes = [
    {path: '', component: OwnerListComponent},
    {path: 'owner/:id', component: OwnerComponent, data: {breadcrumb: 'Owner Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageOwnersRoutingModule {
}
