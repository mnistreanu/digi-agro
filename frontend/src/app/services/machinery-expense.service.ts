import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {MachineryExpenseModel} from '../pages/manage-expenses/machinery-expenses/machinery-expenses-form/machinery-expense.model';
import {ExpenseItemModel} from '../pages/manage-expenses/machinery-expenses/expense-item-table/expense-item.model';
import {MachineryExpenseListModel} from '../pages/manage-expenses/machinery-expenses/machinery-expense-list.model';

@Injectable({
    providedIn: 'root'
})
export class MachineryExpenseService {

    private api: string = environment.apiUrl + '/machinery-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<MachineryExpenseListModel[]> {
        return this.http.get<MachineryExpenseListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<MachineryExpenseModel> {
        return this.http.get<MachineryExpenseModel>(this.api + '/' + id);
    }

    save(model: MachineryExpenseModel): Observable<MachineryExpenseModel> {
        return this.http.post<MachineryExpenseModel>(this.api + '/', model);
    }
}
