import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {ReminderComponent} from "./reminder.component";
import {TranslateModule} from '@ngx-translate/core';
import {FormErrorBlockModule} from "../../modules/form-error-block/form-error-block.module";

export const routes = [
    {path: '', component: ReminderComponent}
];

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        FormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        TranslateModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        ReminderComponent
    ]
})
export class ReminderModule {
}
