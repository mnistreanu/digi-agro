import {Component, OnInit} from "@angular/core";
import {MachineService} from "../../../services/machine.service";
import {BrandService} from "../../../services/brand.service";
import {OwnerService} from "../../../services/owner.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {MachineModel} from "./machine.model";
import {ToastrService} from "ngx-toastr";
import {Messages} from "../../../common/messages";

@Component({
    selector: 'az-machine',
    templateUrl: './machine.component.html',
    styleUrls: ['./machine.component.scss']
})
export class MachineComponent implements OnInit {

    form: FormGroup;
    submitted: boolean = false;

    model: MachineModel;
    isNew: boolean;

    machineTypes: string[];

    owners: string[];
    brands: string[];

    motorTypes: string[];
    disableMotorCharacteristic: boolean = true;

    availableWorkTypes: string[] = ['SOWING', 'PLOWING', 'GATHERING', 'WATERING', 'SPRAY'];

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private ownerService: OwnerService,
                private brandService: BrandService,
                private machineService: MachineService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupMachineTypes();
        this.setupMotorTypes();
        this.setupOwners();
        this.setupBrands();
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

    private setupMachineTypes() {
        this.machineTypes = ['TRACTOR', 'COMBINE', 'SOWING'];
    }

    private setupOwners() {
        this.ownerService.findAll().subscribe(models => {
            this.owners = models.map(model => model.name);
        });
    }

    private setupBrands() {
        this.brandService.findAll().subscribe(models => {
            this.brands = models.map(model => model.name);
        });
    }

    private setupMotorTypes() {
        this.motorTypes = ['NONE', 'ELECTRIC', 'DIESEL', 'BENZINE'];
    }

    private setupModel(id) {
        this.machineService.findOne(id).subscribe(model => {
            this.model = model;
            this.disableMotorCharacteristic = model.motorType == 'NONE';
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new MachineModel();
        this.model.motorType = 'NONE';
        this.isNew = true;

        this.model.workTypes = [];
        this.buildForm();
    }

    private buildForm() {
        const fabricationDate = this.model.fabricationDate ? new Date(this.model.fabricationDate).toISOString().substring(0, 10) : null;

        this.form = this.fb.group({
            type: [this.model.type, Validators.required],
            owner: [this.model.owner, Validators.required],
            brand: [this.model.brand, Validators.required],
            name: [this.model.name, Validators.required],
            identifier: [this.model.identifier, Validators.required],
            fabricationDate: [fabricationDate],
            fabricationCountry: [this.model.fabricationCountry],
            motorType: [this.model.motorType, Validators.required],
            consumption: [{value: this.model.consumption, disabled: this.disableMotorCharacteristic}],
            power: [{value: this.model.power, disabled: this.disableMotorCharacteristic}],
            speedOnRoad: [this.model.speedOnRoad],
            speedInWork: [this.model.speedInWork],
            workTypesIndices: this.buildWorkTypeControls()
        });

    }

    private buildWorkTypeControls() {
        const arr = this.availableWorkTypes.map(work => {
            const checked = this.model.workTypes.indexOf(work) != -1;
            return this.fb.control(checked);
        });
        return this.fb.array(arr);
    }

    public validateIdentifierToUnique() {
        let control = this.form.controls.identifier;
        this.machineService.checkIdentifierUnique(this.model.id || -1, control.value).subscribe((isUnique) => {
            if (!isUnique) {
                let errors = control.errors || {};
                errors.unique = !isUnique;
                control.setErrors(errors);
            }
        });

    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(Messages.VALIDATION_ERROR);
            return;
        }

        this.prepareWorkTypesForSubmit(form);

        Object.assign(this.model, form.value);
        this.isNew = false;
        this.submitted = false;

        this.machineService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(Messages.SAVED);
        });

    }

    private prepareWorkTypesForSubmit(form: FormGroup) {
        let workTypes: string[] = [];

        // array of boolean items. true is selected
        form.value.workTypesIndices.forEach((item, index) => {
            if (item) {
                workTypes.push(this.availableWorkTypes[index]);
            }
        });

        this.model.workTypes = workTypes;
    }

    public remove() {
        this.machineService.remove(this.model).subscribe(() => {
            this.toastr.success(Messages.DELETED);
            this.router.navigate(['/pages/manage-machines']);
        });
    }

    public onMotorTypeChange(event) {
        let motorType = this.form.controls['motorType'].value;
        this.disableMotorCharacteristic = motorType == 'NONE';

        let action = this.disableMotorCharacteristic ? 'disable' : 'enable';
        this.form.controls['consumption'][action]();
        this.form.controls['power'][action]();

        if (this.disableMotorCharacteristic) {
            this.form.controls['consumption'].setValue(null);
            this.form.controls['power'].setValue(null);
        }
    }
}
