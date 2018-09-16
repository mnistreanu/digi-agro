import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {LangService} from '../../../../services/lang.service';
import {Messages} from '../../../../common/messages';
import {FertilizerModel} from '../fertilizer.model';
import {FertilizerService} from '../../../../services/chemicals-pests/fertilizer.service';

@Component({
    selector: 'app-fertilizer',
    templateUrl: './fertilizer.component.html',
    styleUrls: ['./fertilizer.component.scss']
})
export class FertilizerComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: FertilizerModel;

    labels: any;

    constructor(private fb: FormBuilder,
                private langService: LangService,
                private router: Router,
                private route: ActivatedRoute,
                private fertilizerService: FertilizerService,
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

        this.fertilizerService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labels[Messages.SAVED]);
            this.router.navigate(['/pages/employee']);
        });

    }

    public remove() {
        this.fertilizerService.remove(this.model).subscribe(() => {
            this.toastr.success(this.labels[Messages.REMOVED]);
            this.router.navigate(['/pages/employee']);
        });
    }

}
