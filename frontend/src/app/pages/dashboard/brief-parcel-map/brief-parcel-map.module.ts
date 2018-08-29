import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {RouterModule} from '@angular/router';
import {NguiMapModule} from '@ngui/map';
import {FormsModule, ReactiveFormsModule} from '@angular/forms';
import {TranslateModule} from '@ngx-translate/core';
import {DirectivesModule} from '../../../theme/directives/directives.module';
import {BriefParcelMapComponent} from "./brief-parcel-map.component";

export const routes = [

];

@NgModule({
    imports: [
        CommonModule,
        TranslateModule,
        FormsModule,
        ReactiveFormsModule,
        DirectivesModule,
        NguiMapModule.forRoot({apiUrl: 'https://maps.google.com/maps/api/js?key=AIzaSyC_u-ujFg1xhXlOnOV0GyptTlory-KvlFY'}),
        RouterModule.forChild(routes),
    ],
    declarations: [BriefParcelMapComponent]
})
export class BriefParcelMapModule {
}
