import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {ActivatedRoute, Router} from '@angular/router';
import {PesticideService} from '../../../../services/chemicals-pests/pesticide.service';
import {LangService} from '../../../../services/lang.service';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {PesticideModel} from '../pesticide.model';

@Component({
    selector: 'app-pesticide-list',
    templateUrl: './pesticide-list.component.html',
    styleUrls: ['./pesticide-list.component.scss']
})
export class PesticideListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private pesticideService: PesticideService) {
    }

    ngOnInit() {
        this.setupGrid();
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

        const headers: ColDef[] = [
            {
                field: 'edit',
                width: 30,
                minWidth: 24,
                maxWidth: 30,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            },
            {
                headerName: 'pesticide.type',
                field: 'pesticideType',
                width: 180,
                minWidth: 180
            },
            {
                headerName: 'info.name',
                field: 'nameRo',
                width: 300,
                minWidth: 200
            },
            {
                headerName: 'pesticide.active-substance',
                field: 'activeSubstance',
                width: 300,
                minWidth: 200
            },
            {
                headerName: 'pesticide.toxicity-group',
                headerTooltip: 'pesticide.toxicity-group',
                field: 'toxicityGroup',
                width: 44,
                minWidth: 44
            },
            {
                headerName: 'pest.pests',
                field: 'pestsRo',
                width: 400,
                minWidth: 200
            },
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }

            if (header.headerTooltip) {
                this.langService.get(header.headerTooltip).subscribe(m => header.headerTooltip = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.pesticideService.find().subscribe(models => {
            this.adjustModels(models);
            this.options.api.setRowData(models);
        });
    }

    private adjustModels(models: PesticideModel[]) {
        models.forEach(model => {
            this.langService
                .get('pesticide-type.' + model.pesticideType)
                .subscribe(m => model.pesticideType = m);
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

    public add() {
        this.router.navigate(['./-1'], { relativeTo: this.route });
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['./' + model.id], { relativeTo: this.route });
    }


}
