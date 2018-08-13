import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {Constants} from '../common/constants';
import {GeoItem} from '../interfaces/geo-item.interface';

@Injectable({
    providedIn: 'root'
})
export class GeoService {

    private api: string = Constants.API_URL + '/geo';

    constructor(private http: HttpClient) {
    }

    public getCountries(): Observable<GeoItem[]> {
        return this.http.get<GeoItem[]>(this.api + '/country');
    }

    public getCounties(country: string): Observable<GeoItem[]> {
        const query = `?country=${country}`;
        return this.http.get<GeoItem[]>(this.api + '/county' + query);
    }

    public getCities(country: string, county: string): Observable<GeoItem[]> {
        const query = `?country=${country}&county=${county}`;
        return this.http.get<GeoItem[]>(this.api + '/city' + query);
    }

}
