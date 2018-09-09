import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import { BrowserModule } from "@angular/platform-browser";
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {MultiselectDropdownModule} from 'angular-2-dropdown-multiselect';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {AgGridModule} from 'ag-grid-angular';
import {SowingWorksComponent} from './sowing-works/sowing-works.component';
import {OtherWorksComponent} from './other-works/other-works.component';
import {HarvestingWorksComponent} from './harvesting-works/harvesting-works.component';
import {CustomImageRendererComponent} from "../../modules/aggrid/custom-image-renderer/custom-image-renderer.component";
import {CustomImageRendererModule} from "../../modules/aggrid/custom-image-renderer/custom-image-renderer.module";
import { CustomPinnedRowRenderer } from "../../modules/aggrid/custom-pinned-row-renderer/custom-pinned-row-renderer.component";

export const routes = [
    {path: '', redirectTo: 'agro-works', pathMatch: 'full'},
    {path: 'sowing', component: SowingWorksComponent, data: {breadcrumb: 'sowing'}},
    {path: 'works', component: OtherWorksComponent, data: {breadcrumb: 'other works'}},
    {path: 'harvesting', component: HarvestingWorksComponent, data: {breadcrumb: 'harvesting'}},
];

@NgModule({
    imports: [
        TranslateModule,
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        MultiselectDropdownModule,
        DirectivesModule,
        FormErrorBlockModule,
        CustomImageRendererModule,
        AgGridModule.withComponents([CustomImageRendererComponent, CustomPinnedRowRenderer]),
        RouterModule.forChild(routes)
    ],
    declarations: [
        SowingWorksComponent,
        OtherWorksComponent,
        HarvestingWorksComponent,
        CustomPinnedRowRenderer
    ]
})

export class AgroWorksModule {
}
