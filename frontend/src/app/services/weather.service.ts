import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../pages/payload.model';
import { environment } from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class WeatherService {

    private api: string = environment.apiUrl + '/weather';

    constructor(private http: HttpClient) {
    }

    public findWeatherHistory(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/history');
    }
}
