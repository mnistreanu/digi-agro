import {Component, OnInit} from '@angular/core';
import {MapEventModel} from '../map-events/map-event.model';
import {MachineTelemetryModel} from '../machine-telemetry/machine-telemetry.model';

@Component({
    selector: 'app-telemetry',
    templateUrl: './telemetry.component.html',
    styleUrls: ['./telemetry.component.scss']
})
export class TelemetryComponent implements OnInit {


    mapPolylinePath: any[] = [];
    mapMarkers: any[] = [];

    constructor() {
    }

    ngOnInit() {
    }

    processCoordinates(models: MachineTelemetryModel[]) {
        this.setupMapPolyline(models);
    }

    private setupMapPolyline(models: MachineTelemetryModel[]) {
        this.mapPolylinePath = models.map((model) => {
            return {lat: +model.latitude, lng: +model.longitude};
        });
    }

    processEvents(models: MapEventModel[]) {
        this.setupMapMarkers(models);
    }

    private setupMapMarkers(models: MapEventModel[]) {
        this.mapMarkers = models.map((model) => {
            return {
                message: model.message,
                position: {
                    lat: +model.latitude,
                    lng: +model.longitude
                }
            };
        });
    }

}
