import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {LangService} from "../../../services/lang.service";

@Component({
    selector: 'app-machinery-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './machinery-expenses.component.html',
    styleUrls: ['./machinery-expenses.component.scss']
})
export class MachineryExpensesComponent implements OnInit {

    constructor(private langService: LangService) {
    }

    ngOnInit() {
    }

}
