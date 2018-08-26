import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {DateUtil} from '../../../common/dateUtil';
import {NumericUtil} from '../../../common/numericUtil';
import {ForecastHarvestModel} from '../forecast-harvest.model';
import {LangService} from '../../../services/lang.service';

@Component({
    selector: 'app-harvest-factor',
    templateUrl: './harvest-factor.component.html',
    styleUrls: ['./harvest-factor.component.scss']
})
export class HarvestFactorComponent implements OnInit {

    @Input() models: ForecastHarvestModel[];
    @Output() amountChanged: EventEmitter<number> = new EventEmitter<number>();

    options: GridOptions;
    context;

    private harvestAmount;

    constructor(private langService: LangService) {
    }

    ngOnInit() {
        this.setupGrid();
        this.setupAmount();
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};

        this.options.rowData = this.models;
    }

    private setupAmount() {
        this.harvestAmount = this.models.reduce((prev, model) => prev + model.quantity, 0);
        this.amountChanged.emit(this.harvestAmount);
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: 'forecast.factor',
                field: 'factorName',
                editable: true,
                width: 250,
                minWidth: 200
            },
            {
                headerName: 'forecast.quantity',
                field: 'quantity',
                editable: true,
                valueSetter: this.numberSetter,
                onCellValueChanged: (params) => this.onQuantityChange(params),
                width: 150,
                minWidth: 150
            },
            {
                headerName: 'info.creation-time',
                field: 'createdAt',
                width: 160,
                minWidth: 160,
                filter: 'agDateColumnFilter',
                filterParams: {
                    comparator: (d1, d2) => DateUtil.compareWithoutTime(d1, d2)
                },
                valueFormatter: params => DateUtil.formatLocalizedDate(params.value)
            }
        ];

        headers.forEach((header) => {
           this.langService.get(header.headerName).subscribe(m => header.headerName = m);
        });

        return headers;
    }

    private numberSetter(params) {
        const newValue = params.newValue;
        if (newValue == params.oldValue) {
            return false;
        }

        if (!NumericUtil.isNumeric(newValue)) {
            return false;
        }

        const field = params.colDef.field;
        params.data[field] = newValue;

        return true;
    }

    private onQuantityChange(params) {
        this.harvestAmount += (params.newValue || 0) - (params.oldValue || 0);
        this.amountChanged.emit(this.harvestAmount);
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            if (this.options && this.options.api) {
                this.options.api.sizeColumnsToFit();
            }
        }, 500);
    }

    public onAdd() {
        const harvest = new ForecastHarvestModel();
        harvest.createdAt = new Date();
        this.models.push(harvest);
        this.options.api.updateRowData({
            add: [harvest]
        });
    }

}
