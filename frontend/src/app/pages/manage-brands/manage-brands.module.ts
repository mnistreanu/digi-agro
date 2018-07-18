import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {TranslateModule} from '@ngx-translate/core';

import {ManageBrandsRoutingModule} from "./manage-brands-routing.module";
import {BrandListComponent} from "./brand-list/brand-list.component";
import {EditRendererComponent} from "../../modules/aggrid/edit-renderer/edit-renderer.component";
import {AgGridModule} from "ag-grid-angular";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {BrandService} from "../../services/brand.service";
import {BrandComponent} from "./brand/brand.component";
import {EditRendererModule} from "../../modules/aggrid/edit-renderer/edit-renderer.module";
import {FormErrorBlockModule} from "../../modules/form-error-block/form-error-block.module";

@NgModule({
  imports: [
    CommonModule,
    TranslateModule,
    ManageBrandsRoutingModule,
    FormsModule,
    ReactiveFormsModule,
    DirectivesModule,
    FormErrorBlockModule,
    EditRendererModule,
    AgGridModule.withComponents([EditRendererComponent])
  ],
  providers: [BrandService],
  declarations: [BrandListComponent, BrandComponent]
})
export class ManageBrandsModule { }
