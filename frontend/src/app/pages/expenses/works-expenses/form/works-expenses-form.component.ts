import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {WorksExpenseModel} from './works-expense.model';
import {ModalService} from '../../../../services/modal.service';
import {AlertService} from '../../../../services/alert.service';
import {LangService} from '../../../../services/lang.service';
import {WorksExpenseService} from '../../../../services/expenses/works-expense.service';
import {ParcelService} from '../../../../services/parcel.service';
import {CropService} from '../../../../services/crop/crop.service';
import {FieldMapper} from '../../../../common/field.mapper';
import {WorkTypeService} from '../../../../services/work-type.service';
import {GeoLocalizedItem} from '../../../../interfaces/geo-localized-item.interface';
import {CropCategoryService} from '../../../../services/crop/crop-category.service';
import {MachineService} from "../../../../services/machine.service";

@Component({
    selector: 'app-works-expenses-form',
    templateUrl: './works-expenses-form.component.html',
    styleUrls: ['./works-expenses-form.component.scss']
})
export class WorksExpensesFormComponent implements OnInit {

    confirmationModalId = 'expense-remove-confirmation-modal';

    form: FormGroup;
    submitted = false;

    model: WorksExpenseModel;

    machines: any[];
    employees: any[];
    employeeMap: any;
    workTypes: any[];
    crops: any[];
    cropCategories: any[] = [];
    parcels: any[] = [];

    multiSelectSettings = {
        enableSearchFilter: true,
        badgeShowLimit: 1,
        text: this.langService.instant('select-dropdown.select'),
        selectAllText: this.langService.instant('select-dropdown.select-all'),
        unSelectAllText: this.langService.instant('select-dropdown.deselect-all'),
        searchPlaceholderText: this.langService.instant('select-dropdown.search-placeholder'),
        filterSelectAllText: this.langService.instant('select-dropdown.select-all-filtered'),
        filterUnSelectAllText: this.langService.instant('select-dropdown.deselect-all-filtered')
    };

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private modalService: ModalService,
                private workTypeService: WorkTypeService,
                private worksExpenseService: WorksExpenseService,
                private machineService: MachineService,
                private parcelService: ParcelService,
                private cropCategoryService: CropCategoryService,
                private cropService: CropService,
                private langService: LangService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.setupWorkTypes()
            .then(() => this.setupMachines())
            .then(() => this.setupParcels())
            .then(() => this.restoreModel());

