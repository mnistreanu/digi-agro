import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {BranchListComponent} from "./branch-list/branch-list.component";
import {BranchComponent} from "./branch/branch.component";

const routes: Routes = [
    { path: '', component: BranchListComponent },
    { path: 'branch/:id', component: BranchComponent, data: { breadcrumb: 'Branch Form' }}
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ManageBranchesRoutingModule { }
