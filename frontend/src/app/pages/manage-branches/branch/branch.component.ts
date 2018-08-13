import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';
import {BranchModel} from './branch.model';
import {BranchService} from '../../../services/branch.service';
import {ListItem} from '../../../interfaces/list-item.interface';
import {TenantService} from '../../../services/tenant.service';
import {StorageService} from '../../../services/storage.service';
import {Constants} from '../../../common/constants';
import {LangService} from '../../../services/lang.service';

@Component({
    selector: 'app-branch',
    templateUrl: './branch.component.html',
    styleUrls: ['./branch.component.scss']
})
export class BranchComponent implements OnInit {
    form: FormGroup;
    submitted = false;

    model: BranchModel;
    isNew: boolean;

    parents: ListItem[];

    private labelSaved: string;
    private labelRemoved: string;
    private labelValidationError: string;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private branchService: BranchService,
                private storageService: StorageService,
                private langService: LangService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.route.params.subscribe(params => {
            const id = params['id'];

            if (id == -1) {
                this.prepareNewModel();
            }
            else {
                this.setupModel(id);
            }
        });
    }

    private setupLabels() {
        this.langService.get(Messages.SAVED).subscribe(m => this.labelSaved = m);
        this.langService.get(Messages.REMOVED).subscribe(m => this.labelRemoved = m);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe(m => this.labelValidationError = m);
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
        this.fetchParentListItems();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            name: new FormControl(this.model.name, [Validators.required, Validators.maxLength(128)]),
            description: new FormControl(this.model.description, [Validators.maxLength(1024)]),
            country: new FormControl(this.model.country, [Validators.required, Validators.maxLength(2)]),
            county: [this.model.county, Validators.maxLength(2)],
            city: [this.model.city, Validators.maxLength(255)],
            address: [this.model.address, Validators.maxLength(1024)],
            phones: [this.model.phones, Validators.maxLength(128)],
            parentId: [this.model.parentId]
        });
    }

    public onNameChange() {
        const control = this.form.controls.name;
        this.branchService.validateName(this.model.id, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                const errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    private fetchParentListItems() {
        const tenants = [];
        tenants.push(this.storageService.getItem(Constants.TENANT));
        this.branchService.fetchListItems(this.model.id, tenants).subscribe(parents => {
            parents.unshift({id: null, name: 'None'});
            this.parents = parents;
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(this.labelValidationError);
            return;
        }

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.branchService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labelSaved);
        });

    }

    public remove() {
        this.branchService.remove(this.model).subscribe(() => {
            this.toastr.success(this.labelRemoved);
            this.router.navigate(['/pages/manage-branches']);
        });
    }

}
