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
import {ExpenseCategoriesComponent} from './manage-expense-categories/expense-categories/expense-categories.component';

export const routes = [
    {path: 'manage-expense-categories', component: ExpenseCategoriesComponent, data: {breadcrumb: 'Expense categories'}},
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
        AgGridModule.withComponents([ImageRendererComponent, PinnedRowRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        ExpenseCategoriesComponent,
    ]
})

export class EnterpriseModule {
}
