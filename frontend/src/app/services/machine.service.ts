import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {MachineModel} from '../pages/enterprise/manage-machines/machine/machine.model';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class MachineService {

    private api: string = environment.apiUrl + '/machine';

    constructor(private http: HttpClient) {
    }

    validateIdentifier(id: number, value: string): Observable<boolean> {
        let queryParams = `?field=identifier&value=${value}`;
        if (id) {
            queryParams += `&id=${id}`;
        }
        return this.http.get<boolean>(this.api + '/unique' + queryParams);
    }

    findOne(id: number): Observable<MachineModel> {
        return this.http.get<MachineModel>(this.api + '/' + id);
    }

    findAll(): Observable<MachineModel[]> {
        return this.http.get<MachineModel[]>(this.api + '/');
    }

    findByGroup(machineGroupId: number) {
        const queryParams = machineGroupId == null ? '' : '?machineGroupId=' + machineGroupId;
        return this.http.get<MachineModel[]>(this.api + '/' + queryParams);
    }

    save(model: MachineModel): Observable<MachineModel> {
        return this.http.post<MachineModel>(this.api + '/', model);
    }

    remove(model: MachineModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
