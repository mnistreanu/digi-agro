import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {ParcelModel} from '../pages/telemetry/parcel.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ParcelService {

    private api: string = environment.apiUrl + '/parcel';

    constructor(private http: HttpClient) {
    }

    find(): Observable<ParcelModel[]> {
        return this.http.get<ParcelModel[]>(this.api + '/');
    }

}
