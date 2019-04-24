import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {environment} from '../../../environments/environment';
import {Observable} from 'rxjs/Rx';
import {ParcelEventModel} from '../../pages/parcel-event/parcel-event-list/parcel-event.model';

@Injectable({
    providedIn: 'root'
})
export class ParcelEventService {

    private api: string = environment.apiUrl + '/parcel-event';

    constructor(private http: HttpClient) {
    }

    find(parcelId: number): Observable<ParcelEventModel[]> {
        return this.http.get<ParcelEventModel[]>(this.api + '/' + parcelId);
    }

    save(model: ParcelEventModel): Observable<ParcelEventModel> {
        return this.http.post<ParcelEventModel>(this.api + '/', model);
    }

    remove(model: ParcelEventModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }

}
