import {Component, Input, OnChanges, OnInit, SimpleChanges, ViewChild} from '@angular/core';
import {DrawingManager, NguiMapComponent} from '@ngui/map';
import {ParcelModel} from '../../telemetry/parcel.model';
import {LatLng} from '../../../interfaces/lat-lng.interface';

declare const google: any;

@Component({
    selector: 'app-parcel-map-editor',
    templateUrl: './parcel-map-editor.component.html',
    styleUrls: ['./parcel-map-editor.component.scss']
})
export class ParcelMapEditorComponent implements OnInit, OnChanges {

    @ViewChild(DrawingManager) drawingManager: DrawingManager;
    @ViewChild(NguiMapComponent) mapComponent: NguiMapComponent;
    @Input() parcelModel: ParcelModel;

    private mapInstance: any;
    private drawingManagerInstance: any;

    drawingControlOptions: any;
    shapeOptions: any;

    parcelCoordinates: LatLng[];
    onDragging: boolean;

    constructor() {
    }

    ngOnInit() {
        console.log('parcel-map-editor: initialization');
        this.initDrawingManager();
        this.setupDrawingControlOptions();
    }

    onMapReady(map) {
        this.mapInstance = map;
        this.setupInitialCenter();
        this.setupParcel();
    }

    ngOnChanges(changes: SimpleChanges): void {
        this.setupParcel();
    }

    private setupInitialCenter() {
        const moldovaChisinau = {
            lat: 47.0105,
            lng: 28.8638
        };
        this.mapInstance.setCenter(moldovaChisinau);
    }

    private setupDrawingControlOptions() {
        this.drawingControlOptions = {
            position: 'TOP_CENTER',
            drawingModes: ['polygon']
        };

        this.shapeOptions = {
            editable: true,
            draggable: true,
            fillColor: this.getFillColor(),
            fillOpacity: 0.5,
            strokeOpacity: 0.8,
            strokeWeight: 2
        };
    }

    private initDrawingManager() {
        this.drawingManager['initialized$'].subscribe(dm => {
            this.drawingManagerInstance = dm;
            google.maps.event.addListener(dm, 'overlaycomplete', event => {
                dm.setDrawingMode(null);

                const overlay = event.overlay;

                this.registerCoordinates(overlay);
                this.printCoordinates();

                this.registerDragEvents(overlay, 'dragstart');
                this.registerDragEvents(overlay, 'dragend');
                this.registerPathEvent(overlay, overlay.getPath(), 'insert_at');
                this.registerPathEvent(overlay, overlay.getPath(), 'set_at');

                this.registerVertexRemoving(overlay);
            });
        });
    }

    private registerDragEvents(overlay, eventName) {
        google.maps.event.addListener(overlay, eventName, e => {
            console.log(eventName);

            if (eventName === 'dragstart') {
                this.onDragging = true;
            }
            else {
                this.registerCoordinates(overlay);
                this.printCoordinates();
                this.onDragging = false;
            }
        });
    }

    private registerVertexRemoving(overlay) {
        google.maps.event.addListener(overlay, 'rightclick', (e) => {
            if (e.vertex == null) {
                return;
            }
            overlay.getPath().removeAt(e.vertex);
            this.registerCoordinates(overlay);
            this.printCoordinates();
        });
    }

    private registerPathEvent(overlay, overlayPath, eventName) {
        google.maps.event.addListener(overlayPath, eventName, e => {
            if (this.onDragging) {
                return;
            }
            console.log(eventName);
            this.registerCoordinates(overlay);
            this.printCoordinates();
        });
    }

    private registerCoordinates(overlay) {
        const pathArray = overlay.getPath().getArray();
        this.parcelCoordinates = pathArray.map((latLgn) => {
            return [latLgn.lat(), latLgn.lng()];
        });

        this.toggleDrawingManager();
    }

    private printCoordinates() {
        this.parcelCoordinates.forEach((coord) => {
            console.log(coord[0], coord[1]);
        });
    }

    private setupParcel() {
        if (!this.parcelModel) {
            return;
        }

        if (!this.mapInstance) {
            return;
        }

        // todo: do something when model is new (no initial polygon data)
        if (this.parcelModel.id == null) {
            return;
        }

        const parcelPolygon = new google.maps.Polygon({
            paths: this.parcelModel.paths,
            editable: true,
            draggable: true,
            fillColor: this.getFillColor(),
            fillOpacity: 0.5,
            strokeOpacity: 0.8,
            strokeWeight: 2
        });
        parcelPolygon.setMap(this.mapInstance);

        this.registerVertexRemoving(parcelPolygon);
        this.registerDragEvents(parcelPolygon, 'dragstart');
        this.registerDragEvents(parcelPolygon, 'dragend');
        this.registerPathEvent(parcelPolygon, parcelPolygon.getPath(), 'insert_at');
        this.registerPathEvent(parcelPolygon, parcelPolygon.getPath(), 'set_at');

        this.registerCoordinates(parcelPolygon);

        this.mapInstance.setCenter(this.parcelModel.center);
    }

    private getFillColor() {
        return '#fdffad';
    }

    private toggleDrawingManager() {
        setTimeout(() => {
            if (this.parcelCoordinates.length > 0) {
                this.drawingManagerInstance.setMap(null);
            }
            else {
                this.drawingManagerInstance.setMap(this.mapInstance);
            }
        });
    }

}
