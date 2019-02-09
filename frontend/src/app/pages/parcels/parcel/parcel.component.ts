import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelService} from '../../../services/parcel.service';
import {AlertService} from '../../../services/alert.service';
import {ParcelModel} from '../../telemetry/parcel.model';
import {ParcelInfoFormComponent} from '../parcel-info-form/parcel-info-form.component';
import {ParcelMapEditorComponent} from '../parcel-map-editor/parcel-map-editor.component';
import {ParcelSeasonFormComponent} from '../parcel-season-form/parcel-season-form.component';
import {ParcelSeasonModel} from '../parcel-season-form/parcel-season.model';

@Component({
    selector: 'app-parcel',
    templateUrl: './parcel.component.html',
    styleUrls: ['./parcel.component.scss']
})
export class ParcelComponent implements OnInit {

    @ViewChild(ParcelInfoFormComponent) parcelInfoFormComponent;
    @ViewChild(ParcelMapEditorComponent) parcelMapEditorComponent;
    @ViewChild(ParcelSeasonFormComponent) parcelSeasonFormComponent;

    parcelModel: ParcelModel;
    parcelSeasonModel: ParcelSeasonModel;
    tabIndex = 1;
    loadedTabs = {1: true};

    constructor(private router: Router,
                private route: ActivatedRoute,
                private parcelService: ParcelService,
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
            this.parcelService.adjust([model]);
        });
    }

    private prepareNewModel() {
        this.parcelModel = new ParcelModel();
        this.parcelSeasonModel = new ParcelSeasonModel();
    }

    save() {
        const infoFormValid = this.parcelInfoFormComponent.submit();
        if (!infoFormValid) {
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

        if (this.parcelSeasonFormComponent) {
            const mapValid = this.parcelSeasonFormComponent.submit();
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
