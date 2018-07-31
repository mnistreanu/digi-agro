import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {PayloadModel} from "../../pages/payload.model";
import {Constants} from "../../common/constants";

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

    findAll(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/all');
    }

//    see(id): Observable<void> {
//        return this.http.post<void>(this.api + '/' + id);
//    }

}
