import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SelectItem} from '../../../../dto/select-item.dto';
import {CropSubcultureModel} from '../crop-subculture.model';
import {CropSubcultureService} from '../../../../services/crop/crop-subculture.service';
import {UnitOfMeasure} from '../../../../enums/unit-of-measure.enum';
import {AlertService} from '../../../../services/alert.service';
import {CropService} from '../../../../services/crop/crop.service';
import {CropCategoryService} from '../../../../services/crop/crop-category.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';

@Component({
    selector: 'app-crop',
    templateUrl: './crop-subculture-form.component.html',
    styleUrls: ['./crop-subculture-form.component.scss']
})
export class CropSubcultureFormComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: CropSubcultureModel;
    isNew: boolean;

    cropCategories: SelectItem[] = [];
    crops: SelectItem[] = [];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private langService: LangService,
                private cropCategoryService: CropCategoryService,
                private cropService: CropService,
                private cropSubcultureService: CropSubcultureService) {
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
        this.cropSubcultureService.findOne(id).subscribe(model => {
            this.model = model;
            this.setupCrops(model.cropCategoryId, false);
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new CropSubcultureModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {
        this.form = new FormGroup({
            cropCategoryId: new FormControl(this.model.cropCategoryId, [Validators.required]),
            cropId: new FormControl(this.model.cropId, [Validators.required]),
            nameRo: new FormControl(this.model.nameRo, [Validators.required, Validators.maxLength(128)]),
            nameRu: new FormControl(this.model.nameRu, [Validators.required, Validators.maxLength(128)]),
            descriptionRo: new FormControl(this.model.descriptionRo),
            descriptionRu: new FormControl(this.model.descriptionRu),
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
        this.cropSubcultureService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    public remove() {
        this.cropSubcultureService.remove(this.model.id).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
