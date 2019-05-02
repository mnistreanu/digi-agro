import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelDiagnosisModel} from '../parcel-diagnosis.model';
import {ParcelSeasonModel} from '../../parcel-season.model';

@Component({
    selector: 'app-parcel-diagnosis-summarizer',
    templateUrl: './parcel-diagnosis-summarizer.component.html',
    styleUrls: ['./parcel-diagnosis-summarizer.component.scss']
})
export class ParcelDiagnosisSummarizerComponent implements OnInit {

    @Input() parcelDiagnosisModel: ParcelDiagnosisModel;
    @Input() parcelSeasonModel: ParcelSeasonModel;

    form: FormGroup;
    forcedValidation: boolean;

    constructor(private fb: FormBuilder) {
    }

    ngOnInit() {
        this.buildForm();
    }

    private buildForm() {
        debugger;
        if (this.parcelSeasonModel == null) {
            this.parcelSeasonModel = new ParcelSeasonModel();
        }

        this.form = this.fb.group({
            // description: [this.parcelSeasonModel.description]
        });
    }

    public submit() {
        this.forcedValidation = true;

        if (!this.form.valid) {
            return false;
        }

        Object.assign(this.parcelSeasonModel, this.form.value);

        this.forcedValidation = false;
        return true;
    }

}
