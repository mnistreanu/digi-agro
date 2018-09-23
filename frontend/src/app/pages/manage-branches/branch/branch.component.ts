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
import {GeoLocalizedItem} from '../../../interfaces/geo-localized-item.interface';
import {GeoService} from '../../../services/geo.service';
import {TenantModel} from '../../manage-tenants/tenant/tenant.model';
import {AlertService} from '../../../services/alert.service';

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

    countries: GeoLocalizedItem[];
    counties: GeoLocalizedItem[];
    cities: GeoLocalizedItem[];

    private tenant: TenantModel;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private branchService: BranchService,
                private storageService: StorageService,
                private langService: LangService,
                private geoService: GeoService,
                private alertService: AlertService,
                private tenantService: TenantService) {
    }

    ngOnInit() {
        const tenantId = this.storageService.getItem(Constants.TENANT);
        this.tenantService.findOne(tenantId).subscribe(tenant => {
            this.tenant = tenant;
            this.route.params.subscribe(params => {
                const id = params['id'];

                if (id == -1) {
                    this.prepareNewModel();
                }
                else {
                    this.setupModel(id);
                }
            });
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
        this.model.country = this.tenant.country;
        this.model.county = this.tenant.county;
        this.model.city = this.tenant.city;
        this.fetchParentListItems();
        this.buildForm();
    }

    private setupCountries() {
        const locale = this.langService.getLanguage();
        this.geoService.getCountries().subscribe(data => {
            this.countries = data.map((item) => new GeoLocalizedItem(item, locale));
        });
    }

    public onCountryChange() {
        this.form.controls['county'].setValue(null);
        this.form.controls['city'].setValue(null);
        this.setupCounties(this.form.controls['country'].value);
    }

    private setupCounties(country) {
        if (!country) {
            this.counties = [];
            return;
        }
        const locale = this.langService.getLanguage();
        this.geoService.getCounties(country).subscribe(data => {
            this.counties = data.map((item) => new GeoLocalizedItem(item, locale));
        });
    }

    public onCountyChange() {
        this.form.controls['city'].setValue(null);
        const country = this.form.controls['country'].value;
        const county = this.form.controls['county'].value;
        this.setupCities(country, county);
    }

    private setupCities(country, county) {
        if (!country || !county) {
            this.cities = [];
            return;
        }
        const locale = this.langService.getLanguage();
        this.geoService.getCities(country, county).subscribe(data => {
            this.cities = data.map((item) => new GeoLocalizedItem(item, locale));
        });
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

        this.setupCountries();
        this.setupCounties(this.model.country);
        this.setupCities(this.model.country, this.model.county);
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
        tenants.push(this.tenant.id);
        this.branchService.fetchListItems(this.model.id, tenants).subscribe(parents => {
            parents.unshift({id: null, name: 'None'});
            this.parents = parents;
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.branchService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });

    }

    public remove() {
        this.branchService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
