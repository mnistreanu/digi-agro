import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {FuelExpenseModel} from '../../pages/manage-expenses/fuel-expenses/form/fuel-expense.model';
import {ExpenseItemModel} from '../../pages/manage-expenses/fuel-expenses/expense-item-table/expense-item.model';
import {FuelExpensesListModel} from '../../pages/manage-expenses/fuel-expenses/list/fuel-expenses-list.model';

@Injectable({
    providedIn: 'root'
})
export class FuelExpenseService {

    private api: string = environment.apiUrl + '/fuel-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<FuelExpensesListModel[]> {
        return this.http.get<FuelExpensesListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<FuelExpenseModel> {
        return this.http.get<FuelExpenseModel>(this.api + '/' + id);
    }

    save(model: FuelExpenseModel): Observable<FuelExpenseModel> {
        return this.http.post<FuelExpenseModel>(this.api + '/', model);
    }

    remove(model: FuelExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
