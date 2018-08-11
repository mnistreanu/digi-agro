import {Directive, ElementRef, Input, OnInit} from '@angular/core';
import 'skycons/skycons';

@Directive({
    selector: '[skycon]'
})

export class SkyconDirective implements OnInit {
    $el: any;
    @Input() color: string;
    @Input() weather: string;

    constructor(el: ElementRef) {
        this.$el = jQuery(el.nativeElement);
    }

    ngOnInit(): void {
        const icons = new Skycons({'color': this.color});
        icons.set(this.$el[0], this.weather);
        icons.play();
    }
}
