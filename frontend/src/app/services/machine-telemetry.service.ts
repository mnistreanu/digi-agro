import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {Constants} from "../common/constants";
import {Observable} from "rxjs/Rx";
import {MachineTelemetryModel} from "../pages/telemetry/machine-telemetry/machine-telemetry.model";

@Injectable()
export class MachineTelemetryService {

    private api: string = Constants.API_URL + "/machine-telemetry";

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<MachineTelemetryModel> {
        return this.http.get<MachineTelemetryModel>(this.api + '/' + id);
    }


    find(machineIdentifier): Observable<MachineTelemetryModel[]> {
        let query = `?machineIdentifier=${machineIdentifier}`;
        return this.http.get<MachineTelemetryModel[]>(this.api + '/' + query);
    }

    save(model: MachineTelemetryModel): Observable<MachineTelemetryModel> {
        return this.http.post<MachineTelemetryModel>(this.api + '/', model);
    }

    remove(model: MachineTelemetryModel): Observable<void> {
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
