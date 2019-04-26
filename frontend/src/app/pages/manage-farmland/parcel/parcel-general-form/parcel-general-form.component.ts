import {Component, Input, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ParcelModel} from '../parcel.model';
import {SelectItem} from '../../../../dto/select-item.dto';
import {GeoLocalizedItem} from '../../../../interfaces/geo-localized-item.interface';
import {GeoService} from '../../../../services/geo.service';
import {LangService} from '../../../../services/lang.service';
import {BranchService} from '../../../../services/branch.service';

@Component({
    selector: 'app-parcel-general-form',
    templateUrl: './parcel-general-form.component.html',
    styleUrls: ['./parcel-general-form.component.scss']
})
export class ParcelGeneralFormComponent implements OnInit {

    @Input() parcelModel: ParcelModel;

    form: FormGroup;
    forcedValidation: boolean;
    branches: SelectItem[] = [];
    counties: SelectItem[] = [];
    cities: SelectItem[] = [];

    constructor(private fb: FormBuilder,
                private langService: LangService,
                private branchService: BranchService,
                private geoService: GeoService) {
    }

    ngOnInit() {
        this.buildForm();
        this.setupBranches();
        this.setupCounties()
            .then(() => this.onCountyChange());
    }

    private setupBranches() {
        this.branchService.find().subscribe((data) => {
            this.branches = data.map((item) => new SelectItem(item.id, item.name));
        });
    }

    private setupCounties(): Promise<void> {
        return new Promise((resolve) => {
            const locale = this.langService.getLanguage();
            this.geoService.getCounties('md').subscribe(data => {
                this.counties = data.map((item) => new GeoLocalizedItem(item, locale));
            });
            resolve();
        });
    }

    public onCountyChange() {
        const countyId = this.form.controls['countyId'].value;
        this.setupCities(countyId);
    }

    private setupCities(countyId: string) {
        const locale = this.langService.getLanguage();
        this.geoService.getCities('md', countyId).subscribe(data => {
            this.cities = data.map((item) => new GeoLocalizedItem(item, locale));
        });
    }

    private buildForm() {
        this.form = this.fb.group({
            name: [this.parcelModel.name, Validators.required],
            branchId: [this.parcelModel.branchId, Validators.required],
            cadasterNumber: [this.parcelModel.cadasterNumber, Validators.required],
            area: [this.parcelModel.area],
            countyId: [this.parcelModel.countyId, Validators.required],
            cityId: [this.parcelModel.cityId]
        });
    }

    public submit() {
        this.forcedValidation = true;

        if (!this.form.valid) {
            return false;
        }

        Object.assign(this.parcelModel, this.form.value);

        this.forcedValidation = false;
        return true;
    }

}
