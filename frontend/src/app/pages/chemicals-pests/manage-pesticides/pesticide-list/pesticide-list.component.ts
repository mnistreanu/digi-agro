import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {Router} from '@angular/router';
import {PesticideService} from '../../../../services/chemicals-pests/pesticide.service';
import {LangService} from '../../../../services/lang.service';
import {EditRendererComponent} from '../../../../modules/aggrid/edit-renderer/edit-renderer.component';

@Component({
    selector: 'app-pesticide-list',
    templateUrl: './pesticide-list.component.html',
    styleUrls: ['./pesticide-list.component.scss']
})
export class PesticideListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
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
                width: 24,
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
                headerName: 'name RO',
                field: 'nameRo',
                width: 300,
                minWidth: 200
            },
            {
                headerName: 'name RU',
                field: 'nameRu',
                width: 300,
                minWidth: 200
            }
        ];

        headers.forEach(header => {
            if (header.headerName) {
                this.langService.get(header.headerName).subscribe(m => header.headerName = m);
            }
        });

        return headers;
    }

    private setupRows() {
        this.pesticideService.find().subscribe(models => {
            this.options.api.setRowData(models);
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
        this.router.navigate(['/pages/chemicals-pests/pesticide/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/chemicals-pests/pesticide/' + model.id]);
    }


}
