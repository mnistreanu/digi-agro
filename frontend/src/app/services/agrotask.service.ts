import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {PayloadModel} from "../pages/payload.model";

@Injectable()
export class AgroTaskService {

    private api: string = Constants.API_URL + "/agroTasks";

    constructor(private http: HttpClient) {
    }

    find(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/');
    }

    findWorkTypes(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/workTypes');
    }
}
