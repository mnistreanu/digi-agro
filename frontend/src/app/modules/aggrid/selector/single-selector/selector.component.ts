import {AfterViewInit, Component} from '@angular/core';
import {ICellEditorAngularComp} from 'ag-grid-angular';
import {SelectorItem} from '../selector-item.interface';

@Component({
    selector: 'app-selector',
    templateUrl: './selector.component.html',
    styleUrls: ['./selector.component.scss']
})
export class SelectorComponent implements ICellEditorAngularComp, AfterViewInit {

    items: SelectorItem[];
    value: any;
    params: any;

    agInit(params: any): void {
        this.params = params;
        const model = params.node.data;
        this.items = params.getDropDownItems(model);
        const field = params.dropDownValueField;
        this.value = model[field];
    }

    ngAfterViewInit() {
    }

    getValue(): any {
        return this.value;
    }

    onChange() {
        this.params.api.stopEditing();
    }


}
