import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../../pages/payload.model';
import {environment} from '../../../environments/environment';
import {SelectItem} from '../../dto/select-item.dto';
import {CropCategoryModel} from '../../pages/manage-crops/crop-category.model';

@Injectable({
    providedIn: 'root'
})
export class CropCategoryService {

    private api: string = environment.apiUrl + '/crop-category';

    constructor(private http: HttpClient) {
    }

    public find(): Observable<CropCategoryModel[]> {
        return this.http.get<CropCategoryModel[]>(this.api + '/');
    }
}
