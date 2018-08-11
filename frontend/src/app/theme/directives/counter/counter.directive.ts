import {AfterViewInit, Directive, ElementRef, Input} from '@angular/core';

@Directive({
    selector: '[counter]'
})

export class CounterDirective implements AfterViewInit {
    element: any;
    @Input() count: number;
    @Input() interval: number;
    @Input() increment: number;

    constructor(_elementRef: ElementRef) {
        this.element = jQuery(_elementRef.nativeElement);
    }

    ngAfterViewInit(): void {
        const elem = this.element;
        const increment = this.increment;
        const interval = this.interval;
        let count = this.count;

        function counter() {
            count = count + increment;
            setTimeout(() => counter(), interval * 1000);
            elem.html(count.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ','));
        }

        counter();
    }
}
