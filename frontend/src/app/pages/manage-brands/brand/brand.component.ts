import {Component, OnInit} from '@angular/core';
import {BrandService} from '../../../services/brand.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {BrandModel} from './brand.model';
import {MessageService} from '../../../services/message.service';

@Component({
    selector: 'app-brand',
    templateUrl: './brand.component.html',
    styleUrls: ['./brand.component.scss']
})
export class BrandComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: BrandModel;
    isNew: boolean;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private messageService: MessageService,
                private brandService: BrandService) {
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
        this.brandService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new BrandModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            name: [this.model.name, Validators.required]
        });
    }

    public onNameChange() {
        const control = this.form.controls.name;
        this.brandService.validateName(this.model.id, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                const errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.messageService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.brandService.save(this.model).subscribe((model) => {
            this.model = model;
            this.messageService.saved();
        });

    }

    public remove() {
        this.brandService.remove(this.model).subscribe(() => {
            this.messageService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
