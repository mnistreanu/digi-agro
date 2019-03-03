import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SelectItem} from '../../../../dto/select-item.dto';
import {CropVarietyModel} from '../crop-variety.model';
import {CropVarietyService} from '../../../../services/crop/crop-variety.service';
import {AlertService} from '../../../../services/alert.service';
import {CropService} from '../../../../services/crop/crop.service';
import {CropSubcultureService} from '../../../../services/crop/crop-subculture.service';
import {CropCategoryService} from '../../../../services/crop/crop-category.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';

@Component({
    selector: 'app-crop',
    templateUrl: './crop-variety-form.component.html',
    styleUrls: ['./crop-variety-form.component.scss']
})
export class CropVarietyFormComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: CropVarietyModel;
    isNew: boolean;

    // unitOfMeasureSelectItems: SelectItem[] = [];
    cropCategories: SelectItem[] = [];
    crops: SelectItem[] = [];
    cropSubcultures: SelectItem[] = [];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private langService: LangService,
                private cropCategoryService: CropCategoryService,
                private cropService: CropService,
                private cropSubcultureService: CropSubcultureService,
                private cropVarietyService: CropVarietyService) {
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
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            this.crops = models.map(model => {
                return new SelectItem(model.id, model[nameField]);
            });

            if (updateValue) {
                const cropControl = this.form.controls['cropId'];
                const value = this.crops.length > 0 ? this.crops[0].id : null;
                cropControl.setValue(value);
            }
            const cropId = this.form.controls['cropId'].value;
            this.setupCropSubcultures(cropId, true);
        });
    }

    public onCropChange() {
        const cropId = this.form.controls['cropId'].value;
        this.setupCropSubcultures(cropId, true);
    }

    private setupCropSubcultures(cropId, updateValue) {
        this.cropSubcultureService.find(cropId).subscribe(data => {
            const models = data.payload;
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            this.cropSubcultures = models.map(model => {
                return new SelectItem(model.id, model[nameField]);
            });

            if (updateValue) {
                const cropSubcultureControl = this.form.controls['cropSubcultureId'];
                const value = this.cropSubcultures.length > 0 ? this.cropSubcultures[0].id : null;
                cropSubcultureControl.setValue(value);
            }
        });
    }

    // private setupUnitOfMeasure() {
    //     const names = Object.keys(UnitOfMeasure);
    //     for (const name of names) {
    //         this.unitOfMeasureSelectItems.push(new SelectItem(UnitOfMeasure[name], name));
    //     }
    // }

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
        this.cropVarietyService.findOne(id).subscribe(model => {
            this.model = model;
            this.setupCrops(model.cropCategoryId, false);
            if (model.cropSubcultureId != null) {
                this.setupCropSubcultures(model.cropId, false);
            }
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new CropVarietyModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {
        this.form = new FormGroup({
            cropCategoryId: new FormControl(this.model.cropCategoryId, [Validators.required]),
            cropId: new FormControl(this.model.cropId, [Validators.required]),
            cropSubcultureId: new FormControl(this.model.cropSubcultureId),
            nameRo: new FormControl(this.model.nameRo, [Validators.required, Validators.maxLength(128)]),
            nameRu: new FormControl(this.model.nameRu, [Validators.required, Validators.maxLength(128)]),
            descriptionRo: new FormControl(this.model.descriptionRo, [Validators.required]),
            descriptionRu: new FormControl(this.model.descriptionRu, [Validators.required]),
        });
    }


    public save(form: FormGroup) {
        this.submitted = true;

        if (!form.valid) {
            this.alertService.validationFailed();
            return;
        }


        Object.assign(this.model, form.value);
        this.cropVarietyService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });

        this.isNew = false;
        this.submitted = false;
    }

    public remove() {
        this.cropVarietyService.remove(this.model.id).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
