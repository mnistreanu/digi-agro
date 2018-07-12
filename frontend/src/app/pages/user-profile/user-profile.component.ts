import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {UserService} from "../../services/user.service";
import {AuthService} from "../../services/auth.service";
import {emailValidator} from "../../theme/validators/email.validator";
import {UserAccountModel} from "../manage-users/user/user-account.model";
import {Messages} from "../../common/messages";
import {Router} from "@angular/router";
import {Constants} from "../../common/constants";

@Component({
    selector: 'az-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    form: FormGroup;
    submitted: boolean = false;
    model: UserAccountModel;

    constructor(private router: Router,
                private fb: FormBuilder,
                private authService: AuthService,
                private userService: UserService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.authService.fetchCurrentUser().subscribe(data => {
            this.model = data;
            this.buildForm();
        });
    }

    private buildForm() {
        this.form = this.fb.group({
            username: new FormControl(this.model.username, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
            password: [null, Validators.minLength(4)],
            firstName: [this.model.firstName],
            lastName: [this.model.lastName],
            email: [this.model.email, [emailValidator]],
            address: [this.model.address],
            phone: [this.model.phone],
            mobilePhone: [this.model.mobilePhone]
        });
    }

    public validateUsernameToUnique() {
        let control = this.form.controls.username;
        this.userService.checkUsernameUnique(this.model.id || -1, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                let errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(Messages.VALIDATION_ERROR);
            return;
        }

        let usernameChanged = this.model.username != form.controls.username.value;

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.userService.saveProfile(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(Messages.SAVED);
            if (usernameChanged) {
                this.toastr.success('Username Changed! Please, re-login in the system.');
                this.router.navigate([Constants.LOGIN_PAGE]);
            }
            else {
                this.authService.updateUser(model);
            }
        });
    }


}
