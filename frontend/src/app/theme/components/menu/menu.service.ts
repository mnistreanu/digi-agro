import {Injectable} from '@angular/core';
import {AuthService} from "../../../services/auth.service";
import {Authorities} from "../../../common/authorities";
import {adminMenuItems} from "./admin-menu";
import {userMenuItems} from "./user-menu";
import {menuItems} from './menu';

@Injectable()
export class MenuService {

    constructor(private authService: AuthService) {
    }

    public getMenuItems(): Array<Object> {

        if (this.authService.hasAuthority(Authorities.ROLE_USER)) {
            return userMenuItems
        }
        else if (this.authService.hasAuthority(Authorities.ROLE_ADMIN)) {
            return adminMenuItems
        }

        return menuItems;
    }

}
