import {NgModule} from '@angular/core';
import {RouterModule, Routes} from '@angular/router';
import {MachineListComponent} from './machine-list/machine-list.component';
import {MachineComponent} from './machine/machine.component';

const routes: Routes = [
    {path: '', component: MachineListComponent},
    {path: ':id', component: MachineComponent, data: {breadcrumb: 'Machine Form'}}
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ManageMachinesRoutingModule {
}
