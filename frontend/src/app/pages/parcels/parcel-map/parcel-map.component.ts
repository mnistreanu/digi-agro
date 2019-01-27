import {Component, ElementRef, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {ParcelModel} from '../../telemetry/parcel.model';
import {ToastrService} from 'ngx-toastr';
import {DateUtil} from '../../../common/dateUtil';
import {LangService} from '../../../services/lang.service';

@Component({
    selector: 'app-parcel-map',
    templateUrl: './parcel-map.component.html',
    styleUrls: ['./parcel-map.component.scss']
})
export class ParcelMapComponent implements OnInit, OnChanges {

    @Input() models: any[];
    @Input() center: any;

    @ViewChild('infoBody') infoBody: ElementRef;

    private defaultStrokeColor = '#FFC107';
    private defaultZIndex = 1;

    private model: ParcelModel;
    private labels: any;

    constructor(private toastr: ToastrService,
                private langService: LangService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupCenter();
        this.models = [];
    }


    ngOnChanges(changes: SimpleChanges): void {
        if (changes['models']) {
            this.setupCenter();
        }
    }

    private setupLabels() {
        this.labels = {};
        this.langService.get('parcel.name').subscribe(m => this.labels['parcelName'] = m);
        this.langService.get('crop.name').subscribe(m => this.labels['cropName'] = m);
        this.langService.get('parcel.area').subscribe(m => this.labels['area'] = m);
        this.langService.get('parcel.space-between-rows').subscribe(m => this.labels['spaceBetweenRows'] = m);
        this.langService.get('parcel.space-between-plants').subscribe(m => this.labels['spaceBetweenPlants'] = m);
        this.langService.get('parcel.planted-at').subscribe(m => this.labels['plantedAt'] = m);
        this.langService.get('parcel.last-work').subscribe(m => this.labels['lastWorkType'] = m);
        this.langService.get('parcel.last-work-date').subscribe(m => this.labels['lastWorkDate'] = m);
    }

    private setupCenter() {
        if (this.models && this.models.length > 0) {
            this.center = this.models[0].paths[0];
        }
        else {
            this.center = 'Moldova, Chisinau';
        }
    }

    onParcelClick(model) {
        this.model = model;
        const title = this.labels['parcelName'] + ' "' + this.model.name + '"';
        this.toastr.show(this.getParcelInfoMessage(), title, {
            enableHtml: true,
            toastClass: 'toast toast-custom'
        });
    }

    private getParcelInfoMessage() {
        return `
            <div class="toast-custom-icon">
                <img src="${this.model.icon}"/>
            </div>
            <div>
                <b>${this.labels['cropName']}</b>: <i>${this.model.cropName}</i><br/>
                <b>${this.labels['area']}</b>: <i>${Math.round(this.model.area)} ha</i><br/>
                <b>${this.labels['spaceBetweenRows']}</b>: <i>${this.model.spaceBetweenRows || 0} cm</i><br/>
                <b>${this.labels['spaceBetweenPlants']}</b>: <i>${this.model.spaceBetweenPlants || 0} cm</i><br/>
                <b>${this.labels['plantedAt']}</b>: <i>${DateUtil.formatDate(this.model.plantedAt)}</i><br/>
                <b>${this.labels['lastWorkType']}</b>: <i>${this.model.lastWorkType || '-'}</i><br/>
                <b>${this.labels['lastWorkDate']}</b>: <i>${DateUtil.formatDate(this.model.lastWorkDate) || '-'}</i><br/>
            </div>
        `;
    }

    onParcelUp(event) {
        event.target.setOptions({
            strokeColor: '#F00',
            zIndex: this.defaultZIndex + 1
        });
    }

    onParcelOut(event) {
        event.target.setOptions({
            strokeColor: this.defaultStrokeColor,
            zIndex: this.defaultZIndex
        });
    }

}
