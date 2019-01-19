import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import { CropListComponent } from './crop/list/crop-list.component';
import {EditRendererComponent} from '../../modules/aggrid/edit-renderer/edit-renderer.component';
import {AgGridModule} from 'ag-grid-angular';
import {EditRendererModule} from '../../modules/aggrid/edit-renderer/edit-renderer.module';
import {FormErrorBlockModule} from '../../modules/form-error-block/form-error-block.module';
import {DirectivesModule} from '../../theme/directives/directives.module';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {ManageCropsRoutingModule} from './manage-crops-routing.module';
import {TranslateModule} from '@ngx-translate/core';
import {DatatableComponent, NgxDatatableModule} from '@swimlane/ngx-datatable';
import { CropFormComponent } from './crop/form/crop-form.component';
import { CropVarietyTreeComponent } from './crop-variety/tree/crop-variety-tree.component';
import { CropVarietyListComponent } from './crop-variety/list/crop-variety-list.component';
import { CropVarietyComponent } from './crop-variety/form/crop-variety-form.component';

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        ManageCropsRoutingModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        FormErrorBlockModule,
        EditRendererModule,
        NgxDatatableModule,
        EditRendererModule,
        TranslateModule,
        CommonModule,
        FormsModule,
        DirectivesModule,
        AgGridModule.withComponents([EditRendererComponent])
    ],
    declarations: [CropListComponent, CropFormComponent, CropVarietyTreeComponent, CropVarietyListComponent, CropVarietyComponent]
})
export class ManageCropsModule {
}
