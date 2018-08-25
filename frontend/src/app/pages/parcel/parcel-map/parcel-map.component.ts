import {Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ParcelModel} from '../../telemetry/parcel.model';

@Component({
    selector: 'app-parcel-map',
    templateUrl: './parcel-map.component.html',
    styleUrls: ['./parcel-map.component.scss']
})
export class ParcelMapComponent implements OnInit, OnChanges {

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


    ngOnChanges(changes: SimpleChanges): void {
        if (changes['parcels']) {
            this.setupCenter();
        }
    }

    private setupCenter() {
        if (this.parcels && this.parcels.length > 0) {
            this.center = this.parcels[0].paths[0];
        }
        else {
            this.center = 'Moldova, Chisinau';
        }
    }

    onParcelClick(parcel, event) {
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

    onParcelUp(event) {
        event.target.setOptions({
            strokeColor: '#F00',
            zIndex: this.defaultZIndex + 1
        });
    }

    onParcelOut(event) {
        event.target.setOptions({
            strokeColor: this.defaultStrokeColor,
            zIndex: this.defaultZIndex
        });
    }

}
