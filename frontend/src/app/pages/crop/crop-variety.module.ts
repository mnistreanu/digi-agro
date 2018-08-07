import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { CropVarietyComponent } from './crop-variety.component';
import { DirectivesModule } from '../../theme/directives/directives.module';
import {TranslateModule} from "@ngx-translate/core";

export const routes = [
  { path: '', component: CropVarietyComponent, pathMatch: 'full' }
];

@NgModule({
  imports: [
    TranslateModule,
    CommonModule,
    FormsModule,
    DirectivesModule,
    RouterModule.forChild(routes)
  ],
  declarations: [
    CropVarietyComponent
  ]
})
export class CropVarietyModule { }
