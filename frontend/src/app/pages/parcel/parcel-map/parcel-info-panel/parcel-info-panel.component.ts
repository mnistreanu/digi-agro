import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ParcelModel} from '../../../telemetry/parcel.model';

@Component({
    selector: 'app-parcel-info-panel',
    templateUrl: './parcel-info-panel.component.html',
    styleUrls: ['./parcel-info-panel.component.scss']
})
export class ParcelInfoPanelComponent implements OnInit {

    @Input() model: ParcelModel;
    @Output() closed: EventEmitter<void> = new EventEmitter();

    constructor() {
    }

    ngOnInit() {
    }

    close(event) {
        event.preventDefault();
        this.closed.emit();
    }
}
