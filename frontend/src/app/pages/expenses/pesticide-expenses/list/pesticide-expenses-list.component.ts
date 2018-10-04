import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {MachineService} from '../../../../services/machine.service';
import {PinnedRowRendererComponent} from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {PesticideExpensesListModel} from './pesticide-expenses-list.model';

@Component({
    selector: 'app-pesticides-expenses-list',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './pesticide-expenses-list.component.html',
    styleUrls: ['./pesticide-expenses-list.component.scss']
})
export class PesticideExpensesListComponent implements OnInit {
    options: GridOptions;
    context;

    labelDate: string;
    labelName: string;
    labelType: string;
    labelPhase: string;
    labelResult: string;
    labelComments: string;

    constructor(private machineService: MachineService, private langService: LangService) {

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
        this.options.frameworkComponents = { customPinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};

        this.setupRows();
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: 'pesticide.spray-date',
                headerTooltip: 'pesticide.spray-date',
                field: 'date',
                width: 130,
                minWidth: 130,
                suppressFilter: true,
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } }
            },
            {
                headerName: 'info.name',
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'pesticide.type',
                field: 'type',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'pesticide.phase',
                field: 'phase',
                width: 140,
                minWidth: 140,
                suppressFilter: true,
            },
            {
                headerName: 'pesticide.result',
                headerTooltip: 'pesticide.result',
                field: 'result',
                width: 200,
                minWidth: 200,
                suppressFilter: true,
            },
            {
                headerName: 'pesticide.comments',
                headerTooltip: 'pesticide.comments',
                field: 'comments',
                width: 300,
                minWidth: 300,
                suppressFilter: true,
            },
            {
                headerName: 'Registered By',
                field: '',
                width: 100,
                minWidth: 100,
            },
            {
                headerName: 'At',
                field: '',
                width: 60,
                minWidth: 60,
            },

        ];
        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }


    public setupRows() {
        let i = 0;
        this.machineService.findAll().subscribe(modelsArray => {
            const rows = modelsArray.map(data => {
                const model = new PesticideExpensesListModel();
                model.date = new Date().toLocaleDateString();
                model.type = 'Insecticid';
                model.name = 'FASTAC 10 EC';
                model.phase = 'Floarea 3 patrimi';
                model.result = 'Pozitiv';
                model.comments = 'Managerul ( brigadiri, agronomul) efectueaza evidenta in jurnalul ' +
                    'personal zilnic + complecteaza un blanc pentru raport; (de obicei complecteaza contabilitatea)';
                i++;
                return model;
            });
            this.options.api.setRowData(rows);
            this.setupSummaryRow(rows);
        });
    }

    private setupSummaryRow(rows) {
        const summaryRow = {
            date: 'TOTAL',
            diesel: 0
        };
        rows.forEach(source => {
            summaryRow.diesel += source.diesel || 0;
        });
        this.options.api.setPinnedBottomRowData([summaryRow]);
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
}
