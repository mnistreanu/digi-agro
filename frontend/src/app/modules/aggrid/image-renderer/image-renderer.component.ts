import {Component} from '@angular/core';
import {ICellRendererAngularComp} from 'ag-grid-angular';
import {ICellRendererParams} from 'ag-grid';

@Component({
    selector: 'app-image-renderer',
    templateUrl: './image-renderer.component.html',
    styleUrls: ['./image-renderer.component.scss']
})
export class ImageRendererComponent implements ICellRendererAngularComp {

    params: ICellRendererParams;
    imagePath: string;
    text: string;

    constructor() {
    }

    refresh(params: any): boolean {
        return false;
    }

    agInit(params: ICellRendererParams): void {
        this.params = params;
        this.imagePath = params.data[params['iconField']];
        this.text = params.data[params['textField']];
    }

}
