import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from '@angular/router';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {SowingExpenseModel} from './sowing-expense.model';
import {ModalService} from '../../../../services/modal.service';
import {AlertService} from '../../../../services/alert.service';
import {LangService} from '../../../../services/lang.service';
import {SowingExpenseService} from '../../../../services/expenses/sowing-expense.service';
import {ParcelService} from '../../../../services/parcel.service';
import {CropService} from '../../../../services/crop/crop.service';
import {FieldMapper} from '../../../../common/field.mapper';

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

    crops: any[];
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
                private cropService: CropService,
                private langService: LangService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.setupParcels()
            .then(() => this.setupCrops())
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

    private setupCrops(): Promise<void> {
        return new Promise((resolve) => {
            const mapper = new FieldMapper(this.langService.getLanguage());
            const nameField = mapper.get('name');
            this.cropService.find().subscribe(models => {
                this.crops = models.map(model => {
                    return {
                        id: model.id,
                        itemName: model[nameField]
                    };
                });
                resolve();
            });
        });
    }

    public onCropChange() {
        this.form.controls['cropVarietyId'].setValue(null);
        const cropId = this.form.controls['cropId'].value;
        this.setupCropVarieties(cropId);
    }

    private setupCropVarieties(cropId) {

        if (cropId == null) {
            return;
        }

        const mapper = new FieldMapper(this.langService.getLanguage());
        const nameField = mapper.get('name');

        this.cropService.findVarieties(cropId).subscribe(payload => {
            const models = payload.payload || [];
            this.cropVarieties = models.map(model => {
                return {
                    id: model.id,
                    itemName: model[nameField]
                };
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
        const unitOfMeasure = this.model.unitOfMeasure || 'su';
        const cropId = this.model.cropModel ? this.model.cropModel.id : null;
        const cropVarietyId = this.model.cropVarietyModel ? this.model.cropVarietyModel.id : null;

        this.form = this.fb.group({
            expenseDate: [expenseDate, Validators.required],
            cropId: [cropId, Validators.required],
            cropVarietyId: [cropVarietyId],
            unitOfMeasure: [unitOfMeasure],
            normSown1Ha: [this.model.normSown1Ha || 0, Validators.required],
            normSownTotal: [this.model.normSownTotal || 0],
            actualSown1Ha: [this.model.actualSown1Ha || 0, Validators.required],
            actualSownTotal: [this.model.actualSownTotal || 0],
            unitPrice: [this.model.unitPrice || 0, Validators.required],
            totalAmount: [this.model.totalAmount || 0, Validators.required],
            parcels: [this.getSelectedParcels()],
            area: [this.model.area || 0, Validators.required]
        });

        this.setupCropVarieties(cropId);
    }

    public onAriaChange() {
        this.calcNorm();
        this.calcActualExpense();

    }

    public onNormChange() {
        this.calcNorm();
    }

    public onActualExpenseChange() {
        this.calcActualExpense();
    }

    private calcNorm() {
        const area = Number(this.form.controls['area'].value);
        const normPerUnit = Number(this.form.controls['normSown1Ha'].value);
        const normTotal = area * normPerUnit;
        this.form.controls['normSownTotal'].setValue(normTotal);
    }

    private calcActualExpense() {
        const area = Number(this.form.controls['area'].value);
        const actualPerUnit = Number(this.form.controls['actualSown1Ha'].value);
        const actualTotal = area * actualPerUnit;
        this.form.controls['actualSownTotal'].setValue(actualTotal);
        this.calcTotalAmount();
    }

    private calcTotalAmount() {
        const sownTotal = Number(this.form.controls['actualSownTotal'].value);
        const unitPrice = Number(this.form.controls['unitPrice'].value);
        const totalAmount = sownTotal * unitPrice;
        this.form.controls['totalAmount'].setValue(totalAmount);
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

        this.sowingExpenseService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }

    prepareRemove() {
        this.modalService.open(this.confirmationModalId);
    }

    remove() {
        this.sowingExpenseService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.back();
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }
}
