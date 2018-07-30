import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {PayloadModel} from "../pages/payload.model";
import {AgroTaskModel} from "../pages/agro-task-calendar/agro-task.model";

@Injectable()
export class AgroTaskService {

    private api: string = Constants.API_URL + "/agro-task";

    constructor(private http: HttpClient) {
    }

    find(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/');
    }

    findWorkTypes(): Observable<PayloadModel> {
        return this.http.get<PayloadModel>(this.api + '/work-types');
    }

    changeEventTime(id, start, end): Observable<void> {
        let body = {
            id: id,
            start: start,
            end: end
        };

        return this.http.post<void>(this.api + '/schedule', body);
    }

    save(event): Observable<AgroTaskModel> {

        let model = {
            id: event.id,
            workTypeId: event.workTypeId,
            title: event.title,
            description: event.description,
            scheduledStart: event.start,
            scheduledEnd: event.end,
            createdBy: event.createdBy,
            tenantId: event.tenantId
        };

        return this.http.post<AgroTaskModel>(this.api + '/', model);
    }

    remove(id): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }

}