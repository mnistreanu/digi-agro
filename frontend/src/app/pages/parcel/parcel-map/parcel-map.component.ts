import {Component, ElementRef, Input, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ParcelModel} from "../../telemetry/parcel.model";

@Component({
    selector: 'parcel-map',
    templateUrl: './parcel-map.component.html',
    styleUrls: ['./parcel-map.component.scss']
})
export class ParcelMapComponent implements OnInit {

    @Input() parcels: any[];
    @Input() center: any;

    @ViewChild('infoBody') infoBody: ElementRef;

    private infoParcel: ParcelModel;
    private infoWindow;

    constructor() {
    }

    ngOnInit() {
        this.setupCenter();
        this.parcels = [];
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.setupCenter();
    }

    private setupCenter() {
        if (!this.center) {
            this.center = 'Moldova, Chisinau';
        }
        // this.center = first.lat + ',' + first.lng;
    }

    private onParcelClick(parcel, event) {
        this.infoParcel = parcel;

        if (this.infoWindow == null) {
            this.infoWindow = new google.maps.InfoWindow();
        }

        this.infoWindow.setContent(this.infoBody.nativeElement);
        this.infoWindow.setPosition({
            lat: event.latLng.lat(),
            lng: event.latLng.lng()
        });
        this.infoWindow.open(event.target.map);
    }

}
