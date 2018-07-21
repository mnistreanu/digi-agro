import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {AgroTaskModel} from "../pages/agrotask-calendar/agrotask.model";
import {PayloadModel} from "../pages/agrotask-calendar/payload.model";

@Injectable()
export class AgroTaskService {

    private api: string = Constants.API_URL + "/agroTasks";

    constructor(private http: HttpClient) {
    }

    find(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/');
    }
}
