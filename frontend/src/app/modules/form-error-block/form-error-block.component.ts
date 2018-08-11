import {Component, Input, OnInit} from '@angular/core';
import {AbstractControl} from '@angular/forms';

@Component({
    selector: 'app-form-error-block',
    templateUrl: './form-error-block.component.html',
    styleUrls: ['./form-error-block.component.scss']
})
export class FormErrorBlockComponent implements OnInit {

    @Input() control: AbstractControl;

    @Input() title: string;

    @Input() formSubmitted: boolean;

    constructor() {
    }

    ngOnInit() {
    }

}
