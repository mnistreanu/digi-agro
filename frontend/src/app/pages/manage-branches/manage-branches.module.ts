import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';

import {ManageBranchesRoutingModule} from './manage-branches-routing.module';
import {BranchListComponent} from './branch-list/branch-list.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {BranchService} from '../../services/branch.service';
import {BranchComponent} from './branch/branch.component';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        ManageBranchesRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [BranchListComponent, BranchComponent],
    providers: [BranchService]
})
export class ManageBranchesModule {
}
