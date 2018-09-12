import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from "ag-grid";
import {LangService} from "../../../services/lang.service";
import {MachineService} from "../../../services/machine.service";
import {CustomPinnedRowRenderer} from "../../../modules/aggrid/custom-pinned-row-renderer/custom-pinned-row-renderer.component";
import {PesticidesExpensesModel} from "./pesticides-expenses.model";

@Component({
    selector: 'app-pesticides-expenses',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './pesticides-expenses.component.html',
    styleUrls: ['./pesticides-expenses.component.scss']
})
export class PesticidesExpensesComponent implements OnInit {
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
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('pesticides.spray-date').subscribe(msg => this.labelDate = msg);
        this.langService.get('pesticides.name').subscribe(msg => this.labelName = msg);
        this.langService.get('pesticides.type').subscribe(msg => this.labelType = msg);
        this.langService.get('pesticides.phase').subscribe(msg => this.labelPhase = msg);
        this.langService.get('pesticides.result').subscribe(msg => this.labelResult = msg);
        this.langService.get('pesticides.comments').subscribe(msg => this.labelComments = msg);
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        this.options.frameworkComponents = { customPinnedRowRenderer: CustomPinnedRowRenderer };

        this.context = {componentParent: this};

        this.setupRows();
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: this.labelDate,
                field: 'date',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } }
            },
            {
                headerName: this.labelName,
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelType,
                field: 'type',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelPhase,
                field: 'phase',
                width: 140,
                minWidth: 140,
                suppressFilter: true,
            },
            {
                headerName: this.labelResult,
//                headerTooltip: this.labelUnitOfMeasureLong,
                field: 'result',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
            {
                headerName: this.labelComments,
//                headerTooltip: this.labelUnitOfMeasureLong,
                field: 'comments',
                width: 60,
                minWidth: 60,
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


        return headers;
    }


    public setupRows() {
        let i = 0;
        this.machineService.findAll().subscribe(modelsArray => {
            const rows = modelsArray.map(data => {
                const model = new PesticidesExpensesModel();
                model.date = new Date().toLocaleDateString();
                model.type = modelsArray[i].type;
                model.name = modelsArray[i].model;
                model.phase = modelsArray[i].identifier;
                model.result = 'Pozitiv';
                model.comments = 'Managerul ( brigadiri, agronomul) efectueaza evidenta in jurnalul personal zilnic + complecteaza un blanc pentru raport; (de obicei complecteaza contabilitatea)';
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
