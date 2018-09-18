import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../pages/payload.model';
import {ReminderModel} from '../pages/reminder/reminder.model';
import {environment} from '../../environments/environment';

@Injectable()
export class ReminderService {

    private api: string = environment.apiUrl + '/reminder';

    constructor(private http: HttpClient) {
    }

    find(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/');
    }

    findWorkTypes(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/work-types');
    }

    changeEventTime(id, start, end): Observable<void> {
        const body = {
            id: id,
            start: start,
            end: end
        };

        return this.http.post<void>(this.api + '/schedule', body);
    }

    save(event): Observable<ReminderModel> {

        const model = {
            id: event.id,
            workTypeId: event.workTypeId,
            title: event.title,
            description: event.description,
            starting: event.start,
            ending: event.end,
            createdBy: event.createdBy,
            tenantId: event.tenantId
        };

        return this.http.post<ReminderModel>(this.api + '/', model);
    }

    remove(id): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }

}
