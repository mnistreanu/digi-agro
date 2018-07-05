import {Injectable} from "@angular/core";
import {ErrorService} from "./error.service";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Constants} from "../common/constants";
import {Observable} from "rxjs/Rx";
import {MachineModel} from "../pages/manage-machines/machine/machine.model";

@Injectable({
  providedIn: 'root'
})
export class MachineService {

  private api: string = Constants.API_URL + "/machine";

  constructor(private authService: AuthService,
              private errorService: ErrorService,
              private http: HttpClient) {
  }

  checkIdentifierUnique(id: number, value: string): Observable<boolean> {
    let queryParams = `?id=${id}&value=${value}`;
    return this.http.get(this.api + '/checkIdentifierUnique' + queryParams, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findOne(id: number): Observable<MachineModel> {
    return this.http.get(this.api + '/' + id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findAll(): Observable<MachineModel[]> {
    return this.http.get(this.api + '/', this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  fetchIdentifiers(): Observable<string[]> {
    return this.http.get(this.api + '/fetchIdentifiers', this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  save(model: MachineModel): Observable<MachineModel> {
    return this.http.post(this.api + '/', model, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  remove(model: MachineModel): Observable<void> {
    return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }
}
