import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {TenantModel} from '../pages/manage-tenants/tenant/tenant.model';
import {Observable} from 'rxjs/Rx';
import {ListItem} from '../interfaces/list-item.interface';

@Injectable({
    providedIn: 'root'
})
export class TenantService {

    private api: string = Constants.API_URL + '/tenant';

    constructor(private http: HttpClient) {
    }

    fetchListItems(): Observable<ListItem[]> {
        return this.http.get<ListItem[]>(this.api + '/list-items');
    }

    validateName(id: number, value: string): Observable<boolean> {
        let queryParams = `?field=name&value=${value}`;
        if (id) {
            queryParams += `&id=${id}`;
        }
        return this.http.get<boolean>(this.api + '/unique' + queryParams);
    }

    validateFiscalCode(id: number, value: string): Observable<boolean> {
        let queryParams = `?field=fiscalCode&value=${value}`;
        if (id) {
            queryParams += `&id=${id}`;
        }
        return this.http.get<boolean>(this.api + '/unique' + queryParams);
    }

    findOne(id: number): Observable<TenantModel> {
        return this.http.get<TenantModel>(this.api + '/' + id);
    }

    find(): Observable<TenantModel[]> {
        const filterModel = {};
        return this.http.post<TenantModel[]>(this.api + '/find-by', filterModel);
    }

    save(model: TenantModel): Observable<TenantModel> {
        return this.http.post<TenantModel>(this.api + '/', model);
    }

    remove(model: TenantModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
