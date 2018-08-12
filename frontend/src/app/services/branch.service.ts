import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {BranchModel} from '../pages/manage-branches/branch/branch.model';
import {ListItem} from '../interfaces/list-item.interface';

@Injectable({
    providedIn: 'root'
})
export class BranchService {

    private api: string = Constants.API_URL + '/branch';

    constructor(private http: HttpClient) {
    }

    validateName(id: number, value: string): Observable<boolean> {
        let queryParams = `?field=name&value=${value}`;
        if (id) {
            queryParams += `&id=${id}`;
        }
        return this.http.get<boolean>(this.api + '/unique' + queryParams);
    }

    findOne(id: number): Observable<BranchModel> {
        return this.http.get<BranchModel>(this.api + '/' + id);
    }

    find(): Observable<BranchModel[]> {
        return this.http.get<BranchModel[]>(this.api + '/');
    }

    save(model: BranchModel): Observable<BranchModel> {
        return this.http.post<BranchModel>(this.api + '/', model);
    }

    remove(model: BranchModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }

    fetchListItems(skipRootId: number, tenants: number[]): Observable<ListItem[]> {

        const model = {
            skipRootId: skipRootId,
            tenants: tenants
        };

        return this.http.post<ListItem[]>(this.api + '/list-items', model);
    }

}
