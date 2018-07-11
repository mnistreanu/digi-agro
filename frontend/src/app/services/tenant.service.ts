import { Injectable } from '@angular/core';
import {Constants} from "../common/constants";
import {AuthService} from "./auth.service";
import {ErrorService} from "./error.service";
import {HttpClient} from "@angular/common/http";
import {TenantModel} from "../pages/manage-tenants/tenant/tenant.model";
import {Observable} from "rxjs/Rx";
import {ListItem} from "../interfaces/list-item.interface";

@Injectable({
  providedIn: 'root'
})
export class TenantService {

    private api: string = Constants.API_URL + "/tenant";

    constructor(private authService: AuthService,
                private errorService: ErrorService,
                private http: HttpClient) {
    }

    fetchListItems(): Observable<ListItem[]> {
        return this.http.get(this.api + '/fetchListItems', this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    checkNameUnique(id: number, name: string): Observable<boolean> {
        let queryParams = `?id=${id}&name=${name}`;
        return this.http.get(this.api + '/checkNameUnique' + queryParams, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    checkFiscalCodeUnique(id: number, code: string): Observable<boolean> {
        let queryParams = `?id=${id}&code=${code}`;
        return this.http.get(this.api + '/checkFiscalCodeUnique' + queryParams, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    findOne(id: number): Observable<TenantModel> {
        return this.http.get(this.api + '/' + id, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    find(): Observable<TenantModel[]> {
        let filterModel = {};
        return this.http.post(this.api + '/findBy', filterModel, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    save(model: TenantModel): Observable<TenantModel> {
        return this.http.post(this.api + '/', model, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    remove(model: TenantModel): Observable<void> {
        return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }
}
