import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ExpenseListNewComponent} from './expense-list-new/expense-list-new.component';
import {ExpensesPieChartComponent} from './expenses-pie-chart/expenses-pie-chart.component';
import {RouterModule} from '@angular/router';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {DeleteRendererModule} from '../../modules/aggrid/delete-renderer/delete-renderer.module';
import {PinnedRowRendererModule} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {PinnedRowRendererComponent} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {DeleteRendererComponent} from '../../modules/aggrid/delete-renderer/delete-renderer.component';
import {ChartsModule} from 'ng2-charts';

export const routes = [
    {path: '', component: ExpenseListNewComponent}
];


@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        ChartsModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        PinnedRowRendererModule,
        DeleteRendererModule,
        EditRendererModule,
        AgGridModule.withComponents([
            EditRendererComponent,
            PinnedRowRendererComponent,
            DeleteRendererComponent
        ]),
        RouterModule.forChild(routes)
    ],
    declarations: [ExpenseListNewComponent, ExpensesPieChartComponent]
})
export class ExpensesNewModule {
}
