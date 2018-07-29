import {Injectable} from '@angular/core';
import {Constants} from "../common/constants";
import {Observable} from "rxjs/Rx";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {Messages} from "../common/messages";
import {HttpErrorResponse} from "@angular/common/http";
import {StorageService} from "./storage.service";

@Injectable({
    providedIn: 'root'
})
export class ErrorService {

    constructor(private router: Router,
                private storageService: StorageService,
                private toastr: ToastrService) {
    }

    processError(response: HttpErrorResponse): Observable<any> {

        let status = response.status;
        let message = Constants.SERVER_ERROR;
        if (response.error) {
            message = response.error.message;
        }

        if (status == 401 || status == 403) {
            this.toastr.error(status == 401 ? Messages.UNAUTHORIZED : Messages.FORBIDDEN);
            this.router.navigate([Constants.LOGIN_PAGE]);
        }
        else if (status == 400) {
            this.toastr.warning(message);
        }
        else {
            if (status == 404) {
                message = 'Not Found';
            }
            else if (status == 0) {
                message = 'Can\'t connect to Server';
            }
            this.setupError(status, message);
            this.router.navigate([Constants.ERROR_PAGE]);
        }

        return Observable.throwError(message);
    }

    private setupError(status, message) {
        let errorInfo = {
            status: status,
            message: message
        };
        this.storageService.setItem('error-info', JSON.stringify(errorInfo));
    }

    public getError(): Error {
        return JSON.parse(this.storageService.getItem('error-info'));
    }

    public clearError() {
        this.storageService.removeItem('error-info')
    }

}
