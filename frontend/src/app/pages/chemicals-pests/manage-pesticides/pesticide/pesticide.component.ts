import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PesticideModel} from '../pesticide.model';
import {PesticideService} from '../../../../services/chemicals-pests/pesticide.service';
import {AlertService} from '../../../../services/alert.service';

@Component({
    selector: 'app-pesticide',
    templateUrl: './pesticide.component.html',
    styleUrls: ['./pesticide.component.scss']
})
export class PesticideComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: PesticideModel;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private pesticideService: PesticideService) {
    }

    ngOnInit() {
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
        this.pesticideService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new PesticideModel();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            pesticideType: [this.model.pesticideType, Validators.required],
            nameRo: [this.model.nameRo, Validators.required],
            nameRu: [this.model.nameRu],
            descriptionRo: [this.model.descriptionRo],
            descriptionRu: [this.model.descriptionRu],
            pestsRo: [this.model.pestsRo, Validators.required],
            pestsRu: [this.model.pestsRu],
            activeSubstance: [this.model.activeSubstance],
            toxicityGroup: [this.model.toxicityGroup],
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.pesticideService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
            this.router.navigate(['../'], {relativeTo: this.route});
        });

    }

    public remove() {
        this.pesticideService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
