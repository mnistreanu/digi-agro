import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PesticideExpenseModel} from '../../pages/expenses/pesticide-expenses/form/pesticide-expense.model';
import {PesticideExpensesListModel} from '../../pages/expenses/pesticide-expenses/list/pesticide-expenses-list.model';

@Injectable({
    providedIn: 'root'
})
export class PesticideExpenseService {

    private api: string = environment.apiUrl + '/pesticide-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<PesticideExpensesListModel[]> {
        return this.http.get<PesticideExpensesListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<PesticideExpenseModel> {
        return this.http.get<PesticideExpenseModel>(this.api + '/' + id);
    }

    save(model: PesticideExpenseModel): Observable<PesticideExpenseModel> {
        return this.http.post<PesticideExpenseModel>(this.api + '/', model);
    }

    remove(model: PesticideExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
