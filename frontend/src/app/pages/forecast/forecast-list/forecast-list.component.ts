import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {Router} from '@angular/router';
import {LangService} from '../../../services/lang.service';
import {ForecastService} from '../../../services/forecast.service';

@Component({
    selector: 'app-forecast-list',
    templateUrl: './forecast-list.component.html',
    styleUrls: ['./forecast-list.component.scss']
})
export class ForecastListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private langService: LangService,
                private forecastService: ForecastService) {
    }

    ngOnInit() {
        this.setupGrid();
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};

        this.setupRows();

    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: 'forecast.name',
                field: 'name',
                width: 250,
                minWidth: 200
            },
            {
                headerName: 'forecast.additional_info',
                field: 'description',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'forecast.harvesting_year',
                field: 'harvestingYear',
                width: 200,
                minWidth: 200
            }
        ];

        headers.forEach(h => {
            this.langService.get(h.headerName).subscribe(m => h.headerName = m);
        });

        return headers;
    }

    private setupRows() {
        this.forecastService.find().subscribe(models => {
            this.options.api.setRowData(models);
        });
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
        this.router.navigate(['/pages/forecasting/harvesting/-1']);
    }

    public onSelectionChanged() {
        const model = this.options.api.getSelectedRows()[0];
        this.router.navigate(['/pages/forecasting/harvesting/' + model.id]);
    }

}
