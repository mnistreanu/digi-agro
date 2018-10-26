import {Injectable} from '@angular/core';
import {environment} from '../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {EmployeeModel} from '../pages/enterprise/manage-employees/employee/employee.model';

@Injectable({
    providedIn: 'root'
})
export class EmployeeService {

    private api: string = environment.apiUrl + '/employee';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<EmployeeModel> {
        return this.http.get<EmployeeModel>(this.api + '/' + id);
    }

    findAll(): Observable<EmployeeModel[]> {
        return this.http.get<EmployeeModel[]>(this.api + '/');
    }

    save(model: EmployeeModel): Observable<EmployeeModel> {
        return this.http.post<EmployeeModel>(this.api + '/', model);
    }

    remove(model: EmployeeModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
