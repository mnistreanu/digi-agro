import {Injectable} from "@angular/core";
import {Constants} from "../common/constants";
import {AuthService} from "./auth.service";
import {ErrorService} from "./error.service";
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {OwnerModel} from "../pages/manage-owners/owner/owner.model";

@Injectable({
  providedIn: 'root'
})
export class OwnerService {

  private api: string = Constants.API_URL + "/owner";

  constructor(private authService: AuthService,
              private errorService: ErrorService,
              private http: HttpClient) {
  }

  checkNameUnique(id: number, name: string): Observable<boolean> {
    let queryParams = `?id=${id}&name=${name}`;
    return this.http.get(this.api + '/checkNameUnique' + queryParams, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findOne(id: number): Observable<OwnerModel> {
    return this.http.get(this.api + '/' + id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findAll(): Observable<OwnerModel[]> {
    return this.http.get(this.api + '/', this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  save(model: OwnerModel): Observable<OwnerModel> {
    return this.http.post(this.api + '/', model, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  remove(model: OwnerModel): Observable<void> {
    return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }
}
