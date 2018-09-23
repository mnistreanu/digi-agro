import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../../pages/payload.model';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ExpensesService {

    private api: string = environment.apiUrl + '/expenses';

    constructor(private http: HttpClient) {
    }

    public findCategoriesTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/categories-tree');
    }

}
