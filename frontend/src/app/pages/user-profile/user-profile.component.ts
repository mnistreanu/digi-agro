import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ToastrService} from 'ngx-toastr';
import {UserService} from '../../services/user.service';
import {AuthService} from '../../services/auth/auth.service';
import {emailValidator} from '../../theme/validators/email.validator';
import {UserAccountModel} from '../manage-users/user/user-account.model';
import {Messages} from '../../common/messages';
import {Router} from '@angular/router';
import {Constants} from '../../common/constants';
import {LangService} from '../../services/lang.service';
import { environment } from '../../../environments/environment';

@Component({
    selector: 'app-user-profile',
    templateUrl: './user-profile.component.html',
    styleUrls: ['./user-profile.component.scss']
})
export class UserProfileComponent implements OnInit {

    form: FormGroup;
    submitted = false;
    model: UserAccountModel;

    logoFile: File;
    logoUrl: string;

    labelFileIsTooBig: string;
    labelSaved: string;
    labelValidationFail: string;
    labelUsernameChangedRelogin: string;

    constructor(private router: Router,
                private fb: FormBuilder,
                private langService: LangService,
                private authService: AuthService,
                private userService: UserService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.authService.fetchCurrentUser().subscribe(data => {
            this.model = data;
            this.logoUrl = environment.apiUrl + this.model.logoUrl;
            console.log('logo', this.logoUrl);
            this.buildForm();
        });
    }

    private setupLabels() {
        this.langService.get('validation.file-to-big').subscribe((message) => this.labelFileIsTooBig = message);
        this.langService.get('response.username-changed-relogin').subscribe((message) => this.labelUsernameChangedRelogin = message);
        this.langService.get(Messages.SAVED).subscribe((message) => this.labelSaved = message);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe((message) => this.labelValidationFail = message);
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
            mobilePhone: [this.model.mobilePhone],
            language: [this.model.language],
        });
    }

    public onUsernameChange() {
        const control = this.form.controls.username;
        this.userService.validateUsername(this.model.id || -1, control.value).subscribe((isUnique) => {
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
            this.toastr.warning(this.labelValidationFail);
            return;
        }

        if (this.logoFile && this.logoFile.size > 2000000) {
            this.toastr.error(this.labelFileIsTooBig + '2MB');
            return;
        }

        const usernameChanged = this.model.username != form.controls.username.value;

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.userService.saveProfile(this.model, this.logoFile).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labelSaved);
            if (usernameChanged) {
                this.toastr.success(this.labelUsernameChangedRelogin);
                this.router.navigate([Constants.LOGIN_PAGE]);
            }
            else {
                this.authService.updateUser(model);
            }
        });
    }

    public onUploadLogo(event) {
        if (!event.target.files || !event.target.files[0]) {
            return;
        }

        this.logoFile = event.target.files[0];

        const reader = new FileReader();
        reader.onload = (progressEvent: ProgressEvent) => {
            this.logoUrl = (<FileReader>progressEvent.target).result;
        };

        reader.readAsDataURL(event.target.files[0]);
    }

}
