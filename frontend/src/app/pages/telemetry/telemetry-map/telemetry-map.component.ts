import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ParcelService} from "../../../services/parcel.service";
import {ParcelModel} from "../parcel.model";

@Component({
    selector: 'az-telemetry-map',
    templateUrl: './telemetry-map.component.html',
    styleUrls: ['./telemetry-map.component.scss']
})
export class TelemetryMapComponent implements OnInit, OnChanges {

    @Input() polylinePath: any[];
    @Input() markers: any[];

    private center: string;

    private parcels: ParcelModel[];
    private currentParcel: ParcelModel;

    constructor(private parcelService: ParcelService) {
    }

    ngOnInit() {
        this.setupCenter();
        this.setupParcels();
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.setupCenter();
    }

    private setupCenter() {
        if (this.polylinePath && this.polylinePath.length > 0) {
            let first = this.polylinePath[0];
            this.center = first.lat + ',' + first.lng;
        }
        else {
            this.center = 'Moldova, Chisinau';
        }
    }

    private setupParcels() {
        this.parcelService.find().subscribe((data) => {
            this.parcels = data;
            this.parcels.forEach((parcel) => {
                parcel.fillColor = this.randomColor();
                parcel.paths = [];
                parcel.coordinates.forEach((c) => {
                   let latLng = {
                       lat: c[0],
                       lng: c[1]
                   };
                   parcel.paths.push(latLng);
               });
            });
        });
    }

    private onParcelClick(parcel, event) {
        this.currentParcel = parcel;
        // event.target.nguiMapComponent.openInfoWindow('iw', event.target, {
        //         lat: 48,
        //         lng: 28
        //     });
        let infoWindow = new google.maps.InfoWindow;
        infoWindow.setContent('test');
        infoWindow.setPosition({
            lat: event.latLng.lat(),
            lng: event.latLng.lng()
        });
        infoWindow.open(event.target.map);
    }

    private randomColor(): string {
        return '#' + Math.random().toString(16).slice(-3);
    }

}
