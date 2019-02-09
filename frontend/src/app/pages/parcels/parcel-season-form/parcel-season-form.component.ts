import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelSeasonModel} from './parcel-season.model';

@Component({
    selector: 'app-parcel-season-form',
    templateUrl: './parcel-season-form.component.html',
    styleUrls: ['./parcel-season-form.component.scss']
})
export class ParcelSeasonFormComponent implements OnInit {

    @Input() parcelSeasonModel: ParcelSeasonModel;

    form: FormGroup;
    forcedValidation: boolean;

    constructor(private fb: FormBuilder) {
    }

    ngOnInit() {
        this.buildForm();
    }

    private buildForm() {
        this.form = this.fb.group({
            cropCategoryId: [this.parcelSeasonModel.cropCategoryId, Validators.required],
            cropId: [this.parcelSeasonModel.cropId, Validators.required],
            cropSubcultureId: [this.parcelSeasonModel.cropSubcultureId],
            cropVarietyId: [this.parcelSeasonModel.cropVarietyId],
            yieldGoal: [this.parcelSeasonModel.yieldGoal],
            unitOfMeasure: [this.parcelSeasonModel.unitOfMeasure],
            plantedAt: [this.parcelSeasonModel.plantedAt],
            rowsOnParcel: [this.parcelSeasonModel.rowsOnParcel],
            plantsOnRow: [this.parcelSeasonModel.plantsOnRow],
            spaceBetweenRows: [this.parcelSeasonModel.spaceBetweenRows],
            spaceBetweenPlants: [this.parcelSeasonModel.spaceBetweenPlants]
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
