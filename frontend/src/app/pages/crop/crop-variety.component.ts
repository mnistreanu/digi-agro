import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from "ag-grid";
import {CropService} from "../../services/crop.service";
import {CropVarietyModel} from "./crop-variety.model";
import {LangService} from "../../services/lang.service";
import {CropFieldMapper} from "./crop-field.mapper";

@Component({
    selector: 'az-crop-variety',
    templateUrl: './crop-variety.component.html'
})
export class CropVarietyComponent implements OnInit {

    private options: GridOptions;
    private context;

    private labelName: string;
    private labelDescription: string;

    private fieldMapper: CropFieldMapper;

    constructor(private langService: LangService,
                private cropService: CropService) {
    }

    ngOnInit() {
        this.fieldMapper = new CropFieldMapper(this.langService.getLanguage());
        this.setupLabels();
        this.setupGrid();
        this.setupCropsTree();
    }

    private setupLabels() {
        this.langService.get('info.name').subscribe(msg => this.labelName = msg);
        this.langService.get('info.description').subscribe(msg => this.labelDescription = msg);
    }

    private setupCropsTree() {
        this.cropService.findVarietiesTree().subscribe(payloadModel => {
            this.adjustTree(payloadModel.payload);
        });
    }

    private adjustTree(models: CropVarietyModel[]) {

        let rows = [];
        let categories = {};
        let crops = {};

        let parent;
        models.forEach(model => {
            if (model.cropCategoryId == null && model.cropId == null) {
                // category
                categories[model.id] = model;
                rows.push(model);
            }
            else if (model.cropCategoryId != null) {
                // crop
                parent = categories[model.cropCategoryId];
                parent.children = parent.children || [];
                parent.children.push(model);
                crops[model.id] = model;
            }
            else {
                // variety
                parent = crops[model.cropId];
                parent.children = parent.children || [];
                parent.children.push(model);
            }
        });

        this.options.api.setRowData(rows);
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

        let headers: ColDef[] = [
            {
                headerName: this.labelName,
                field: this.fieldMapper.getName(),
                cellRenderer: "agGroupCellRenderer",
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelDescription,
                field: this.fieldMapper.getDescription(),
                width: 600,
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
        }
        else {
            return null;
        }
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            this.options.api.sizeColumnsToFit();
        }, 500);
    }


}
