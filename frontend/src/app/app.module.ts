import "pace";
import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {AgmCoreModule} from "@agm/core";

import {routing} from "./app.routing";
import {AppConfig} from "./app.config";

import {TranslateLoader, TranslateModule} from "@ngx-translate/core";
import {TranslateHttpLoader} from "@ngx-translate/http-loader";

import {AppComponent} from "./app.component";
import {ErrorComponent} from "./pages/error/error.component";
import {AuthService} from "./services/auth.service";
import {ErrorService} from "./services/error.service";
import {HttpClient, HttpClientModule} from "@angular/common/http";
import {UserService} from "./services/user.service";
import {ToastrModule} from "ngx-toastr";
import {LangService} from "./services/lang.service";
import { ManageCalendarComponent } from './pages/manage-calendar/manage-calendar.component';

@NgModule({
    declarations: [
        AppComponent,
        ErrorComponent,
        ManageCalendarComponent
    ],
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        ToastrModule.forRoot({positionClass: 'toast-bottom-right', closeButton: true}),
        AgmCoreModule.forRoot({
            apiKey: 'AIzaSyDe_oVpi9eRSN99G4o6TwVjJbFBNr58NxE'
        }),
        TranslateModule.forRoot({
            loader: {
                provide: TranslateLoader,
                useFactory: HttpLoaderFactory,
                deps: [HttpClient]
            }
        }),
        routing
    ],
    providers: [AppConfig, AuthService, ErrorService, UserService, LangService],
    bootstrap: [AppComponent]
})
export class AppModule {
}

// required for AOT compilation
export function HttpLoaderFactory(http: HttpClient) {
    return new TranslateHttpLoader(http);
}
