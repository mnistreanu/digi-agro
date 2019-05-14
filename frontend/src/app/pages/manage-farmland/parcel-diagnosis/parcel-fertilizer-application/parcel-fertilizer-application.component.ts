import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelDiagnosisModel} from '../parcel-diagnosis.model';
import {ParcelSeasonModel} from '../../parcel-season.model';
import {FieldMapper} from '../../../../common/field.mapper';
import {LangService} from '../../../../services/lang.service';
import {NumericUtil} from '../../../../common/numericUtil';
import {UnitOfMeasureUtil} from '../../../../common/unit-of-measure-util';
import {DateUtil} from '../../../../common/dateUtil';

@Component({
    selector: 'app-parcel-fertilizer-application',
    templateUrl: './parcel-fertilizer-application.component.html',
    styleUrls: ['./parcel-fertilizer-application.component.scss']
})
export class ParcelFertilizerApplicationComponent implements OnInit {

    @Input() parcelDiagnosisModel: ParcelDiagnosisModel;
    @Input() parcelSeasonModel: ParcelSeasonModel;

    form: FormGroup;
    forcedValidation: boolean;

    constructor(private fb: FormBuilder,
                private langService: LangService) {
    }

    ngOnInit() {
        this.buildForm();
        this.adjustModel(this.parcelSeasonModel);
    }

    private buildForm() {
        if (this.parcelSeasonModel == null) {
            this.parcelSeasonModel = new ParcelSeasonModel();
        }

        this.form = this.fb.group({
            irrigated: [NumericUtil.formatBoolean(this.parcelSeasonModel.irrigated)]
        });
    }

    private adjustModel(model: ParcelSeasonModel) {

        const fieldMapper = new FieldMapper(this.langService.getLanguage());
        const nameField = fieldMapper.get('name');
        if (model.cropModel != null) {
            model['cropName'] = model.cropModel[nameField];
        }

        if (model.cropVarietyModel != null) {
            model['cropVarietyName'] = model.cropVarietyModel[nameField];
        }

        model['irrigatedFormatted'] =  NumericUtil.formatBoolean(model.irrigated);
        model['yieldGoalFormatted'] = UnitOfMeasureUtil.formatKgHa(model.yieldGoal);
        model['yieldActualFormatted'] = UnitOfMeasureUtil.formatKgHa(model.yieldGoal);
        model['plantedAtFormatted'] = DateUtil.formatDate(model.plantedAt);
        model['harvestDateFormatted'] = DateUtil.formatDate(model.plantedAt);
        model['notes'] = 'Un studiu realizat de Banca Mondială, arată însă că agricultura ' +
            'din Republica Moldova este ineficientă, anul trecut (2011) sectorul a înregistrat ' +
            'o productivitate scăzută, investițiile în domeniu au fost mici, iar costurile exagerate. ' +
            'Productivitatea sectorului este de 2 ori mai mică decît în media europeană';
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
