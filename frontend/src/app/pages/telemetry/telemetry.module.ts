import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {TelemetryComponent} from './telemetry/telemetry.component';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {DirectivesModule} from "../../theme/directives/directives.module";
import {MachineTelemetryService} from "../../services/machine-telemetry.service";
import {EditRendererModule} from "../../modules/aggrid/edit-renderer/edit-renderer.module";
import {DeleteRendererModule} from "../../modules/aggrid/delete-renderer/delete-renderer.module";
import {EditRendererComponent} from "../../modules/aggrid/edit-renderer/edit-renderer.component";
import {AgGridModule} from "ag-grid-angular";
import {DeleteRendererComponent} from "../../modules/aggrid/delete-renderer/delete-renderer.component";
import {NguiMapModule} from "@ngui/map";
import {MapEventsComponent} from './map-events/map-events.component';
import {MapEventService} from "../../services/map-event.service";
import {TelemetryMapComponent} from './telemetry-map/telemetry-map.component';
import {MachineTelemetryComponent} from './machine-telemetry/machine-telemetry.component';
import {TranslateModule} from '@ngx-translate/core';

export const routes = [
    {path: '', component: TelemetryComponent, pathMatch: 'full'}
];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        EditRendererModule,
        DeleteRendererModule,
        AgGridModule.withComponents([EditRendererComponent, DeleteRendererComponent]),
        NguiMapModule.forRoot({apiUrl: 'https://maps.google.com/maps/api/js?key=AIzaSyC_u-ujFg1xhXlOnOV0GyptTlory-KvlFY'}),
        RouterModule.forChild(routes),
    ],
    declarations: [TelemetryComponent, MapEventsComponent, TelemetryMapComponent, MachineTelemetryComponent],
    providers: [MachineTelemetryService, MapEventService]
})
export class TelemetryModule {
}
