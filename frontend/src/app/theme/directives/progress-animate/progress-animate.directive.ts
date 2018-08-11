import {Directive, ElementRef, OnInit} from '@angular/core';

@Directive({
    selector: '[progressAnimate]'
})

export class ProgressAnimateDirective implements OnInit {
    public element: any;

    constructor(private _elementRef: ElementRef) {
        this.element = jQuery(_elementRef.nativeElement);
    }

    ngOnInit(): void {
        const elem = this.element;
        const timeout = 0;
        const increment = 1;
        const maxprogress = elem.attr('aria-valuenow');
        let progress = 0;

        function animate() {
            setTimeout(() => {
                progress += increment;
                if (progress < maxprogress) {
                    elem.css('width', progress + '%');
                    animate();
                }
            }, timeout);
        }

        animate();
    }

}
