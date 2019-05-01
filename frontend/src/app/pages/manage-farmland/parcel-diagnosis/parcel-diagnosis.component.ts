import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelDiagnosisModel} from './parcel-diagnosis.model';
import {ParcelService} from '../../../services/parcel/parcel.service';
import {AlertService} from '../../../services/alert.service';
import {ParcelDiagnosisSummarizerComponent} from './parcel-diagnosis-summarizer/parcel-diagnosis-summarizer.component';
import {ParcelDiagnosisMapComponent} from './parcel-diagnosis-map/parcel-diagnosis-map.component';

@Component({
    selector: 'app-parcel-diagnosis',
    templateUrl: './parcel-diagnosis.component.html',
    styleUrls: ['./parcel-diagnosis.component.scss']
})
export class ParcelDiagnosisComponent implements OnInit {

    @ViewChild(ParcelDiagnosisSummarizerComponent) parcelDiagnosisSummarizerComponent;
    @ViewChild(ParcelDiagnosisMapComponent) parcelDiagnosisMapComponent;

    parcelDiagnosisModel: ParcelDiagnosisModel;
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
            this.setupModel(id);
        });
    }


    private setupModel(id) {
        // this.parcelService.findOne(id).subscribe(model => {
        //     this.parcelModel = model;
        //     this.parcelSeasonModel = new ParcelSeasonModel();
        //     this.parcelSeasonModel.parcelId = this.parcelModel.id;
        //     this.parcelService.adjust([model]);
        // });

    }


    save() {
        // const generalFormValid = this.parcelGeneralFormComponent.submit();
        // if (!generalFormValid) {
        //     this.alertService.validationFailed();
        //     return;
        // }
        //
        // const isNew = this.parcelModel.id == null;
        // if (isNew && !this.parcelMapEditorComponent) {
        //     this.alertService.warning('parcel.complete-map');
        //     return;
        // }
        //
        // if (this.parcelMapEditorComponent) {
        //     const mapValid = this.parcelMapEditorComponent.submit();
        //     if (!mapValid) {
        //         this.alertService.warning('parcel.complete-map');
        //         return;
        //     }
        // }
        //
        // this.parcelService.save(this.parcelModel).subscribe(model => {
        //     this.parcelModel = model;
        //     this.parcelService.adjust([this.parcelModel]);
        //     this.alertService.saved();
        // });
        //
        // this.parcelSeasonModel = this.parcelCropFormComponent.parcelSeasonModel;
        //
        // this.parcelCropSeasonService.saveYearSeason(this.parcelSeasonModel).subscribe(model => {
        //     this.parcelSeasonModel = model;
        //     // this.parcelService.adjust([this.parcelModel]);
        //     this.alertService.saved();
        // });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
