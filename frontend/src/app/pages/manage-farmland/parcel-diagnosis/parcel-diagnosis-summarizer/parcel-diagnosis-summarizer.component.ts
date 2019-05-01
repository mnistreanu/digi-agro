import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelDiagnosisModel} from '../parcel-diagnosis.model';

@Component({
    selector: 'app-parcel-diagnosis-summarizer',
    templateUrl: './parcel-diagnosis-summarizer.component.html',
    styleUrls: ['./parcel-diagnosis-summarizer.component.scss']
})
export class ParcelDiagnosisSummarizerComponent implements OnInit {

    @Input() parcelDiagnosisModel: ParcelDiagnosisModel;

    form: FormGroup;
    forcedValidation: boolean;

    constructor(private fb: FormBuilder) {
    }

    ngOnInit() {
        this.buildForm();
    }

    private buildForm() {
        this.form = this.fb.group({
            landWorthinessPoints: [this.parcelDiagnosisModel.landWorthinessPoints],
            area: [this.parcelDiagnosisModel.area, Validators.required],
            irrigated: [this.parcelDiagnosisModel.irrigated],
            description: [this.parcelDiagnosisModel.description]
        });
    }

    public submit() {
        this.forcedValidation = true;

        if (!this.form.valid) {
            return false;
        }

        Object.assign(this.parcelDiagnosisModel, this.form.value);

        this.forcedValidation = false;
        return true;
    }

}
