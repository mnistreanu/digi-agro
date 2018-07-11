import { Component, OnInit } from '@angular/core';
import {Router} from "@angular/router";
import {ColDef, GridOptions} from "ag-grid";
import {EditRendererComponent} from "../../../modules/aggrid/edit-renderer/edit-renderer.component";
import {BranchService} from "../../../services/branch.service";
import {BranchModel} from "../branch/branch.model";

@Component({
  selector: 'az-branch-list',
  templateUrl: './branch-list.component.html',
  styleUrls: ['./branch-list.component.scss']
})
export class BranchListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private branchService: BranchService) {
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
                field: 'name',
                width: 200,
                minWidth: 200,
                cellRenderer: "agGroupCellRenderer"
            },
            {
                headerName: 'Description',
                field: 'description',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Country',
                field: 'country',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'County',
                field: 'county',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Village City',
                field: 'villageCity',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Address',
                field: 'address',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Phones',
                field: 'phones',
                width: 200,
                minWidth: 200
            }
        ];

        return headers;
    }

    private setupRows() {
        this.branchService.find().subscribe(models => {
            models = this.adjustTreeModels(models);
            // this.options.api.setRowData(models);
            // models = [ {
            //     group: "Group A",
            //     name: "max",
            //     participants: [
            //         {
            //             group: "A.1",
            //             name: "1",
            //             year: "2008",
            //             country: "United States"
            //         },
            //         {
            //             group: "A.2",
            //             name: "2",
            //             year: "2008",
            //             country: "United States"
            //         },
            //         {
            //             group: "A.3",
            //             name: "3",
            //             year: "2008",
            //             country: "United States"
            //         }
            //     ]
            // }];

            this.options.api.setRowData(models);
        })
    }

    private adjustTreeModels(models: BranchModel[]) {
        let treeModels = [];
        let modelMap = {};

        for (let model of models) {
            modelMap[model.id] = model;
            if (model.parentId == null) {
                treeModels.push(model);
            }
            else {
                let parent = modelMap[model.parentId];
                if (!parent.participants) {
                    parent.participants = [];
                }
                parent.participants.push(model);
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
        this.router.navigate(['/pages/manage-branches/branch/-1']);
    }

    public onEdit(node) {
        let model = node.data;
        this.router.navigate(['/pages/manage-branches/branch/' + model.id]);
    }

}
