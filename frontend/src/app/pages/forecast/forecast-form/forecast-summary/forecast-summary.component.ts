import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
    selector: 'app-forecast-summary',
    templateUrl: './forecast-summary.component.html',
    styleUrls: ['./forecast-summary.component.scss']
})
export class ForecastSummaryComponent implements OnInit, OnChanges {

    @Input() unitPrice: number;
    @Input() summaryAmount: number;

    summaryPrice: number;

    constructor() {
    }

    ngOnInit() {
        this.calcSummary();
    }


    ngOnChanges(changes: SimpleChanges): void {
        this.calcSummary();
    }

    private calcSummary() {
        if (!this.unitPrice || !this.summaryAmount) {
            this.summaryPrice = 0;
            return;
        }
        this.summaryPrice = this.unitPrice * this.summaryAmount;
    }

}
