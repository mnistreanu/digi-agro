import {Component, OnInit, ViewChild} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelService} from '../../../services/parcel.service';
import {AlertService} from '../../../services/alert.service';
import {ParcelModel} from '../../telemetry/parcel.model';
import {ParcelInfoFormComponent} from '../parcel-info-form/parcel-info-form.component';
import {ParcelMapEditorComponent} from '../parcel-map-editor/parcel-map-editor.component';

@Component({
    selector: 'app-parcel',
    templateUrl: './parcel.component.html',
    styleUrls: ['./parcel.component.scss']
})
export class ParcelComponent implements OnInit {

    @ViewChild(ParcelInfoFormComponent) parcelInfoFormComponent;
    @ViewChild(ParcelMapEditorComponent) parcelMapEditorComponent;


    parcelModel: ParcelModel;
    tabIndex = 1;
    loadedTabs = {tabIndex: true};

    constructor(private fb: FormBuilder,
                private router: Router,
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
            this.parcelService.adjust([model]);
        });
    }

    private prepareNewModel() {
        this.parcelModel = new ParcelModel();
    }

    save() {
        console.log('save');
        const infoFormValid = this.parcelInfoFormComponent.validateAndSubmit();
        if (!infoFormValid) {
            this.alertService.validationFailed();
        }
        // todo: process map data...
    }

    remove() {
        console.log('Delete');
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
