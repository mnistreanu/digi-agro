import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {ActivatedRoute, Router} from '@angular/router';
import {FertilizerService} from '../../../../services/chemicals-pests/fertilizer.service';
import {LangService} from '../../../../services/lang.service';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {FertilizerModel} from '../fertilizer.model';

@Component({
    selector: 'app-fertilizer-list',
    templateUrl: './fertilizer-list.component.html',
    styleUrls: ['./fertilizer-list.component.scss']
})
export class FertilizerListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private fertilizerService: FertilizerService) {
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

        this.setupRows();

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
                headerName: 'fertilizer.type',
                field: 'fertilizerType',
                width: 150,
                minWidth: 100
            },
            {
                headerName: 'info.name-ro',
                field: 'nameRo',
                width: 400,
                minWidth: 200
            },
            {
                headerName: 'info.name-ru',
                field: 'nameRu',
                width: 400,
                minWidth: 200
            },
            // {
            //     headerName: 'info.description-ro',
            //     field: 'descriptionRo',
            //     width: 400,
            //     minWidth: 200
            // },
            // {
            //     headerName: 'info.description-ru',
            //     field: 'descriptionRu',
            //     width: 400,
            //     minWidth: 200
            // }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.fertilizerService.find().subscribe(models => {
            this.adjustModels(models);
            this.options.api.setRowData(models);
        });
    }

    private adjustModels(models: FertilizerModel[]) {
        models.forEach(model => {
            this.langService
                .get('fertilizer-type.' + model.fertilizerType)
                .subscribe(m => model.fertilizerType = m);
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

    public add() {
        this.router.navigate(['./-1'], {relativeTo: this.route});
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], {relativeTo: this.route});
    }


}
