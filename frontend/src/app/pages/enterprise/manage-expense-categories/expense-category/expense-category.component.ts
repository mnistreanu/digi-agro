import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from '../../../../services/alert.service';
import {ExpenseCategoryModel} from './expense-category.model';
import {ExpensesService} from '../../../../services/expenses/expenses.service';

@Component({
    selector: 'app-expense-category',
    templateUrl: './expense-category.component.html',
    styleUrls: ['./expense-category.component.scss']
})
export class ExpenseCategoryComponent implements OnInit {

    form: FormGroup;
    submitted = false;
    model: ExpenseCategoryModel;
    mainCategories: ExpenseCategoryModel[] = [];

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private expensesService: ExpensesService) {
    }

    ngOnInit() {
        this.expensesService.findCategoriesTree().subscribe(payloadModel => {
            const models = payloadModel.payload;
            models.forEach((model: ExpenseCategoryModel) => {
                this.mainCategories.push(model);
            });
        });

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
        this.expensesService.findOneCategory(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new ExpenseCategoryModel();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            // id: [this.model.id, Validators.required],
            parentId: [this.model.parentId],
            name: [this.model.name, Validators.required],
            defaultName: [this.model.defaultName],
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.expensesService.saveCategory(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
            this.router.navigate(['../'], {relativeTo: this.route});
        });

    }

    // public remove() {
    //     this.expensesService.removeCategory(this.model).subscribe(() => {
    //         this.alertService.removed();
    //         this.router.navigate(['../'], {relativeTo: this.route});
    //     });
    // }

}
