import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {ForecastModel} from '../pages/forecast/forecast.model';

@Injectable({
    providedIn: 'root'
})
export class ForecastService {

    private api: string = Constants.API_URL + '/forecast';

    constructor(private http: HttpClient) {
    }

    save(forecastModel: ForecastModel): Observable<void> {
        return this.http.post<void>(this.api + '/', forecastModel);
    }

}
