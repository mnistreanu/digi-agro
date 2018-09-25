import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {ExpenseCategoryTreeModel} from './expense-category-tree.model';
import {ExpensesService} from '../../../../services/expenses/expenses.service';

@Component({
    selector: 'app-expense-category-tree',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './expense-category-tree.component.html',
    styleUrls: ['./expense-category-tree.component.scss']
})
export class ExpenseCategoryTreeComponent implements OnInit {
    options: GridOptions;
    context;

    labelName: string;

    constructor(private expensesService: ExpensesService,
                private router: Router,
                private route: ActivatedRoute,
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
                field: 'edit',
                width: 24,
                minWidth: 24,
                maxWidth: 30,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            },
            {
                headerName: this.labelName,
                field: 'name',
                width: 200,
                minWidth: 200,
                // pinned: 'left',
                cellRenderer: 'agGroupCellRenderer',
                suppressFilter: true
            },
        ];

        return headers;
    }



    private setupTreeData() {
        this.expensesService.findCategoriesTree().subscribe(payloadModel => {
            const models = payloadModel.payload;
            console.log(models);
            this.adjustModels(models);
            this.options.api.setRowData(models);
        });
    }

    private adjustModels(models: ExpenseCategoryTreeModel[]) {
        const mapper = new FieldMapper(this.langService.getLanguage());
        const defaultName = mapper.get('defaultName');
        models.forEach((model: ExpenseCategoryTreeModel) => {
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

    public add() {
        this.router.navigate(['./-1'], { relativeTo: this.route });
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], { relativeTo: this.route });
    }
}
