import {Component, OnInit} from '@angular/core';
import {ToastrService} from 'ngx-toastr';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LangService} from '../../../../services/lang.service';
import {MachineryExpenseModel} from './machinery-expense.model';
import {IMultiSelectOption, IMultiSelectSettings, IMultiSelectTexts} from 'angular-2-dropdown-multiselect';
import {MachineService} from '../../../../services/machine.service';
import {EmployeeService} from '../../../../services/employee.service';
import {Messages} from '../../../../common/messages';
import {MachineryExpenseService} from '../../../../services/machinery-expense.service';

@Component({
    selector: 'app-machinery-expenses-form',
    templateUrl: './machinery-expenses-form.component.html',
    styleUrls: ['./machinery-expenses-form.component.scss']
})
export class MachineryExpensesFormComponent implements OnInit {


    form: FormGroup;
    submitted = false;

    model: MachineryExpenseModel;

    machines: IMultiSelectOption[];
    employees: IMultiSelectOption[];

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

    private labels: any;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private machineryExpenseService: MachineryExpenseService,
                private machineService: MachineService,
                private employeeService: EmployeeService,
                private langService: LangService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
        this.setupMachines()
            .then(() => this.setupEmployees())
            .then(() => this.restoreModel());
    }

    private setupLabels() {
        this.labels = {};
        this.langService.get(Messages.SAVED).subscribe(m => this.labels[Messages.SAVED] = m);
        this.langService.get(Messages.REMOVED).subscribe(m => this.labels[Messages.REMOVED] = m);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe(m => this.labels[Messages.VALIDATION_FAIL] = m);
    }

    private setupMachines(): Promise<void> {
        return new Promise((resolve) => {
            this.machineService.findAll().subscribe(models => {
                this.machines = models.map(model => {
                    return {
                        id: model.id,
                        name: model.identifier + ' ' + model.model + ' ' + model.brand
                    };
                });
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
        this.machineryExpenseService.findOne(id).subscribe(model => {
            this.model = model;
            this.model.expenseDate = this.model.expenseDate ? new Date(this.model.expenseDate) : null;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new MachineryExpenseModel();
        this.buildForm();
    }


    private buildForm() {
        const expenseDate = this.model.expenseDate ? this.model.expenseDate.toISOString().substring(0, 10) : null;
        this.form = this.fb.group({
            title: [this.model.title, Validators.required],
            expenseDate: [expenseDate, Validators.required],
            machines: [this.model.machines || []],
            employees: [this.model.employees || []],
        });
    }

    public save() {
        this.submitted = true;

        if (!this.form.valid) {
            this.toastr.warning(this.labels[Messages.VALIDATION_FAIL]);
            return;
        }

        Object.assign(this.model, this.form.value);
        this.submitted = false;

        this.model.expenseItems.filter(item => {
            return !item.deleted || (item.deleted && item.id != null);
        });

        this.machineryExpenseService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labels[Messages.SAVED]);
        });
    }

}
