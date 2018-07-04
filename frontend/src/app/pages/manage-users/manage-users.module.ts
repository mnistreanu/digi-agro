import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";

import {ManageUsersRoutingModule} from "./manage-users-routing.module";
import {UserListComponent} from "./user-list/user-list.component";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {AgGridModule} from "ag-grid-angular/main";
import {EditRendererComponent} from "../../theme/aggrid/edit-renderer/edit-renderer.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {UserComponent} from "./user/user.component";
import {FormErrorBlockComponent} from "../../theme/components/form-error-block/form-error-block.component";

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
    ManageUsersRoutingModule,
    AgGridModule.withComponents([EditRendererComponent])
  ],
  declarations: [UserListComponent, UserComponent, EditRendererComponent, FormErrorBlockComponent ]
})
export class ManageUsersModule { }
