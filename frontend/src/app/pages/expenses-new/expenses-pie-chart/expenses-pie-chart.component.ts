import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';
import {ExpenseCategoryTotalModel} from '../models/expense-category-total.model';
import {ExpenseCategoryModel} from '../../enterprise/manage-expense-categories/expense-category/expense-category.model';

@Component({
    selector: 'app-expenses-pie-chart',
    templateUrl: './expenses-pie-chart.component.html',
    styleUrls: ['./expenses-pie-chart.component.scss']
})
export class ExpensesPieChartComponent implements OnInit, OnChanges {

    @Input() models: ExpenseCategoryTotalModel[];

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
        this.labels = [];
        this.data = [];

        this.models.forEach((model: ExpenseCategoryTotalModel) => {
            this.labels.push(model.type);
            this.data.push(model.cost);
        });
    }
}
