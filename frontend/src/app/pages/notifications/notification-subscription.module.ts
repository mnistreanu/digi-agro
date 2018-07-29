///<reference path="notification-subscription.component.ts"/>
import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {EditRendererComponent} from "../../modules/aggrid/edit-renderer/edit-renderer.component";
import {AgGridModule} from "ag-grid-angular";
import {EditRendererModule} from "../../modules/aggrid/edit-renderer/edit-renderer.module";
import {FormErrorBlockModule} from "../../modules/form-error-block/form-error-block.module";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslateModule} from '@ngx-translate/core';
import {NotificationSubscriptionComponent} from "./notification-subscription.component";
import {RouterModule} from "@angular/router";

export const routes = [
    { path: '', component: NotificationSubscriptionComponent, pathMatch: 'full' }
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
    declarations: [NotificationSubscriptionComponent]
})
export class NotificationSubscriptionModule {
}
