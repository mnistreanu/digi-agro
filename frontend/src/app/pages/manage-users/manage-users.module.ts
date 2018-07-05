import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {ManageUsersRoutingModule} from "./manage-users-routing.module";
import {UserListComponent} from "./user-list/user-list.component";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {AgGridModule} from "ag-grid-angular/main";
import {EditRendererComponent} from "../../modules/aggrid/edit-renderer/edit-renderer.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserComponent} from "./user/user.component";
import {FormErrorBlockComponent} from "../../modules/form-error-block/form-error-block.component";
import {EditRendererModule} from "../../modules/aggrid/edit-renderer/edit-renderer.module";
import {FormErrorBlockModule} from "../../modules/form-error-block/form-error-block.module";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
    ManageUsersRoutingModule,
    FormErrorBlockModule,
    EditRendererModule,
    AgGridModule.withComponents([EditRendererComponent])
  ],
  declarations: [UserListComponent, UserComponent ]
})
export class ManageUsersModule { }
