import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {ParcelModel} from '../../pages/manage-farmland/parcel/parcel.model';
import {environment} from '../../../environments/environment';
import {FieldMapper} from '../../common/field.mapper';
import {LangService} from '../lang.service';
import {LatLng} from '../../interfaces/lat-lng.interface';

@Injectable({
    providedIn: 'root'
})
export class ParcelService {

    private api: string = environment.apiUrl + '/parcel';

    constructor(private http: HttpClient,
                private langService: LangService) {
    }

    findOne(id: number) {
        return this.http.get<ParcelModel>(this.api + '/' + id);
    }

    find(): Observable<ParcelModel[]> {
        return this.http.get<ParcelModel[]>(this.api + '/');
    }

    save(model: ParcelModel): Observable<ParcelModel> {
        return this.http.post<ParcelModel>(this.api + '/', model);
    }

    remove(model: ParcelModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }

    adjust(models: ParcelModel[]) {
        const fieldMapper = new FieldMapper(this.langService.getLanguage());
        const lastWorkTypeField = fieldMapper.get('lastWorkType');
        const cropNameField = fieldMapper.get('cropName');

        models.forEach((parcel) => {
            parcel.fillColor = this.randomColor();
            parcel.icon = '/assets/img/crops/' + parcel.icon;
            parcel.paths = parcel.coordinates.map((c) => {
                return {
                    lat: c[0],
                    lng: c[1]
                };
            });
            parcel.center = this.getCenterOfPolygon(parcel.paths);
            parcel.lastWorkType = parcel[lastWorkTypeField];
            parcel.cropName = parcel[cropNameField];
        });
    }

    private getCenterOfPolygon(paths): LatLng {

        const pointCount = paths.length;
        let lat = 0;
        let lng = 0;

        paths.forEach(point => {
            lat += point.lat;
            lng += point.lng;
        });

        return {
            lat: lat / pointCount,
            lng: lng / pointCount
        };
    }

    private randomColor(): string {
        return '#' + Math.random().toString(16).slice(-3);
    }

}
