import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from "@angular/router";
import {NguiMapModule} from "@ngui/map";
import {AgGridModule} from "ag-grid-angular";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {TranslateModule} from "@ngx-translate/core";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {ParcelListComponent} from './parcel-list/parcel-list.component';
import {ParcelMapComponent} from './parcel-map/parcel-map.component';
import {ParcelComponent} from './parcel/parcel.component';
import {NavigateRendererModule} from "../../modules/aggrid/view-renderer/navigate-renderer.module";
import {NavigateRendererComponent} from "../../modules/aggrid/view-renderer/navigate-renderer.component";

export const routes = [
    {path: '', component: ParcelComponent, pathMatch: 'full'}
];

@NgModule({
  imports: [
      CommonModule,
      TranslateModule,
      FormsModule,
      ReactiveFormsModule,
      DirectivesModule,
      NavigateRendererModule,
      AgGridModule.withComponents([NavigateRendererComponent]),
      NguiMapModule.forRoot({apiUrl: 'https://maps.google.com/maps/api/js?key=AIzaSyC_u-ujFg1xhXlOnOV0GyptTlory-KvlFY'}),
      RouterModule.forChild(routes),
  ],
  declarations: [ParcelListComponent, ParcelMapComponent, ParcelComponent]
})
export class ParcelModule { }
