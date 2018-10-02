import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {MachineryExpenseModel} from '../../pages/expenses/machinery-expenses/form/machinery-expense.model';
import {MachineryExpensesListModel} from '../../pages/expenses/machinery-expenses/list/machinery-expenses-list.model';

@Injectable({
    providedIn: 'root'
})
export class MachineryExpenseService {

    private api: string = environment.apiUrl + '/machinery-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<MachineryExpensesListModel[]> {
        return this.http.get<MachineryExpensesListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<MachineryExpenseModel> {
        return this.http.get<MachineryExpenseModel>(this.api + '/' + id);
    }

    save(model: MachineryExpenseModel): Observable<MachineryExpenseModel> {
        return this.http.post<MachineryExpenseModel>(this.api + '/', model);
    }

    remove(model: MachineryExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
