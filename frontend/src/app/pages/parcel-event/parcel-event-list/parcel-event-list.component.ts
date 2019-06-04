import {Component, OnDestroy, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {ModalService} from '../../../services/modal.service';
import {AlertService} from '../../../services/alert.service';
import {LangService} from '../../../services/lang.service';
import {AgDeleteColumnType} from '../../../modules/aggrid/column-types/ag-delete-type';
import {AgNumericColumnType} from '../../../modules/aggrid/column-types/ag-numeric-type';
import {AgDateColumnType} from '../../../modules/aggrid/column-types/ag-date-type';
import {EventTypeService} from '../../../services/event/event-type.service';
import {EventTypeModel} from '../event-type/model/event-type.model';
import {ParcelEventModel} from './parcel-event.model';
import {SelectorComponent} from '../../../modules/aggrid/selector/single-selector/selector.component';
import {ParcelService} from '../../../services/parcel/parcel.service';
import {ParcelModel} from '../../manage-farmland/parcel/parcel.model';
import {ParcelEventService} from '../../../services/parcel/parcel-event.service';

@Component({
    selector: 'app-parcel-event-list',
    templateUrl: './parcel-event-list.component.html',
    styleUrls: ['./parcel-event-list.component.scss']
})
export class ParcelEventListComponent implements OnInit, OnDestroy {

    options: GridOptions;
    context;

    confirmationModalId = 'event-remove-confirmation-modal';

    eventTypeMap: Map<number, EventTypeModel>;
    eventTypes: EventTypeModel[];

    parcels: ParcelModel[];

    currentModel: ParcelEventModel;
    selectedParcelId: number;

    constructor(private modalService: ModalService,
                private eventTypeService: EventTypeService,
                private parcelService: ParcelService,
                private parcelEventService: ParcelEventService,
                private alertService: AlertService,
                private langService: LangService) {
    }


    ngOnInit() {
        this.setupEventTypes()
            .then(() => this.setupParcels())
            .then(() => this.setupGrid());
    }

    ngOnDestroy() {
    }

    private setupEventTypes(): Promise<void> {
        return new Promise((resolve) => {
            this.eventTypeService.getTree().subscribe((models: EventTypeModel[]) => {
                this.eventTypeMap = <Map<number, EventTypeModel>> {};
                this.eventTypes = models;

                const extractor = (model: EventTypeModel) => {
                    this.eventTypeMap[model.id] = model;
                    if (model.children) {
                        model.children.forEach(extractor);
                    }
                };

                models.forEach(extractor);
                resolve();
            });
        });
    }


    private setupParcels(): Promise<void> {
        return new Promise((resolve) => {
            this.parcelService.find().subscribe(models => {
                this.parcels = models;
                if (models.length > 0) {
                    this.selectedParcelId = models[0].id;
                }
                resolve();
            });
        });
    }

    public onParcelChange() {
        this.setupRows();
    }

    private setupGrid() {
        this.options = <GridOptions>{};
        this.setupColumnTypes();

        this.options.enableColResize = true;
        this.options.enableSorting = true;
        this.options.enableFilter = true;
        this.options.columnDefs = this.setupHeaders();
        this.context = {componentParent: this};
        this.options.frameworkComponents = {
            selectorEditor: SelectorComponent
        };

        this.setupRows();
    }

    private setupColumnTypes() {
        this.options.columnTypes = {
            deleteType: AgDeleteColumnType.getType(),
            dateType: AgDateColumnType.getType(),
            numericType: AgNumericColumnType.getType()
        };
    }

    private setupHeaders() {

        let headers: ColDef[] = [];

        headers = headers.concat(<ColDef[]>[
            {type: 'deleteType'},
            {
                headerName: 'info.date',
                field: 'date',
                type: 'dateType',
                editable: true,
                onCellValueChanged: (params) => this.updateModel(params.data)
            },
            {
                headerName: 'Type',
                field: 'eventTypeId',
                width: 100,
                minWidth: 100,
                editable: true,
                cellEditor: 'selectorEditor',
                cellEditorParams: {
                    getDropDownItems: () => this.createDropDownItems(this.eventTypes),
                    dropDownValueField: 'eventTypeId'
                },
                valueFormatter: (params) => this.eventTypeFormatter(params),
                onCellValueChanged: (params) => this.onEventTypeChange(params)
            },
            {
                headerName: 'Detail',
                field: 'eventTypeDetailId',
                width: 100,
                minWidth: 100,
                cellEditor: 'selectorEditor',
                editable: (params) => {
                    const model: ParcelEventModel = params.data;
                    return model.eventTypeId != null;
                },
                cellEditorParams: {
                    getDropDownItems: (model) => {
                        const eventType = this.eventTypeMap[model.eventTypeId];
                        return this.createDropDownItems(eventType.children);
                    },
                    dropDownValueField: 'eventTypeDetailId'
                },
                valueFormatter: (params) => this.eventTypeFormatter(params),
                onCellValueChanged: (params) => this.updateModel(params.data)
            },
            {
                headerName: 'Title',
                field: 'title',
                width: 100,
                minWidth: 100,
                editable: true,
                onCellValueChanged: (params) => this.updateModel(params.data)
            },
            {
                headerName: 'Description',
                field: 'description',
                width: 200,
                minWidth: 100,
                editable: true,
                onCellValueChanged: (params) => this.updateModel(params.data)
            }
        ]);

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private createDropDownItems(list: EventTypeModel[]) {
        if (!list) {
            return [];
        }
        return list.map(item => {
            return {
                id: item.id,
                text: item.name
            };
        });
    }

    private eventTypeFormatter(params) {
        const item = this.eventTypeMap[params.value];
        return item ? item.name : null;
    }

    private onEventTypeChange(params) {
        const model = params.data;

        if (params.oldValue !== params.newValue) {
            model.eventTypeDetailId = null;
            this.options.api.updateRowData({update: [model]});
        }

        this.updateModel(params.data);
    }

    public setupRows() {
        this.parcelEventService.find(this.selectedParcelId).subscribe(models => {
            models.forEach(model => model.date = new Date(model.date));
            this.options.api.setRowData(models);
        });
    }

    public onDelete(node) {
        this.modalService.open(this.confirmationModalId);
        this.currentModel = node.data;
    }

    public remove() {
        this.parcelEventService.remove(this.currentModel).subscribe(() => {
            this.options.api.updateRowData({remove: [this.currentModel]});
            this.currentModel = null;
            this.alertService.removed();
        });
    }

    public add() {
        let model = this.buildModel();

        this.parcelEventService.save(model).subscribe(responseModel => {
            model = responseModel;
            model.date = new Date(model.date);

            this.options.api.updateRowData({add: [model]});
            this.alertService.saved();
        });
    }

    private buildModel() {
        const model = new ParcelEventModel();
        model.date = new Date();
        model.eventTypeId = this.eventTypes[0].id;
        model.parcelId = this.selectedParcelId;
        model.date = new Date();

        return model;
    }

    private updateModel(model) {
        this.parcelEventService.save(model).subscribe(() => {
            this.alertService.saved();
        });
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            if (this.options && this.options.api) {
                this.options.api.sizeColumnsToFit();
            }
        }, 500);
    }


}
