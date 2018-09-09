import {Injectable} from '@angular/core';
import {Constants} from '../common/constants';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {PayloadModel} from '../pages/payload.model';
import { environment } from '../../environments/environment';
import {DateUtil} from "../common/dateUtil";

@Injectable({
    providedIn: 'root'
})
export class WeatherService {

    private api: string = environment.apiUrl + '/weather';

    constructor(private http: HttpClient) {
    }

    /**
     * Date format "yyyyMMdd"
     * @param locationId
     * @param dateFrom
     * @param dateTo
     * @returns {Observable<Object>}
     */
    public findWeatherHistory(locationId:number, dateFrom:Date, dateTo:Date): Observable<PayloadModel> {
        let params = '?locationId='+locationId+
            '&dateFrom=' + DateUtil.formatDateDB(dateFrom) +
            '&dateTo=' + DateUtil.formatDateDB(dateTo);
        return this.http.get<PayloadModel>(this.api + '/history_interval' + params );
    }
}
