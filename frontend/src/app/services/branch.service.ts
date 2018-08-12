import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {BranchModel} from '../pages/manage-branches/branch/branch.model';
import {ListItem} from '../interfaces/list-item.interface';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class BranchService {

    private api: string = environment.apiUrl + '/tenant-branch';

    constructor(private http: HttpClient) {
    }

    validateName(id: number, name: string): Observable<boolean> {
        const queryParams = `?id=${id}&name=${name}`;
        return this.http.get<boolean>(this.api + '/validate-name' + queryParams);
    }

    findOne(id: number): Observable<BranchModel> {
        return this.http.get<BranchModel>(this.api + '/' + id);
    }

    find(tenantId: number): Observable<BranchModel[]> {
        const filterModel = {
            tenantId: tenantId
        };
        return this.http.post<BranchModel[]>(this.api + '/find-by', filterModel);
    }

    save(model: BranchModel): Observable<BranchModel> {
        return this.http.post<BranchModel>(this.api + '/', model);
    }

    remove(model: BranchModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }

    fetchListItems(tenantId: number, skipId: number): Observable<ListItem[]> {
        let query = `?tenantId=${tenantId}`;
        if (skipId != null) {
            query += `&skipId=${skipId}`;
        }
        return this.http.get<ListItem[]>(this.api + '/list-items' + query);
    }

    findByTenants(tenants: number[]): Observable<ListItem[]> {
        return this.http.post<ListItem[]>(this.api + '/find-by-tenants', tenants);
    }
}
