import {Injectable} from "@angular/core";
import {
    HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest,
    HttpResponse
} from "@angular/common/http";
import {Observable} from "rxjs/Rx";
import {AuthService} from "./auth.service";
import {ErrorService} from "./error.service";

@Injectable()
export class GlobalHttpInterceptorService implements HttpInterceptor {

    constructor(private authService: AuthService,
                private errorService: ErrorService) {
    }

    intercept(req: HttpRequest<any>, next: HttpHandler): Observable<any> {

        req = req.clone({
            setHeaders: this.authService.createTokenHeader()
        });

        // if (req.url.indexOf('/file?path=') == -1) {
        //     req = req.clone({
        //         setHeaders: {'Content-Type': 'application/json'}
        //     });
        // }

        return next.handle(req).do(
            (event: HttpEvent<any>) => {
                if (event instanceof HttpResponse) {
                    // do stuff with response if you want
                }
            },
            (err: any) => {
                if (err instanceof HttpErrorResponse) {
                    this.errorService.processError(err);
                }
            }
        );
    }
}
