import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {SelectItem} from '../../../dto/select-item.dto';
import {CropVarietyModel} from './crop-variety.model';
import {CropVarietyService} from '../../../services/crop/crop-variety.service';
import {UnitOfMeasure} from '../../../enums/unit-of-measure.enum';
import {MessageService} from '../../../services/message.service';

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
    cropSelectItems: SelectItem[] = [];

    constructor(private router: Router,
                private route: ActivatedRoute,
                private messageService: MessageService,
                private cropVarietyService: CropVarietyService) {
    }

    ngOnInit() {

        this.cropVarietyService.findCategoryItems().subscribe(data => {
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
            this.messageService.validationFailed();
            return;
        }

        this.isNew = false;
        this.submitted = false;

        if (this.model.id) {
            this.cropVarietyService.update(this.model.id, form.value).subscribe((model) => {
                this.model = model;
                this.messageService.saved();
            });
        } else {
            this.cropVarietyService.create(form.value).subscribe((model) => {
                this.model = model;
                this.messageService.saved();
            });
        }

    }

    public remove() {
        this.cropVarietyService.remove(this.model.id).subscribe(() => {
            this.messageService.removed();
            this.router.navigate(['/pages/manage-crops/crop-varieties']);
        });
    }

}
