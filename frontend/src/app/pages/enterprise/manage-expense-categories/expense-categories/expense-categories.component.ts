import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';
import {PinnedRowRendererComponent} from '../../../../modules/aggrid/pinned-row-renderer/pinned-row-renderer.component';
import {ExpenseCategoriesModel} from './expense-categories.model';
import {ExpenseCategoryService} from '../../../../services/expenses/expense-category.service';

@Component({
    selector: 'app-expense-categories',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './expense-categories.component.html',
    styleUrls: ['./expense-categories.component.scss']
})
export class ExpenseCategoriesComponent implements OnInit {
    options: GridOptions;
    context;

    labelName: string;

    constructor(private expensesService: ExpenseCategoryService,
                private langService: LangService) {

    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
        this.setupTreeData();
    }

    private setupLabels() {
        this.langService.get('expense-category.name').subscribe(msg => this.labelName = msg);
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
        this.options.columnDefs = this.setupHeaders();
        // this.options.frameworkComponents = { pinnedRowRenderer: PinnedRowRendererComponent };

        this.context = {componentParent: this};
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: this.labelName,
                field: 'name',
                width: 200,
                minWidth: 200,
                pinned: 'left',
                cellRenderer: 'agGroupCellRenderer',
                suppressFilter: true
            },
        ];

        return headers;
    }



    private setupTreeData() {
        this.expensesService.getTree().subscribe(payloadModel => {
            const models = payloadModel.payload;
            console.log(models);
            this.adjustModels(models);
            this.options.api.setRowData(models);
        });
    }

    private adjustModels(models: ExpenseCategoriesModel[]) {
        const mapper = new FieldMapper(this.langService.getLanguage());
        const defaultName = mapper.get('defaultName');
        models.forEach((model: ExpenseCategoriesModel) => {
            model.name = model.name;
            model.defaultName = defaultName;
            model.children.forEach(child => {
                child.name = child.name;

            });
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
}
