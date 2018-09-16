import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {MachineryExpenseModel} from '../pages/manage-expenses/machinery-expenses/machinery-expenses-form/machinery-expense.model';
import {ExpenseItemModel} from '../pages/manage-expenses/machinery-expenses/expense-item-table/expense-item.model';

@Injectable({
    providedIn: 'root'
})
export class MachineryExpenseService {

    private api: string = environment.apiUrl + '/machinery-expense';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<MachineryExpenseModel> {
        // return this.http.get<MachineryExpenseModel>(this.api + '/' + id);

        // todo: remove, this is only for testing...
        return Observable.create(observer => {
            setTimeout(() => {
                const testModel = new MachineryExpenseModel();
                testModel.expenseTitle = 'Reparatie machinelor';
                testModel.expenseDate = new Date();
                testModel.machines = [1];
                testModel.employees = [1];
                testModel.expenseItems = [<ExpenseItemModel> {title: 'Ambreaj la cutia de viteze', cost: 3400}];

                observer.next(testModel);
                observer.complete();
            }, 500);
        });

    }
}
