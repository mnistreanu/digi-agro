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

    constructor() {
    }

    refresh(params: any): boolean {
        return false;
    }

    agInit(params: ICellRendererParams): void {
        this.params = params;
        const field = params.colDef.field;
        this.imagePath = params.data[field];
    }

}
