import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SowingExpenseModel} from './sowing-expense.model';
import {ModalService} from '../../../../services/modal.service';
import {AlertService} from '../../../../services/alert.service';
import {LangService} from '../../../../services/lang.service';
import {SowingExpenseService} from '../../../../services/expenses/sowing-expense.service';
import {ParcelService} from '../../../../services/parcel.service';

@Component({
    selector: 'app-sowing-expenses-form',
    templateUrl: './sowing-expenses-form.component.html',
    styleUrls: ['./sowing-expenses-form.component.scss']
})
export class SowingExpensesFormComponent implements OnInit {

    confirmationModalId = 'expense-remove-confirmation-modal';

    form: FormGroup;
    submitted = false;

    model: SowingExpenseModel;

    crops: any[] = [];
    cropVarieties: any[] = [];
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
                private sowingExpenseService: SowingExpenseService,
                private parcelService: ParcelService,
                private langService: LangService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.setupParcels()
            .then(() => this.restoreModel());
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

    private getSelectedParcels() {
        let selectedItems = [];
        if (this.model.parcels && this.model.parcels.length > 0) {
            const map = {};
            this.parcels.forEach(item => map[item.id] = item);
            selectedItems = this.model.parcels.map(id => map[id]);
        }
        return selectedItems;
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
        this.sowingExpenseService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new SowingExpenseModel();
        this.model.expenseDate = new Date();
        this.buildForm();
    }


    private buildForm() {
        const expenseDate = this.model.expenseDate ? new Date(this.model.expenseDate).toISOString().substring(0, 10) : null;
        this.form = this.fb.group({
            expenseDate: [expenseDate, Validators.required],
            crop: [this.model.crop, Validators.required],
            variety: [this.model.variety, Validators.required],
            unitOfMeasure: [this.model.unitOfMeasure, Validators.required],
            normSown1Ha: [this.model.normSown1Ha],
            normSownTotal: [this.model.normSownTotal],
            actualSown1Ha: [this.model.actualSown1Ha],
            actualSownTotal: [this.model.actualSownTotal],
            unitPrice: [this.model.unitPrice],
            totalAmount: [this.model.totalAmount],
            parcels: [this.getSelectedParcels()],
            area: [this.model.area],
        });
        console.log(this.form);
    }

    save() {
        this.submitted = true;

        if (!this.form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, this.form.value);
        this.submitted = false;

        // this.sowingExpenseService.save(this.model).subscribe((model) => {
        //     this.model = model;
        //     this.alertService.saved();
        // });
    }

    prepareRemove() {
        this.modalService.open(this.confirmationModalId);
    }

    remove() {
        // this.sowingExpenseService.remove(this.model).subscribe(() => {
        //     this.alertService.removed();
        //     this.back();
        // });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }
}
