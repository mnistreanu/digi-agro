import {Component} from "@angular/core";
import {ICellRendererParams} from "ag-grid";
import {ICellRendererAngularComp} from "ag-grid-angular";

@Component({
  selector: 'app-delete-renderer',
  templateUrl: './delete-renderer.component.html',
  styleUrls: ['./delete-renderer.component.css']
})
export class DeleteRendererComponent implements ICellRendererAngularComp {

  params: ICellRendererParams;

  constructor() { }

  refresh(params: any): boolean {
    return false;
  }

  agInit(params: ICellRendererParams): void {
    this.params = params;
  }

  onDelete() {
    this.params.context.componentParent.onDelete(this.params.node);
  }

}
