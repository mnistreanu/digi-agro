import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import { AppState } from '../../../app.state';
import { SidebarService } from '../sidebar/sidebar.service';
import {AuthService} from "../../../services/auth/auth.service";
import {Constants} from "../../../common/constants";

@Component({
  selector: 'az-navbar',
  encapsulation: ViewEncapsulation.None,
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss'],
  providers: [ SidebarService ]
})

export class NavbarComponent implements OnInit {
    public isMenuCollapsed:boolean = false;

    username: string;
    logoUrl: string;

    constructor(private authService: AuthService,
                private _state:AppState,
                private _sidebarService:SidebarService) {
    }

    ngOnInit(): void {
        this._state.subscribe('menu.isCollapsed', (isCollapsed) => {
            this.isMenuCollapsed = isCollapsed;
        });

        this.authService.userChanged.subscribe(() => {
            this.setupUserData();
        });

        this.setupUserData();
    }

    private setupUserData() {
        let userData = this.authService.getUserData();
        if (userData == null) {
            return;
        }
        this.username = userData.username;
        this.logoUrl = Constants.API_URL + userData.logoUrl;
    }

    public closeSubMenus(){
       /* when using <az-sidebar> instead of <az-menu> uncomment this line */
      // this._sidebarService.closeAllSubMenus();
    }

    public toggleMenu() {
        this.isMenuCollapsed = !this.isMenuCollapsed; 
        this._state.notifyDataChanged('menu.isCollapsed', this.isMenuCollapsed);        
    }

}
