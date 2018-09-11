import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {PinnedRowRendererComponent} from '../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {OtherWorksModel} from './other-works.model';
import {AgroWorksService} from '../../../services/agro-works.service';
import {FieldMapper} from '../../../common/field.mapper';

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

    constructor(private agroWorksService: AgroWorksService,
                private langService: LangService) {

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
        this.options.frameworkComponents = { pinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};
    }

    private setupHeaders() {

        this.options.defaultColDef = {
            pinnedRowCellRenderer: 'pinnedRowRenderer',
            pinnedRowCellRendererParams: { style: { fontWeight: 'bold' } } // color: 'blue'
        };

        const headers: ColDef[] = [
            {
                headerName: 'Denumirea lucrarilor',
                field: 'workTypeName',
                width: 90,
                minWidth: 90,
                pinned: 'left',
                cellRenderer: 'agGroupCellRenderer',
                suppressFilter: true
            },
            {
                headerName: 'Un.mas.',
                field: 'unitOfMeasure',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Cantitatea',
                field: 'quantity',
                width: 60,
                minWidth: 60,
                suppressFilter: true
            },
            {
                headerName: 'a/m',
                field: 'am',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'n/s',
                field: 'ns',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'z/m',
                field: 'zm',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'o/m',
                field: 'am',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'suma',
                field: 'suma',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'prod',
                field: 'prod',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'petrol',
                field: 'petrol',
                width: 60,
                minWidth: 60
            },
            {
                headerName: 'ulei',
                field: 'ulei',
                width: 60,
                minWidth: 60
            }
        ];

        return headers;
    }


    /*public setupRows() {
        let i = 0;
        this.machineService.findAll().subscribe(modelsArray => {
            const rows = modelsArray.map(data => {
                const model = new OtherWorksModel();
                model.date = new Date().toLocaleDateString();
                model.type = modelsArray[i].type;
                model.name = modelsArray[i].model;
                model.phase = modelsArray[i].identifier;
                model.result = 'Pozitiv';
                model.comments = 'Managerul ( brigadiri, agronomul) efectueaza evidenta in jurnalul personal zilnic
    + complecteaza un blanc pentru raport; (de obicei complecteaza contabilitatea)';
                i++;
                return model;
            });
            this.options.api.setRowData(rows);
            this.setupSummaryRow(rows);
        });
    }*/

    private setupTreeData() {
        this.agroWorksService.findCropsTree().subscribe(payloadModel => {
            const models = payloadModel.payload;
            console.log(models);
            this.adjustModels(models);
            this.options.api.setRowData(models);
            this.setupSummaryRow(models);
        });
    }

    private adjustModels(models: OtherWorksModel[]) {
        const mapper = new FieldMapper(this.langService.getLanguage());
        const cropName = mapper.get('cropName');
        const workTypeName = mapper.get('workTypeName');
        models.forEach((model: OtherWorksModel) => {
            model.workTypeName = model[cropName];
            model.children.forEach(child => {

                child.am = Math.random() * 100;
                child.ns = Math.random() * 100;
                child.zm = Math.random() * 100;
                child.om = Math.random() * 100;
                child.suma = Math.random() * 100;
                child.prod = Math.random() * 100;
                child.petrol = Math.random() * 100;
                child.ulei = Math.random() * 100;

                this.aggregate(model, child);
                child.workTypeName = child[workTypeName];
            });
        });
    }

    private aggregate(source: OtherWorksModel, item: OtherWorksModel) {
        source.quantity = source.quantity || 0;
        source.quantity += item.quantity || 0;

        source.am = source.am || 0;
        source.am += item.am || 0;

        source.ns = source.ns || 0;
        source.ns += item.ns || 0;

        source.zm = source.zm || 0;
        source.zm += item.zm || 0;

        source.om = source.om || 0;
        source.om += item.om || 0;

        source.suma = source.suma || 0;
        source.suma += item.suma || 0;

        source.prod = source.prod || 0;
        source.prod += item.prod || 0;

        source.petrol = source.petrol || 0;
        source.petrol += item.petrol || 0;

        source.ulei = source.ulei || 0;
        source.ulei += item.ulei || 0;
    }

    private setupSummaryRow(rows) {
        const summaryRow = new OtherWorksModel();
        summaryRow.workTypeName = 'TOTAL';
        rows.forEach(row => {
            row.children.forEach(child => {
                this.aggregate(summaryRow, child);
            });
        });
        this.options.api.setPinnedBottomRowData([summaryRow]);
    }

    private getNodeChildDetails(item) {
        if (!item.children) {
            return null;
        }

        return {
            group: true,
            expanded: false,
            children: item.children
        };
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
