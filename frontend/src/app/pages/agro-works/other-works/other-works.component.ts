import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {MachineService} from '../../../services/machine.service';
import {PinnedRowRendererComponent} from '../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {OtherWorksModel} from './other-works.model';
import {AgroWorksService} from "../../../services/agro-works.service";

@Component({
    selector: 'app-other-works',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './other-works.component.html',
    styleUrls: ['./other-works.component.scss']
})
export class OtherWorksComponent implements OnInit {
    options: GridOptions;
    context;

    labelDate: string;
    labelName: string;
    labelType: string;
    labelPhase: string;
    labelResult: string;
    labelComments: string;

    constructor(private agroWorksService: AgroWorksService, private langService: LangService) {

    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
        this.setupTreeData();
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
        this.options.frameworkComponents = { customPinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};

//        this.setupRows();
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: 'Denumirea lucrarilor', // this.labelDate,
                field: 'date',
                width: 90,
                minWidth: 90,
                suppressFilter: true,
                pinnedRowCellRenderer: 'customPinnedRowRenderer',
                pinnedRowCellRendererParams: { style: { color: 'red', fontWeight: 'bold' } }
            },
            {
                headerName: 'Un.mas.', //this.labelName,
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Cantitatea', //this.labelType,
                field: 'type',
                width: 60,
                minWidth: 60,
                suppressFilter: true,
            },
        ];


        return headers;
    }

    //
    // public setupRows() {
    //     let i = 0;
    //     this.machineService.findAll().subscribe(modelsArray => {
    //         const rows = modelsArray.map(data => {
    //             const model = new OtherWorksModel();
    //             model.date = new Date().toLocaleDateString();
    //             model.type = modelsArray[i].type;
    //             model.name = modelsArray[i].model;
    //             model.phase = modelsArray[i].identifier;
    //             model.result = 'Pozitiv';
    //             model.comments = 'Managerul ( brigadiri, agronomul) efectueaza evidenta in jurnalul personal zilnic + complecteaza un blanc pentru raport; (de obicei complecteaza contabilitatea)';
    //             i++;
    //             return model;
    //         });
    //         this.options.api.setRowData(rows);
    //         this.setupSummaryRow(rows);
    //     });
    // }

    private setupTreeData() {
        this.agroWorksService.findCropsTree().subscribe(payloadModel => {
            debugger;
            this.adjustTree(payloadModel.payload);
        });
    }

    private adjustTree(models: OtherWorksModel[]) {

        const rows = [];
        const crops = {};


        let parent;
        models.forEach(model => {
            if (model.cropId != null) {
                // crop
                crops[model.id] = model;
                rows.push(model);
            } else {
                // work
                parent = crops[model.workId];
                parent.children = parent.children || [];
                parent.children.push(model);
            }
        });


        this.options.api.setRowData(rows);
    }
    

    private getNodeChildDetails(rowItem) {
        if (rowItem.children) {
            return {
                group: true,
                expanded: false,
                children: rowItem.children,
                key: rowItem.group
            };
        } else {
            return null;
        }
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
