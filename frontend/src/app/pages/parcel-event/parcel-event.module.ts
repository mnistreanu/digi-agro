import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {NguiAutoCompleteModule} from '@ngui/auto-complete';
import {TranslateModule} from '@ngx-translate/core';
import {EventTypeListComponent} from './event-type/event-type-list/event-type-list.component';
import {EventTypeFormComponent} from './event-type/event-type-form/event-type-form.component';
import { ParcelEventListComponent } from './parcel-event-list/parcel-event-list.component';
import {ConfirmationModalModule} from '../../modules/confirmation-modal/confirmation-modal.module';
import {DeleteRendererModule} from '../../modules/aggrid/delete-renderer/delete-renderer.module';
import {DeleteRendererComponent} from '../../modules/aggrid/delete-renderer/delete-renderer.component';
import {SelectorModule} from '../../modules/aggrid/selector/selector.module';
import {SelectorComponent} from '../../modules/aggrid/selector/single-selector/selector.component';

export const routes = [
    {path: 'event-type', component: EventTypeListComponent},
    {path: 'event-type/:id', component: EventTypeFormComponent},
    {path: 'event-list', component: ParcelEventListComponent},
];


@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        NguiAutoCompleteModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,
        DirectivesModule,
        FormErrorBlockModule,
        DeleteRendererModule,
        EditRendererModule,
        ConfirmationModalModule,
        SelectorModule,
        AgGridModule.withComponents([EditRendererComponent, DeleteRendererComponent, SelectorComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [EventTypeListComponent, EventTypeFormComponent, ParcelEventListComponent]
})
export class ParcelEventModule {
}
