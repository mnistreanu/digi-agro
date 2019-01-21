import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../../services/lang.service';
import {CropSeasonService} from '../../../../services/crop/crop-season.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {CropSeasonModel} from "../form/crop-season-form.model";

@Component({
    selector: 'app-crop-season-list',
    templateUrl: './crop-season-list.component.html',
    styleUrls: ['./crop-season-list.component.scss']
})
export class CropSeasonListComponent implements OnInit {

    options: GridOptions;
    context;

    filterMap: Map<string, string> = new Map<string, string>();


    constructor(private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private cropSeasonService: CropSeasonService) {
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
                headerName: 'crop.season',
                field: 'harvestYear',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'agro-work.start-date',
                field: 'startDate',
                width: 300,
                minWidth: 200
            },
            {
                headerName: 'agro-work.end-date',
                field: 'endDate',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'agro-work.yield-goal',
                field: 'yieldGoal',
                width: 100,
                minWidth: 100
            }
        ];

        headers.forEach((h) => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.cropSeasonService.find().subscribe(models => {
            this.adjustModels(models);
            this.options.api.setRowData(models);
        });
    }

    // private setupRows() {
    //     this.cropSeasonService.find().subscribe(data => {
    //         this.adjustModels(models);
    //         debugger;
    //         const fieldMapper = new FieldMapper(this.langService.getLanguage());
    //         const cropNameField = fieldMapper.get('cropName');
    //         const nameField = fieldMapper.get('name');
    //         const descriptionField = fieldMapper.get('description');
    //
    //         const models = JSON.parse(data['items']);
    //         models.forEach((model) => {
    //             model['cropName'] = model[cropNameField];
    //             model['name'] = model[nameField];
    //             model['description'] = model[descriptionField];
    //         });
    //
    //         this.options.api.setRowData(models);
    //     });
    // }

    private adjustModels(models: CropSeasonModel[]) {
        // models.forEach(model => {
        //     this.langService
        //         .get('fertilizer-type.' + model.fertilizerType)
        //         .subscribe(m => model.fertilizerType = m);
        // });
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
