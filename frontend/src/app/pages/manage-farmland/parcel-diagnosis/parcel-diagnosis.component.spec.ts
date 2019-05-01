import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelDiagnosisComponent } from './parcel-diagnosis.component';

describe('ParcelDiagnosisComponent', () => {
  let component: ParcelDiagnosisComponent;
  let fixture: ComponentFixture<ParcelDiagnosisComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelDiagnosisComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelDiagnosisComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
