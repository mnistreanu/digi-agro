import { Injectable } from '@angular/core';
import {Observable} from "rxjs/Rx";
import {HttpClient} from "@angular/common/http";
import {AuthService} from "./auth.service";
import {Constants} from "../common/constants";
import {BrandModel} from "../pages/manage-brands/brand/brand.model";
import {ErrorService} from "./error.service";

@Injectable()
export class BrandService {

  private api: string = Constants.API_URL + "/brand";

  constructor(private authService: AuthService,
              private errorService: ErrorService,
              private http: HttpClient) {
  }

  checkNameUnique(id: number, name: string): Observable<boolean> {
    let queryParams = `?id=${id}&name=${name}`;
    return this.http.get(this.api + '/checkNameUnique' + queryParams, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findOne(id: number): Observable<BrandModel> {
    return this.http.get(this.api + '/' + id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  findAll(): Observable<BrandModel[]> {
    return this.http.get(this.api + '/', this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  save(model: BrandModel): Observable<BrandModel> {
    return this.http.post(this.api + '/', model, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }

  remove(model: BrandModel): Observable<void> {
    return this.http.delete(this.api + '/' + model.id, this.authService.getOptions())
        .catch(error => this.errorService.processError(error));
  }
  
}
