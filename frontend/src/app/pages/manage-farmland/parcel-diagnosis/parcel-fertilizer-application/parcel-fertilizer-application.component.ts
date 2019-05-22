import {Component, Input, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelDiagnosisModel} from '../parcel-diagnosis.model';
import {ParcelSeasonModel} from '../../parcel-season.model';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';
import {NumericUtil} from '../../../../common/numericUtil';
import {UnitOfMeasureUtil} from '../../../../common/unit-of-measure-util';
import {DateUtil} from '../../../../common/dateUtil';
import {FertilizerService} from '../../../../services/chemicals-pests/fertilizer.service';
import {ParcelFertilizerApplicationModel} from './parcel-fertilizer-application.model';

@Component({
    selector: 'app-parcel-fertilizer-application',
    templateUrl: './parcel-fertilizer-application.component.html',
    styleUrls: ['./parcel-fertilizer-application.component.scss']
})
export class ParcelFertilizerApplicationComponent implements OnInit {

    @Input() parcelDiagnosisModel: ParcelDiagnosisModel;
    @Input() parcelSeasonModel: ParcelSeasonModel;

    options: GridOptions;
    context;

    form: FormGroup;
    forcedValidation: boolean;

    constructor(private fb: FormBuilder,
                private langService: LangService,
                private fertilizerService: FertilizerService) {
    }

    ngOnInit() {
        this.setupGrid();
        this.buildForm();
        // this.adjustModel(this.parcelSeasonModel);
    }

    private buildForm() {
        if (this.parcelSeasonModel == null) {
            this.parcelSeasonModel = new ParcelSeasonModel();
        }

        this.form = this.fb.group({
            // fertilizerType: [this.model.fertilizerType, Validators.required],
            // nameRo: [this.model.nameRo, Validators.required],
            // nameRu: [this.model.nameRu],
            // descriptionRo: [this.model.descriptionRo, Validators.required],
            // descriptionRu: [this.model.descriptionRu],
        });
    }

    // private adjustModel(model: ParcelSeasonModel) {
    //
    //     const fieldMapper = new FieldMapper(this.langService.getLanguage());
    //     const nameField = fieldMapper.get('name');
    //     if (model.cropModel != null) {
    //         model['cropName'] = model.cropModel[nameField];
    //     }
    //
    //     if (model.cropVarietyModel != null) {
    //         model['cropVarietyName'] = model.cropVarietyModel[nameField];
    //     }
    //
    //     model['irrigatedFormatted'] =  NumericUtil.formatBoolean(model.irrigated);
    //     model['yieldGoalFormatted'] = UnitOfMeasureUtil.formatKgHa(model.yieldGoal);
    //     model['yieldActualFormatted'] = UnitOfMeasureUtil.formatKgHa(model.yieldGoal);
    //     model['plantedAtFormatted'] = DateUtil.formatDate(model.plantedAt);
    //     model['harvestDateFormatted'] = DateUtil.formatDate(model.plantedAt);
    //     model['notes'] = 'Un studiu realizat de Banca Mondială, arată însă că agricultura ' +
    //         'din Republica Moldova este ineficientă, anul trecut (2011) sectorul a înregistrat ' +
    //         'o productivitate scăzută, investițiile în domeniu au fost mici, iar costurile exagerate. ' +
    //         'Productivitatea sectorului este de 2 ori mai mică decît în media europeană';
    // }
    //
    // public submit() {
    //     this.forcedValidation = true;
    //
    //     if (!this.form.valid) {
    //         return false;
    //     }
    //
    //     Object.assign(this.parcelSeasonModel, this.form.value);
    //
    //     this.forcedValidation = false;
    //     return true;
    // }

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
                headerName: 'info.date',
                field: 'applicationDate',
                width: 150,
                minWidth: 100,
                valueFormatter: (params) => DateUtil.formatDate(params.value)            },
            {
                headerName: 'fertilizer.comments',
                field: 'comments',
                width: 300,
                minWidth: 200
            },
            {
                headerName: 'Tip Plasare',
                field: 'placementType',
                width: 250,
                minWidth: 200
            },
            {
                headerName: 'fertilizer.type',
                field: 'fertilizerType',
                width: 150,
                minWidth: 100
            },
            {
                headerName: 'fertilizer.name',
                field: 'fertilizerNameRo',
                width: 400,
                minWidth: 200
            },
            {
                headerName: 'Preț(tonă)',
                field: 'tonePrice',
                width: 400,
                minWidth: 200
            },
            {
                headerName: 'Hectare',
                field: 'fertilizedArea',
                width: 400,
                minWidth: 200
            },
            {
                headerName: 'Rată',
                field: 'rate',
                width: 400,
                minWidth: 200
            },
            {
                headerName: 'Cost/Hectare',
                field: 'hectareCost',
                width: 400,
                minWidth: 200
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        //
        //
        //
        // this.options.api.setRowData(models);
        //
        this.fertilizerService.findApplications(1, 2019).subscribe(models => {
            // this.adjustModels(models);
            this.options.api.setRowData(models);
        });

        this.adjustGridSize();
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
