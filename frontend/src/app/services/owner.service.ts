import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {OwnerModel} from '../pages/manage-owners/owner/owner.model';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class OwnerService {

    private api: string = environment.apiUrl + '/owner';

    constructor(private http: HttpClient) {
    }

    validateName(id: number, name: string): Observable<boolean> {
        const queryParams = `?id=${id}&name=${name}`;
        return this.http.get<boolean>(this.api + '/validate-name' + queryParams);
    }

    findOne(id: number): Observable<OwnerModel> {
        return this.http.get<OwnerModel>(this.api + '/' + id);
    }

    findAll(): Observable<OwnerModel[]> {
        return this.http.get<OwnerModel[]>(this.api + '/');
    }

    save(model: OwnerModel): Observable<OwnerModel> {
        return this.http.post<OwnerModel>(this.api + '/', model);
    }

    remove(model: OwnerModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
