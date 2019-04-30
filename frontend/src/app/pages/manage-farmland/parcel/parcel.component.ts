import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelService} from '../../../services/parcel/parcel.service';
import {AlertService} from '../../../services/alert.service';
import {ParcelModel} from './parcel.model';
import {ParcelSeasonModel} from '../parcel-season.model';
import {ParcelGeneralFormComponent} from './parcel-general-form/parcel-general-form.component';
import {ParcelMapEditorComponent} from './parcel-map-editor/parcel-map-editor.component';
import {ParcelCropFormComponent} from './parcel-crop-form/parcel-crop-form.component';
import {ParcelSoilFormComponent} from './parcel-soil-form/parcel-soil-form.component';
import {ParcelCropSeasonService} from '../../../services/parcel/parcel-crop-season.service';

@Component({
    selector: 'app-parcel',
    templateUrl: './parcel.component.html',
    styleUrls: ['./parcel.component.scss']
})
export class ParcelComponent implements OnInit {

    @ViewChild(ParcelGeneralFormComponent) parcelGeneralFormComponent;
    @ViewChild(ParcelMapEditorComponent) parcelMapEditorComponent;
    @ViewChild(ParcelCropFormComponent) parcelCropFormComponent;
    @ViewChild(ParcelSoilFormComponent) parcelSoilFormComponent;

    parcelModel: ParcelModel;
    parcelSeasonModel: ParcelSeasonModel;
    tabIndex = 1;
    loadedTabs = {1: true};

    constructor(private router: Router,
                private route: ActivatedRoute,
                private parcelService: ParcelService,
                private parcelCropSeasonService: ParcelCropSeasonService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.restoreModel();
    }

    changeTab(tabIndex) {
        this.tabIndex = tabIndex;
        this.loadedTabs[this.tabIndex] = true;
    }

    private restoreModel() {
        this.route.params.subscribe(params => {
            const id = params['id'];

            if (id == -1) {
                this.prepareNewModel();
            }
            else {
                this.setupModel(id);
            }
        });
    }


    private setupModel(id) {
        this.parcelService.findOne(id).subscribe(model => {
            this.parcelModel = model;
            this.parcelSeasonModel = new ParcelSeasonModel();
            this.parcelSeasonModel.parcelId = this.parcelModel.id;
            this.parcelService.adjust([model]);
        });

    }

    private prepareNewModel() {
        this.parcelModel = new ParcelModel();
        this.parcelSeasonModel = new ParcelSeasonModel();
    }

    save() {
        const generalFormValid = this.parcelGeneralFormComponent.submit();
        if (!generalFormValid) {
            this.alertService.validationFailed();
            return;
        }

        const seasonFormValid = this.parcelCropFormComponent.submit();
        if (!seasonFormValid) {
            this.alertService.validationFailed();
            return;
        }

        const soilFormValid = this.parcelSoilFormComponent.submit();
        if (!soilFormValid) {
            this.alertService.validationFailed();
            return;
        }

        const isNew = this.parcelModel.id == null;
        if (isNew && !this.parcelMapEditorComponent) {
            this.alertService.warning('parcel.complete-map');
            return;
        }

        if (this.parcelMapEditorComponent) {
            const mapValid = this.parcelMapEditorComponent.submit();
            if (!mapValid) {
                this.alertService.warning('parcel.complete-map');
                return;
            }
        }

        this.parcelService.save(this.parcelModel).subscribe(model => {
            this.parcelModel = model;
            this.parcelService.adjust([this.parcelModel]);
            this.alertService.saved();
        });

        this.parcelSeasonModel = this.parcelCropFormComponent.parcelSeasonModel;

        this.parcelCropSeasonService.saveYearSeason(this.parcelSeasonModel).subscribe(model => {
            this.parcelSeasonModel = model;
            // this.parcelService.adjust([this.parcelModel]);
            this.alertService.saved();
        });
    }

    remove() {
        this.parcelService.remove(this.parcelModel).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
