import {Component, OnInit} from '@angular/core';
import {ColDef, GridOptions} from "ag-grid";
import {UserService} from "../../../services/user.service";
import {EditRendererComponent} from "../../../modules/aggrid/edit-renderer/edit-renderer.component";
import {Router} from "@angular/router";

@Component({
    selector: 'az-user-list',
    templateUrl: './user-list.component.html',
    styleUrls: ['./user-list.component.scss']
})
export class UserListComponent implements OnInit {

    options: GridOptions;
    context;

    constructor(private router: Router,
                private userService: UserService) {
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

        let headers: ColDef[] = [
            {
                field: 'edit',
                width: 24,
                minWidth: 24,
                editable: false,
                suppressResize: true,
                suppressMenu: true,
                cellRendererFramework: EditRendererComponent,
                cellStyle: () => {return {padding: 0}}
            },
            {
                headerName: 'Username',
                field: 'username',
                width: 250,
                minWidth: 200
            },
            {
                headerName: 'First Name',
                field: 'firstName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Last Name',
                field: 'lastName',
                width: 200,
                minWidth: 200
            },
            {
                headerName: 'Email',
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
        })
    }

    public onGridReady() {
        this.options.api.sizeColumnsToFit();
    }

    public adjustGridSize() {
        setTimeout(() => {this.options.api.sizeColumnsToFit();}, 500);
    }

    public addUser() {
        this.router.navigate(['/pages/manage-users/user/-1']);
    }

    public onEdit(node) {
        let model = node.data;
        this.router.navigate(['/pages/manage-users/user/' + model.id]);
    }


}
