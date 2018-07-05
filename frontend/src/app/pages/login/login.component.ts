import {Component, OnInit, ViewEncapsulation} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {Constants} from "../../common/constants";
import {AppState} from "../../app.state";

@Component({
    selector: 'app-login',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    private form: FormGroup;
    private badCredentials: boolean = false;
    returnUrl: String;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private fb: FormBuilder,
                private authService: AuthService) {
    }

    ngOnInit(): void {
        this.authService.logout();

        this.form = this.fb.group({
            'username': ['', Validators.compose([Validators.required])],
            'password': ['', Validators.compose([Validators.required])]
        });
        this.returnUrl = this.route.snapshot.queryParams['returnUrl'] || Constants.PAGE_AFTER_LOGIN;
    }


    public onSubmit(formData) {
        if (!this.form.valid) {
            return;
        }
        this.authService.login(formData).subscribe(result => {
            if (result) {
                this.router.navigate([this.returnUrl]);
            }
            else {
                this.badCredentials = true;
            }
        }, () => {
            this.badCredentials = true;
        });
    }
}