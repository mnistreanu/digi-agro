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
import {LangService} from "./lang.service";


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
                private langService: LangService,
                private errorService: ErrorService) {
    }

    logout() {
        localStorage.removeItem(Constants.USER_DATA);
        localStorage.removeItem(Authorities.AUTHORITY_OBJECT);
        this.langService.clear();
        this.userChangedChanged.emit();
    }

    login(model: UserAuth): Observable<boolean> {
        return this.http.post<boolean>(this.api + '/login', model)
            .map((response) => {
                let token = response['token'];
                let authorities = response['authorities'];
                let logoUrl = response['logoUrl'];
                let language = response['language'];
                if (token) {
                    let userData = {username: model.username, token: token, logoUrl: logoUrl};
                    localStorage.setItem(Constants.USER_DATA, JSON.stringify(userData));
                    this.langService.setLanguage(language);
                    this.setupAuthorities(authorities);
                    return true;
                }
                this.userChangedChanged.emit();
                return false;
            });
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
        return this.http.get<UserAccountModel>(this.api + '/current-ser');
    }

    getToken(): String {
        let userData = JSON.parse(localStorage.getItem(Constants.USER_DATA));
        return userData && userData.token ? userData.token : "";
    }

    createTokenHeader() {
        let headers = {};
        headers[Constants.AUTH_HEADER] = Constants.TOKEN_PREFIX + this.getToken();
        return headers;
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
        this.langService.setLanguage(model.language);
        this.userChangedChanged.emit();
    }

}
