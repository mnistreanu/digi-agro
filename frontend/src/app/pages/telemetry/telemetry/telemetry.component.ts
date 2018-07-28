import {Component, OnInit, ViewChild} from "@angular/core";
import {MachineService} from "../../../services/machine.service";
import {AuthService} from "../../../services/auth/auth.service";
import {CoordinatesComponent} from "../coordinates/coordinates.component";
import {TelemetryModel} from "./telemetry.model";
import {MapEventsComponent} from "../map-events/map-events.component";
import {MapEventModel} from "../map-events/map-event.model";

@Component({
    selector: 'az-telemetry',
    templateUrl: './telemetry.component.html',
    styleUrls: ['./telemetry.component.scss']
})
export class TelemetryComponent implements OnInit {

    username: string;
    machinesPresent: boolean = false;
    machineIdentifier: string;
    availableMachineIdentifiers: string[];

    mapPolylinePath: any[] = [];
    mapMarkers: any[] = [];

    constructor(private machineService: MachineService,
                private authService: AuthService) {
    }

    ngOnInit() {
        this.setupUser();
        this.setupMachineIdentifiers();
    }

    private setupUser() {
        this.username = this.authService.getUsername();
    }

    private setupMachineIdentifiers() {
        this.machineService.fetchIdentifiers().subscribe(data => {
            this.availableMachineIdentifiers = data;

            this.machinesPresent = data && data.length > 0;
            if (!this.machinesPresent) {
                return;
            }

            this.machineIdentifier = this.availableMachineIdentifiers[0];
        })
    }

    public onMachineChange() {

    }

    processCoordinates(models: TelemetryModel[]) {
        this.setupMapPolyline(models);
    }

    private setupMapPolyline(models: TelemetryModel[]) {
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
