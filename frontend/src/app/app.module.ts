import "pace";
import {BrowserModule} from "@angular/platform-browser";
import {NgModule} from "@angular/core";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";

import {AgmCoreModule} from "@agm/core";

import {routing} from "./app.routing";
import {AppConfig} from "./app.config";

import {AppComponent} from "./app.component";
import {ErrorComponent} from "./pages/error/error.component";
import {AuthService} from "./services/auth.service";
import {ErrorService} from "./services/error.service";
import {HttpClientModule} from "@angular/common/http";

@NgModule({
  declarations: [
    AppComponent,
    ErrorComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDe_oVpi9eRSN99G4o6TwVjJbFBNr58NxE'
    }),
    routing   
  ],
  providers: [AppConfig, AuthService, ErrorService],
  bootstrap: [AppComponent]
})
export class AppModule { }
