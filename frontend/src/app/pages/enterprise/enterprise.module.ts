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
import {BranchListComponent} from '../manage-branches/branch-list/branch-list.component';
import {BranchComponent} from '../manage-branches/branch/branch.component';
import {BranchService} from '../../services/branch.service';

export const routes = [
    {path: 'manage-branches', component: BranchListComponent, data: {breadcrumb: 'Branches'}},
    {path: 'manage-branches/:id', component: BranchComponent, data: {breadcrumb: 'Branch Form'}},
    {path: 'manage-expense-categories', component: ExpenseCategoryTreeComponent, data: {breadcrumb: 'Expense categories'}},
    {path: 'manage-expense-categories/:id', component: ExpenseCategoryComponent, data: {breadcrumb: 'Expense category'}},
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
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
        ExpenseCategoryTreeComponent,
        ExpenseCategoryComponent
    ],
    providers: [BranchService]
})

export class EnterpriseModule {
}

