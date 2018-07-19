import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {MapEventModel} from "../pages/telemetry/map-events/map-event.model";
import {Observable} from "rxjs/Rx";
import {HttpClient} from "@angular/common/http";

@Injectable({
  providedIn: 'root'
})
export class MapEventService {

  private api: string = Constants.API_URL + "/map-event";

  constructor(private http: HttpClient) {
  }

  findByMachineIdentifierAndUsername(machineIdentifier, username): Observable<MapEventModel[]> {
    let query = `?machineIdentifier=${machineIdentifier}&username=${username}`;
    return this.http.get<MapEventModel[]>(this.api + '/' + query);
  }

  save(model: MapEventModel): Observable<MapEventModel> {
    return this.http.post<MapEventModel>(this.api + '/', model);
  }

  remove(model: MapEventModel): Observable<void> {
    return this.http.delete<void>(this.api + '/' + model.id);
  }

  update(id, field: any, value: any): Observable<void> {
    let updateModel = {
      id: id,
      field: field,
      value: value
    };
    return this.http.post<void>(this.api + '/update', JSON.stringify(updateModel));
  }
}
