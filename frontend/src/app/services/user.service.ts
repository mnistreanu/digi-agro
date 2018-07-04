import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Rx";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {AuthService} from "./auth.service";
import {UserLight} from "../interfaces/user-light.interface";
import {UserAccountModel} from "../pages/manage-users/user/user-account.model";

@Injectable({
  providedIn: 'root'
})
export class UserService {

  private api: string = Constants.API_URL + "/user";

  constructor(private authService: AuthService,
              private errorService: ErrorService,
              private http: HttpClient) { }

  findAll(): Observable<UserLight[]> {
    return this.http.get(this.api + '/', this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findOne(id: number): Observable<UserAccountModel> {
    return this.http.get(this.api + '/' + id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  checkUsernameUnique(id: number, username: string): Observable<boolean> {
    let queryParams = `?id=${id}&username=${username}`;
    return this.http.get(this.api + '/checkUsernameUnique' + queryParams, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  save(user: UserAccountModel): Observable<UserAccountModel> {
    return this.http.post(this.api + '/', user, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  remove(user: UserAccountModel): Observable<void> {
    return this.http.delete(this.api + '/' + user.id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

}
