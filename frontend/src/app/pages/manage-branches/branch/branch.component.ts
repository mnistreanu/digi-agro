import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from "@angular/forms";
import {ActivatedRoute, Router} from "@angular/router";
import {ToastrService} from "ngx-toastr";
import {Messages} from "../../../common/messages";
import {BranchModel} from "./branch.model";
import {BranchService} from "../../../services/branch.service";
import {ListItem} from "../../../interfaces/list-item.interface";
import {TenantService} from "../../../services/tenant.service";

@Component({
  selector: 'az-branch',
  templateUrl: './branch.component.html',
  styleUrls: ['./branch.component.scss']
})
export class BranchComponent implements OnInit {
    form: FormGroup;
    submitted: boolean = false;

    model: BranchModel;
    isNew: boolean;

    parents: ListItem[];
    tenants: ListItem[];

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private branchService: BranchService,
                private tenantService: TenantService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.fetchTenantListItems();
        this.route.params.subscribe(params => {
            let id = params['id'];

            if (id == -1) {
                this.prepareNewModel();
            }
            else {
                this.setupModel(id);
            }
        });
    }

    private fetchTenantListItems() {
        this.tenantService.fetchListItems().subscribe(tenants => {
            this.tenants = tenants;
        });
    }

    private setupModel(id) {
        this.branchService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
            this.fetchParentListItems();
        });
    }

    private prepareNewModel() {
        this.model = new BranchModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            name: new FormControl(this.model.name, [Validators.required, Validators.maxLength(128)]),
            description: new FormControl(this.model.description, [Validators.maxLength(1024)]),
            country: new FormControl(this.model.country, [Validators.required, Validators.maxLength(2)]),
            county: [this.model.county, Validators.maxLength(2)],
            villageCity: [this.model.villageCity, Validators.maxLength(255)],
            address: [this.model.address, Validators.maxLength(1024)],
            phones: [this.model.phones, Validators.maxLength(128)],
            tenantId: [this.model.tenantId, Validators.required],
            parentId: [this.model.parentId]
        });
    }

    public onNameChange() {
        let control = this.form.controls.name;
        this.branchService.validateName(this.model.id || -1, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                let errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    public onTenantChange() {
        this.form.controls['parentId'].setValue(null);
        this.fetchParentListItems();
    }

    private fetchParentListItems() {
        let tenant = this.form.controls['tenantId'].value;
        this.branchService.fetchListItems(tenant, this.model.id).subscribe(parents => {
            parents.unshift({id: null, name: 'None'});
            this.parents = parents;
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(Messages.VALIDATION_ERROR);
            return;
        }

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.branchService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(Messages.SAVED);
        });

    }

    public remove() {
        this.branchService.remove(this.model).subscribe(() => {
            this.toastr.success(Messages.DELETED);
            this.router.navigate(['/pages/manage-branches']);
        });
    }

}
