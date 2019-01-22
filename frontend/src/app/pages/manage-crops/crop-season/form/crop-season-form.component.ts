import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {LangService} from '../../../../services/lang.service';
import {CropCategoryService} from '../../../../services/crop/crop-category.service';
import {CropService} from '../../../../services/crop/crop.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {CropSeasonModel} from './crop-season-form.model';
import {SelectItem} from '../../../../dto/select-item.dto';
import {CropSeasonService} from '../../../../services/crop/crop-season.service';
import {AlertService} from '../../../../services/alert.service';
import {CropVarietyService} from '../../../../services/crop/crop-variety.service';

@Component({
    selector: 'app-crop',
    templateUrl: './crop-season-form.component.html',
    styleUrls: ['./crop-season-form.component.scss']
})
export class CropSeasonFormComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: CropSeasonModel;
    isNew: boolean;

    cropCategories: SelectItem[] = [];
    crops: SelectItem[] = [];
    varieties: SelectItem[] = [];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private langService: LangService,
                private cropCategoryService: CropCategoryService,
                private cropService: CropService,
                private cropVarietyService: CropVarietyService,
                private cropSeasonService: CropSeasonService) {
    }

    ngOnInit() {
        this.setupCategories();
        this.restoreModel();
    }

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
        this.setupCropVarieties(cropId, true);
    }

    private setupCropVarieties(cropId, updateValue) {
        this.cropVarietyService.find(cropId).subscribe(data => {
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

    private restoreModel() {
        this.route.params.subscribe(params => {
            const id = params['id'];

            if (id == -1) {
                this.prepareNewModel();
            }
            else {
                this.setupModel(id);
            }
        });
    }

    private setupModel(id) {
        this.cropSeasonService.findOne(id).subscribe(model => {
            this.model = model;
            this.setupCrops(model.cropCategoryId, false);
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new CropSeasonModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {
        this.form = new FormGroup({
            harvestYear: new FormControl(this.model.harvestYear, [Validators.required]),
            cropCategoryId: new FormControl(this.model.cropCategoryId, [Validators.required]),
            cropId: new FormControl(this.model.cropId, [Validators.required]),
            cropVarietyId: new FormControl(this.model.cropVarietyId),
            startDate: new FormControl(this.model.startDate, [Validators.required]),
            endDate: new FormControl(this.model.endDate, [Validators.required]),
            yieldGoal: new FormControl(this.model.yieldGoal)
        });
    }


    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.alertService.validationFailed();
            return;
        }

        this.isNew = false;
        this.submitted = false;

        Object.assign(this.model, form.value);
        this.cropSeasonService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    public remove() {
        this.cropSeasonService.remove(this.model.id).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
