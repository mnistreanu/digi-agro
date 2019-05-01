import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelDiagnosisMapComponent } from './parcel-diagnosis-map.component';

describe('ParcelDiagnosisMapComponent', () => {
  let component: ParcelDiagnosisMapComponent;
  let fixture: ComponentFixture<ParcelDiagnosisMapComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelDiagnosisMapComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelDiagnosisMapComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
