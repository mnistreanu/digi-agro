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


@Injectable({
    providedIn: 'root'
})
export class AuthService {

    authStatusChanged: EventEmitter<boolean> = new EventEmitter();

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
        localStorage.removeItem(Constants.TOKEN_KEY);
        this.authStatusChanged.emit(false);
    }

    login(model: UserAuth): Observable<boolean> {
        return this.http.post(Constants.API_URL + '/auth', model, this.httpOptions)
            .map((response) => {
                let token = response['token'];
                if (token) {
                    localStorage.setItem(Constants.TOKEN_KEY, JSON.stringify({username: model.username, token: token}));
                    this.setupAuthorities();
                    return true;
                }
                this.authStatusChanged.emit(false);
                return false;
            })
            .catch(error => this.errorService.processError(error));
    }

    setupAuthorities() {
        this.http.get(Constants.API_URL + '/authorities', this.getOptions())
            .subscribe((authorities:string[]) => {
                let authorityObject = {};
                authorities.forEach(authority => {
                    authorityObject[authority] = true;
                });
                localStorage.setItem(Authorities.AUTHORITY_OBJECT, JSON.stringify(authorityObject));
                    this.authStatusChanged.emit(true);
            },
            error => this.errorService.processError(error));
    }

    checkAuth() {
        if (!this.isAuthenticated()) {
            this.router.navigate([Constants.LOGIN_PAGE]);
        }
    }

    isAuthenticated(): boolean {
        return !!localStorage.getItem(Constants.TOKEN_KEY);
    }

    getUsername(): string {
        let userInfo = JSON.parse(localStorage.getItem(Constants.TOKEN_KEY));
        if (userInfo) {
            return userInfo.username;
        }
        return null;
    }

    getToken(): String {
        let userInfo = JSON.parse(localStorage.getItem(Constants.TOKEN_KEY));
        return userInfo && userInfo.token ? userInfo.token : "";
    }

    getOptions() {
        let headers = {'Content-Type':  'application/json'};
        headers[Constants.AUTH_HEADER] = Constants.TOKEN_PREFIX + this.getToken();

        return {
            headers: new HttpHeaders(headers)
        };
    }

    hasAuthority(authorityName) {
        return this.isAuthenticated() && JSON.parse(localStorage.getItem(Authorities.AUTHORITY_OBJECT))[authorityName];
    }
}