        this.setupCropCategories();
    }

    private setupWorkTypes(): Promise<void> {
        const locale = this.langService.getLanguage();
        return new Promise((resolve) => {
            this.workTypeService.find().subscribe(data => {
                this.workTypes = data.map((item) => new GeoLocalizedItem(item, locale));
                resolve();
            });
        });
    }


    private setupMachines(): Promise<void> {

        return new Promise((resolve) => {
            this.machineService.findAll().subscribe(machineModels => {
                this.machines = [];
                this.employees = [];
                this.employeeMap = {};
                machineModels.forEach(machine => {
                    this.machines.push({
                        id: machine.id,
                        itemName: this.getMachineLabel(machine)
                    });
                    this.registerEmployees(machine.id, machine.employees);
                });
                resolve();
            });
        });
    }

    private getMachineLabel(model) {
        return model.brand + ' ' + model.model + ' (' + model.identifier + ')';
    }

    private registerEmployees(machineId, items) {
        if (!items) {
            return;
        }
        items.forEach(employee => {
            let listModel = this.employeeMap[employee.id];

            if (!listModel) {
                listModel = {
                    id: employee.id,
                    itemName: this.getEmployeeLabel(employee),
                    machineMap: {}
                };
                this.employeeMap[employee.id] = listModel;
                this.employees.push(listModel);
            }

            listModel.machineMap[machineId] = machineId;
        });
    }

    private getEmployeeLabel(model) {
        return model.firstName + ' ' + model.lastName;
    }

    public resetUnlinkedEmployees() {
        const machineIds = this.form.controls['machines'].value.map(m => m.id);
        const employeesControl = this.form.controls['employees'];
        const employees = employeesControl.value.filter(employee => {
            return machineIds.some(machineId => employee.machineMap[machineId]);
        });
        employeesControl.setValue(employees);
    }
    private setupParcels(): Promise<void> {
        return new Promise((resolve) => {
            this.parcelService.find().subscribe(models => {
                this.parcels = [];
                models.forEach(parcelModel => {
                    this.parcels.push({
                        id: parcelModel.id,
                        itemName: parcelModel.name
                    });
                });
                resolve();
            });
        });
    }


    private setupCropCategories() {
        const locale = this.langService.getLanguage();
        this.cropCategoryService.find().subscribe(data => {
            this.cropCategories = data.map((item) => new GeoLocalizedItem(item, locale));
        });
    }

    public onCropCategoryChange() {
        const cropCategoryId = this.form.controls['cropCategoryId'].value;
        this.setupCrops(cropCategoryId, true);
    }

    private setupCrops(cropCategoryId, updateValue) {
        const locale = this.langService.getLanguage();
        this.cropService.findCrops(cropCategoryId).subscribe(data => {
            const models = data.payload;
            this.crops = models.map((item) => new GeoLocalizedItem(item, locale));


            if (updateValue) {
                const cropControl = this.form.controls['cropId'];
                const value = this.crops.length > 0 ? this.crops[0].id : null;
                cropControl.setValue(value);
            }
        });
    }

    private getSelectedParcels() {
        let selectedItems = [];
        if (this.model.parcels && this.model.parcels.length > 0) {
            const map = {};
            this.parcels.forEach(item => map[item.id] = item);
            selectedItems = this.model.parcels.map(id => map[id]);
        }
        return selectedItems;
    }


    private getSelectedMachines() {

        let selectedMachines = [];
        if (this.model.machines && this.model.machines.length > 0) {
            const map = {};
            this.machines.forEach(item => map[item.id] = item);
            selectedMachines = this.model.machines.map(machineId => map[machineId]);
        }

        return selectedMachines;
    }

    private getSelectedEmployees() {

        let selectedEmployees = [];
        if (this.model.employees && this.model.employees.length > 0) {
            const map = {};
            this.employees.forEach(item => map[item.id] = item);
            selectedEmployees = this.model.employees.map(id => map[id]);
        }

        return selectedEmployees;
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
        this.worksExpenseService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new WorksExpenseModel();
        this.model.expenseDate = new Date();
        this.buildForm();
    }


    private buildForm() {
        const expenseDate = this.model.expenseDate ? new Date(this.model.expenseDate).toISOString().substring(0, 10) : null;

        this.form = this.fb.group({
            expenseDate: [expenseDate, Validators.required],
            workTypeId: [this.model.workTypeId, Validators.required],
            cropCategoryId: [this.model.cropCategoryId],
            cropId: [this.model.cropId, Validators.required],
            unitOfMeasure: [this.model.unitOfMeasure, Validators.required],
            quantity: [this.model.quantity || 0, Validators.required],
            quantityNorm: [this.model.quantityNorm || 0, Validators.required],
            quantityDefacto: [this.model.quantityDefacto || 0],
            price1Norm: [this.model.price1Norm || 0, Validators.required],
            sum: [this.model.sum || 0, Validators.required],
            parcels: [this.getSelectedParcels()],
            machines: [this.getSelectedMachines()],
            employees: [this.getSelectedEmployees()],
        });

    }

    public onQuantityChange () {
        this.calcSum();
    }

    public onPrice1NormChange() {
        this.calcSum();
    }

    public onActualExpenseChange() {
        this.calcSum();
    }

    private calcSum() {
        const quantity = Number(this.form.controls['quantity'].value);
        const quantityNorm = Number(this.form.controls['quantityNorm'].value);
        const quantityDefacto = Number(this.form.controls['quantityDefacto'].value);
        const sum = quantity * Math.random();
        this.form.controls['sum'].setValue(sum);
    }

    save() {
        this.submitted = true;

        if (!this.form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, this.form.value);
        this.submitted = false;

        this.model.parcels = this.form.value.parcels.map(item => item.id);

        console.log(this.model);

        this.worksExpenseService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    prepareRemove() {
        this.modalService.open(this.confirmationModalId);
    }

    remove() {
        this.worksExpenseService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }
}
