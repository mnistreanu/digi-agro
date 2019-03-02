import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../../pages/payload.model';
import {environment} from '../../../environments/environment';
import {ExpenseCategoryModel} from '../../pages/enterprise/manage-expense-categories/expense-category/expense-category.model';

@Injectable({
    providedIn: 'root'
})
export class ExpenseCategoryService {

    private api: string = environment.apiUrl + '/expense-category';

    constructor(private http: HttpClient) {
    }

    getTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/tree');
    }

    getRoots(): Observable<ExpenseCategoryModel[]> {
        return this.http.get<ExpenseCategoryModel[]>(this.api + '/roots');
    }

    find(category: string): Observable<ExpenseCategoryModel[]> {
        return this.http.get<ExpenseCategoryModel[]>(this.api + '/category/' + category);
    }

    findOne(id: number): Observable<ExpenseCategoryModel> {
        return this.http.get<ExpenseCategoryModel>(this.api + '/' + id);
    }

    save(model: ExpenseCategoryModel): Observable<ExpenseCategoryModel> {
        return this.http.post<ExpenseCategoryModel>(this.api + '/', model);
    }

    remove(model: ExpenseCategoryModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
