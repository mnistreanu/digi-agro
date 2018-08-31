import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import 'chart.js/dist/Chart.js';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ChartsModule} from 'ng2-charts';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {ChemicalsExpensesComponent} from "./chemicals-expenses/chemicals-expenses.component";
import {MachineryExpensesComponent} from "./machinery-expenses/machinery-expenses.component";
import {FuelExpensesComponent} from "./fuel-expenses/fuel-expenses.component";
import {SowingExpensesComponent} from "./sowing-expenses/sowing-expenses.component";
import {WorksExpensesComponent} from "./works-expenses/works-expenses.component";

export const routes = [
    {path: '', redirectTo: 'expenses', pathMatch: 'full'},
    {path: 'machinery', component: MachineryExpensesComponent, data: {breadcrumb: 'machinery'}},
    {path: 'fuel', component: FuelExpensesComponent, data: {breadcrumb: 'fuel'}},
    {path: 'sowing', component: SowingExpensesComponent, data: {breadcrumb: 'sowing'}},
    {path: 'works', component: WorksExpensesComponent, data: {breadcrumb: 'works'}},
    {path: 'chemicals', component: ChemicalsExpensesComponent, data: {breadcrumb: 'chemicals'}}
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,
        ChartsModule,
        DirectivesModule,
        FormErrorBlockModule,
        AgGridModule.withComponents([]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        MachineryExpensesComponent,
        FuelExpensesComponent,
        SowingExpensesComponent,
        WorksExpensesComponent,
        ChemicalsExpensesComponent
    ]
})

export class ManageExpensesModule {
}
