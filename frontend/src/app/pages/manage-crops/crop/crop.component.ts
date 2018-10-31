import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SelectItem} from '../../../dto/select-item.dto';
import {CropService} from '../../../services/crop/crop.service';
import {CropModel} from '../crop.model';
import {AlertService} from '../../../services/alert.service';
import {CropCategoryModel} from '../crop-category.model';
import {CropCategoryService} from '../../../services/crop/crop-category.service';
import {LangService} from '../../../services/lang.service';
import {FieldMapper} from '../../../common/field.mapper';

@Component({
    selector: 'app-crop',
    templateUrl: './crop.component.html',
    styleUrls: ['./crop.component.scss']
})
export class CropComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: CropModel;
    isNew: boolean;

    cropCategorySelectItems: SelectItem[] = [];

    constructor(private route: ActivatedRoute,
                private router: Router,
                private alertService: AlertService,
                private langService: LangService,
                private cropCategoryService: CropCategoryService,
                private cropService: CropService) {
    }

    ngOnInit() {

        this.cropCategoryService.find().subscribe(models => {
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            this.cropCategorySelectItems = models.map(model => {
                return new SelectItem(model.id, model[nameField]);
            });
        });

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
        this.cropService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new CropModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {
        this.form = new FormGroup({
            nameRo: new FormControl(this.model.nameRo, [Validators.required, Validators.maxLength(128)]),
            nameRu: new FormControl(this.model.nameRu, [Validators.required, Validators.maxLength(128)]),
            cropCategoryId: new FormControl(this.model.cropCategoryId, [Validators.required])
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
        this.cropService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    public remove() {
        this.cropService.remove(this.model.id).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    private back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
