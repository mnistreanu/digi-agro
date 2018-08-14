import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Constants} from '../common/constants';
import {Observable} from 'rxjs/Rx';
import {MachineModel} from '../pages/manage-machines/machine/machine.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class MachineService {

    private api: string = environment.apiUrl + '/machine';

    constructor(private http: HttpClient) {
    }

    validateIdentifier(id: number, value: string): Observable<boolean> {
        const queryParams = `?id=${id}&value=${value}`;
        return this.http.get<boolean>(this.api + '/validate-identifier' + queryParams);
    }

    findOne(id: number): Observable<MachineModel> {
        return this.http.get<MachineModel>(this.api + '/' + id);
    }

    findAll(): Observable<MachineModel[]> {
        return this.http.get<MachineModel[]>(this.api + '/');
    }

    fetchIdentifiers(): Observable<string[]> {
        return this.http.get<string[]>(this.api + '/identifiers');
    }

    save(model: MachineModel): Observable<MachineModel> {
        return this.http.post<MachineModel>(this.api + '/', model);
    }

    remove(model: MachineModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
