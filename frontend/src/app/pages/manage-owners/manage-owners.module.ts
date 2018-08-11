import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TranslateModule} from '@ngx-translate/core';

import {ManageOwnersRoutingModule} from './manage-owners-routing.module';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {OwnerListComponent} from './owner-list/owner-list.component';
import {OwnerComponent} from './owner/owner.component';
import {OwnerService} from '../../services/owner.service';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        ManageOwnersRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [OwnerListComponent, OwnerComponent],
    providers: [OwnerService]
})
export class ManageOwnersModule {
}
