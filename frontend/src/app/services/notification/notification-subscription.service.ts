import {Injectable} from '@angular/core';
import {Constants} from '../../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {NotificationSubscriptionModel} from '../../pages/notifications/subscription/notification-subscription.model';
import { environment } from '../../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class NotificationSubscriptionService {

    private api: string = environment.apiUrl + '/notification-subscription';

    constructor(private http: HttpClient) {
    }

    find(): Observable<NotificationSubscriptionModel[]> {
        return this.http.get<NotificationSubscriptionModel[]>(this.api + '/');
    }

    changeSubscription(typeId: number, subscribed: boolean): Observable<void> {
        const body = {
            typeId: typeId,
            subscribed: subscribed
        };
        return this.http.post<void>(this.api + '/', body);
    }
}
