import {Injectable} from '@angular/core';
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";

@Injectable({
    providedIn: 'root'
})
export class ParcelService {

    private api: string = Constants.API_URL + "/parcel";

    constructor(private http: HttpClient) {
    }

    find(): Observable<any> {
        return this.http.get<any>(this.api + '/');
    }

}
