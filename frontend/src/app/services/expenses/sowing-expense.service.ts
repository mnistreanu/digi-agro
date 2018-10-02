import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {SowingExpenseModel} from '../../pages/expenses/sowing-expenses/form/sowing-expense.model';
import {SowingExpensesListModel} from '../../pages/expenses/sowing-expenses/list/sowing-expenses-list.model';

@Injectable({
    providedIn: 'root'
})
export class SowingExpenseService {

    private api: string = environment.apiUrl + '/sowing-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<SowingExpensesListModel[]> {
        return this.http.get<SowingExpensesListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<SowingExpenseModel> {
        return this.http.get<SowingExpenseModel>(this.api + '/' + id);
    }

    save(model: SowingExpenseModel): Observable<SowingExpenseModel> {
        return this.http.post<SowingExpenseModel>(this.api + '/', model);
    }

    remove(model: SowingExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
