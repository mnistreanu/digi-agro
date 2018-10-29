import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SelectItem} from '../../../dto/select-item.dto';
import {CropVarietyModel} from './crop-variety.model';
import {CropVarietyService} from '../../../services/crop/crop-variety.service';
import {UnitOfMeasure} from '../../../enums/unit-of-measure.enum';
import {AlertService} from '../../../services/alert.service';
import {CropService} from '../../../services/crop/crop.service';

@Component({
    selector: 'app-crop',
    templateUrl: './crop-variety.component.html',
    styleUrls: ['./crop-variety.component.scss']
})
export class CropVarietyComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: CropVarietyModel;
    isNew: boolean;

    unitOfMeasureSelectItems: SelectItem[] = [];
    categorySelectItems: SelectItem[] = [];
    cropSelectItems: SelectItem[] = [];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private cropService: CropService,
                private cropVarietyService: CropVarietyService) {
    }

    ngOnInit() {

        this.cropService.findCategoryItems().subscribe(data => {
            this.categorySelectItems = data;
        });

        this.cropVarietyService.findCropItems().subscribe(data => {
            this.cropSelectItems = data;
        });

        const names = Object.keys(UnitOfMeasure);

        for (let mu of names) {
            this.unitOfMeasureSelectItems.push(new SelectItem(UnitOfMeasure[mu], mu));
        }

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
            this.model.cropCategoryId = model.cropModel.cropCategoryId;
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
            nameRo: new FormControl(this.model.nameRo, [Validators.required, Validators.maxLength(128)]),
            nameRu: new FormControl(this.model.nameRu, [Validators.required, Validators.maxLength(128)]),
            descriptionRo: new FormControl(this.model.descriptionRo, [Validators.required]),
            descriptionRu: new FormControl(this.model.descriptionRu, [Validators.required]),
            seedConsumptionHa: new FormControl(this.model.seedConsumptionHa, [Validators.required]),
            unitOfMeasure: new FormControl(this.model.unitOfMeasure, [Validators.required])
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

        if (this.model.id) {
            this.cropVarietyService.update(this.model.id, form.value).subscribe((model) => {
                this.model = model;
                this.alertService.saved();
            });
        } else {
            this.cropVarietyService.create(form.value).subscribe((model) => {
                this.model = model;
                this.alertService.saved();
            });
        }

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
