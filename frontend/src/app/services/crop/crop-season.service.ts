import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {CropSeasonModel} from '../../pages/manage-crops/crop-season/form/crop-season-form.model';
import {CropSeasonListModel} from '../../pages/manage-crops/crop-season/list/crop-season-list.model';
import {LangService} from '../lang.service';
import {FieldMapper} from '../../common/field.mapper';

@Injectable({
    providedIn: 'root'
})
export class CropSeasonService {

    private api: string = environment.apiUrl + '/crop-season';

    public seasonsChanged: EventEmitter<void> = new EventEmitter();
    public seasonYearChanged: EventEmitter<number> = new EventEmitter();
    public seasonYear: number;

    constructor(private http: HttpClient,
                private langService: LangService) {
    }

    public emitSeasonsChanged() {
        this.seasonsChanged.emit();
    }

    public changeSeasonYear(year: number) {
        this.seasonYear = year;
        this.seasonYearChanged.emit(year);
    }

    public getYears(): Observable<number[]> {
        return this.http.get<number[]>(this.api + '/years');
    }

    public find(): Observable<CropSeasonListModel[]> {
        return this.http.get<CropSeasonListModel[]>(this.api + '/');
    }

    public adjustListModels(models: CropSeasonListModel[]) {
        const fieldMapper = new FieldMapper(this.langService.getLanguage());
        const nameField = fieldMapper.get('name');
        return models.map((model: CropSeasonListModel) => {
            model.harvestYearCropVariety = model.harvestYear + ' ' + model.cropModel[nameField];
            if (model.cropVarietyModel != null) {
                model.harvestYearCropVariety += ' ' + model.cropVarietyModel[nameField];
            }
            return model;
        });
    }

    public adjustModel(model: CropSeasonListModel) {
        const fieldMapper = new FieldMapper(this.langService.getLanguage());
        const nameField = fieldMapper.get('name');
        model.harvestYearCropVariety = model.harvestYear + ' ' + model.cropModel[nameField];
        if (model.cropVarietyModel != null) {
            model.harvestYearCropVariety += ' ' + model.cropVarietyModel[nameField];
        }
    }

    findOne(id: number): Observable<CropSeasonModel> {
        return this.http.get<CropSeasonModel>(this.api + '/' + id);
    }

    save(model: CropSeasonModel): Observable<CropSeasonModel> {
        return this.http.post<CropSeasonModel>(this.api + '/', model);
    }

    remove(id: number): Observable<void> {
        return this.http.delete<void>(this.api + '/' + id);
    }
}
