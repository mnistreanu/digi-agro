import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../pages/payload.model';
import { environment } from '../../environments/environment';
import {CropDTO} from '../dto/crop/crop.dto'
import { SelectItem } from '../dto/select-item.dto';
import { CropModel } from '../pages/manage-crops/crop.model';

@Injectable({
    providedIn: 'root'
})
export class CropService {

    private api: string = environment.apiUrl + '/crops';

    constructor(private http: HttpClient) {
    }

    // public findAll(): Observable<CropDTO[]> {
    //     return this.http.get<CropDTO[]>(this.api+"?page_no=0&rows_per_page=10&filter=&sort=");
    // }

    public findAll(page: number, size: number, filters: Map<string, string>, order: string): Observable<CropDTO[]> {
        var url = this.api + "?page_no=" + page + "&rows_per_page=" + size;

        if (filters) {
            filters.forEach((value: string, key: string) => {
                //console.log(key, value);
                url += '&filter=' + key + ";" + value;
            });
        } else {
            url += '&filter='
        }
        console.log(url);

        return this.http.get<CropDTO[]>( url );
    }

    public findCategories(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/categories');
    }

    public findCategoryItems(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.api + '/categories/select_items');
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

    create(model: FormData): Observable<CropModel> {
        return this.http.post<CropModel>(this.api, model);
    }

    update(id:number, model: FormData): Observable<CropModel> {
        return this.http.put<CropModel>(this.api + '/' + id, model);
    }

    findOne(id: number): Observable<CropModel> {
        return this.http.get<CropModel>(this.api + '/' + id);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
