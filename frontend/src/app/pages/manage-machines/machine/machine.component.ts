import {Component, OnInit} from '@angular/core';
import {MachineService} from '../../../services/machine.service';
import {BrandService} from '../../../services/brand.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MachineModel} from './machine.model';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';
import {LangService} from '../../../services/lang.service';
import {WorkTypeService} from '../../../services/work-type.service';
import {AgroWorkTypeModel} from '../../reminder/agro-work-type.model';
import {FieldMapper} from '../../../common/field.mapper';
import {IMultiSelectSettings} from 'angular-2-dropdown-multiselect';


@Component({
    selector: 'app-machine',
    templateUrl: './machine.component.html',
    styleUrls: ['./machine.component.scss']
})
export class MachineComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: MachineModel;
    isNew: boolean;

    owners: string[];
    brands: string[];

    hasMotor: boolean;

    availableWorkTypes: AgroWorkTypeModel[];
    multiSelectSettings: IMultiSelectSettings = {
        checkedStyle: 'fontawesome',
        dynamicTitleMaxItems: 10,
        maxHeight: '100px'
    };

    private labels: any;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private workTypeService: WorkTypeService,
                private brandService: BrandService,
                private machineService: MachineService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupBrands();
        this.setupWorkTypes();
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
        this.labels = {};
        this.langService.get(Messages.SAVED).subscribe(m => this.labels[Messages.SAVED] = m);
        this.langService.get(Messages.REMOVED).subscribe(m => this.labels[Messages.REMOVED] = m);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe(m => this.labels[Messages.VALIDATION_FAIL] = m);
    }

    private setupBrands() {
        this.brandService.findAll().subscribe(models => {
            this.brands = models.map(model => model.name);
        });
    }

    private setupWorkTypes() {
        this.workTypeService.find().subscribe(models => {
            this.availableWorkTypes = models;
            const fieldMapper = new FieldMapper(this.langService.getLanguage());
            const nameField = fieldMapper.get('name');
            models.forEach(model => model.name = model[nameField]);
            console.log('wt', models);
        });
    }

    private setupModel(id) {
        this.machineService.findOne(id).subscribe(model => {
            this.model = model;
            this.hasMotor = model.motorType != 'NONE';
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new MachineModel();
        this.model.motorType = 'NONE';
        this.hasMotor = false;
        this.isNew = true;

        this.model.workTypes = [];
        this.buildForm();
    }

    private buildForm() {
        const fabricationDate = this.model.fabricationDate ? new Date(this.model.fabricationDate).toISOString().substring(0, 10) : null;

        this.form = this.fb.group({
            type: [this.model.type, Validators.required],
            brand: [this.model.brand, Validators.required],
            name: [this.model.name, Validators.required],
            identifier: [this.model.identifier, Validators.required],
            fabricationDate: [fabricationDate],
            fabricationCountry: [this.model.fabricationCountry],
            motorType: [this.model.motorType, Validators.required],
            consumption: [{value: this.model.consumption, disabled: !this.hasMotor}],
            power: [{value: this.model.power, disabled: !this.hasMotor}],
            speedOnRoad: [this.model.speedOnRoad],
            speedInWork: [this.model.speedInWork],
            workTypes: [this.model.workTypes]
        });
        console.log(this.model.workTypes);
    }

    public onIdentifierChange() {
        const control = this.form.controls.identifier;
        this.machineService.validateIdentifier(this.model.id, control.value).subscribe((isUnique) => {
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
            this.toastr.warning(this.labels[Messages.VALIDATION_FAIL]);
            return;
        }
        console.log(form.value);

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.machineService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labels[Messages.SAVED]);
        });
    }

    public remove() {
        this.machineService.remove(this.model).subscribe(() => {
            this.toastr.success(this.labels[Messages.REMOVED]);
            this.router.navigate(['/pages/manage-machines']);
        });
    }

    public onMotorTypeChange(event) {
        const motorType = this.form.controls['motorType'].value;
        this.hasMotor = motorType != 'NONE';

        const action = this.hasMotor ? 'enable' : 'disable';
        this.form.controls['consumption'][action]();
        this.form.controls['power'][action]();

        if (!this.hasMotor) {
            this.form.controls['consumption'].setValue(null);
            this.form.controls['power'].setValue(null);
        }
    }
}
