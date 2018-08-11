import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {UserListComponent} from './user-list/user-list.component';
import {UserFormComponent} from './user-form/user-form.component';

const routes: Routes = [
    {path: '', component: UserListComponent},
    // { path: 'user/:id', component: UserComponent, data: { breadcrumb: 'User Form' }}
    {path: 'user/:id', component: UserFormComponent, data: {breadcrumb: 'User Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageUsersRoutingModule {
}
