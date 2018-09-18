import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../environments/environment';
import {PayloadModel} from '../pages/payload.model';

@Injectable({
    providedIn: 'root'
})
export class AgroWorksService {

    private api: string = environment.apiUrl + '/agroworks';

    constructor(private http: HttpClient) {
    }

    public findCropsTree(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/others-tree');
    }

}
