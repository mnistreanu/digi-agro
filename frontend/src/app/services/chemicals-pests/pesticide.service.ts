import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PesticideModel} from '../../pages/chemicals-pests/manage-pesticides/pesticide.model';

@Injectable({
    providedIn: 'root'
})
export class PesticideService {

    private api: string = environment.apiUrl + '/pesticide';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<PesticideModel> {
        return this.http.get<PesticideModel>(this.api + '/' + id);
    }

    find(): Observable<PesticideModel[]> {
        return this.http.get<PesticideModel[]>(this.api + '/');
    }

    // find(typeId: number): Observable<PesticideModel[]> {
    //     return this.http.get<PesticideModel[]>(this.api + '/' + typeId);
    // }

    save(model: PesticideModel): Observable<PesticideModel> {
        return this.http.post<PesticideModel>(this.api + '/', model);
    }

    remove(model: PesticideModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
