import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {FormsModule} from '@angular/forms';
import {CropVarietyComponent} from './crop-variety.component';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {TranslateModule} from '@ngx-translate/core';
import {AgGridModule} from 'ag-grid-angular';
import {RouterModule} from '@angular/router';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';

export const routes = [
    {path: '', component: CropVarietyComponent, pathMatch: 'full'}
];

@NgModule({
    imports: [
        EditRendererModule,
        TranslateModule,
        AgGridModule.withComponents([EditRendererComponent]),
        CommonModule,
        FormsModule,
        DirectivesModule,
        RouterModule.forChild(routes)
    ],
    declarations: [
        CropVarietyComponent
    ]
})
export class CropVarietyModule {
}
