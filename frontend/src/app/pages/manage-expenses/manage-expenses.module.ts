import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {MachineryExpensesComponent} from './machinery-expenses/list/machinery-expenses.component';
import {FuelExpensesComponent} from './fuel-expenses/fuel-expenses.component';
import {SowingExpensesComponent} from './sowing-expenses/sowing-expenses.component';
import {WorksExpensesComponent} from './works-expenses/works-expenses.component';
import {FertilizersExpensesComponent} from './fertilizers-expenses/fertilizers-expenses.component';
import {PesticidesExpensesComponent} from './pesticides-expenses/pesticides-expenses.component';
import {ImageRendererComponent} from '../../modules/aggrid/image-renderer/image-renderer.component';
import {ImageRendererModule} from '../../modules/aggrid/image-renderer/image-renderer.module';
import {PinnedRowRendererModule} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.module';
import {PinnedRowRendererComponent} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {MachineryExpensesFormComponent} from './machinery-expenses/form/machinery-expenses-form.component';
import {ExpenseItemTableComponent} from './machinery-expenses/expense-item-table/expense-item-table.component';
import {DeleteRendererComponent} from '../../modules/aggrid/delete-renderer/delete-renderer.component';
import {DeleteRendererModule} from '../../modules/aggrid/delete-renderer/delete-renderer.module';
import {ConfirmationModalModule} from '../../modules/confirmation-modal/confirmation-modal.module';

export const routes = [
    {path: '', redirectTo: 'expenses', pathMatch: 'full'},
    {path: 'machinery', component: MachineryExpensesComponent, data: {breadcrumb: 'machinery'}},
    {path: 'machinery/:id', component: MachineryExpensesFormComponent, data: {breadcrumb: 'machinery form'}},
    {path: 'fuel', component: FuelExpensesComponent, data: {breadcrumb: 'fuel'}},
    {path: 'sowing', component: SowingExpensesComponent, data: {breadcrumb: 'sowing'}},
    {path: 'works', component: WorksExpensesComponent, data: {breadcrumb: 'works'}},
//    {path: 'chemicals', component: ChemicalsExpensesComponent, data: {breadcrumb: 'chemicals'}},
    {path: 'fertilizers', component: FertilizersExpensesComponent, data: {breadcrumb: 'fertilizers'}},
    {path: 'pesticides', component: PesticidesExpensesComponent, data: {breadcrumb: 'pesticides'}}
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
        DeleteRendererModule,
        ConfirmationModalModule,
        AgGridModule.withComponents([ImageRendererComponent, PinnedRowRendererComponent, DeleteRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        MachineryExpensesComponent,
        FuelExpensesComponent,
        SowingExpensesComponent,
        WorksExpensesComponent,
        PesticidesExpensesComponent,
        FertilizersExpensesComponent,
        MachineryExpensesFormComponent,
        ExpenseItemTableComponent
    ]
})

export class ManageExpensesModule {
}
