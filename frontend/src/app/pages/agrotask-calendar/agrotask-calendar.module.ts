import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { DirectivesModule } from '../../theme/directives/directives.module';
import {AgroTaskCalendarComponent} from "./agrotask-calendar.component";
import {TranslateModule} from '@ngx-translate/core';

export const routes = [
  { path: '', component: AgroTaskCalendarComponent }
];

@NgModule({
  imports: [
    CommonModule,
    FormsModule,
    DirectivesModule,
    TranslateModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    AgroTaskCalendarComponent
  ]
})
export class AgroTaskCalendarModule { }
