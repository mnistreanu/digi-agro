import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {UserListComponent} from "./user-list/user-list.component";
import {UserComponent} from "./user/user.component";

const routes: Routes = [
  { path: '', component: UserListComponent },
  { path: 'user/:id', component: UserComponent, data: { breadcrumb: 'User Form' }}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageUsersRoutingModule { }
