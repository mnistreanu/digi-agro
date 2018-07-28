import {Component, OnInit, ViewEncapsulation} from "@angular/core";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth/auth.service";
import {Constants} from "../../common/constants";
import {ListItem} from "../../interfaces/list-item.interface";
import {AuthResponseModel} from "../../services/auth/auth-response.model";
import {StorageService} from "../../services/storage.service";

@Component({
    selector: 'app-login',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

    private form: FormGroup;
    private badCredentials: boolean = false;
    private returnUrl: String;

    private authenticated: boolean;
    private needShowTenantSelector: boolean;
    private authData: AuthResponseModel;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private storageService: StorageService,
                private fb: FormBuilder,
                private authService: AuthService) {
    }

    ngOnInit(): void {
        this.authService.logout();
        this.authenticated = false;
        this.needShowTenantSelector = false;

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

        this.authService.login(formData).subscribe((authData: AuthResponseModel) => {
            this.authenticated = true;
            this.authData = authData;
            this.processTenants();
        }, () => {
            this.badCredentials = true;
        });
    }

    private processTenants() {
        if (this.authData.tenants.length <= 1) {
            if (this.authData.tenants.length == 1) {
                this.storageService.setItem(Constants.TENANT, this.authData.tenants[0]);
            }
            this.finishLogin();
        }
        else {
            this.needShowTenantSelector = true;
        }
    }

    private finishLogin() {
        this.authService.finishLogin(this.authData);
        this.router.navigate([this.returnUrl]);
    }

    private onTenantSelected() {
        this.finishLogin();
    }

}