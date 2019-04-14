import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {AlertService} from '../../../../services/alert.service';
import {EventTypeModel} from '../model/event-type.model';
import {EventTypeService} from '../../../../services/event/event-type.service';

@Component({
    selector: 'app-event-type-form',
    templateUrl: './event-type-form.component.html',
    styleUrls: ['./event-type-form.component.scss']
})
export class EventTypeFormComponent implements OnInit {

    form: FormGroup;
    submitted = false;
    model: EventTypeModel;

    roots: EventTypeModel[];
    isRootCurrentModel = false;

    constructor(private fb: FormBuilder,
                private router: Router,
                private route: ActivatedRoute,
                private alertService: AlertService,
                private eventTypeService: EventTypeService) {
    }

    ngOnInit() {
        this.setupRoots();

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

    private setupRoots() {
        this.eventTypeService.getRoots().subscribe(models => {
            this.roots = models;
        });
    }

    private setupModel(id) {
        this.eventTypeService.findOne(id).subscribe(model => {
            this.model = model;
            this.isRootCurrentModel = model.parentId == null;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new EventTypeModel();
        this.buildForm();
    }

    private buildForm() {

        this.form = this.fb.group({
            parentId: [this.model.parentId],
            name: [this.model.name, Validators.required],
            description: [this.model.description],
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

        this.eventTypeService.save(this.model).subscribe((model) => {
            this.model = model;
            this.alertService.saved();
        });

    }

    public remove() {
        this.eventTypeService.remove(this.model).subscribe(() => {
            this.alertService.removed();
            this.router.navigate(['../'], {relativeTo: this.route});
        });
    }

    back() {
        this.router.navigate(['../'], {relativeTo: this.route});
    }

}
