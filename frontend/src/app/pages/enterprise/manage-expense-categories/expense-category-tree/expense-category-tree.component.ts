import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';
import {ExpenseCategoryService} from '../../../../services/expenses/expense-category.service';
import {ExpenseCategoryTreeModel} from './expense-category-tree.model';
import {AgEditColumnType} from '../../../../modules/aggrid/column-types/ag-edit-type';

@Component({
    selector: 'app-expense-category-tree',
    templateUrl: './expense-category-tree.component.html',
    styleUrls: ['./expense-category-tree.component.scss']
})
export class ExpenseCategoryTreeComponent implements OnInit {
    options: GridOptions;
    context;

    constructor(private expenseCategoryService: ExpenseCategoryService,
                private router: Router,
                private route: ActivatedRoute,
                private langService: LangService) {

    }

    ngOnInit() {
        this.setupGrid();
        this.setupTreeData();
    }


    private setupGrid() {
        this.options = <GridOptions>{};
        this.setupColumnTypes();

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();

        this.context = {componentParent: this};
    }

    private setupColumnTypes() {
        this.options.columnTypes = {
            editColumnType: AgEditColumnType.getType()
        };
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                type: 'editColumnType'
            },
            {
                headerName: 'info.name',
                field: 'name',
                width: 200,
                minWidth: 200,
                cellRenderer: 'agGroupCellRenderer'
            },
            {
                headerName: 'info.description',
                field: 'description',
                width: 200,
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private setupTreeData() {
        this.expenseCategoryService.getTree().subscribe(payloadModel => {
            const models = payloadModel.payload;
            this.options.api.setRowData(models);
        });
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

    public add() {
        this.router.navigate(['./-1'], { relativeTo: this.route });
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], { relativeTo: this.route });
    }
}
