import { Injectable } from '@angular/core';
import {Constants} from "../common/constants";
import {AuthService} from "./auth.service";
import {ErrorService} from "./error.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {BranchModel} from "../pages/manage-branches/branch/branch.model";
import {ListItem} from "../interfaces/list-item.interface";

@Injectable({
  providedIn: 'root'
})
export class BranchService {

    private api: string = Constants.API_URL + "/tenant-branch";

    constructor(private authService: AuthService,
                private errorService: ErrorService,
                private http: HttpClient) {
    }

    checkNameUnique(id: number, name: string): Observable<boolean> {
        let queryParams = `?id=${id}&name=${name}`;
        return this.http.get(this.api + '/checkNameUnique' + queryParams, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    findOne(id: number): Observable<BranchModel> {
        return this.http.get(this.api + '/' + id, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    find(tenantId: number): Observable<BranchModel[]> {
        let filterModel = {
            tenantId: tenantId
        };
        return this.http.post(this.api + '/findBy', filterModel, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    save(model: BranchModel): Observable<BranchModel> {
        return this.http.post(this.api + '/', model, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    remove(model: BranchModel): Observable<void> {
        return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    fetchListItems(tenantId: number, skipId: number): Observable<ListItem[]> {
        let query = `?tenantId=${tenantId}`;
        if (skipId != null) {
            query += `&skipId=${skipId}`;
        }
        return this.http.get(this.api + '/fetchItems' + query, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    findByTenants(tenants: number[]): Observable<ListItem[]> {
        return this.http.post(this.api + '/findByTenants', tenants, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }
}
