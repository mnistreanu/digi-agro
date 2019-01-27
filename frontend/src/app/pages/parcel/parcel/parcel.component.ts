import {Component, OnInit} from '@angular/core';
import {FormBuilder} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ParcelService} from '../../../services/parcel.service';
import {AlertService} from '../../../services/alert.service';
import {ParcelModel} from '../../telemetry/parcel.model';

@Component({
    selector: 'app-parcel',
    templateUrl: './parcel.component.html',
    styleUrls: ['./parcel.component.scss']
})
export class ParcelComponent implements OnInit {

    parcelModel: ParcelModel;
    tabIndex = 1;

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

    private prepareNewModel() {}

    save() {
        alert('Save');
    }

    remove() {
        alert('Delete');
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
