import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelModel} from '../../telemetry/parcel.model';

@Component({
    selector: 'app-parcel-crop-form',
    templateUrl: './parcel-crop-form.component.html',
    styleUrls: ['./parcel-crop-form.component.scss']
})
export class ParcelCropFormComponent implements OnInit {

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
            cadasterNumber: [this.parcelModel.cadasterNumber, Validators.required],
            landWorthinessPoints: [this.parcelModel.landWorthinessPoints],
            area: [this.parcelModel.area],
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
