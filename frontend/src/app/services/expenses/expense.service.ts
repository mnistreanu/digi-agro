import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {ExpenseModel} from '../../pages/expenses/models/expense.model';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {ExpenseSeasonTreeModel} from '../../pages/expenses/expense-season-list/expense-season-tree.model';
import {ExpenseSummaryModel} from '../../pages/expenses/models/expense-summary.model';

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

    fetchSummaryModels(cropSeasonId): Observable<ExpenseSummaryModel[]> {
        return this.http.get<ExpenseSummaryModel[]>(this.api + '/summary/' + cropSeasonId);
    }

    convertSummaryModels(expenses: ExpenseModel[]): ExpenseSummaryModel[] {
        let models = [];

        const rootMap = {};
        expenses.forEach((expenseModel: ExpenseModel) => {
            let model = rootMap[expenseModel.categoryId];
            if (!model) {
                model = new ExpenseSummaryModel();
                rootMap[expenseModel.categoryId] = model;
                models.push(model);

                model.categoryName = expenseModel.categoryName;
                model.cost = 0;
            }

            model.cost += expenseModel.cost || 0;
        });

        if (models.length === 0) {
            models = null;
        }

        return models;
    }

    save(model: ExpenseModel): Observable<ExpenseModel> {
        return this.http.post<ExpenseModel>(this.api + '/', model);
    }

    remove(model: ExpenseModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
