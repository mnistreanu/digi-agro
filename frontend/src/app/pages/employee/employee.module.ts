import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule, Routes} from '@angular/router';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {EmployeeListComponent} from '../enterprise/manage-employees/employee-list/employee-list.component';
import {EmployeeComponent} from '../enterprise/manage-employees/employee/employee.component';

const routes: Routes = [
    {path: '', component: EmployeeListComponent},
    {path: ':id', component: EmployeeComponent, data: {breadcrumb: 'Employee Form'}}
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        RouterModule.forChild(routes),
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [EmployeeListComponent, EmployeeComponent]
})
export class EmployeeModule {
}
