import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {ListItem} from "../interfaces/list-item.interface";
import {AgroTaskModel} from "../pages/agrotask-calendar/agrotask.model";

@Injectable({
  providedIn: 'root'
})
export class AgroTaskService {

    private api: string = Constants.API_URL + "/agrotask";

    constructor(private http: HttpClient) {
    }

    fetchListItems(): Observable<ListItem[]> {
        return this.http.get<ListItem[]>(this.api + '/list-items');
    }

    findOne(id: number): Observable<AgroTaskModel> {
        return this.http.get<AgroTaskModel>(this.api + '/' + id);
    }

    find(): Observable<AgroTaskModel[]> {
        let filterModel = {};
        return this.http.post<AgroTaskModel[]>(this.api + '/find-by', filterModel);
    }

    save(model: AgroTaskModel): Observable<AgroTaskModel> {
        return this.http.post<AgroTaskModel>(this.api + '/', model);
    }

    remove(model: AgroTaskModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
