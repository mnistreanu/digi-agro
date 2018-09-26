import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {ExpenseListModel} from '../../pages/expenses/expense-list/expense-list.model';
import {ExpenseModel} from '../../pages/expenses/expense/expense.model';

@Injectable({
    providedIn: 'root'
})
export class ExpenseService {

    private api: string = environment.apiUrl + '/expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<ExpenseListModel[]> {
        return this.http.get<ExpenseListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<ExpenseModel> {
        return this.http.get<ExpenseModel>(this.api + '/' + id);
    }

    save(model: ExpenseModel): Observable<ExpenseModel> {
        return this.http.post<ExpenseModel>(this.api + '/', model);
    }

    remove(model: ExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
