import {Injectable} from '@angular/core';
import {Observable} from 'rxjs/Rx';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {MachineGroupModel} from '../../pages/enterprise/machine-groups/machine-group/machine-group.model';

@Injectable({
    providedIn: 'root'
})
export class MachineGroupService {

    private api: string = environment.apiUrl + '/machine-group';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<MachineGroupModel> {
        return this.http.get<MachineGroupModel>(this.api + '/' + id);
    }

    findAll(): Observable<MachineGroupModel[]> {
        return this.http.get<MachineGroupModel[]>(this.api + '/');
    }

    save(model: MachineGroupModel): Observable<MachineGroupModel> {
        return this.http.post<MachineGroupModel>(this.api + '/', model);
    }

    remove(model: MachineGroupModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
