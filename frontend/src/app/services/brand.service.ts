import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from '@angular/common/http';
import {Constants} from '../common/constants';
import {BrandModel} from '../pages/manage-brands/brand/brand.model';

@Injectable()
export class BrandService {

    private api: string = Constants.API_URL + '/brand';

    constructor(private http: HttpClient) {
    }

    validateName(id: number, name: string): Observable<boolean> {
        const queryParams = `?id=${id}&name=${name}`;
        return this.http.get<boolean>(this.api + '/validate-name' + queryParams);
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
