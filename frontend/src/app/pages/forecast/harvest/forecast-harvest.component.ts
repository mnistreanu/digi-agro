import {Component, ViewEncapsulation, OnInit, NgModule} from '@angular/core';
import { FormControl, FormBuilder, FormGroup, Validators } from '@angular/forms';
import { MyForm } from './forecast-harvest.interface';

@Component({
    selector: 'az-forecast-harvest',
    encapsulation: ViewEncapsulation.None,
    templateUrl: './forecast-harvest.component.html'
})

export class ForecastHarvestComponent implements OnInit  {
    myForm: FormGroup;

    constructor(private formBuilder: FormBuilder) { }

    ngOnInit() {
        this.myForm = this.formBuilder.group({
            simple: ['', Validators.required],
            minLength: ['', Validators.compose([Validators.required,  Validators.minLength(3)])],
            maxLength:  ['', Validators.compose([Validators.required,  Validators.maxLength(10)])]
        });
    }

    onSubmit({ value, valid }: { value: MyForm, valid: boolean }) {
        console.log(value, valid);
    }

}
