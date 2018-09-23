import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {EmployeeService} from '../../../services/employee.service';
import {EmployeeModel} from './employee.model';
import {MessageService} from '../../../services/message.service';

@Component({
    selector: 'app-employee',
    templateUrl: './employee.component.html',
    styleUrls: ['./employee.component.scss']
})
export class EmployeeComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: EmployeeModel;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private messageService: MessageService,
                private employeeService: EmployeeService) {
    }

    ngOnInit() {
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
            this.messageService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);
        this.submitted = false;

        this.employeeService.save(this.model).subscribe((model) => {
            this.model = model;
            this.messageService.saved();
            this.router.navigate(['../'], {relativeTo: this.route});
        });

    }

    public remove() {
        this.employeeService.remove(this.model).subscribe(() => {
            this.messageService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

}
