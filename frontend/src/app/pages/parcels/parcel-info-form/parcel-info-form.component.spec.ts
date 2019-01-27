import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelInfoFormComponent } from './parcel-info-form.component';

describe('ParcelInfoFormComponent', () => {
  let component: ParcelInfoFormComponent;
  let fixture: ComponentFixture<ParcelInfoFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelInfoFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelInfoFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
