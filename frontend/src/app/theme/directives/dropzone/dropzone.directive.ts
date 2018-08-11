import {Directive, ElementRef, OnInit} from '@angular/core';

@Directive({
    selector: '[dropzone]'
})

export class DropzoneUploadDirective implements OnInit {
    $el: any;

    constructor(el: ElementRef) {
        this.$el = jQuery(el.nativeElement);
    }

    ngOnInit(): void {
        const dropzone = new Dropzone(this.$el[0], {
            addRemoveLinks: true
        });
        Dropzone.autoDiscover = false;
        // Dropzone.options.myAwesomeDropzone = false;
    }

}
