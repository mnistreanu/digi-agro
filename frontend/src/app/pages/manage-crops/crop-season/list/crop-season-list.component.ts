import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {ColDef, GridOptions} from 'ag-grid';
import {FieldMapper} from '../../../../common/field.mapper';
import {DateUtil} from '../../../../common/dateUtil';
import {LangService} from '../../../../services/lang.service';
import {CropSeasonService} from '../../../../services/crop/crop-season.service';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {CropSeasonListModel} from './crop-season-list.model';
import {UnitOfMeasureUtil} from '../../../../common/unit-of-measure-util';

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
                field: 'harvestYearCropVariety',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'agro-work.start-date',
                field: 'startDate',
                valueFormatter: params => DateUtil.formatDate(params.value),
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'agro-work.end-date',
                field: 'endDate',
                valueFormatter: params => DateUtil.formatDate(params.value),
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'agro-work.yield-goal',
                headerTooltip: 'agro-work.yield-goal',
                field: 'yieldGoal',
                valueFormatter: params => UnitOfMeasureUtil.formatKgHa(params.value),
                width: 200,
                minWidth: 200
            }
        ];

        headers.forEach((h) => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }

            if (h.headerTooltip) {
                this.langService.get(h.headerTooltip).subscribe(m => h.headerTooltip = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.cropSeasonService.find().subscribe(models => {
            this.cropSeasonService.adjustListModels(models);
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
