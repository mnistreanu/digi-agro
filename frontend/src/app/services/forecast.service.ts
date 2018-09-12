import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {ForecastModel} from '../pages/forecast/forecast.model';
import {environment} from '../../environments/environment';

@Injectable({
    providedIn: 'root'
})
export class ForecastService {

    private api: string = environment.apiUrl + '/forecast';

    constructor(private http: HttpClient) {
    }

    save(forecastModel: ForecastModel): Observable<ForecastModel> {
        return this.http.post<ForecastModel>(this.api + '/', forecastModel);
    }

    public find(): Observable<ForecastModel[]> {
        return this.http.get<ForecastModel[]>(this.api + '/');
    }

    public findOne(id): Observable<ForecastModel> {
        return this.http.get<ForecastModel>(this.api + '/' + id);
    }

    public remove(id): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }

}
