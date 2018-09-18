import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PestModel} from '../../pages/chemicals-pests/manage-pests/pest.model';

@Injectable({
    providedIn: 'root'
})
export class PestService {

    private api: string = environment.apiUrl + '/pest';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<PestModel> {
        return this.http.get<PestModel>(this.api + '/' + id);
    }

    find(): Observable<PestModel[]> {
        return this.http.get<PestModel[]>(this.api + '/');
    }

    save(model: PestModel): Observable<PestModel> {
        return this.http.post<PestModel>(this.api + '/', model);
    }

    remove(model: PestModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
