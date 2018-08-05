import {Component, OnInit, ViewChild} from '@angular/core';
import {ParcelModel} from "../../telemetry/parcel.model";
import {ParcelMapComponent} from "../parcel-map/parcel-map.component";

@Component({
    selector: 'az-parcel',
    templateUrl: './parcel.component.html',
    styleUrls: ['./parcel.component.scss']
})
export class ParcelComponent implements OnInit {

    @ViewChild(ParcelMapComponent) parcelMap: ParcelMapComponent;

    private parcels: ParcelModel[];

    constructor() {
    }

    ngOnInit() {
        this.parcels = [];
    }

    registerParcels(data) {
        this.parcels = data;
    }

    registerCenter(center) {
        this.parcelMap.updateCenter(center);
    }

}
