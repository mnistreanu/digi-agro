import {NgModule} from "@angular/core";
import {CommonModule} from "@angular/common";
import {PERFECT_SCROLLBAR_CONFIG, PerfectScrollbarConfigInterface, PerfectScrollbarModule} from "ngx-perfect-scrollbar";
import {ToastrModule} from "ngx-toastr";
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

const DEFAULT_PERFECT_SCROLLBAR_CONFIG: PerfectScrollbarConfigInterface = {
    suppressScrollX: true
};


@NgModule({
    imports: [
        CommonModule,
        PerfectScrollbarModule,
        DirectivesModule,
        PipesModule,
        routing
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
        SearchComponent
    ],
    providers: [
        AuthGuard,
        AdminGuard,
        UserGuard,
        {provide: PERFECT_SCROLLBAR_CONFIG, useValue: DEFAULT_PERFECT_SCROLLBAR_CONFIG}
    ]
})
export class PagesModule {
}
