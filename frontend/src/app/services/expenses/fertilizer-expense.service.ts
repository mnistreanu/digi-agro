import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {FertilizerExpenseModel} from '../../pages/expenses/fertilizer-expenses/form/fertilizer-expense.model';
import {FertilizerExpensesListModel} from '../../pages/expenses/fertilizer-expenses/list/fertilizer-expenses-list.model';

@Injectable({
    providedIn: 'root'
})
export class FertilizerExpenseService {

    private api: string = environment.apiUrl + '/fertilizer-expense';

    constructor(private http: HttpClient) {
    }

    find(): Observable<FertilizerExpensesListModel[]> {
        return this.http.get<FertilizerExpensesListModel[]>(this.api + '/');
    }

    findOne(id: number): Observable<FertilizerExpenseModel> {
        return this.http.get<FertilizerExpenseModel>(this.api + '/' + id);
    }

    save(model: FertilizerExpenseModel): Observable<FertilizerExpenseModel> {
        return this.http.post<FertilizerExpenseModel>(this.api + '/', model);
    }

    remove(model: FertilizerExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
