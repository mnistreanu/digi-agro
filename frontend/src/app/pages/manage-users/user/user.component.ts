import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {UserService} from "../../../services/user.service";
import {ActivatedRoute, Router} from "@angular/router";
import {UserAccountModel} from "./user-account.model";
import {emailValidator} from "../../../theme/validators/email.validator";
import {ToastrService} from "ngx-toastr";
import {Messages} from "../../../common/messages";

@Component({
    selector: 'az-user',
    templateUrl: './user.component.html',
    styleUrls: ['./user.component.scss']
})
export class UserComponent implements OnInit {

    form: FormGroup;
    submitted: boolean = false;

    model: UserAccountModel;
    isNew: boolean;


    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private userService: UserService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.route.params.subscribe(params => {
            let id = params['id'];

            if (id == -1) {
                this.prepareNewModel();
            }
            else {
                this.setupModel(id);
            }
        });
    }

    private prepareNewModel() {
        this.model = new UserAccountModel();
        this.isNew = true;
        this.buildForm();
    }

    private setupModel(id) {
        this.userService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private buildForm() {

        const birthDate = this.model.birthDate ? new Date(this.model.birthDate).toISOString().substring(0, 10) : null;

        this.form = this.fb.group({
            username: new FormControl(this.model.username, [Validators.required, Validators.minLength(3), Validators.maxLength(20)]),
            password: [null, Validators.minLength(4)],
            firstName: [this.model.firstName],
            lastName: [this.model.lastName],
            idnp: [this.model.idnp],
            birthDate: [birthDate],
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

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.userService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(Messages.SAVED);
        });
    }

    public remove() {
        this.userService.remove(this.model).subscribe(() => {
            this.toastr.success(Messages.DELETED);
            this.router.navigate(['/pages/manage-users']);
        });
    }


}
