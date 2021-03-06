import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {UserModel} from './user.model';
import {emailValidator} from '../../../../theme/validators/email.validator';
import {matchPasswordValidator} from '../../../../theme/validators/match-password.validator';
import {UserService} from '../../../../services/user.service';
import {AuthService} from '../../../../services/auth/auth.service';
import {ActivatedRoute, Router} from '@angular/router';
import {TenantService} from '../../../../services/tenant.service';
import {ListItem} from '../../../../interfaces/list-item.interface';
import {IMultiSelectSettings} from 'angular-2-dropdown-multiselect';
import {BranchService} from '../../../../services/branch.service';
import {Authorities} from '../../../../common/authorities';
import {AlertService} from '../../../../services/alert.service';

@Component({
    selector: 'app-user-form',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

    public steps: any[];
    public accountForm: FormGroup;
    public personalForm: FormGroup;
    public tenantForm: FormGroup;
    public details: any = {};

    model: UserModel;

    tenants: ListItem[];
    branches: ListItem[];

    multiSelectSettings: IMultiSelectSettings = {
        checkedStyle: 'fontawesome',
        dynamicTitleMaxItems: 10
    };

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private authService: AuthService,
                private userService: UserService,
                private branchService: BranchService,
                private tenantService: TenantService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.setupTenants();
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

    private setupTenants() {
        this.tenantService.fetchListItems().subscribe(data => {
            this.tenants = data;
        });
    }

    private setupBranches() {
        const tenants = this.tenantForm.controls.tenants.value;
        if (tenants.length == 0) {
            this.branches = [];
            this.tenantForm.controls.branches.setValue(null);
        }
        else {
            this.branchService.fetchListItems(null, tenants).subscribe((data) => {
                this.branches = data;
            });
        }
    }

    private prepareNewModel() {
        this.model = new UserModel();
        this.setupRole();
        this.buildForm();
    }

    private setupModel(id) {
        this.userService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
            this.setupBranches();
        });
    }

    private buildForm() {
        this.steps = [
            {name: 'user.account-information', icon: 'fa fa-lock', active: true, valid: false, submitted: false},
            {name: 'user.personal-information', icon: 'fa fa-user', active: false, valid: false, submitted: false},
            {name: 'user.tenant-information', icon: 'far fa-user-tie', active: false, valid: false, submitted: false},
            {name: 'user.confirm-details', icon: 'fa fa-check-square-o', active: false, valid: false, submitted: false}
        ];

        this.accountForm = this.fb.group({
                username: new FormControl(this.model.username, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
                password: [null, Validators.compose([Validators.minLength(4)])],
                confirmPassword: [null],
                email: [this.model.email, Validators.compose([Validators.required, emailValidator])]
            }, {validator: matchPasswordValidator('password', 'confirmPassword')}
        );

        this.personalForm = this.fb.group({
            firstName: [this.model.firstName],
            lastName: [this.model.lastName],
            address: [this.model.address],
            phone: [this.model.phone],
            mobilePhone: [this.model.mobilePhone]
        });

        this.tenantForm = this.fb.group({
            tenants: [this.model.tenants, Validators.required],
            branches: [this.model.branches],
        });
    }

    public next() {
        const accountForm = this.accountForm;
        const personalForm = this.personalForm;
        const tenantForm = this.tenantForm;

        if (this.steps[this.steps.length - 1].active) {
            return false;
        }

        this.steps.some(function (step, index, steps) {
            if (index < steps.length - 1) {
                if (step.active) {
                    if (step.name == 'user.account-information') {
                        step.submitted = true;
                        if (accountForm.valid) {
                            step.active = false;
                            step.valid = true;
                            steps[index + 1].active = true;
                            return true;
                        }
                    }
                    if (step.name == 'user.personal-information') {
                        step.submitted = true;
                        if (personalForm.valid) {
                            step.active = false;
                            step.valid = true;
                            steps[index + 1].active = true;
                            return true;
                        }
                    }
                    if (step.name == 'user.tenant-information') {
                        step.submitted = true;
                        if (tenantForm.valid) {
                            step.active = false;
                            step.valid = true;
                            steps[index + 1].active = true;
                            return true;
                        }
                    }
                }
            }
        });

        this.details.username = this.accountForm.value.username;
        this.details.email = this.accountForm.value.email;
        this.details.phone = this.personalForm.value.phone;
        this.details.mobilePhone = this.personalForm.value.mobilePhone;
        this.details.address = this.personalForm.value.address;
    }

    public prev() {
        if (this.steps[0].active) {
            return false;
        }
        this.steps.some(function (step, index, steps) {
            if (index != 0) {
                if (step.active) {
                    step.active = false;
                    steps[index - 1].active = true;
                    return true;
                }
            }
        });
    }

    public onUsernameChange() {
        const control = this.accountForm.controls.username;
        this.userService.validateUsername(this.model.id || -1, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                const errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    public onTenantChange() {
        this.setupBranches();
    }

    public confirm() {
        this.steps.forEach(step => step.valid = true);

        Object.assign(this.model, this.accountForm.value);
        Object.assign(this.model, this.personalForm.value);
        Object.assign(this.model, this.tenantForm.value);


        this.userService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });

    }

    private setupRole() {
        if (this.authService.isSuperAdmin()) {
            this.model.roleName = Authorities.ROLE_ADMIN;
        }
        else {
            this.model.roleName = Authorities.ROLE_USER;
        }
    }

    public remove() {
        this.userService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
