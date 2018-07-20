import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from "ngx-perfect-scrollbar";
import {DirectivesModule} from "../theme/directives/directives.module";
import {PipesModule} from "../theme/pipes/pipes.module";
import {routing} from "./pages.routing";
import {PagesComponent} from "./pages.component";
import {BlankComponent} from "./blank/blank.component";
import {MenuComponent} from "../theme/components/menu/menu.component";
import {SidebarComponent} from "../theme/components/sidebar/sidebar.component";
import {NavbarComponent} from "../theme/components/navbar/navbar.component";
import {MessagesComponent} from "../theme/components/messages/messages.component";
import {BreadcrumbComponent} from "../theme/components/breadcrumb/breadcrumb.component";
import {BackTopComponent} from "../theme/components/back-top/back-top.component";
import {SearchComponent} from "./search/search.component";
import {AdminGuard} from "../guard/admin-guard";
import {UserGuard} from "../guard/user-guard";
import {AuthGuard} from "../guard/auth-guard";
import {SuperAdminGuard} from "../guard/super-admin-guard";
import {SuperAdminOrAdminGuard} from "../guard/super-admin-or-admin-guard";
import {TenantService} from "../services/tenant.service";
import {UserProfileComponent} from "./user-profile/user-profile.component";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {FormErrorBlockModule} from "../modules/form-error-block/form-error-block.module";
import {TranslateModule} from '@ngx-translate/core';

import {AgroTaskCalendarComponent} from './agrotask-calendar/agrotask-calendar.component';
import {AgroTaskCalendarModule} from './agrotask-calendar/agrotask-calendar.module';
import {AgroTaskService} from "../services/agrotask.service";

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};


@NgModule({
    imports: [
        CommonModule,
        PerfectScrollbarModule,
        DirectivesModule,
        PipesModule,
        routing,
        FormsModule,
        ReactiveFormsModule,
        FormErrorBlockModule,
        TranslateModule,
        AgroTaskCalendarModule
    ],
    declarations: [
        PagesComponent,
        BlankComponent,
        MenuComponent,
        SidebarComponent,
        NavbarComponent,
        MessagesComponent,
        BreadcrumbComponent,
        BackTopComponent,
        SearchComponent,
        UserProfileComponent,
        AgroTaskCalendarComponent
    ],
    providers: [
        AuthGuard,
        SuperAdminGuard,
        SuperAdminOrAdminGuard,
        AdminGuard,
        UserGuard,
        TenantService,
        AgroTaskService,
        {provide: PERFECT_SCROLLBAR_CONFIG, useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG}
    ]
})
export class PagesModule {
}
