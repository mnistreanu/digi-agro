import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EditRendererComponent} from "../../../modules/aggrid/edit-renderer/edit-renderer.component";
import {AgGridModule} from "ag-grid-angular";
import {EditRendererModule} from "../../../modules/aggrid/edit-renderer/edit-renderer.module";
import {FormErrorBlockModule} from "../../../modules/form-error-block/form-error-block.module";
import {DirectivesModule} from "../../../../app/theme/directives/directives.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslateModule} from '@ngx-translate/core';
import {RouterModule} from "@angular/router";
import {NotificationListComponent} from "./notification-list.component";

export const routes = [
    { path: '', component: NotificationListComponent, pathMatch: 'full' }
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
        AgGridModule.withComponents([EditRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [NotificationListComponent]
})
export class NotificationListModule {
}
