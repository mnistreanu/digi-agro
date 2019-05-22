import {Injectable} from '@angular/core';
import {environment} from '../../../environments/environment';
import {HttpClient} from '@angular/common/http';
import {Observable} from 'rxjs/Rx';
import {FertilizerModel} from '../../pages/chemicals-pests/manage-fertilizers/fertilizer.model';
import {ParcelFertilizerApplicationModel} from '../../pages/manage-farmland/parcel-diagnosis/parcel-fertilizer-application/parcel-fertilizer-application.model';

@Injectable({
    providedIn: 'root'
})
export class FertilizerService {

    private api: string = environment.apiUrl + '/fertilizer';

    constructor(private http: HttpClient) {
    }

    findOne(id: number): Observable<FertilizerModel> {
        return this.http.get<FertilizerModel>(this.api + '/' + id);
    }

    find(): Observable<FertilizerModel[]> {
        return this.http.get<FertilizerModel[]>(this.api + '/');
    }

    findApplications(parcelId: number, harvestYear: number): Observable<ParcelFertilizerApplicationModel[]> {
        return this.http.get<ParcelFertilizerApplicationModel[]>(this.api + '-application/' + parcelId + '/' + harvestYear);
    }

    findByType(typeId: number): Observable<FertilizerModel[]> {
        return this.http.get<FertilizerModel[]>(this.api + '/by-type/' + typeId);
    }

    save(model: FertilizerModel): Observable<FertilizerModel> {
        return this.http.post<FertilizerModel>(this.api + '/', model);
    }

    remove(model: FertilizerModel): Observable<void> {
        return this.http.delete<void>(this.api + '/' + model.id);
    }
}
