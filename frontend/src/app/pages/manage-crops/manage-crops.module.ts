import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import { CropsComponent } from './crops/crops.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ManageCropsRoutingModule} from './manage-crops-routing.module';
//import {TenantListFilterComponent} from './tenant-list-filter/tenant-list-filter.component';
import {TranslateModule} from '@ngx-translate/core';
import {DatatableComponent, NgxDatatableModule} from '@swimlane/ngx-datatable';
import { CropComponent } from './crop/crop.component';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        ManageCropsRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        NgxDatatableModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [CropsComponent, CropComponent
    //    TenantComponent, TenantListFilterComponent
    ]
})
export class ManageCropsModule {
}
