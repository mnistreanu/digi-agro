import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {CropVarietyModel} from '../../pages/manage-crops/crop-variety/crop-variety.model';
import {PayloadModel} from '../../pages/payload.model';

@Injectable({
    providedIn: 'root'
})
export class CropVarietyService {

    private api: string = environment.apiUrl + '/crop-variety';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<CropVarietyModel> {
        return this.http.get<CropVarietyModel>(this.api + '/' + id);
    }

    public findByCrop(cropId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/by-crop/' + cropId);
    }

    public findBySubculture(cropSubcultureId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/by-subculture/' + cropSubcultureId);
    }

    public findAll(page: number, size: number, filters: Map<string, string>, order: string): Observable<CropVarietyModel[]> {
        let url = this.api + '?page_no=' + page + '&rows_per_page=' + size;

        if (filters) {
            filters.forEach((value: string, key: string) => {
                url += '&filter=' + key + ';' + value;
            });
        }
        else {
            url += '&filter=';
        }
        console.log(url);

        return this.http.get<CropVarietyModel[]>(url);
    }

    public getTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/tree');
    }

    save(model: CropVarietyModel): Observable<CropVarietyModel> {
        return this.http.post<CropVarietyModel>(this.api, model);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
