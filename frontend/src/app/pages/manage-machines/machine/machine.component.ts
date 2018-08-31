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

    workTypes: AgroWorkTypeModel[];

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
        this.setupBrands()
        .then(() => this.setupWorkTypes())
        .then(() => this.restoreModel());
    }

    private setupLabels() {
        this.labels = {};
        this.langService.get(Messages.SAVED).subscribe(m => this.labels[Messages.SAVED] = m);
        this.langService.get(Messages.REMOVED).subscribe(m => this.labels[Messages.REMOVED] = m);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe(m => this.labels[Messages.VALIDATION_FAIL] = m);
    }

    private setupBrands(): Promise<void> {
        return new Promise((resolve) => {
            this.brandService.findAll().subscribe(models => {
                this.brands = models.map(model => model.name);
                resolve();
            });
        });
    }

    private setupWorkTypes(): Promise<void> {
        return new Promise((resolve) => {
            this.workTypeService.find().subscribe(models => {
                this.workTypes = models;
                const fieldMapper = new FieldMapper(this.langService.getLanguage());
                const nameField = fieldMapper.get('name');
                models.forEach(model => model.name = model[nameField]);
                resolve();
            });
        });
    }

    private restoreModel() {
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

        this.form = this.fb.group({
            type: [this.model.type, Validators.required],
            brand: [this.model.brand, Validators.required],
            name: [this.model.name, Validators.required],
            identifier: [this.model.identifier, Validators.required],
            fabricationYear: [this.model.fabricationYear],
            fabricationCountry: [this.model.fabricationCountry],
            motorType: [this.model.motorType, Validators.required],
            consumption: [{value: this.model.consumption, disabled: !this.hasMotor}],
            power: [{value: this.model.power, disabled: !this.hasMotor}],
            speedOnRoad: [this.model.speedOnRoad],
            speedInWork: [this.model.speedInWork],
            workTypeControls: this.buildWorkTypeControls()
        });
    }

    private buildWorkTypeControls() {
        const arr = this.workTypes.map(workTypeModel => {
            const checked = this.model.workTypes.some((wtId) => wtId == workTypeModel.id);
            return this.fb.control(checked);
        });
        return this.fb.array(arr);
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

        Object.assign(this.model, form.value);
        this.model.workTypes = this.getWorkTypes(form);

        this.isNew = false;
        this.submitted = false;

        this.machineService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labels[Messages.SAVED]);
            this.router.navigate(['/pages/manage-machines']);
        });
    }

    private getWorkTypes(form: FormGroup): number[] {
        const checkedItems: number[] = [];

        form.value.workTypeControls.forEach((checked, index) => {
            if (checked) {
                checkedItems.push(this.workTypes[index].id);
            }
        });

        return checkedItems;
    }

    public remove() {
        this.machineService.remove(this.model).subscribe(() => {
            this.toastr.success(this.labels[Messages.REMOVED]);
            this.router.navigate(['/pages/manage-machines']);
        });
    }

    public onMotorTypeChange() {
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
