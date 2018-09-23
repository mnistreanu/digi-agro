import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {PestModel} from '../pest.model';
import {PestService} from '../../../../services/chemicals-pests/pest.service';
import {AlertService} from '../../../../services/alert.service';

@Component({
    selector: 'app-harmful-organism',
    templateUrl: './pest.component.html',
    styleUrls: ['./pest.component.scss']
})
export class PestComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: PestModel;

    labels: any;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private pestService: PestService) {
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
        this.pestService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new PestModel();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            nameRo: [this.model.nameRo, Validators.required],
            nameRu: [this.model.nameRu, Validators.required]
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

        this.pestService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
            this.router.navigate(['../'], {relativeTo: this.route});
        });

    }

    public remove() {
        this.pestService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
