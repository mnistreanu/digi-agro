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

    public getTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/tree');
    }


    findOneCategory(id: number): Observable<ExpenseCategoryModel> {
        return this.http.get<ExpenseCategoryModel>(this.api + '/category/' + id);
    }

    saveCategory(model: ExpenseCategoryModel): Observable<ExpenseCategoryModel> {
        return this.http.post<ExpenseCategoryModel>(this.api + '/category/', model);
    }

    removeCategory(model: ExpenseCategoryModel): Observable<void> {
        return this.http.delete<void>(this.api + '/category/' + model.id);
    }
}
