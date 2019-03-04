import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ExpenseModel} from '../../pages/expenses/models/expense.model';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ExpenseService {

    private api: string = environment.apiUrl + '/expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<ExpenseModel[]> {
        return this.http.get<ExpenseModel[]>(this.api + '/');
    }

    save(model: ExpenseModel): Observable<ExpenseModel> {
        return this.http.post<ExpenseModel>(this.api + '/', model);
    }

    remove(model: ExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
