import {AfterViewInit, Component} from '@angular/core';
import {ICellEditorAngularComp} from 'ag-grid-angular';
import {GroupedSelectorItem} from './grouped-selector-item.interface';

@Component({
    selector: 'app-grouped-selector',
    templateUrl: './grouped-selector.component.html',
    styleUrls: ['./grouped-selector.component.scss']
})
export class GroupedSelectorComponent implements ICellEditorAngularComp, AfterViewInit {

    items: GroupedSelectorItem[];
    value: any;

    agInit(params: any): void {
        this.items = params.getDropDownItems();
        const field = params.dropDownValueField;
        this.value = params.node.data[field];
    }

    ngAfterViewInit() {
    }

    getValue(): any {
        return this.value;
    }


}
