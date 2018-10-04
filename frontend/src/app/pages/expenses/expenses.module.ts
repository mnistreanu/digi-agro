import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {MachineryExpensesListComponent} from './machinery-expenses/list/machinery-expenses-list.component';
import {SowingExpensesListComponent} from './sowing-expenses/list/sowing-expenses-list.component';
import {SowingExpensesFormComponent} from './sowing-expenses/form/sowing-expenses-form.component';
import {FuelExpensesListComponent} from './fuel-expenses/list/fuel-expenses-list.component';
import {WorksExpensesListComponent} from './works-expenses/list/works-expenses-list.component';
import {ImageRendererComponent} from '../../modules/aggrid/image-renderer/image-renderer.component';
import {ImageRendererModule} from '../../modules/aggrid/image-renderer/image-renderer.module';
import {PinnedRowRendererModule} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.module';
import {PinnedRowRendererComponent} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {MachineryExpensesFormComponent} from './machinery-expenses/form/machinery-expenses-form.component';
import {MachineryExpenseItemTableComponent} from './machinery-expenses/expense-item-table/machinery-expense-item-table.component';
import {DeleteRendererComponent} from '../../modules/aggrid/delete-renderer/delete-renderer.component';
import {DeleteRendererModule} from '../../modules/aggrid/delete-renderer/delete-renderer.module';
import {ConfirmationModalModule} from '../../modules/confirmation-modal/confirmation-modal.module';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AdminGuard} from '../../guards/admin-guard';
import { AngularMultiSelectModule } from 'angular2-multiselect-dropdown/angular2-multiselect-dropdown';
import {FuelExpensesFormComponent} from './fuel-expenses/form/fuel-expenses-form.component';
import { ExpenseListComponent } from './expense-list/expense-list.component';
import {FuelExpenseItemTableComponent} from './fuel-expenses/expense-item-table/fuel-expense-item-table.component';
import {SowingExpenseItemTableComponent} from './sowing-expenses/expense-item-table/sowing-expense-item-table.component';
import {WorksExpensesFormComponent} from './works-expenses/form/works-expenses-form.component';
import {PesticideExpensesListComponent} from './pesticide-expenses/list/pesticide-expenses-list.component';
import {PesticideExpensesFormComponent} from './pesticide-expenses/form/pesticide-expenses-form.component';
import {FertilizerExpensesFormComponent} from './fertilizer-expenses/form/fertilizer-expenses-form.component';
import {FertilizerExpensesListComponent} from './fertilizer-expenses/list/fertilizer-expenses-list.component';

export const routes = [
    {path: '', component: ExpenseListComponent},
    {path: 'machinery', component: MachineryExpensesListComponent},
    {path: 'machinery/:id', component: MachineryExpensesFormComponent, canActivate: [AdminGuard]},
    {path: 'fuel', component: FuelExpensesListComponent},
    {path: 'fuel/:id', component: FuelExpensesFormComponent, canActivate: [AdminGuard]},
    {path: 'sowing', component: SowingExpensesListComponent},
    {path: 'sowing/:id', component: SowingExpensesFormComponent},
    {path: 'works', component: WorksExpensesListComponent},
    {path: 'works/:id', component: WorksExpensesFormComponent},
    {path: 'fertilizer', component: FertilizerExpensesListComponent},
    {path: 'fertilizer/:id', component: FertilizerExpensesFormComponent},
    {path: 'pesticide', component: PesticideExpensesListComponent},
    {path: 'pesticide/:id', component: PesticideExpensesFormComponent},
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,      // todo: remove
        AngularMultiSelectModule,
        DirectivesModule,
        FormErrorBlockModule,
        ImageRendererModule,
        PinnedRowRendererModule,
        DeleteRendererModule,
        EditRendererModule,
        ConfirmationModalModule,
        AgGridModule.withComponents([
            EditRendererComponent,
            ImageRendererComponent,
            PinnedRowRendererComponent,
            DeleteRendererComponent
        ]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        MachineryExpensesListComponent,
        MachineryExpensesFormComponent,
        MachineryExpenseItemTableComponent,
        FuelExpensesListComponent,
        FuelExpensesFormComponent,
        FuelExpenseItemTableComponent,
        SowingExpensesListComponent,
        SowingExpensesFormComponent,
        SowingExpenseItemTableComponent,
        WorksExpensesListComponent,
        WorksExpensesFormComponent,
        PesticideExpensesListComponent,
        PesticideExpensesFormComponent,
        FertilizerExpensesListComponent,
        FertilizerExpensesFormComponent,
        ExpenseListComponent
    ]
})

export class ExpensesModule {
}
