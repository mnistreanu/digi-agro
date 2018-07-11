import {Injectable} from "@angular/core";
import {ActivatedRouteSnapshot, CanActivate, Router, RouterStateSnapshot} from "@angular/router";
import {AuthService} from "../services/auth.service";
import {Authorities} from "../common/authorities";
import {Messages} from "../common/messages";
import {ToastrService} from "ngx-toastr";


@Injectable()
export class SuperAdminGuard implements CanActivate {

    constructor(private router: Router,
                private authService: AuthService,
                private toastr: ToastrService) {
    }

    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot) {

        if (this.authService.hasAuthority(Authorities.ROLE_SUPER_ADMIN)) {
            return true;
        }

        this.router.navigate(['/login']);

        this.toastr.error(Messages.FORBIDDEN);
        return false;
    }

}
