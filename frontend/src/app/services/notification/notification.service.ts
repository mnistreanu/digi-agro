import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {Constants} from '../../common/constants';
import {NotificationModel} from '../../pages/notifications/notification.model';

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    private api: string = Constants.API_URL + '/notification';

    constructor(private http: HttpClient) {
    }

    find(): Observable<NotificationModel[]> {
        return this.http.get<NotificationModel[]>(this.api + '/');
    }

    findAll(): Observable<NotificationModel[]> {
        return this.http.get<NotificationModel[]>(this.api + '/?all=true');
    }

    see(models: NotificationModel[]): Observable<void> {
        const ids = models.map(model => model.id);
        return this.http.post<void>(this.api + '/see', ids);
    }

}
