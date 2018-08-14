import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {ForecastModel} from '../pages/forecast/forecast.model';
import { environment } from '../../environments/environment';
import {PayloadModel} from "../pages/payload.model";

@Injectable({
    providedIn: 'root'
})
export class ForecastService {

    private api: string = environment.apiUrl + '/forecast';

    constructor(private http: HttpClient) {
    }

    save(forecastModel: ForecastModel): Observable<void> {
        return this.http.post<void>(this.api + '/', forecastModel);
    }

    public findForecasts(tenantId: number): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/active-forecasts?tenantId=1');
    }


}
