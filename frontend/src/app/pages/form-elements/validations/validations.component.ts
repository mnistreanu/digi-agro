import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {MyForm} from './validations.interface';

@Component({
    selector: 'app-validations',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './validations.component.html'
})

export class ValidationsComponent implements OnInit {
    myForm: FormGroup;

    constructor(private formBuilder: FormBuilder) {
    }

    ngOnInit() {
        this.myForm = this.formBuilder.group({
            simple: ['', Validators.required],
            minLength: ['', Validators.compose([Validators.required, Validators.minLength(3)])],
            maxLength: ['', Validators.compose([Validators.required, Validators.maxLength(10)])],
            email: ['', Validators.compose([Validators.required, emailValidator])],
            password: ['', Validators.required],
            confirmPassword: ['', Validators.required],
            website: ['', Validators.compose([Validators.required, websiteValidator])]
        }, {validator: matchingPasswords('password', 'confirmPassword')});
    }

    onSubmit({value, valid}: { value: MyForm, valid: boolean }) {
        console.log(value, valid);
    }

}

export function emailValidator(control: FormControl): { [key: string]: any } {
    const emailRegexp = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
    if (control.value && !emailRegexp.test(control.value)) {
        return {invalidEmail: true};
    }
}

export function websiteValidator(control: FormControl): { [key: string]: any } {
    const websiteRegexp = /(https?:\/\/)?([\w\d]+\.)?[\w\d]+\.\w+\/?.+$/;
    if (control.value && !websiteRegexp.test(control.value)) {
        return {invalidUrl: true};
    }
}

export function matchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
    return (group: FormGroup) => {
        const password = group.controls[passwordKey];
        const passwordConfirmation = group.controls[passwordConfirmationKey];
        if (password.value !== passwordConfirmation.value) {
            return passwordConfirmation.setErrors({mismatchedPasswords: true});
        }
    };
}
