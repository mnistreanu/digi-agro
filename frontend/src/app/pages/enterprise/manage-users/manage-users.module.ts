import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';

import {ManageUsersRoutingModule} from './manage-users-routing.module';
import {UserListComponent} from './user-list/user-list.component';
import {DirectivesModule} from '../../../theme/directives/directives.module';
import {AgGridModule} from 'ag-grid-angular/main';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {EditRendererModule} from '../../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../../modules/form-error-block/form-error-block.module';
import {UserFormComponent} from './user-form/user-form.component';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {TranslateModule} from '@ngx-translate/core';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        ManageUsersRoutingModule,
        FormErrorBlockModule,
        EditRendererModule,
        MultiselectDropdownModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [UserListComponent, UserFormComponent]
})
export class ManageUsersModule {
}
