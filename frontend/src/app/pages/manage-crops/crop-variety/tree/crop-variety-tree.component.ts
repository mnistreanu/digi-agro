import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {CropVarietyTreeModel} from './crop-variety-tree.model';
import {CropVarietyService} from '../../../../services/crop/crop-variety.service';

@Component({
    selector: 'app-crop-variety-tree',
    templateUrl: './crop-variety-tree.component.html'
})
export class CropVarietyTreeComponent implements OnInit {

    options: GridOptions;
    context;

    private labelName: string;
    private labelDescription: string;

    constructor(private langService: LangService,
                private cropVarietyService: CropVarietyService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
        this.setupCropsTree();
    }

    private setupLabels() {
        this.langService.get('info.name').subscribe(msg => this.labelName = msg);
        this.langService.get('info.description').subscribe(msg => this.labelDescription = msg);
    }

    private setupCropsTree() {
        this.cropVarietyService.getTree().subscribe(payloadModel => {
            this.adjustModels(payloadModel.payload);
        });
    }

    private adjustModels(models: CropVarietyTreeModel[]) {
        models.forEach(model => this.adjustModel(model));
        this.options.api.setRowData(models);
    }

    private adjustModel(model: CropVarietyTreeModel) {
        const fieldMapper = new FieldMapper(this.langService.getLanguage());
        const nameField = fieldMapper.get('name');
        const descriptionField = fieldMapper.get('description');

        model['name'] = model[nameField];
        model['description'] = model[descriptionField];

        if (model.children) {
            model.children.forEach(child => this.adjustModel(child));
        }
    }


    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};
    }

    private setupHeaders() {

        const headers: ColDef[] = [
            {
                headerName: this.labelName,
                field: 'name',
                cellRenderer: 'agGroupCellRenderer',
                width: 280,
                minWidth: 280
            },
            {
                headerName: this.labelDescription,
                field: 'description',
                width: 400,
                minWidth: 200
            }
        ];

        return headers;
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
