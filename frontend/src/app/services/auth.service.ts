import {EventEmitter, Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {Constants} from "../common/constants";
import {UserAuth} from "../interfaces/user-auth";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {ErrorService} from "./error.service";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Authorities} from "../common/authorities";
import {UserAccountModel} from "../pages/manage-users/user/user-account.model";


@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private api: string = Constants.API_URL + "/auth";

    userChangedChanged: EventEmitter<void> = new EventEmitter();

    private httpOptions = {
        headers: new HttpHeaders({
            'Content-Type':  'application/json'
        })
    };

    constructor(private http: HttpClient,
                private router: Router,
                private errorService: ErrorService) {
    }

    logout() {
        localStorage.removeItem(Constants.USER_DATA);
        localStorage.removeItem(Authorities.AUTHORITY_OBJECT);
        this.userChangedChanged.emit();
    }

    login(model: UserAuth): Observable<boolean> {
        return this.http.post(this.api + '/login', model, this.httpOptions)
            .map((response) => {
                let token = response['token'];
                let authorities = response['authorities'];
                let logoUrl = response['logoUrl'];
                if (token) {
                    let userData = {username: model.username, token: token, logoUrl: logoUrl};
                    localStorage.setItem(Constants.USER_DATA, JSON.stringify(userData));
                    this.setupAuthorities(authorities);
                    return true;
                }
                this.userChangedChanged.emit();
                return false;
            })
            .catch(error => this.errorService.processError(error));
    }

    setupAuthorities(authorities: string[]) {
        localStorage.setItem(Authorities.AUTHORITY_OBJECT, '{}');
        let authorityObject = {};
        authorities.forEach(authority => {
            authorityObject[authority] = true;
        });
        localStorage.setItem(Authorities.AUTHORITY_OBJECT, JSON.stringify(authorityObject));
        this.userChangedChanged.emit();
    }

    checkAuth() {
        if (!this.isAuthenticated()) {
            this.router.navigate([Constants.LOGIN_PAGE]);
        }
    }

    isAuthenticated(): boolean {
        return !!localStorage.getItem(Constants.USER_DATA);
    }

    getUsername(): string {
        let userData = JSON.parse(localStorage.getItem(Constants.USER_DATA));
        if (userData) {
            return userData.username;
        }
        return null;
    }

    getUserData() {
        return JSON.parse(localStorage.getItem(Constants.USER_DATA));
    }

    fetchCurrentUser(): Observable<UserAccountModel> {
        return this.http.get(this.api + '/current-ser', this.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    getToken(): String {
        let userData = JSON.parse(localStorage.getItem(Constants.USER_DATA));
        return userData && userData.token ? userData.token : "";
    }

    getOptions() {
        let headers = {'Content-Type':  'application/json'};
        headers[Constants.AUTH_HEADER] = Constants.TOKEN_PREFIX + this.getToken();

        return {
            headers: new HttpHeaders(headers)
        };
    }

    getOptionsNoContentType() {
        let headers = {};
        headers[Constants.AUTH_HEADER] = Constants.TOKEN_PREFIX + this.getToken();

        return {
            headers: new HttpHeaders(headers)
        };
    }


    hasAuthority(authorityName) {
        let authorityObject = localStorage.getItem(Authorities.AUTHORITY_OBJECT);
        return this.isAuthenticated() && JSON.parse(authorityObject)[authorityName];
    }

    isSuperAdmin() {
        return this.hasAuthority(Authorities.ROLE_SUPER_ADMIN);
    }

    isAdmin() {
        return this.hasAuthority(Authorities.ROLE_ADMIN);
    }

    updateUser(model: UserAccountModel) {
        let userData = JSON.parse(localStorage.getItem(Constants.USER_DATA));
        userData.logoUrl = model.logoUrl;
        localStorage.setItem(Constants.USER_DATA, JSON.stringify(userData));
        this.userChangedChanged.emit();
    }

}
