import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelSeasonModel} from './parcel-season.model';
import {SelectItem} from '../../../dto/select-item.dto';
import {FieldMapper} from '../../../common/field.mapper';
import {AlertService} from '../../../services/alert.service';
import {LangService} from '../../../services/lang.service';
import {CropCategoryService} from '../../../services/crop/crop-category.service';
import {CropService} from '../../../services/crop/crop.service';
import {CropSubcultureService} from '../../../services/crop/crop-subculture.service';
import {CropVarietyService} from '../../../services/crop/crop-variety.service';
import {CropSeasonService} from '../../../services/crop/crop-season.service';
import {ParcelService} from '../../../services/parcel.service';
import {DateUtil} from '../../../common/dateUtil';

@Component({
    selector: 'app-parcel-season-form',
    templateUrl: './parcel-season-form.component.html',
    styleUrls: ['./parcel-season-form.component.scss']
})
export class ParcelSeasonFormComponent implements OnInit {

    @Input() parcelSeasonModel: ParcelSeasonModel;

    form: FormGroup;
    forcedValidation: boolean;

    cropCategories: SelectItem[] = [];
    crops: SelectItem[] = [];
    subcultures: SelectItem[] = [];
    varieties: SelectItem[] = [];

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private langService: LangService,
                private cropCategoryService: CropCategoryService,
                private cropService: CropService,
                private cropSubcultureService: CropSubcultureService,
                private cropVarietyService: CropVarietyService,
                private parcelService: ParcelService) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let harvestYear = params['harvestYear'];
            harvestYear = 2019;
            // if (harvestYear == -1) {
            //     this.prepareNewModel(harvestYear);
            // }
            // else {
                this.setupModel(harvestYear, this.parcelSeasonModel.parcelId );
            // }
        });
    }

    private prepareNewModel() {
        this.parcelSeasonModel = new ParcelSeasonModel();
        this.setupCategories();
        this.buildForm();
    }

    private setupModel(harvestYear, parcelId) {
        this.parcelService.findYearSeason(harvestYear, parcelId).subscribe(model => {
            this.parcelSeasonModel = model;
            this.buildForm();
            this.setupCategories();
            this.onCropCategoryChange();
            this.onCropChange();
            this.onCropSubcultureChange();
            debugger;
            // const fieldMapper = new FieldMapper(this.langService.getLanguage());
            // const nameField = fieldMapper.get('name');
            // this.cropCategories = models.map(model => {
            //     return new SelectItem(model.id, model[nameField]);
            // });
        });

        // this.userService.findOne(id).subscribe(model => {
        //     this.model = model;
        //     this.buildForm();
        //     this.setupBranches();
        // });
    }
    //
    // private setupCropSeason() {
    //     const parcelId = 1;
    //     this.parcelService.findLastSeason(parcelId).subscribe(model => {
    //         this.parcelSeasonModel = model;
    //         debugger;
    //         // const fieldMapper = new FieldMapper(this.langService.getLanguage());
    //         // const nameField = fieldMapper.get('name');
    //         // this.cropCategories = models.map(model => {
    //         //     return new SelectItem(model.id, model[nameField]);
    //         // });
    //     });
    // }

    private setupCategories() {
        this.cropCategoryService.find().subscribe(models => {
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            this.cropCategories = models.map(model => {
                return new SelectItem(model.id, model[nameField]);
            });
        });
    }

    public onCropCategoryChange() {
        const cropCategoryId = this.form.controls['cropCategoryId'].value;
        this.setupCrops(cropCategoryId, true);
    }

    private setupCrops(cropCategoryId, updateValue) {
        this.cropService.findCrops(cropCategoryId).subscribe(data => {
            const models = data.payload;
            if (models != null) {
                const fieldMapper = new FieldMapper(this.langService.getLanguage());
                const nameField = fieldMapper.get('name');
                this.crops = models.map(model => {
                    return new SelectItem(model.id, model[nameField]);
                });
                // this.crops.splice(0, 0, new SelectItem(null, ''));
            } else {
                this.crops = [];
            }

            if (updateValue) {
                // TODO only if necessary
            }
        });
    }

    public onCropChange() {
        const cropId = this.form.controls['cropId'].value;
        this.varieties = [];
        this.setupCropSubcultures(cropId, true);
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
        const cropSubcultureId = this.form.controls['cropSubcultureId'].value;
        this.setupCropVarieties(cropSubcultureId, true);
    }

    private setupCropVarieties(cropSubcultureId, updateValue) {
        this.cropVarietyService.find(cropSubcultureId).subscribe(data => {
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

    private buildForm() {
        this.form = this.fb.group({
            cropCategoryId: [this.parcelSeasonModel.cropCategoryId, Validators.required],
            cropId: [this.parcelSeasonModel.cropId, Validators.required],
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
