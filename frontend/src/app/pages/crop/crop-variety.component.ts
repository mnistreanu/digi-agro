import { Component, ViewEncapsulation } from '@angular/core';
import { AppConfig } from "../../app.config";
import 'style-loader!fullcalendar/dist/fullcalendar.min.css';
import {CropModel} from "./crop.model";
import {ColDef, GridOptions} from "ag-grid";
import {ListItem} from "../../interfaces/list-item.interface";
import {Router} from "@angular/router";
import {CropService} from "../../services/crop.service";
import {MultiLanguageItem} from "../../interfaces/multi-language-item.interface";
import {EditRendererComponent} from "../../modules/aggrid/edit-renderer/edit-renderer.component";

@Component({
  selector: 'az-crop-variety',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './crop-variety.component.html'
})
export class CropVarietyComponent {

    options: GridOptions;
    context;

    cropCategoryId;
    treeModel: MultiLanguageItem[];

    constructor(private router: Router,
                private cropService: CropService) {
    }

    ngOnInit() {
        this.setupCropsTree();
        this.setupGrid();
    }

    private setupCropsTree() {
        this.cropService.findVarietiesTree().subscribe(payloadModel => {
            this.treeModel = payloadModel.payload;
            debugger;
            // if (this.treeModel.length > 0) {
            //     this.cropCategoryId = this.treeModel[0].id;
            //     this.setupRows();
            // }
        });
    }

    public onCropCategoryChange() {
        if (this.cropCategoryId == null) {
            return;
        }
        this.setupRows();
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};

    }


    private setupRows() {
        // this.cropService.findCrops(this.cropCategoryId).subscribe(payloadModel => {
        //     let models = payloadModel.payload;
        //     models = this.adjustTreeModels(models);
        //     this.options.api.setRowData(models);
        // })
    }

    private adjustTreeModels(models: CropModel[]) {
        let treeModels = [];
        let modelMap = {};
        for (let model of models) {
            modelMap[model.id] = model;
            if (model.cropCategoryId == null) {
                treeModels.push(model);
            }
            else {
                let category = modelMap[model.cropCategoryId];
                if (!category.participants) {
                    category.participants = [];
                }
                category.participants.push(model);
            }
        }
        return treeModels;
    }

    private getNodeChildDetails(rowItem) {
        if (rowItem.participants) {
            return {
                group: true,
                expanded: false,
                children: rowItem.participants,
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
        setTimeout(() => {this.options.api.sizeColumnsToFit();}, 500);
    }

    public add() {
        // this.router.navigate(['/pages/manage-branches/branch/-1']);
    }

    public onEdit(node) {
        // let model = node.data;
        // this.router.navigate(['/pages/manage-branches/branch/' + model.id]);
    }


    private setupHeaders() {

        let headers: ColDef[] = [
            {
                field: 'edit',
                width: 24,
                minWidth: 24,
                maxWidth: 30,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {return {padding: 0};}
            },
            {
                headerName: 'Name',
                field: 'nameRo',
                width: 200,
                minWidth: 200,
                cellRenderer: "agGroupCellRenderer"
            },
            {
                headerName: 'Name',
                field: 'nameRu',
                width: 200,
                minWidth: 200,
                cellRenderer: "agGroupCellRenderer"
            },

        ];

        return headers;
    }

}
