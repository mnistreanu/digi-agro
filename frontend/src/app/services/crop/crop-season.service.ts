import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {CropSeasonModel} from '../../pages/manage-crops/crop-season/form/crop-season-form.model';

@Injectable({
    providedIn: 'root'
})
export class CropSeasonService {

    private api: string = environment.apiUrl + '/crop-season';

    constructor(private http: HttpClient) {
    }

    public find(): Observable<CropSeasonModel[]> {
        return this.http.get<CropSeasonModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<CropSeasonModel> {
        return this.http.get<CropSeasonModel>(this.api + '/' + id);
    }

    save(model: CropSeasonModel): Observable<CropSeasonModel> {
        return this.http.post<CropSeasonModel>(this.api, model);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
