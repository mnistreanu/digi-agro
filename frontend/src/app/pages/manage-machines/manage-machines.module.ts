import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';

import {NguiAutoCompleteModule} from '@ngui/auto-complete';
import {ManageMachinesRoutingModule} from './manage-machines-routing.module';
import {MachineListComponent} from './machine-list/machine-list.component';
import {MachineComponent} from './machine/machine.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {MachineService} from '../../services/machine.service';
import {BrandService} from '../../services/brand.service';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        ManageMachinesRoutingModule,
        NguiAutoCompleteModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [MachineListComponent, MachineComponent],
    providers: [MachineService, BrandService]
})
export class ManageMachinesModule {
}
