import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../pages/payload.model';
import { environment } from '../../environments/environment';
import {DateUtil} from '../common/dateUtil';

@Injectable({
    providedIn: 'root'
})
export class WeatherService {

    private api: string = environment.apiUrl + '/weather';

    constructor(private http: HttpClient) {
    }

    /**
     * Date format "yyyyMMdd"
     * @param countryCode
     * @param countyCode
     * @returns {Observable<Object>}
     */
    public findWeatherForecast(countryCode: string, countyCode: string): Observable<PayloadModel> {
        const params = '?country=' + countryCode + '&county='  + countyCode;
        return this.http.get<PayloadModel>(this.api + '/forecast' + params );
    }

    /**
     * Date format "yyyyMMdd"
     * @param countryCode
     * @param countyCode
     * @param dateFrom
     * @param dateTo
     * @returns {Observable<Object>}
     */
    public findWeatherHistory(countryCode: string, countyCode: string, dateFrom: Date, dateTo: Date): Observable<PayloadModel> {
        const params = '?country=' + countryCode + '&county='  + countyCode +
            '&dateFrom=' + DateUtil.formatDateDB(dateFrom) +
            '&dateTo=' + DateUtil.formatDateDB(dateTo);
        return this.http.get<PayloadModel>(this.api + '/history_interval' + params );
    }
}
