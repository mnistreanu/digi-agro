import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import { BrowserModule } from "@angular/platform-browser";
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {ChemicalsExpensesComponent} from './chemicals-expenses/chemicals-expenses.component';
import {MachineryExpensesComponent} from './machinery-expenses/machinery-expenses.component';
import {FuelExpensesComponent} from './fuel-expenses/fuel-expenses.component';
import {SowingExpensesComponent} from './sowing-expenses/sowing-expenses.component';
import {WorksExpensesComponent} from './works-expenses/works-expenses.component';
import {CustomImageRendererComponent} from "../../modules/aggrid/custom-image-renderer/custom-image-renderer.component";
import {CustomImageRendererModule} from "../../modules/aggrid/custom-image-renderer/custom-image-renderer.module";
import { CustomPinnedRowRenderer } from "../../modules/aggrid/custom-pinned-row-renderer/custom-pinned-row-renderer.component";

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
        DirectivesModule,
        FormErrorBlockModule,
        CustomImageRendererModule,
        AgGridModule.withComponents([CustomImageRendererComponent, CustomPinnedRowRenderer]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        MachineryExpensesComponent,
        FuelExpensesComponent,
        SowingExpensesComponent,
        WorksExpensesComponent,
        ChemicalsExpensesComponent,
        CustomPinnedRowRenderer
    ]
})

export class ManageExpensesModule {
}
