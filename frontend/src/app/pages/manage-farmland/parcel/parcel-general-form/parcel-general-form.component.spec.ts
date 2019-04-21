import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelGeneralFormComponent } from './parcel-general-form.component';

describe('ParcelGeneralFormComponent', () => {
  let component: ParcelGeneralFormComponent;
  let fixture: ComponentFixture<ParcelGeneralFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelGeneralFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelGeneralFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
