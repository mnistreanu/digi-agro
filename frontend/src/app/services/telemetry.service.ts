import {Injectable} from "@angular/core";
import {HttpClient} from "@angular/common/http";
import {ErrorService} from "./error.service";
import {AuthService} from "./auth.service";
import {Constants} from "../common/constants";
import {TelemetryModel} from "../pages/telemetry/telemetry/telemetry.model";
import {Observable} from "rxjs/Rx";

@Injectable()
export class TelemetryService {

    private api: string = Constants.API_URL + "/telemetry";

    constructor(private authService: AuthService,
                private errorService: ErrorService,
                private http: HttpClient) {
    }

    findOne(id: number): Observable<TelemetryModel> {
        return this.http.get(this.api + '/' + id, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }


    findByMachineIdentifierAndUsername(machineIdentifier, username): Observable<TelemetryModel[]> {
        let query = `?machineIdentifier=${machineIdentifier}&username=${username}`;
        return this.http.get(this.api + '/' + query, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    save(model: TelemetryModel): Observable<TelemetryModel> {
        return this.http.post(this.api + '/', model, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    remove(model: TelemetryModel): Observable<void> {
        return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }

    updateCoordinate(id, field: any, value: any): Observable<void> {
        let model = {
            id: id,
            field: field,
            value: value
        };
        return this.http.post(this.api + '/coordinates', model, this.authService.getOptions())
            .catch(error => this.errorService.processError(error));
    }
}
