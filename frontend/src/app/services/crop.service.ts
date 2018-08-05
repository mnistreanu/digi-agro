import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {PayloadModel} from "../pages/payload.model";

@Injectable({
    providedIn: 'root'
})
export class CropService {

    private api: string = Constants.API_URL + "/crop";

    constructor(private http: HttpClient) {
    }

    public findCategories(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/categories');
    }

    public findCrops(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/crops');
    }

    public findVarieties(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/varieties');
    }


}
