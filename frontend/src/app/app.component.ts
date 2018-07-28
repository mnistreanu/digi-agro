import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {AuthService} from "./services/auth/auth.service";
import {LangService} from "./services/lang.service";

@Component({
    selector: 'az-root',
    encapsulation: ViewEncapsulation.None,
    template: `<router-outlet></router-outlet>`,
    styleUrls: ['./app.component.scss']
})

export class AppComponent implements OnInit {

    constructor(private authService: AuthService,
                private langService: LangService) {
    }

    ngOnInit(): void {
        this.authService.checkAuth();
        this.langService.restoreLanguage();
    }

}
