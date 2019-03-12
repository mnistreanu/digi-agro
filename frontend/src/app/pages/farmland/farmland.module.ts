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
import {NguiAutoCompleteModule} from '@ngui/auto-complete';
import {ParcelListComponent} from './field-diagnosis/list/parcel-list.component';
import {CropPlannerComponent} from './crop-planner/list/crop-planner.component';
import {ManageHarvestComponent} from './manage-harvest/list/manage-harvest.component';

export const routes = [
    {path: 'field-list', component: ParcelListComponent, data: {breadcrumb: 'Field list'}},
    {path: 'manage-harvest', component: ManageHarvestComponent, data: {breadcrumb: 'Manage Harvest'}},
    {path: 'crop-planner', component: CropPlannerComponent, data: {breadcrumb: 'Crop planner'}},
    // {path: 'manage-branches/:id', component: BranchComponent, data: {breadcrumb: 'Branch Form'}},
    // {path: 'manage-users', component: UserListComponent, data: {breadcrumb: 'Users'}},
    // {path: 'manage-users/:id', component: UserFormComponent, data: {breadcrumb: 'User Form'}},
    // {path: 'manage-employees', component: EmployeeListComponent, data: {breadcrumb: 'Employees'}},
    // {path: 'manage-employees/:id', component: EmployeeComponent, data: {breadcrumb: 'Employee Form'}},
    // {path: 'manage-machines', component: MachineListComponent, data: {breadcrumb: 'Machines'}},
    // {path: 'manage-machines/:id', component: MachineComponent, data: {breadcrumb: 'Machine Form'}},
    // {path: 'manage-expense-categories', component: ExpenseCategoryTreeComponent, data: {breadcrumb: 'Expense categories'}},
    // {path: 'manage-expense-categories/:id', component: ExpenseCategoryComponent, data: {breadcrumb: 'Expense category'}},
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
        ParcelListComponent,
        CropPlannerComponent,
        ManageHarvestComponent
    ],
    providers: []
})

export class FarmlandModule {
}

