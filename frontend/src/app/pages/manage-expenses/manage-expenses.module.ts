import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {MachineryExpensesComponent} from './machinery-expenses/machinery-expenses.component';
import {FuelExpensesComponent} from './fuel-expenses/fuel-expenses.component';
import {SowingExpensesComponent} from './sowing-expenses/sowing-expenses.component';
import {WorksExpensesComponent} from './works-expenses/works-expenses.component';
import {FertilizersExpensesComponent} from './fertilizers-expenses/fertilizers-expenses.component';
import {PesticidesExpensesComponent} from './pesticides-expenses/pesticides-expenses.component';
import {CustomImageRendererComponent} from '../../modules/aggrid/custom-image-renderer/custom-image-renderer.component';
import {CustomImageRendererModule} from '../../modules/aggrid/custom-image-renderer/custom-image-renderer.module';
import {PinnedRowRendererModule} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.module';
import {PinnedRowRendererComponent} from '../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';

export const routes = [
    {path: '', redirectTo: 'expenses', pathMatch: 'full'},
    {path: 'machinery', component: MachineryExpensesComponent, data: {breadcrumb: 'machinery'}},
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
        CustomImageRendererModule,
        PinnedRowRendererModule,
        AgGridModule.withComponents([CustomImageRendererComponent, PinnedRowRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        MachineryExpensesComponent,
        FuelExpensesComponent,
        SowingExpensesComponent,
        WorksExpensesComponent,
        PesticidesExpensesComponent,
        FertilizersExpensesComponent
    ]
})

export class ManageExpensesModule {
}
