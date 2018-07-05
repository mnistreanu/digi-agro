import { Component, OnInit } from '@angular/core';
import {BrandService} from "../../../services/brand.service";
import {ActivatedRoute, Router} from "@angular/router";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {BrandModel} from "./brand.model";

@Component({
  selector: 'az-brand',
  templateUrl: './brand.component.html',
  styleUrls: ['./brand.component.scss']
})
export class BrandComponent implements OnInit {

  form: FormGroup;
  submitted: boolean = false;

  model: BrandModel;
  isNew: boolean;

  constructor(private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private brandService: BrandService) {
  }

  ngOnInit() {
    this.route.params.subscribe(params => {
      let id = params['id'];

      if (id == -1) {
        this.prepareNewModel();
      }
      else {
        this.setupModel(id);
      }
    });
  }

  private setupModel(id) {
    this.brandService.findOne(id).subscribe(model => {
      this.model = model;
      this.buildForm();
    });
  }

  private prepareNewModel() {
    this.model = new BrandModel();
    this.isNew = true;
    this.buildForm();
  }

  private buildForm() {

    this.form = this.fb.group({
      name: [this.model.name, Validators.required]
    });
  }

  public validateNameToUnique() {
    let control = this.form.controls.name;
    this.brandService.checkNameUnique(this.model.id || -1, control.value).subscribe((isUnique) => {
      if (!isUnique) {
        let errors = control.errors || {};
        errors.unique = !isUnique;
        control.setErrors(errors);
      }
    });

  }

  public save(form: FormGroup) {

    this.submitted = true;

    if (!form.valid) {
      return;
    }

    Object.assign(this.model, form.value);
    this.isNew = false;
    this.submitted = false;

    this.brandService.save(this.model).subscribe((model) => {
      this.model = model;
    });

  }

  public remove() {
    this.brandService.remove(this.model).subscribe(() => {
      this.router.navigate(['/pages/manage-brands']);
    });
  }

}
