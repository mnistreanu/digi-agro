import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {PayloadModel} from "../pages/payload.model";

@Injectable({
    providedIn: 'root'
})
export class NotificationService {

    private api: string = Constants.API_URL + "/notification";

    constructor(private http: HttpClient) {
    }

    find(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/');
    }

    findTypes(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/types');
    }

//    see(id): Observable<void> {
//        return this.http.post<void>(this.api + '/' + id);
//    }

}
