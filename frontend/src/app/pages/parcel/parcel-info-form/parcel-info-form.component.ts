import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelModel} from '../../telemetry/parcel.model';

@Component({
    selector: 'app-parcel-info-form',
    templateUrl: './parcel-info-form.component.html',
    styleUrls: ['./parcel-info-form.component.scss']
})
export class ParcelInfoFormComponent implements OnInit {

    @Input() parcelModel: ParcelModel;

    form: FormGroup;
    submitted: boolean;

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


}
