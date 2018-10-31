import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../../pages/payload.model';
import {environment} from '../../../environments/environment';
import {CropModel} from '../../pages/manage-crops/crop.model';

@Injectable({
    providedIn: 'root'
})
export class CropService {

    private api: string = environment.apiUrl + '/crops';

    constructor(private http: HttpClient) {
    }

    public find(): Observable<CropModel[]> {
        return this.http.get<CropModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<CropModel> {
        return this.http.get<CropModel>(this.api + '/' + id);
    }

    public findAll(page: number, size: number, filters: Map<string, string>, order: string): Observable<CropModel[]> {
        let url = this.api + '?page_no=' + page + '&rows_per_page=' + size;

        if (filters) {
            filters.forEach((value: string, key: string) => {
                url += '&filter=' + key + ';' + value;
            });
        } else {
            url += '&filter=';
        }
        console.log(url);

        return this.http.get<CropModel[]>(url);
    }

    public findCrops(categoryId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/crops?categoryId=' + categoryId);
    }

    save(model: CropModel): Observable<CropModel> {
        return this.http.post<CropModel>(this.api, model);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
