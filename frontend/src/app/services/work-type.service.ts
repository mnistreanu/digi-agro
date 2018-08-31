import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../environments/environment';
import {AgroWorkTypeModel} from '../pages/reminder/agro-work-type.model';

@Injectable({
    providedIn: 'root'
})
export class WorkTypeService {

    private api: string = environment.apiUrl + '/work-type';

    constructor(private http: HttpClient) {
    }

    find(): Observable<AgroWorkTypeModel[]> {
        return this.http.get<AgroWorkTypeModel[]>(this.api + '/');
    }
}
