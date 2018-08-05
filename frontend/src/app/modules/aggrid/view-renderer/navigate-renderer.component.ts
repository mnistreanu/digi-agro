import {Component} from "@angular/core";
import {ICellRendererAngularComp} from "ag-grid-angular";
import {ICellRendererParams} from "ag-grid";

@Component({
    selector: 'ag-navigate-renderer',
    templateUrl: './navigate-renderer.component.html',
    styleUrls: ['./navigate-renderer.component.css']
})
export class NavigateRendererComponent implements ICellRendererAngularComp {

    params: ICellRendererParams;

    constructor() {
    }

    refresh(params: any): boolean {
        return false;
    }

    agInit(params: ICellRendererParams): void {
        this.params = params;
    }

    navigate() {
        this.params.context.componentParent.navigate(this.params.node);
    }

}
