import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelModel} from '../parcel.model';

@Component({
    selector: 'app-parcel-general-form',
    templateUrl: './parcel-general-form.component.html',
    styleUrls: ['./parcel-general-form.component.scss']
})
export class ParcelGeneralFormComponent implements OnInit {

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
            name: [this.parcelModel.name, Validators.required],
            branchId: [this.parcelModel.branchId, Validators.required],
            cadasterNumber: [this.parcelModel.cadasterNumber, Validators.required],
            landWorthinessPoints: [this.parcelModel.landWorthinessPoints],
            area: [this.parcelModel.area],
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
