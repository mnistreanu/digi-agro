import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {AlertService} from '../../../../services/alert.service';
import {ActivatedRoute, Router} from '@angular/router';
import {MachineGroupService} from '../../../../services/machine/machine-group.service';
import {MachineGroupModel} from './machine-group.model';

@Component({
    selector: 'app-machine-group',
    templateUrl: './machine-group.component.html',
    styleUrls: ['./machine-group.component.scss']
})
export class MachineGroupComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: MachineGroupModel;
    isNew: boolean;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private machineGroupService: MachineGroupService,
                private alertService: AlertService) {
    }

    ngOnInit() {
        this.restoreModel();
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
        this.machineGroupService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new MachineGroupModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {
        this.form = this.fb.group({
            name: [this.model.name, Validators.required]
        });
    }

    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.alertService.validationFailed();
            return;
        }

        Object.assign(this.model, form.value);

        this.isNew = false;
        this.submitted = false;

        this.machineGroupService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });
    }


    public remove() {
        this.machineGroupService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
