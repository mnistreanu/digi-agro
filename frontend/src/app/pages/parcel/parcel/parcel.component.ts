import {Component, OnInit} from '@angular/core';
import {ParcelModel} from "../../telemetry/parcel.model";

@Component({
    selector: 'az-parcel',
    templateUrl: './parcel.component.html',
    styleUrls: ['./parcel.component.scss']
})
export class ParcelComponent implements OnInit {

    private parcels: ParcelModel[];

    constructor() {
    }

    ngOnInit() {
        this.parcels = [];
    }

    registerParcels(data) {
        this.parcels = data;
    }

}
