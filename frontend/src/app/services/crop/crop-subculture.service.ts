import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {CropSubcultureModel} from '../../pages/manage-crops/crop-subculture/crop-subculture.model';
import {PayloadModel} from '../../pages/payload.model';

@Injectable({
    providedIn: 'root'
})
export class CropSubcultureService {

    private api: string = environment.apiUrl + '/crop-subculture';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<CropSubcultureModel> {
        return this.http.get<CropSubcultureModel>(this.api + '/' + id);
    }

    public findAll(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/');
    }

    public find(cropId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/by-crop/' + cropId);
    }

    public getTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/tree');
    }

    save(model: CropSubcultureModel): Observable<CropSubcultureModel> {
        return this.http.post<CropSubcultureModel>(this.api, model);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
