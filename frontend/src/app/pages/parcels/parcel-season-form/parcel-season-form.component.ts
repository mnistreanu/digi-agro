import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelSeasonModel} from './parcel-season.model';
import {SelectItem} from '../../../dto/select-item.dto';
import {FieldMapper} from '../../../common/field.mapper';
import {AlertService} from '../../../services/alert.service';
import {LangService} from '../../../services/lang.service';
import {CropSubcultureService} from '../../../services/crop/crop-subculture.service';
import {CropVarietyService} from '../../../services/crop/crop-variety.service';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {ParcelService} from '../../../services/parcel/parcel.service';
import {DateUtil} from '../../../common/dateUtil';
import {CropSeasonListModel} from '../../manage-crops/crop-season/list/crop-season-list.model';

@Component({
    selector: 'app-parcel-season-form',
    templateUrl: './parcel-season-form.component.html',
    styleUrls: ['./parcel-season-form.component.scss']
})
export class ParcelSeasonFormComponent implements OnInit {

    @Input() parcelSeasonModel: ParcelSeasonModel;

    form: FormGroup;
    forcedValidation: boolean;

    harvestYear: number;

    seasons: SelectItem[] = [];
    subcultures: SelectItem[] = [];
    varieties: SelectItem[] = [];

    cropSeasonModels: CropSeasonListModel[] = [];

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private langService: LangService,
                private cropSeasonService: CropSeasonService,
                private cropSubcultureService: CropSubcultureService,
                private cropVarietyService: CropVarietyService,
                private parcelService: ParcelService) {
    }

    ngOnInit() {
        this.harvestYear = this.cropSeasonService.seasonYear;

        this.cropSeasonService.seasonYearChanged.subscribe((year) => {
            this.harvestYear = year;
            this.setupCropSeasons().then(() => this.onCropSeasonChange());
        });

        this.route.params.subscribe(params => {
            this.setupModel(this.harvestYear, this.parcelSeasonModel.parcelId );
        });
    }

    private prepareNewModel() {
        this.parcelSeasonModel = new ParcelSeasonModel();
        this.setupCropSeasons()
            .then(() => this.buildForm());
    }

    private setupModel(harvestYear, parcelId) {
        this.parcelService.findYearSeason(harvestYear, parcelId).subscribe(model => {
            if (model.id != null) {
                this.parcelSeasonModel = model;
            }
            this.buildForm();
            this.setupCropSeasons()
                .then(() => this.onCropSeasonChange());
        });
    }

    private setupCropSeasons(): Promise<void> {
        return new Promise((resolve) => {
            this.cropSeasonService.findByYear(this.harvestYear).subscribe(models => {
                this.cropSeasonModels = models;
                const fieldMapper = new FieldMapper(this.langService.getLanguage());
                const nameField = fieldMapper.get('name');
                this.seasons = models.map(model => {
                    let itemLabel = model.harvestYear + ' ' + model.cropModel[nameField];
                    if (model.cropVarietyModel != null) {
                        itemLabel += ' ' + model.cropVarietyModel[nameField];
                    }
                    return new SelectItem(model.id, itemLabel);
                });
                resolve();
            });
        });
    }

    private onCropSeasonChange() {
        const cropSeasonId = this.form.controls['cropSeasonId'].value;
        this.subcultures = [];
        this.varieties = [];

        this.cropSeasonModels.forEach((model: CropSeasonListModel) => {
            if (cropSeasonId == model.id) {
                this.parcelSeasonModel.cropCategoryId = model.cropCategoryId;
                this.parcelSeasonModel.cropId = model.cropId;
                this.setupCropSubcultures(model.cropId, true);
                this.form.controls['cropSubcultureId'].setValue(model.cropSubcultureId);
                this.setupCropVarieties(model.cropId, model.cropSubcultureId, true);
                this.form.controls['cropVarietyId'].setValue(model.cropVarietyId);
            }
        });
    }

    private setupCropSubcultures(cropId, updateValue) {
        this.cropSubcultureService.find(cropId).subscribe(data => {
            const models = data.payload;

            if (models != null) {
                const fieldMapper = new FieldMapper(this.langService.getLanguage());
                const nameField = fieldMapper.get('name');
                this.subcultures = models.map(model => {
                    return new SelectItem(model.id, model[nameField]);
                });
            } else {
                this.subcultures = [];
            }

            if (updateValue) {
                // TODO only if necessary
            }
        });
    }

    public onCropSubcultureChange() {
        const cropId = this.parcelSeasonModel.cropId;
        const cropSubcultureId = this.form.controls['cropSubcultureId'].value;
        this.setupCropVarieties(cropId, cropSubcultureId, true);
    }

    private setupCropVarieties(cropId, cropSubcultureId, updateValue) {
        if (cropId != null) {
            this.cropVarietyService.findByCrop(cropId).subscribe(data => {
                const models = data.payload;

                if (models != null) {
                    const fieldMapper = new FieldMapper(this.langService.getLanguage());
                    const nameField = fieldMapper.get('name');
                    this.varieties = models.map(model => {
                        return new SelectItem(model.id, model[nameField]);
                    });
                } else {
                    this.varieties = [];
                }

                if (updateValue) {
                    // TODO only if necessary
                }
            });
        }

        if (cropSubcultureId != null) {
            this.cropVarietyService.findBySubculture(cropSubcultureId).subscribe(data => {
                const models = data.payload;

                if (models != null) {
                    const fieldMapper = new FieldMapper(this.langService.getLanguage());
                    const nameField = fieldMapper.get('name');
                    this.varieties = models.map(model => {
                        return new SelectItem(model.id, model[nameField]);
                    });
                } else {
                    this.varieties = [];
                }

                if (updateValue) {
                    // TODO only if necessary
                }
            });
        }

    }

    private buildForm() {
        this.form = this.fb.group({
            cropSeasonId: [this.parcelSeasonModel.cropSeasonId, Validators.required],
            // cropCategoryId: [this.parcelSeasonModel.cropCategoryId, Validators.required],
            // cropId: [this.parcelSeasonModel.cropId, Validators.required],
            cropSubcultureId: [this.parcelSeasonModel.cropSubcultureId],
            cropVarietyId: [this.parcelSeasonModel.cropVarietyId],
            yieldGoal: [this.parcelSeasonModel.yieldGoal, Validators.min(0)],
            unitOfMeasure: [this.parcelSeasonModel.unitOfMeasure],
            plantedAt: [DateUtil.formatDateISO(this.parcelSeasonModel.plantedAt)],
            rowsOnParcel: [this.parcelSeasonModel.rowsOnParcel, Validators.min(0)],
            plantsOnRow: [this.parcelSeasonModel.plantsOnRow, Validators.min(0)],
            spaceBetweenRows: [this.parcelSeasonModel.spaceBetweenRows, Validators.min(0)],
            spaceBetweenPlants: [this.parcelSeasonModel.spaceBetweenPlants, Validators.min(0)]
        });
    }

    public submit() {
        this.forcedValidation = true;

        if (!this.form.valid) {
            return false;
        }

        Object.assign(this.parcelSeasonModel, this.form.value);

        this.forcedValidation = false;
        return true;
    }

}
