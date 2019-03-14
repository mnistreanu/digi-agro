import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ExpenseModel} from '../../pages/expenses/models/expense.model';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {ExpenseSeasonTreeModel} from '../../pages/expenses/expense-season-list/expense-season-tree.model';

@Injectable({
    providedIn: 'root'
})
export class ExpenseService {

    private api: string = environment.apiUrl + '/expense';

    constructor(private http: HttpClient) {
    }

    getExpenseSeasonTreeModels(): Observable<ExpenseSeasonTreeModel[]> {
        return this.http.get<ExpenseSeasonTreeModel[]>(this.api + '/season-tree');
    }

    find(cropSeasonId): Observable<ExpenseModel[]> {
        return this.http.get<ExpenseModel[]>(this.api + '/' + cropSeasonId);
    }

    save(model: ExpenseModel): Observable<ExpenseModel> {
        return this.http.post<ExpenseModel>(this.api + '/', model);
    }

    remove(model: ExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
