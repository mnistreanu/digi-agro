import {Injectable} from '@angular/core';
import {Constants} from "../common/constants";
import {Observable} from "rxjs/Rx";
import {Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {Messages} from "../common/messages";

@Injectable({
    providedIn: 'root'
})
export class ErrorService {

    constructor(private router: Router,
                private toastr: ToastrService) {
    }

    processError(response: any): Observable<any> {

        let status = response.status;
        let message = response.error.message || Constants.SERVER_ERROR;


        if (status == 401 || status == 403) {
            this.toastr.error(status == 401 ? Messages.UNAUTHORIZED : Messages.FORBIDDEN);
            this.router.navigate([Constants.LOGIN_PAGE]);
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
        localStorage.setItem('error-info', JSON.stringify(errorInfo));
    }

    public getError(): Error {
        return JSON.parse(localStorage.getItem('error-info'));
    }

    public clearError() {
        localStorage.removeItem('error-info')
    }

}
