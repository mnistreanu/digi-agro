import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {ImageRendererComponent} from '../../modules/aggrid/image-renderer/image-renderer.component';
import {ImageRendererModule} from '../../modules/aggrid/image-renderer/image-renderer.module';
import {PinnedRowRendererModule} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.module';
import {PinnedRowRendererComponent} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {ExpenseCategoryTreeComponent} from './manage-expense-categories/expense-category-tree/expense-category-tree.component';
import {ExpenseCategoryComponent} from './manage-expense-categories/expense-category/expense-category.component';
import {BranchListComponent} from './manage-branches/branch-list/branch-list.component';
import {BranchComponent} from './manage-branches/branch/branch.component';
import {BranchService} from '../../services/branch.service';
import {EmployeeListComponent} from './manage-employees/employee-list/employee-list.component';
import {EmployeeComponent} from './manage-employees/employee/employee.component';
import {MachineListComponent} from './manage-machines/machine-list/machine-list.component';
import {MachineComponent} from './manage-machines/machine/machine.component';
import {BrandService} from '../../services/brand.service';
import {MachineService} from '../../services/machine.service';
import {NguiAutoCompleteModule} from '@ngui/auto-complete';
import {UserListComponent} from './manage-users/user-list/user-list.component';
import {UserFormComponent} from './manage-users/user-form/user-form.component';
import { MachineGroupListComponent } from './machine-groups/machine-group-list/machine-group-list.component';
import { MachineGroupComponent } from './machine-groups/machine-group/machine-group.component';

export const routes = [
    {path: 'manage-branches', component: BranchListComponent, data: {breadcrumb: 'Branches'}},
    {path: 'manage-branches/:id', component: BranchComponent, data: {breadcrumb: 'Branch Form'}},
    {path: 'manage-users', component: UserListComponent, data: {breadcrumb: 'Users'}},
    {path: 'manage-users/:id', component: UserFormComponent, data: {breadcrumb: 'User Form'}},
    {path: 'manage-employees', component: EmployeeListComponent, data: {breadcrumb: 'Employees'}},
    {path: 'manage-employees/:id', component: EmployeeComponent, data: {breadcrumb: 'Employee Form'}},
    {path: 'machine-groups', component: MachineGroupListComponent, data: {breadcrumb: 'Machine Groups'}},
    {path: 'machine-groups/:id', component: MachineGroupComponent, data: {breadcrumb: 'Machine Group Form'}},
    {path: 'manage-machines', component: MachineListComponent, data: {breadcrumb: 'Machines'}},
    {path: 'manage-machines/:id', component: MachineComponent, data: {breadcrumb: 'Machine Form'}},
    {path: 'manage-expense-categories', component: ExpenseCategoryTreeComponent, data: {breadcrumb: 'Expense categories'}},
    {path: 'manage-expense-categories/:id', component: ExpenseCategoryComponent, data: {breadcrumb: 'Expense category'}},
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        NguiAutoCompleteModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,
        DirectivesModule,
        FormErrorBlockModule,
        ImageRendererModule,
        PinnedRowRendererModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent, ImageRendererComponent, PinnedRowRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        BranchListComponent,
        BranchComponent,
        UserListComponent,
        UserFormComponent,
        EmployeeListComponent,
        EmployeeComponent,
        MachineListComponent,
        MachineComponent,
        ExpenseCategoryTreeComponent,
        ExpenseCategoryComponent,
        MachineGroupListComponent,
        MachineGroupComponent
    ],
    providers: [BranchService, MachineService, BrandService]
})

export class EnterpriseModule {
}

