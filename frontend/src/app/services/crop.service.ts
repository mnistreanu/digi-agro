import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../pages/payload.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class CropService {

    private api: string = environment.apiUrl + '/crop';

    constructor(private http: HttpClient) {
    }

    public findCategories(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/categories');
    }

    public findCrops(categoryId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/crops?categoryId=' + categoryId);
    }

    public findVarieties(cropId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/varieties?cropId=' + cropId);
    }

    public findVarietiesTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/tree');
    }
}
