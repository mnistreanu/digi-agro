import {Injectable} from "@angular/core";
import {Observable} from "rxjs/Rx";
import {Constants} from "../common/constants";
import {UserAuthI} from "../interfaces/user-auth";
import {Headers, RequestOptions} from "@angular/http";
import "rxjs/add/operator/map";
import "rxjs/add/operator/catch";
import {ErrorService} from "./error.service";
import {Router} from "@angular/router";
import {HttpClient, HttpHeaders} from "@angular/common/http";


@Injectable({
    providedIn: 'root'
})
export class AuthService {

    private options = new RequestOptions({headers: new Headers({'Content-Type': 'application/json'})});

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
    }


    login(model: UserAuthI): Observable<boolean> {
        return this.http.post(Constants.API_URL + '/auth', model, this.httpOptions)
            .map((response) => {
                let token = response.token;
                if (token) {
                    localStorage.setItem(Constants.TOKEN_KEY, JSON.stringify({username: model.username, token: token}));
                    return true;
                }
                this.authStatusChanged.emit(false);
                return false;
            })
            .catch(error => this.errorService.processError(error));
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

}
