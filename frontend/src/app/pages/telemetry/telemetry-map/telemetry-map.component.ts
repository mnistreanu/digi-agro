import {Component, Input, OnChanges, OnInit, SimpleChanges} from '@angular/core';

@Component({
  selector: 'az-telemetry-map',
  templateUrl: './telemetry-map.component.html',
  styleUrls: ['./telemetry-map.component.scss']
})
export class TelemetryMapComponent implements OnInit, OnChanges {

  @Input() polylinePath: any[];
  @Input() markers: any[];

  polygonPath: any[];

  center: string;

  constructor() { }

  ngOnInit() {
    this.setupCenter();
    this.setupPolygonPath();
  }

  ngOnChanges(changes: SimpleChanges): void {
    this.setupCenter();
  }

  private setupCenter() {
    if (this.polylinePath && this.polylinePath.length > 0) {
      let first = this.polylinePath[0];
      this.center = first.lat + ',' + first.lng;
    }
    else {
      this.center = 'Moldova, Chisinau';
    }
  }

  private setupPolygonPath() {
    this.polygonPath = [
      {lat: 47.06834223187641, lng: 28.80297943383789},
      {lat: 47.073920381236654, lng: 28.812717275024397},
      {lat: 47.0619794350457, lng: 28.82713683068846},
      {lat: 47.057933, lng: 28.821051},
    ];
  }

  public onPolygonClick(data) {
    console.log('onPolygonClick', data);
    let coords = {
      lat: data.latLng.lat(),
      lng: data.latLng.lng(),
    };
    alert(JSON.stringify(coords));
  }

  public onMouseDown(data) {
    console.log('onMouseDown', data);
  }

  public onMouseUp(data) {
    console.log('onMouseUp', data);
    console.log(data.latLng.lat(), data.latLng.lng());
  }

}
