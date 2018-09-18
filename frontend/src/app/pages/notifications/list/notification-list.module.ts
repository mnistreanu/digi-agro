import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {AgGridModule} from 'ag-grid-angular';
import {FormErrorBlockModule} from '../../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {RouterModule} from '@angular/router';
import {NotificationListComponent} from './notification-list.component';
import {ImageRendererModule} from '../../../modules/aggrid/image-renderer/image-renderer.module';
import {ImageRendererComponent} from '../../../modules/aggrid/image-renderer/image-renderer.component';

export const routes = [
    {path: '', component: NotificationListComponent, pathMatch: 'full'}
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        ImageRendererModule,
        AgGridModule.withComponents([ImageRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [NotificationListComponent]
})
export class NotificationListModule {
}
