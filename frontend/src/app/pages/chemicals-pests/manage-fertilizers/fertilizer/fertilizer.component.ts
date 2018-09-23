import {Component, NgModule, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router, RouterModule} from '@angular/router';
import {FertilizerModel} from '../fertilizer.model';
import {FertilizerService} from '../../../../services/chemicals-pests/fertilizer.service';
import {AlertService} from '../../../../services/alert.service';

@NgModule({
    imports: [
        RouterModule,
    ],
    providers: []
})

@Component({
    selector: 'app-fertilizer',
    templateUrl: './fertilizer.component.html',
    styleUrls: ['./fertilizer.component.scss']
})
export class FertilizerComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: FertilizerModel;

    constructor(private fb: FormBuilder,
                private alertService: AlertService,
                private router: Router,
                private route: ActivatedRoute,
                private fertilizerService: FertilizerService) {
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
        this.fertilizerService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new FertilizerModel();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            fertilizerType: [this.model.fertilizerType, Validators.required],
            nameRo: [this.model.nameRo, Validators.required],
            nameRu: [this.model.nameRu],
            descriptionRo: [this.model.descriptionRo, Validators.required],
            descriptionRu: [this.model.descriptionRu],
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

        this.fertilizerService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
            this.router.navigate(['../'], { relativeTo: this.route });
        });

    }

    public remove() {
        this.fertilizerService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], { relativeTo: this.route });
        });
    }

}
