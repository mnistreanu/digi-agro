import {Component, EventEmitter, OnInit, Output} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {ManageHarvestModel} from '../manage-harvest.model';
import {ParcelService} from '../../../../services/parcel/parcel.service';
import {LangService} from '../../../../services/lang.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {DateUtil} from '../../../../common/dateUtil';

@Component({
    selector: 'app-manage-harvest',
    templateUrl: './manage-harvest.component.html',
    styleUrls: ['./manage-harvest.component.scss']
})
export class ManageHarvestComponent implements OnInit {

    @Output() dataChanged: EventEmitter<ManageHarvestModel[]> = new EventEmitter<ManageHarvestModel[]>();
    @Output() centerChanged: EventEmitter<any> = new EventEmitter<any>();

    options: GridOptions;
    context;

    models: ManageHarvestModel[] = [];

    constructor(private parcelService: ParcelService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupGrid();
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
                headerName: 'parcel.cadaster-number',
                field: 'cadasterNumber',
                width: 150,
                minWidth: 150
            },
            {
                headerName: 'info.name',
                field: 'description',
                tooltipField: 'description',
                width: 150,
                minWidth: 150
            },
            {
                headerName: 'crop.crop',
                field: 'name',
                width: 150,
                minWidth: 150
            },
            {
                headerName: 'Seeded Hectares',
                field: 'seededHectares',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Harvest Date',
                field: 'harvestDate',
                valueFormatter: params => DateUtil.formatDate(params.value),
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'hectares',
                field: 'hectares',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Actual Yield',
                field: 'actualYield',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Total Yield',
                field: 'totalYield',
                width: 100,
                minWidth: 100
            },
            {
                headerName: 'Comments',
                field: 'Comments',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Sale Price',
                field: 'salePrice',
                width: 100,
                minWidth: 100
            }

        ];

        headers.forEach(h => {
            this.langService.get(h.headerName).subscribe(m => h.headerName = m);
        });

        return headers;
    }


    public setupRows() {
        this.parcelService.find().subscribe(models => {
            models.forEach((model) => {
                const mhm: ManageHarvestModel = new ManageHarvestModel();
                mhm.id = model.id;
                mhm.name = model.name;
                mhm.cadasterNumber = model.cadasterNumber;
                mhm.description = model.description;
                mhm.seededHectares = Math.round(Math.random() * 100);
                mhm.harvestDate = new Date();
                mhm.hectares = Math.round(Math.random() * 100);
                mhm.actualYield = Math.round(Math.random() * 100);
                mhm.totalYield = Math.round(Math.random() * 100);
                mhm.comments = 'some comments';
                mhm.salePrice = Math.round(Math.random() * 100);

                this.models.push(mhm);
            });

            this.options.api.setRowData(this.models);
            this.adjustGridSize();
            // this.adjustModels(this.models);
            this.dataChanged.emit(this.models);
        });
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }
//
//     public adjustModels(models: ManageHarvestModel[]) {
//         const fieldMapper = new FieldMapper(this.langService.getLanguage());
//         const lastWorkTypeField = fieldMapper.get('lastWorkType');
//         const cropNameField = fieldMapper.get('cropName');
//
//         this.models.forEach((model) => {
// //            model.lastWorkType = model[lastWorkTypeField];
// //            model.cropName = model[cropNameField];
//         });
//     }

    public adjustGridSize() {
        setTimeout(() => {
            if (this.options && this.options.api) {
                this.options.api.sizeColumnsToFit();
            }
        }, 500);
    }

    onSelectionChanged() {
        const model = this.options.api.getSelectedRows()[0];
        const firstCoord = model.paths[0];
        this.centerChanged.emit(firstCoord);
    }

}
