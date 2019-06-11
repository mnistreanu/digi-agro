import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ExpenseSummaryModel} from '../models/expense-summary.model';

@Component({
    selector: 'app-expenses-pie-chart',
    templateUrl: './expenses-pie-chart.component.html',
    styleUrls: ['./expenses-pie-chart.component.scss']
})
export class ExpensesPieChartComponent implements OnInit, OnChanges {

    @Input() models: ExpenseSummaryModel[];

    labels: Array<string>;
    data: Array<number>;
    options: any;

    constructor() {
    }

    ngOnInit() {
        this.setupChartOptions();
        this.processModels();
    }


    ngOnChanges(changes: SimpleChanges): void {
        this.processModels();
    }

    private setupChartOptions() {
        this.options = {
            responsive: true,
            maintainAspectRatio: false,
            legend: {
                position: 'bottom',
                labels: {
                    fontSize: 10,
                    padding: 5,
                    usePointStyle: true
                }
            },
            tooltips: {
                enabled: true,
                callbacks: {
                    label: (tooltipItem, data) => {
                        const dataset = data.datasets[tooltipItem.datasetIndex];
                        const total = dataset.data.reduce((previousValue, cValue) => previousValue + cValue);
                        const currentValue = dataset.data[tooltipItem.index];
                        const percentage = Math.round(100 * (currentValue / total));
                        const label = data.labels[tooltipItem.index];
                        return `${label} (${percentage}%) ${currentValue}`;
                    }
                }
            }
        };
    }

    processModels() {
        const labels = [];
        const data = [];

        this.models.forEach((model: ExpenseSummaryModel) => {
            labels.push(model.categoryName);
            data.push(model.cost);
        });

        this.data = data;

        if (this.labelsChanged(labels)) {
            setTimeout(() => {
                this.labels = labels;
            });
        }
    }

    private labelsChanged(newLabels): boolean {

        if (!this.labels || this.labels.length !== newLabels.length) {
            return true;
        }

        for (let i = 0; i < newLabels.length; i++) {
            if (this.labels[i] !== newLabels[i]) {
                return true;
            }
        }

        return false;
    }

}
