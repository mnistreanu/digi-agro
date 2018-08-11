import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from 'ag-grid';
import {UserService} from '../../../services/user.service';
import {EditRendererComponent} from '../../../modules/aggrid/edit-renderer/edit-renderer.component';
import {Router} from '@angular/router';
import {LangService} from '../../../services/lang.service';

@Component({
    selector: 'app-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

    options: GridOptions;
    context;

    labelUsername: string;
    labelFirstName: string;
    labelLastName: string;
    labelEmail: string;

    constructor(private router: Router,
                private langService: LangService,
                private userService: UserService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupGrid();
    }

    private setupLabels() {
        this.langService.get('user.username').subscribe((msg) => this.labelUsername = msg);
        this.langService.get('user.firstname').subscribe((msg) => this.labelFirstName = msg);
        this.langService.get('user.lastname').subscribe((msg) => this.labelLastName = msg);
        this.langService.get('user.email').subscribe((msg) => this.labelEmail = msg);
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
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {
                    return {padding: 0};
                }
            },
            {
                headerName: this.labelUsername,
                field: 'username',
                width: 250,
                minWidth: 200
            },
            {
                headerName: this.labelFirstName,
                field: 'firstName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelLastName,
                field: 'lastName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: this.labelEmail,
                field: 'email',
                width: 250,
                minWidth: 200
            }
        ];

        return headers;
    }

    private setupRows() {
        this.userService.findAll().subscribe(models => {
            this.options.api.setRowData(models);
        });
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {
            this.options.api.sizeColumnsToFit();
        }, 500);
    }

    public addUser() {
        this.router.navigate(['/pages/manage-users/user/-1']);
    }

    public onEdit(node) {
        const model = node.data;
        this.router.navigate(['/pages/manage-users/user/' + model.id]);
    }


}
