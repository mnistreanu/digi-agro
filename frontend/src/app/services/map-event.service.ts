import { Injectable } from '@angular/core';
import {Constants} from "../common/constants";
import {AuthService} from "./auth.service";
import {MapEventModel} from "../pages/telemetry/map-events/map-event.model";
import {Observable} from "rxjs/Rx";
import {HttpClient} from "@angular/common/http";
import {ErrorService} from "./error.service";

@Injectable({
  providedIn: 'root'
})
export class MapEventService {

  private api: string = Constants.API_URL + "/map-event";

  constructor(private authService: AuthService,
              private errorService: ErrorService,
              private http: HttpClient) {
  }

  findByMachineIdentifierAndUsername(machineIdentifier, username): Observable<MapEventModel[]> {
    let query = `?machineIdentifier=${machineIdentifier}&username=${username}`;
    return this.http.get(this.api + '/findByMachineIdentifierAndUsername' + query, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  save(model: MapEventModel): Observable<MapEventModel> {
    return this.http.post(this.api + '/', model, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  remove(model: MapEventModel): Observable<void> {
    return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  update(id, field: any, value: any): Observable<void> {
    let updateModel = {
      id: id,
      field: field,
      value: value
    };
    return this.http.post(this.api + '/update', JSON.stringify(updateModel),this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }
}
