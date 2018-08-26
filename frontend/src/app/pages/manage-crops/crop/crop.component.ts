import {Component, OnInit} from '@angular/core';
import {TenantModel} from '../../manage-tenants/tenant/tenant.model';
import {FormBuilder, FormControl, FormGroup, Validators} from '@angular/forms';
import {ActivatedRoute, Router} from '@angular/router';
import {TenantService} from '../../../services/tenant.service';
import {ToastrService} from 'ngx-toastr';
import {Messages} from '../../../common/messages';
import {GeoService} from '../../../services/geo.service';
import {LangService} from '../../../services/lang.service';
import {GeoLocalizedItem} from '../../../interfaces/geo-localized-item.interface';
import { SelectItem } from '../../../dto/select-item.dto';
import { CropService } from '../../../services/crop.service';
import { CropModel } from '../crop-variety/crop.model';

@Component({
    selector: 'app-crop',
    templateUrl: './crop.component.html',
    styleUrls: ['./crop.component.scss']
})
export class CropComponent implements OnInit {

    form: FormGroup;
    submitted = false;

    model: CropModel;
    isNew: boolean;

    cropCategorySelectItems: SelectItem[] = [];

    private labelSaved: string;
    private labelRemoved: string;
    private labelValidationError: string;

    constructor(private router: Router,
                private route: ActivatedRoute,
                private geoService: GeoService,
                private langService: LangService,
                private cropService: CropService,
                private toastr: ToastrService) {
    }

    ngOnInit() {
        this.setupLabels();

        this.cropService.findCategoryItems().subscribe(data => {
            this.cropCategorySelectItems = data;
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

    private setupLabels() {
        this.langService.get(Messages.SAVED).subscribe(m => this.labelSaved = m);
        this.langService.get(Messages.REMOVED).subscribe(m => this.labelRemoved = m);
        this.langService.get(Messages.VALIDATION_FAIL).subscribe(m => this.labelValidationError = m);
    }


    private setupModel(id) {
        this.cropService.findOne(id).subscribe(model => {
            this.model = model;
            this.buildForm();
        });
    }

    private prepareNewModel() {
        this.model = new CropModel();
        this.isNew = true;
        this.buildForm();
    }

    private buildForm() {
        this.form = new FormGroup({
            nameRo: new FormControl(this.model.nameRo, [Validators.required, Validators.maxLength(128)]),
            nameRu: new FormControl(this.model.nameRu, [Validators.required, Validators.maxLength(128)]),
            cropCategoryId: new FormControl(this.model.cropCategoryId, [Validators.required])
        });
    }


    public save(form: FormGroup) {

        this.submitted = true;

        if (!form.valid) {
            this.toastr.warning(this.labelValidationError);
            return;
        }

        this.isNew = false;
        this.submitted = false;

        if (this.model.id) {
            this.cropService.update(this.model.id, this.form.value).subscribe((model) => {
                this.model = model;
                this.toastr.success(this.labelSaved);
            });
        } else {
            this.cropService.create(this.form.value).subscribe((model) => {
                this.model = model;
                this.toastr.success(this.labelSaved);
            });
        }

    }

    public remove() {
        this.cropService.remove(this.model.id).subscribe(() => {
            this.toastr.success(this.labelRemoved);
            this.router.navigate(['/pages/manage-crops']);
        });
    }

}
