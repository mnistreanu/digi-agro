import {Component, OnInit} from '@angular/core';
import {MachineService} from '../../../../services/machine.service';
import {BrandService} from '../../../../services/brand.service';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {MachineModel} from './machine.model';
import {Messages} from '../../../../common/messages';
import {LangService} from '../../../../services/lang.service';
import {WorkTypeService} from '../../../../services/work-type.service';
import {AgroWorkTypeModel} from '../../../reminder/agro-work-type.model';
import {FieldMapper} from '../../../../common/field.mapper';
import {EmployeeService} from '../../../../services/employee.service';
import {EmployeeModel} from '../../manage-employees/employee/employee.model';
import {IMultiSelectSettings, IMultiSelectTexts} from 'angular-2-dropdown-multiselect';
import {ListItem} from '../../../../interfaces/list-item.interface';
import {AlertService} from '../../../../services/alert.service';
import {MachineGroupModel} from '../../machine-groups/machine-group/machine-group.model';
import {MachineGroupService} from '../../../../services/machine/machine-group.service';


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

    employees: ListItem[];
    workTypes: AgroWorkTypeModel[];
    machineGroups: MachineGroupModel[];

    multiSelectSettings: IMultiSelectSettings = {
        checkedStyle: 'fontawesome',
        buttonClasses: 'btn btn-secondary btn-block',
        dynamicTitleMaxItems: 3,
        displayAllSelectedText: true
    };
    multiSelectTexts: IMultiSelectTexts = {
        checkAll: this.langService.instant(Messages.SELECT_ALL),
        uncheckAll: this.langService.instant(Messages.UNSELECT_ALL),
        checked: this.langService.instant(Messages.ITEM_SELECTED),
        checkedPlural: this.langService.instant(Messages.ITEMS_SELECTED),
        defaultTitle: this.langService.instant(Messages.PLEASE_SELECT),
        allSelected: this.langService.instant(Messages.ALL_SELECTED),
    };


    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private langService: LangService,
                private workTypeService: WorkTypeService,
                private brandService: BrandService,
                private machineService: MachineService,
                private machineGroupService: MachineGroupService,
                private employeeService: EmployeeService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.setupBrands()
            .then(() => this.setupEmployees())
            .then(() => this.setupWorkTypes())
            .then(() => this.setupMachineGroups())
            .then(() => this.restoreModel());
    }

    private setupBrands(): Promise<void> {
        return new Promise((resolve) => {
            this.brandService.findAll().subscribe(models => {
                this.brands = models.map(model => model.name);
                resolve();
            });
        });
    }

    private setupEmployees(): Promise<void> {
        return new Promise((resolve) => {
            this.employeeService.findAll().subscribe(models => {
                this.employees = models.map(model => {
                    return {
                        id: model.id,
                        name: model.firstName + ' ' + model.lastName
                    };
                });
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

    private setupMachineGroups(): Promise<void> {
        return new Promise((resolve) => {
            this.machineGroupService.find().subscribe(models => {
                this.machineGroups = models;
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
        this.model.employees = [];
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            type: [this.model.type, Validators.required],
            brand: [this.model.brand, Validators.required],
            model: [this.model.model, Validators.required],
            identifier: [this.model.identifier, Validators.required],
            fabricationYear: [this.model.fabricationYear],
            fabricationCountry: [this.model.fabricationCountry],
            motorType: [this.model.motorType, Validators.required],
            consumption: [{value: this.model.consumption, disabled: !this.hasMotor}],
            power: [{value: this.model.power, disabled: !this.hasMotor}],
            speedOnRoad: [this.model.speedOnRoad],
            speedInWork: [this.model.speedInWork],
            employees: [this.model.employees.map(e => e.id)],
            machineGroupId: [this.model.machineGroupId],
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
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);
        this.model.workTypes = this.getWorkTypes(form);
        this.model.employees = this.getEmployees(form);

        this.isNew = false;
        this.submitted = false;

        this.machineService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
            this.router.navigate(['../'], {relativeTo: this.route});
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

    private getEmployees(form: FormGroup): EmployeeModel[] {
        const employeeMap = {};
        this.employees.forEach(e => employeeMap[e.id] = e);

        return form.value.employees.map((employeeId) => employeeMap[employeeId]);
    }

    public remove() {
        this.machineService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
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

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }
}
