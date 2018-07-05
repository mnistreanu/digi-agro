import { Component, OnInit } from '@angular/core';
import {ColDef, GridOptions} from "ag-grid";
import {TelemetryModel} from "./telemetry.model";
import {MachineService} from "../../../services/machine.service";
import {TelemetryService} from "../../../services/telemetry.service";
import {AuthService} from "../../../services/auth.service";
import {DeleteRendererComponent} from "../../../modules/aggrid/delete-renderer/delete-renderer.component";
import {DateUtil} from "../../../common/dateUtil";
import {NumericUtil} from "../../../common/numericUtil";

@Component({
  selector: 'az-telemetry',
  templateUrl: './telemetry.component.html',
  styleUrls: ['./telemetry.component.scss']
})
export class TelemetryComponent implements OnInit {

  options: GridOptions;
  context;

  username: string;

  machinesPresent: boolean = false;
  machineIdentifier: string;
  availableMachineIdentifiers: string[];

  mapPath: any[] = [];
  mapCenter: string;

  models: TelemetryModel[] = [];


  constructor(private telemetryService: TelemetryService,
              private machineService: MachineService,
              private authService: AuthService) {
  }

  ngOnInit() {
    this.setupUser();
    this.setupMachineIdentifiers();
  }

  private setupUser() {
    this.username = this.authService.getUsername();
  }

  private setupMachineIdentifiers() {
    this.machineService.fetchIdentifiers().subscribe(data => {
      this.availableMachineIdentifiers = data;

      this.machinesPresent = data && data.length > 0;
      if (!this.machinesPresent) {
        return;
      }

      this.machineIdentifier = this.availableMachineIdentifiers[0];
      this.setupGrid();
    })
  }

  private setupGrid() {
    this.options = <GridOptions>{};

    this.options.enableColResize = true;
    this.options.enableSorting = true;
    this.options.enableFilter = true;
    this.options.columnDefs = this.setupHeaders();
    this.context = {componentParent: this};

    this.setupRows();
  }

  private setupHeaders() {

    let headers: ColDef[] = [
      {
        headerName: 'Machine Identifier',
        field: 'machineIdentifier',
        width: 200,
        minWidth: 200
      },
      {
        headerName: 'Latitude',
        field: 'latitude',
        width: 100,
        minWidth: 100,
        editable: true,
        valueSetter: (params) => this.coordinateValueSetter(params),
        onCellValueChanged: (params) => this.onCoordinateChange(params)
      },
      {
        headerName: 'Longitude',
        field: 'longitude',
        width: 100,
        minWidth: 100,
        editable: true,
        valueSetter: (params) => this.coordinateValueSetter(params),
        onCellValueChanged: (params) => this.onCoordinateChange(params)
      },
      {
        headerName: 'Created At',
        field: 'createdAt',
        width: 120,
        minWidth: 120,
        maxWidth: 120,
        valueFormatter: (params) => DateUtil.formatDateWithTime(params.value)
      },
      {
        field: 'delete',
        headerName: '',
        width: 24,
        minWidth: 24,
        maxWidth: 30,
        editable: false,
        suppressResize: true,
        suppressMenu: true,
        cellRendererFramework: DeleteRendererComponent,
        cellStyle: () => {
          return {padding: 0};
        }
      }
    ];

    return headers;
  }

  private coordinateValueSetter(params) {
    let newValue = params.newValue;
    if (newValue == params.oldValue) {
      return false;
    }

    if (!NumericUtil.isNumeric(newValue)) {
      return false;
    }

    let field = params.colDef.field;
    params.data[field] = newValue;

    return true;
  }

  private onCoordinateChange(params) {

    let model = params.data;
    let field = params.colDef.field;
    let value = params.newValue;

    this.telemetryService.updateCoordinate(model.id, field, value).subscribe(() => {});

    this.setupMapPath();
  }

  private setupRows() {
    this.telemetryService.findByMachineIdentifierAndUsername(this.machineIdentifier, this.username).subscribe(models => {
      this.options.api.setRowData(models);
      this.models = models;
      this.adjustGridSize();
      this.setupMapPath();
    })
  }

  public onMachineChange() {
    this.setupRows();
  }

  private setupMapPath() {
    this.mapPath = this.models.map((model) => {
      return {lat: +model.latitude, lng: +model.longitude};
    });

    if (this.models && this.models.length > 0) {
      this.mapCenter = this.models[0].latitude + ',' + this.models[0].longitude;
    }
    else {
      this.mapCenter = 'Moldova, Chisinau';
    }
  }

  public onGridReady() {
    this.options.api.sizeColumnsToFit();
  }

  public adjustGridSize() {
    setTimeout(() => {this.options.api.sizeColumnsToFit();}, 500);
  }

  public add() {
    let item = new TelemetryModel();

    item.machineIdentifier = this.machineIdentifier;
    item.username = this.username;
    item.createdAt = new Date();
    item.latitude = 0;
    item.longitude = 0;

    this.options.api.updateRowData({add: [item]});

    this.telemetryService.save(item).subscribe((savedModel) => {
      item.id = savedModel.id;

      this.models.push(item);
      this.setupMapPath();
    });
  }

  public onDelete(node) {
    let model = node.data;
    this.options.api.updateRowData({remove: [model]});

    this.telemetryService.remove(model).subscribe(() => {});

    this.models.splice(this.models.indexOf(model), 1);
    this.setupMapPath();
  }


}
