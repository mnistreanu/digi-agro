import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {OwnerService} from '../../../services/owner.service';
import {ActivatedRoute, Router} from '@angular/router';
import {OwnerModel} from './owner.model';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';

@Component({
    selector: 'app-owner',
    templateUrl: './owner.component.html',
    styleUrls: ['./owner.component.scss']
})
export class OwnerComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: OwnerModel;
    isNew: boolean;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private ownerService: OwnerService,
                private toastr: ToastrService) {
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
        this.ownerService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new OwnerModel();
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
        this.ownerService.validateName(this.model.id || -1, control.value).subscribe((isUnique) => {
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
            this.toastr.warning(Messages.VALIDATION_FAIL);
            return;
        }

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.ownerService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(Messages.SAVED);
        });

    }

    public remove() {
        this.ownerService.remove(this.model).subscribe(() => {
            this.toastr.success(Messages.REMOVED);
            this.router.navigate(['pages/manage-owners']);
        });
    }

}
