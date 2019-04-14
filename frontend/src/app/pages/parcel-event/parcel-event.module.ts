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

export const routes = [
    {path: 'event-type', component: EventTypeListComponent},
    {path: 'event-type/:id', component: EventTypeFormComponent}
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
        EditRendererModule,
        AgGridModule.withComponents([EditRendererComponent]),
        RouterModule.forChild(routes)
    ],
    declarations: [EventTypeListComponent, EventTypeFormComponent]
})
export class ParcelEventModule {
}
