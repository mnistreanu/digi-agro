import {Component, OnInit} from '@angular/core';
import {TenantModel} from './tenant.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {TenantService} from '../../../services/tenant.service';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';

@Component({
    selector: 'app-tenant',
    templateUrl: './tenant.component.html',
    styleUrls: ['./tenant.component.scss']
})
export class TenantComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: TenantModel;
    isNew: boolean;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private tenantService: TenantService,
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
        this.tenantService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new TenantModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            name: new FormControl(this.model.name, [Validators.required, Validators.maxLength(128)]),
            description: new FormControl(this.model.description, [Validators.maxLength(1024)]),
            fiscalCode: [this.model.fiscalCode],
            country: new FormControl(this.model.country, [Validators.required, Validators.maxLength(2)]),
            county: [this.model.county, Validators.maxLength(2)],
            villageCity: [this.model.villageCity, Validators.maxLength(255)],
            address: [this.model.address, Validators.maxLength(1024)],
            phones: [this.model.phones, Validators.maxLength(128)],
        });
    }

    public onNameChange() {
        const control = this.form.controls.name;
        this.tenantService.validateName(this.model.id, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                const errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    public onFiscalCodeChange() {
        const control = this.form.controls.fiscalCode;
        this.tenantService.validateFiscalCode(this.model.id, control.value).subscribe((isUnique) => {
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

        this.tenantService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(Messages.SAVED);
        });

    }

    public remove() {
        this.tenantService.remove(this.model).subscribe(() => {
            this.toastr.success(Messages.REMOVED);
            this.router.navigate(['/pages/manage-tenants']);
        });
    }

}
