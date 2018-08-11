import {Component, OnInit, ViewEncapsulation} from '@angular/core';

@Component({
    selector: 'app-components',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './components.component.html'
})
export class ComponentsComponent implements OnInit {
    ngOnInit(): void {
        jQuery('[data-toggle="tooltip"]').tooltip();
        jQuery('[data-toggle="popover"]').popover();
    }
}
