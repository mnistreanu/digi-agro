import { Component, OnInit } from '@angular/core';
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {OwnerService} from "../../../services/owner.service";
import {ActivatedRoute, Router} from "@angular/router";
import {OwnerModel} from "./owner.model";

@Component({
  selector: 'az-owner',
  templateUrl: './owner.component.html',
  styleUrls: ['./owner.component.scss']
})
export class OwnerComponent implements OnInit {

  form: FormGroup;
  submitted: boolean = false;

  model: OwnerModel;
  isNew: boolean;

  constructor(private fb: FormBuilder,
              private router: Router,
              private route: ActivatedRoute,
              private ownerService: OwnerService) {
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
    this.ownerService.findOne(id).subscribe(model => {
      this.model = model;
      this.buildForm();
    });
  }

  private prepareNewModel() {
    this.model = new OwnerModel();
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
    this.ownerService.checkNameUnique(this.model.id || -1, control.value).subscribe((isUnique) => {
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

    this.ownerService.save(this.model).subscribe((model) => {
      this.model = model;
    });

  }

  public remove() {
    this.ownerService.remove(this.model).subscribe(() => {
      this.router.navigate(['pages/manage-owners']);
    });
  }

}
