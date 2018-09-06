import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from '@angular/common/http';
import {BrandModel} from '../pages/manage-brands/brand/brand.model';
import {environment} from '../../environments/environment';

@Injectable()
export class BrandService {

    private api: string = environment.apiUrl + '/brand';

    constructor(private http: HttpClient) {
    }

    validateName(id: number, value: string): Observable<boolean> {
        let queryParams = `?field=name&value=${value}`;
        if (id) {
            queryParams += `&id=${id}`;
        }
        return this.http.get<boolean>(this.api + '/unique' + queryParams);
    }

    findOne(id: number): Observable<BrandModel> {
        return this.http.get<BrandModel>(this.api + '/' + id);
    }

    findAll(): Observable<BrandModel[]> {
        return this.http.get<BrandModel[]>(this.api + '/');
    }

    save(model: BrandModel): Observable<BrandModel> {
        return this.http.post<BrandModel>(this.api + '/', model);
    }

    remove(model: BrandModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }

}
