import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {ExpenseListComponent} from './expense-list/expense-list.component';
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
import {ConfirmationModalModule} from '../../modules/confirmation-modal/confirmation-modal.module';
import {ExpenseSeasonListComponent} from './expense-season-list/expense-season-list.component';
import {GroupedSelectorComponent} from '../../modules/aggrid/selector/grouped-selector/grouped-selector.component';
import {SelectorModule} from '../../modules/aggrid/selector/selector.module';
import {ExpenseBreakdownComponent} from './expense-breakdown/expense-breakdown.component';
import {SelectorComponent} from "../../modules/aggrid/selector/single-selector/selector.component";

export const routes = [
    {path: '', component: ExpenseListComponent},
    {path: 'expense-season-list', component: ExpenseSeasonListComponent}
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
        ConfirmationModalModule,
        SelectorModule,
        AgGridModule.withComponents([
            EditRendererComponent,
            PinnedRowRendererComponent,
            DeleteRendererComponent,
            GroupedSelectorComponent, // todo: remove
            SelectorComponent
        ]),
        RouterModule.forChild(routes)
    ],
    declarations: [ExpenseListComponent, ExpenseSeasonListComponent, ExpensesPieChartComponent, ExpenseBreakdownComponent],
    exports: [
        ExpenseListComponent,
        ExpenseBreakdownComponent
    ]
})
export class ExpensesModule {
}
