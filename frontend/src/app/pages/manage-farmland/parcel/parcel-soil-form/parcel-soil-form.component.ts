import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelModel} from '../parcel.model';

@Component({
    selector: 'app-parcel-soil-form',
    templateUrl: './parcel-soil-form.component.html',
    styleUrls: ['./parcel-soil-form.component.scss']
})
export class ParcelSoilFormComponent implements OnInit {

    @Input() parcelModel: ParcelModel;

    form: FormGroup;
    forcedValidation: boolean;

    constructor(private fb: FormBuilder) {
    }

    ngOnInit() {
        this.buildForm();
    }

    private buildForm() {
        this.form = this.fb.group({
            landWorthinessPoints: [this.parcelModel.landWorthinessPoints],
            area: [this.parcelModel.area, Validators.required],
            irrigated: [this.parcelModel.irrigated],
            description: [this.parcelModel.description]
        });
    }

    public submit() {
        this.forcedValidation = true;

        if (!this.form.valid) {
            return false;
        }

        Object.assign(this.parcelModel, this.form.value);

        this.forcedValidation = false;
        return true;
    }

}
