import {Component, OnInit, ViewChild} from '@angular/core';
import {DrawingManager} from '@ngui/map';

@Component({
    selector: 'app-parcel-map-editor',
    templateUrl: './parcel-map-editor.component.html',
    styleUrls: ['./parcel-map-editor.component.scss']
})
export class ParcelMapEditorComponent implements OnInit {

    @ViewChild(DrawingManager) drawingManager: DrawingManager;

    center: any;
    drawingControlOptions: any;
    shapeOptions: any;

    parcelCoordinates: any;
    onDragging: boolean;

    constructor() {
    }

    ngOnInit() {
        this.setupCenter();
        this.initDrawingManager();
        this.setupDrawingControlOptions();
    }

    private setupCenter() {
        this.center = 'Moldova, Chisinau';
    }

    private setupDrawingControlOptions() {
        this.drawingControlOptions = {
            position: 'TOP_CENTER',
            drawingModes: ['polygon']
        };

        this.shapeOptions = {
            fillColor: '#fdffad',
            fillOpacity: 0.5,
            strokeWeight: 2,
            editable: true,
            draggable: true,
            zIndex: 1
        };
    }

    private initDrawingManager() {
        this.drawingManager['initialized$'].subscribe(dm => {
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
    }

    private printCoordinates() {
        this.parcelCoordinates.forEach((coord) => {
            console.log(coord[0], coord[1]);
        });
    }

}
