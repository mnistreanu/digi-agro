import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs/Rx';
import {EventTypeModel} from '../../pages/parcel-event/event-type/model/event-type.model';

@Injectable({
    providedIn: 'root'
})
export class EventTypeService {

    private api: string = environment.apiUrl + '/event-type';

    constructor(private http: HttpClient) {
    }

    getTree(): Observable<EventTypeModel[]> {
        return this.http.get<EventTypeModel[]>(this.api + '/tree');
    }

    getRoots(): Observable<EventTypeModel[]> {
        return this.http.get<EventTypeModel[]>(this.api + '/roots');
    }

    findOne(id: number): Observable<EventTypeModel> {
        return this.http.get<EventTypeModel>(this.api + '/' + id);
    }

    save(model: EventTypeModel): Observable<EventTypeModel> {
        return this.http.post<EventTypeModel>(this.api + '/', model);
    }

    remove(model: EventTypeModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
