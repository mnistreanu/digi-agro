import {Component} from "@angular/core";
import {ICellRendererAngularComp} from "ag-grid-angular";
import {ICellRendererParams} from "ag-grid";

@Component({
  selector: 'app-edit-renderer',
  templateUrl: './edit-renderer.component.html',
  styleUrls: ['./edit-renderer.component.css']
})
export class EditRendererComponent implements ICellRendererAngularComp {

  params: ICellRendererParams;

  constructor() { }

  refresh(params: any): boolean {
    return false;
  }

  agInit(params: ICellRendererParams): void {
    this.params = params;
  }

  onEdit() {
    this.params.context.componentParent.onEdit(this.params.node);
  }

}
