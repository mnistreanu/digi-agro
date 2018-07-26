import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {RouterModule} from '@angular/router';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {AgroTaskCalendarComponent} from "./agro-task-calendar.component";
import {TranslateModule} from '@ngx-translate/core';
import {FormErrorBlockModule} from "../../modules/form-error-block/form-error-block.module";

export const routes = [
    {path: '', component: AgroTaskCalendarComponent}
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
        AgroTaskCalendarComponent
    ]
})
export class AgroTaskCalendarModule {
}
