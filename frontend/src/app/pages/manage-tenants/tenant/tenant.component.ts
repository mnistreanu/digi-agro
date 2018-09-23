import {Component, OnInit} from '@angular/core';
import {TenantModel} from './tenant.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {TenantService} from '../../../services/tenant.service';
import {GeoService} from '../../../services/geo.service';
import {LangService} from '../../../services/lang.service';
import {GeoLocalizedItem} from '../../../interfaces/geo-localized-item.interface';
import {AlertService} from '../../../services/alert.service';

@Component({
    selector: 'app-tenant',
    templateUrl: './tenant.component.html',
    styleUrls: ['./tenant.component.scss']
})
export class TenantComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: TenantModel;
    isNew: boolean;

    countries: GeoLocalizedItem[];
    counties: GeoLocalizedItem[];
    cities: GeoLocalizedItem[];

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private geoService: GeoService,
                private langService: LangService,
                private tenantService: TenantService,
                private alertService: AlertService) {
    }

    ngOnInit() {
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

    private setupModel(id) {
        this.tenantService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new TenantModel();
        this.isNew = true;
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
            fiscalCode: [this.model.fiscalCode],
            country: new FormControl(this.model.country, [Validators.required, Validators.maxLength(2)]),
            county: [this.model.county, Validators.maxLength(2)],
            city: [this.model.city, Validators.maxLength(255)],
            address: [this.model.address, Validators.maxLength(1024)],
            phones: [this.model.phones, Validators.maxLength(128)],
        });

        this.setupCountries();
        this.setupCounties(this.model.country);
        this.setupCities(this.model.country, this.model.county);
    }

    public onNameChange() {
        const control = this.form.controls.name;
        this.tenantService.validateName(this.model.id, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                const errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });
    }

    public onFiscalCodeChange() {
        const control = this.form.controls.fiscalCode;
        this.tenantService.validateFiscalCode(this.model.id, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                const errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
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

        this.tenantService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });

    }

    public remove() {
        this.tenantService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
