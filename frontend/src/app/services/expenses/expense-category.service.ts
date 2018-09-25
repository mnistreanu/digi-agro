import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../../pages/payload.model';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ExpenseCategoryService {

    private api: string = environment.apiUrl + '/expense-category';

    constructor(private http: HttpClient) {
    }

    public getTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/tree');
    }

}
