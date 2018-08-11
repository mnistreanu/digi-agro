import {Component, ElementRef, Input, OnInit, ViewChild} from '@angular/core';
import {ParcelModel} from '../../telemetry/parcel.model';

@Component({
    selector: 'app-parcel-map',
    templateUrl: './parcel-map.component.html',
    styleUrls: ['./parcel-map.component.scss']
})
export class ParcelMapComponent implements OnInit {

    @Input() parcels: any[];
    @Input() center: any;

    @ViewChild('infoBody') infoBody: ElementRef;

    private defaultStrokeColor = '#FFC107';
    private defaultZIndex = 1;

    private parcel: ParcelModel;
    private infoWindow;

    constructor() {
    }

    ngOnInit() {
        this.setupCenter();
        this.parcels = [];
    }


    private setupCenter() {
        this.center = 'Moldova, Chisinau';
    }

    private onParcelClick(parcel, event) {
        this.parcel = parcel;

        if (this.infoWindow == null) {
            this.infoWindow = new google.maps.InfoWindow({
                disableAutoPan: true
            });
        }

        this.infoWindow.setContent(this.infoBody.nativeElement);
        this.infoWindow.setPosition({
            lat: event.latLng.lat(),
            lng: event.latLng.lng()
        });
        this.infoWindow.open(event.target.map);
    }

    private onParcelUp(event) {
        event.target.setOptions({
            strokeColor: '#F00',
            zIndex: this.defaultZIndex + 1
        });
    }

    private onParcelOut(event) {
        event.target.setOptions({
            strokeColor: this.defaultStrokeColor,
            zIndex: this.defaultZIndex
        });
    }

}
