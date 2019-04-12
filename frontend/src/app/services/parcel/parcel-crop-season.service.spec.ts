import {inject, TestBed} from '@angular/core/testing';

import {ParcelCropSeasonService} from './parcel-crop-season.service';

describe('ParcelCropSeasonService', () => {
    beforeEach(() => {
        TestBed.configureTestingModule({
            providers: [ParcelCropSeasonService]
        });
    });

    it('should be created', inject([ParcelCropSeasonService], (service: ParcelCropSeasonService) => {
        expect(service).toBeTruthy();
    }));
});
