import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelDiagnosisFormComponent } from './parcel-diagnosis-form.component';

describe('ParcelDiagnosisFormComponent', () => {
  let component: ParcelDiagnosisFormComponent;
  let fixture: ComponentFixture<ParcelDiagnosisFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelDiagnosisFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelDiagnosisFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
