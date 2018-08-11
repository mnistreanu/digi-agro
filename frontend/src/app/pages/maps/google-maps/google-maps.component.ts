import {Component, ViewEncapsulation} from '@angular/core';

@Component({
    selector: 'app-google-maps',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './google-maps.component.html',
    styleUrls: ['./google-maps.component.scss']
})

export class GoogleMapsComponent {
    lat = 45.421530;
    lng = -75.697193;
    zoom = 7;
}
