import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LangService} from '../../../../services/lang.service';
import {FuelExpenseModel} from './fuel-expense.model';
import {IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts} from 'angular-2-dropdown-multiselect';
import {MachineService} from '../../../../services/machine.service';
import {EmployeeService} from '../../../../services/employee.service';
import {Messages} from '../../../../common/messages';
import {FuelExpenseService} from '../../../../services/expenses/fuel-expense.service';
import {ModalService} from '../../../../services/modal.service';
import {AlertService} from '../../../../services/alert.service';

@Component({
    selector: 'app-fuel-expenses-form',
    templateUrl: './fuel-expenses-form.component.html',
    styleUrls: ['./fuel-expenses-form.component.scss']
})
export class FuelExpensesFormComponent implements OnInit {

    confirmationModalId = 'expense-remove-confirmation-modal';

    form: FormGroup;
    submitted = false;

    model: FuelExpenseModel;

    machines: any[];
    employees: IMultiSelectOption[];

    multiSelectSettings = {
        enableSearchFilter: true,
        badgeShowLimit: 1,
        text: this.langService.instant('select-dropdown.select'),
        selectAllText: this.langService.instant('select-dropdown.select-all'),
        unSelectAllText: this.langService.instant('select-dropdown.deselect-all'),
        searchPlaceholderText: this.langService.instant('select-dropdown.search-placeholder'),
        filterSelectAllText: this.langService.instant('select-dropdown.select-all-filtered'),
        filterUnSelectAllText: this.langService.instant('select-dropdown.deselect-all-filtered'),
    };

    public multiSelectControlSettings: IMultiSelectSettings = {
        checkedStyle: 'fontawesome',
        buttonClasses: 'btn btn-secondary btn-block',
        dynamicTitleMaxItems: 3,
        displayAllSelectedText: true,
        showCheckAll: true,
        showUncheckAll: true
    };

    public multiSelectControlTexts: IMultiSelectTexts = {
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
                private modalService: ModalService,
                private fuelExpenseService: FuelExpenseService,
                private machineService: MachineService,
                private employeeService: EmployeeService,
                private langService: LangService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.setupMachines()
            .then(() => this.setupEmployees())
            .then(() => this.restoreModel());
    }

    private setupMachines(): Promise<void> {
        return new Promise((resolve) => {
            this.machineService.findAll().subscribe(models => {
                this.machines = models.map(model => {
                    return {
                        id: model.id,
                        itemName: this.getMachineLabel(model)
                    };
                });
                resolve();
            });
        });
    }

    private getMachineLabel(model) {
        return model.identifier + ' ' + model.model + ' ' + model.brand;
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
        this.fuelExpenseService.findOne(id).subscribe(model => {
            this.model = model;
            this.model.expenseDate = this.model.expenseDate ? new Date(this.model.expenseDate) : null;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new FuelExpenseModel();
        this.buildForm();
    }


    private buildForm() {
        const expenseDate = this.model.expenseDate ? this.model.expenseDate.toISOString().substring(0, 10) : null;

        this.form = this.fb.group({
            title: [this.model.title, Validators.required],
            expenseDate: [expenseDate, Validators.required],
            machines: [this.getSelectedMachines()],
            employees: [this.model.employees || []],
        });
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

    save() {
        this.submitted = true;

        if (!this.form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, this.form.value);
        this.submitted = false;

        this.model.machines = this.form.value.machines.map(item => item.id);

        this.model.expenseItems = this.model.expenseItems.filter(item => {
            return !item.deleted || (item.deleted && item.id != null);
        });

        this.fuelExpenseService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    prepareRemove() {
        this.modalService.open(this.confirmationModalId);
    }

    remove() {
        this.fuelExpenseService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }
}
