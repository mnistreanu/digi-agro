import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TenantListComponent} from './tenant-list/tenant-list.component';
import {EditRendererComponent} from "../../modules/aggrid/edit-renderer/edit-renderer.component";
import {AgGridModule} from "ag-grid-angular";
import {EditRendererModule} from "../../modules/aggrid/edit-renderer/edit-renderer.module";
import {FormErrorBlockModule} from "../../modules/form-error-block/form-error-block.module";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TenantComponent} from './tenant/tenant.component';
import {ManageTenantsRoutingModule} from "./manage-tenants-routing.module";
import {TenantListFilterComponent} from './tenant-list-filter/tenant-list-filter.component';

@NgModule({
    imports: [
        CommonModule,
        ManageTenantsRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [TenantListComponent, TenantComponent, TenantListFilterComponent]
})
export class ManageTenantsModule {
}
