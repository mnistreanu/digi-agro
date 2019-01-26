import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {CropSubcultureService} from '../../../../services/crop/crop-subculture.service';
import {LangService} from '../../../../services/lang.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';

@Component({
    selector: 'app-crop-subcultures-list',
    templateUrl: './crop-subculture-list.component.html',
    styleUrls: ['./crop-subculture-list.component.scss']
})
export class CropSubcultureListComponent implements OnInit {

    options: GridOptions;
    context;

    totalRecords = 0;
    pageSize = 10;


    constructor(private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private cropSubcultureService: CropSubcultureService) {
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
        // this.options.pagination = true;
        // this.options.paginationPageSize = this.pageSize;
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
                headerName: 'crop.crop',
                field: 'cropName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop.subculture',
                field: 'name',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'info.description',
                field: 'description',
                width: 300,
                minWidth: 200
            },
        ];

        headers.forEach((h) => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.cropSubcultureService.findAll().subscribe(data => {
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const cropNameField = fieldMapper.get('cropName');
            const nameField = fieldMapper.get('name');
            const descriptionField = fieldMapper.get('description');
            const models = data.payload;
            models.forEach((model) => {
                model['cropName'] = model[cropNameField];
                model['name'] = model[nameField];
                model['description'] = model[descriptionField];
            });

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

    public add() {
        this.router.navigate(['./-1'], {relativeTo: this.route});
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], {relativeTo: this.route});
    }

}
