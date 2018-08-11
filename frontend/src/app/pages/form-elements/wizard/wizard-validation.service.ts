export class WizardValidationService {

    static emailValidator(control): { [key: string]: any } {
        const emailRegexp = /[a-z0-9._%+-]+@[a-z0-9.-]+\.[a-z]{2,3}$/;
        if (control.value && !emailRegexp.test(control.value)) {
            return {invalidEmail: true};
        }
    }

    static matchingPasswords(passwordKey: string, passwordConfirmationKey: string) {
        return (group) => {
            const password = group.controls[passwordKey];
            const passwordConfirmation = group.controls[passwordConfirmationKey];
            if (password.value !== passwordConfirmation.value) {
                return passwordConfirmation.setErrors({mismatchedPasswords: true});
            }
        };
    }

    static numberValidator(control): { [key: string]: any } {
        const onlyNumberRegexp = /.*[^0-9].*/;
        if (control.value && onlyNumberRegexp.test(control.value)) {
            return {invalidNumber: true};
        }
    }

}
