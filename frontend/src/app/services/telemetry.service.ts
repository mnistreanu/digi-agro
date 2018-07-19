import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Constants} from "../common/constants";
import {TelemetryModel} from "../pages/telemetry/telemetry/telemetry.model";
import {Observable} from "rxjs/Rx";

@Injectable()
export class TelemetryService {

    private api: string = Constants.API_URL + "/telemetry";

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<TelemetryModel> {
        return this.http.get<TelemetryModel>(this.api + '/' + id);
    }


    findByMachineIdentifierAndUsername(machineIdentifier, username): Observable<TelemetryModel[]> {
        let query = `?machineIdentifier=${machineIdentifier}&username=${username}`;
        return this.http.get<TelemetryModel[]>(this.api + '/' + query);
    }

    save(model: TelemetryModel): Observable<TelemetryModel> {
        return this.http.post<TelemetryModel>(this.api + '/', model);
    }

    remove(model: TelemetryModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }

    updateCoordinate(id, field: any, value: any): Observable<void> {
        let model = {
            id: id,
            field: field,
            value: value
        };
        return this.http.post<void>(this.api + '/coordinates', model);
    }
}
