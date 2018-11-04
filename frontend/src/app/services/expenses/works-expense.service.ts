import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {WorksExpenseModel} from '../../pages/expenses/works-expenses/form/works-expense.model';
import {WorksExpensesListModel} from '../../pages/expenses/works-expenses/list/works-expenses-list.model';

@Injectable({
    providedIn: 'root'
})
export class WorksExpenseService {

    private api: string = environment.apiUrl + '/works-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<WorksExpensesListModel[]> {
        return this.http.get<WorksExpensesListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<WorksExpenseModel> {
        return this.http.get<WorksExpenseModel>(this.api + '/' + id);
    }

    save(model: WorksExpenseModel): Observable<WorksExpenseModel> {
        return this.http.post<WorksExpenseModel>(this.api + '/', model);
    }

    remove(model: WorksExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.expenseId);
    }
}
