import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {LangService} from '../../../services/lang.service';
import {ActivatedRoute, Router} from '@angular/router';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';
import {EmployeeService} from '../../../services/employee.service';
import {EmployeeModel} from './employee.model';

@Component({
    selector: 'app-employee',
    templateUrl: './employee.component.html',
    styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: EmployeeModel;

    labels: any;

    constructor(private fb: FormBuilder,
                private langService: LangService,
                private router: Router,
                private route: ActivatedRoute,
                private employeeService: EmployeeService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();
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

    private setupModel(id) {
        this.employeeService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new EmployeeModel();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            title: [this.model.title],
            firstName: [this.model.firstName, Validators.required],
            lastName: [this.model.lastName, Validators.required]
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(this.labels[Messages.VALIDATION_FAIL]);
            return;
        }

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.employeeService.save(this.model).subscribe((model) => {
            this.model = model;
            this.toastr.success(this.labels[Messages.SAVED]);
            this.router.navigate(['/pages/employee']);
        });

    }

    public remove() {
        this.employeeService.remove(this.model).subscribe(() => {
            this.toastr.success(this.labels[Messages.REMOVED]);
            this.router.navigate(['/pages/employee']);
        });
    }

}
