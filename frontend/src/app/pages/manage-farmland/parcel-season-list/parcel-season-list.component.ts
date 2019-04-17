import {Component, OnDestroy, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {LangService} from '../../../services/lang.service';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {ActivatedRoute, Router} from '@angular/router';
import {NumericUtil} from '../../../common/numericUtil';
import {ParcelCropSeasonService} from '../../../services/parcel/parcel-crop-season.service';
import {ParcelSeasonModel} from '../parcel-season-form/parcel-season.model';
import {FieldMapper} from '../../../common/field.mapper';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {UnitOfMeasureUtil} from '../../../common/unit-of-measure-util';

@Component({
    selector: 'app-parcel-season-list',
    templateUrl: './parcel-season-list.component.html',
    styleUrls: ['./parcel-season-list.component.scss']
})
export class ParcelSeasonListComponent implements OnInit, OnDestroy {

    options: GridOptions;
    context;

    models: ParcelSeasonModel[] = [];
    seasonYearSubscription;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private cropSeasonService: CropSeasonService,
                private parcelCropSeasonService: ParcelCropSeasonService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupGrid();

        this.seasonYearSubscription = this.cropSeasonService.seasonYearChanged.subscribe(() => {
            this.setupRows();
        });
    }

    ngOnDestroy() {
        this.seasonYearSubscription.unsubscribe();
    }

    private setupGrid() {
        this.options = <GridOptions>{};

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.rowSelection = 'single';
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
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            },
            {
                headerName: 'info.name',
                field: 'parcelName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'parcel.cadaster-number',
                field: 'cadasterNumber',
                width: 175,
                minWidth: 175
            },
            {
                headerName: 'parcel.area',
                field: 'area',
                width: 100,
                minWidth: 100,
                valueFormatter: (params) => NumericUtil.format(params.value)
            },
            {
                headerName: 'parcel.irrigated-short',
                field: 'irrigated',
                width: 80,
                minWidth: 80,
                valueFormatter: (params) => NumericUtil.formatBoolean(params.value)
            },
            {
                headerName: 'crop.crop',
                field: 'cropName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'crop.variety',
                field: 'cropVarietyName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'parcel.yield-goal',
                field: 'yieldGoal',
                valueFormatter: params => UnitOfMeasureUtil.formatKgHa(params.value),
                width: 150,
                minWidth: 150
            }

        ];

        headers.forEach(h => {
            if (h.headerName) {
                this.langService.get(h.headerName).subscribe(m => h.headerName = m);
            }
        });

        return headers;
    }

    public setupRows() {
        if (!this.cropSeasonService.seasonYear) {
            return;
        }
        this.parcelCropSeasonService.findParcels(this.cropSeasonService.seasonYear).subscribe(models => {
            models.forEach(model => this.adjustModel(model));
            this.options.api.setRowData(models);
            this.models = models;
            this.adjustGridSize();
            this.setupSummaryRow(this.models);
        });
    }

    private adjustModel(model: ParcelSeasonModel) {

        const fieldMapper = new FieldMapper(this.langService.getLanguage());
        const nameField = fieldMapper.get('name');
        if (model.cropModel != null) {
            model['cropName'] = model.cropModel[nameField];
        }

        if (model.cropVarietyModel != null) {
            model['cropVarietyName'] = model.cropVarietyModel[nameField];
        }
    }

    private setupSummaryRow(models) {
        const summaryRow = new ParcelSeasonModel();
        models.forEach(model => this.aggregate(summaryRow, model, true));
        this.options.api.setPinnedBottomRowData([summaryRow]);
    }

    private aggregate(source: ParcelSeasonModel, item: ParcelSeasonModel, applyAddition) {
        const sumFields = ['area', 'yieldGoal'];
        sumFields.forEach(field => {
            source[field] = source[field] || 0;
            if (applyAddition) {
                source[field] += item[field] || 0;
            }
            else {
                source[field] -= item[field] || 0;
            }
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

    // public addParcel() {
    //     this.router.navigate(['./-1'], {relativeTo: this.route});
    // }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], {relativeTo: this.route});
    }

}
