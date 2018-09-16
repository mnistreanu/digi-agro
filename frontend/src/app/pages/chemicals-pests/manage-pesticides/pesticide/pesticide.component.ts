import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {LangService} from '../../../../services/lang.service';
import {Messages} from '../../../../common/messages';
import {PesticideModel} from '../pesticide.model';
import {PesticideService} from '../../../../services/chemicals-pests/pesticide.service';

@Component({
    selector: 'app-pesticide',
    templateUrl: './pesticide.component.html',
    styleUrls: ['./pesticide.component.scss']
})
export class PesticideComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: PesticideModel;

    labels: any;

    constructor(private fb: FormBuilder,
                private langService: LangService,
                private router: Router,
                private route: ActivatedRoute,
                private pesticideService: PesticideService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
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

    private setupLabels() {
        this.labels = {};
        this.langService.get(Messages.SAVED).subscribe(m => this.labels[Messages.SAVED] = m);
        this.langService.get(Messages.REMOVED).subscribe(m => this.labels[Messages.REMOVED] = m);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe(m => this.labels[Messages.VALIDATION_FAIL] = m);
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
            nameRo: [this.model.nameRo, Validators.required],
            nameRu: [this.model.nameRu, Validators.required]
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(this.labels[Messages.VALIDATION_FAIL]);
            return;
        }

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.pesticideService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labels[Messages.SAVED]);
            this.router.navigate(['/pages/employee']);
        });

    }

    public remove() {
        this.pesticideService.remove(this.model).subscribe(() => {
            this.toastr.success(this.labels[Messages.REMOVED]);
            this.router.navigate(['/pages/employee']);
        });
    }

}
