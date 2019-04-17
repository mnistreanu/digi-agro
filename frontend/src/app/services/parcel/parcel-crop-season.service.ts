import {Injectable} from '@angular/core';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {environment} from '../../../environments/environment';
import {LangService} from '../lang.service';
import {ParcelSeasonModel} from '../../pages/manage-farmland/parcel-season-form/parcel-season.model';
import {ManageHarvestModel} from '../../pages/manage-farmland/manage-harvest/manage-harvest.model';

@Injectable({
    providedIn: 'root'
})
export class ParcelCropSeasonService {

    private api: string = environment.apiUrl + '/parcel-crop-season';

    constructor(private http: HttpClient,
                private langService: LangService) {
    }

    findYearSeasonParcel(harvestYear: number, parcelId: number): Observable<ParcelSeasonModel> {
        return this.http.get<ParcelSeasonModel>(this.api + '/' + harvestYear + '/' + parcelId);
    }

    findParcels(harvestYear: number): Observable<ParcelSeasonModel[]> {
        if (harvestYear) {
            return this.http.get<ParcelSeasonModel[]>(this.api + '/' + harvestYear);
        }
        else {
            return Observable.create(observer => {
                observer.next([]);
                observer.complete();
            });
        }
    }

    findLastSeason(parcelId: number): Observable<ParcelSeasonModel> {
        return this.http.get<ParcelSeasonModel>(this.api + '/last/' + parcelId);
    }

    findHarvestSummary(harvestYear: number): Observable<ManageHarvestModel> {
        return this.http.get<ManageHarvestModel>(this.api + '-harvest-summary/' + harvestYear);
    }

    saveYearSeason(model: ParcelSeasonModel): Observable<ParcelSeasonModel> {
        return this.http.post<ParcelSeasonModel>(this.api + '/', model);
    }

}
