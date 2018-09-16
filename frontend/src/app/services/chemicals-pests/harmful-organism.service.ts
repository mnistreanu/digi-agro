import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {HarmfulOrganismModel} from '../../pages/chemicals-pests/manage-harmful-organisms/harmful-organism.model';

@Injectable({
    providedIn: 'root'
})
export class HarmfulOrganismService {

    private api: string = environment.apiUrl + '/harmful-organisms';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<HarmfulOrganismModel> {
        return this.http.get<HarmfulOrganismModel>(this.api + '/' + id);
    }

    find(): Observable<HarmfulOrganismModel[]> {
        return this.http.get<HarmfulOrganismModel[]>(this.api + '/');
    }

    save(model: HarmfulOrganismModel): Observable<HarmfulOrganismModel> {
        return this.http.post<HarmfulOrganismModel>(this.api + '/', model);
    }

    remove(model: HarmfulOrganismModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
