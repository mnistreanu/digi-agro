import {Component, Input, OnInit} from '@angular/core';
import {LangService} from '../../../../services/lang.service';
import {ColDef, GridOptions} from 'ag-grid';
import {DeleteRendererComponent} from '../../../../modules/aggrid/delete-renderer/delete-renderer.component';
import {PesticideExpenseItemModel} from './pesticide-expense-item.model';
import {NumericUtil} from '../../../../common/numericUtil';
import {PinnedRowRendererComponent} from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {ModalService} from '../../../../services/modal.service';

@Component({
    selector: 'app-pesticide-expense-item-table',
    templateUrl: './pesticide-expense-item-table.component.html',
    styleUrls: ['./pesticide-expense-item-table.component.scss']
})
export class PesticideExpenseItemTableComponent implements OnInit {

    @Input() models: PesticideExpenseItemModel[];

    confirmationModalId = 'expense-item-remove-confirmation-modal';

    options: GridOptions;
    context;

    currentModel: PesticideExpenseItemModel;

    constructor(private langService: LangService,
                private modalService: ModalService) {
    }

    ngOnInit() {
        this.setupGrid();
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};
        this.options.frameworkComponents = {pinnedRowRenderer: PinnedRowRendererComponent};

        this.options.rowData = this.models;
        this.setupSummaryRow(this.models);
    }

    private setupHeaders() {

        this.options.defaultColDef = {
            pinnedRowCellRenderer: 'pinnedRowRenderer',
            pinnedRowCellRendererParams: {style: {fontWeight: 'bold'}}
        };

        const headers: ColDef[] = [
            {
                field: 'delete',
                headerName: '',
                width: 24,
                minWidth: 24,
                maxWidth: 30,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: DeleteRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            },
            {
                headerName: 'machine.spare-part',
                field: 'title',
                width: 300,
                minWidth: 300,
                editable: params => !params.data.readOnly
            },
            // {
            //     headerName: 'unit-of-measure.quantity',
            //     field: 'quantity',
            //     width: 80,
            //     minWidth: 80,
            //     editable: params => !params.data.readOnly,
            // },
            // {
            //     headerName: 'unit-of-measure.unit-long',
            //     headerTooltip: 'unit-of-measure.unit-long',
            //     field: 'unitOfMeasure',
            //     width: 125,
            //     minWidth: 125,
            //     editable: params => !params.data.readOnly
            // },
            // {
            //     headerName: 'expenses.unit-cost',
            //     field: 'unitCost',
            //     width: 80,
            //     minWidth: 80,
            //     editable: params => !params.data.readOnly,
            // },
            {
                headerName: 'machine.spare-part-price',
                field: 'totalCost',
                width: 120,
                minWidth: 120,
                editable: params => !params.data.readOnly,
                valueSetter: (params) => this.costValueSetter(params),
                onCellValueChanged: (params) => this.onCostChange(params)
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
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

    private setupSummaryRow(models) {
        const summaryRow = new PesticideExpenseItemModel();
        summaryRow.readOnly = true;
        this.langService.get('expenses.total-spent').subscribe(m => summaryRow.title = m);
        summaryRow.totalCost = 0;
        models.forEach(model => this.aggregate(summaryRow, model, true));
        this.options.pinnedBottomRowData = [summaryRow];
    }

    private aggregate(source: PesticideExpenseItemModel, item: PesticideExpenseItemModel, applyAddition) {
        const sumFields = ['totalCost'];
        sumFields.forEach(field => {
            source[field] = source[field] || 0;
            if (applyAddition) {
                source[field] += item[field] || 0;
            }
            else {
                source[field] -= item[field] || 0;
            }
        });
    }

    private costValueSetter(params) {
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

    private onCostChange(params) {
        const field = params.colDef.field;
        const newValue = params.newValue || 0;
        const oldValue = params.oldValue || 0;
        const diff = newValue - oldValue;

        const summaryRow = this.getSummaryRow();
        summaryRow[field] = summaryRow[field] || 0;
        summaryRow[field] += diff;

        this.updateSummaryRow();
    }

    public add() {
        const model = new PesticideExpenseItemModel();
        this.models.push(model);
        this.options.api.updateRowData({
            add: [model]
        });
    }

    public onDelete(node) {
        this.modalService.open(this.confirmationModalId);
        this.currentModel = node.data;
    }

    public remove() {
        this.currentModel.deleted = true;
        this.options.api.updateRowData({remove: [this.currentModel]});
        const summaryRow = this.getSummaryRow();
        this.aggregate(summaryRow, this.currentModel, false);
        this.updateSummaryRow();
        this.currentModel = null;
    }

    private getSummaryRow() {
        const summaryRowNode = this.options.api.getPinnedBottomRow(0);
        return summaryRowNode.data;
    }

    private updateSummaryRow() {
        const summaryRowNode = this.options.api.getPinnedBottomRow(0);
        const refreshCellParams = {
            rowNodes: [summaryRowNode]
        };
        this.options.api.refreshCells(refreshCellParams);
    }

}
