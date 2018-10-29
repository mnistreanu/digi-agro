import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import { environment } from '../../../environments/environment';
import { CropVarietyDTO } from '../../dto/crop/crop-variety.dto'
import { SelectItem } from '../../dto/select-item.dto';
import { CropVarietyModel } from '../../pages/manage-crops/crop-variety/crop-variety.model';

@Injectable({
    providedIn: 'root'
})
export class CropVarietyService {

    private api: string = environment.apiUrl + '/crop_varieties';

    constructor(private http: HttpClient) {
    }

    public findCropItems(): Observable<SelectItem[]> {
        return this.http.get<SelectItem[]>(this.api + '/crops/select_items');
    }
    public findAll(page: number, size: number, filters: Map<string, string>, order: string): Observable<CropVarietyDTO[]> {
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

        return this.http.get<CropVarietyDTO[]>( url );
    }

    create(model: FormData): Observable<CropVarietyModel> {
        return this.http.post<CropVarietyModel>(this.api, model);
    }

    update(id:number, model: FormData): Observable<CropVarietyModel> {
        return this.http.put<CropVarietyModel>(this.api + '/' + id, model);
    }

    findOne(id: number): Observable<CropVarietyModel> {
        return this.http.get<CropVarietyModel>(this.api + '/' + id);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
