import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelCropFormComponent } from './parcel-crop-form.component';

describe('ParcelCropFormComponent', () => {
  let component: ParcelCropFormComponent;
  let fixture: ComponentFixture<ParcelCropFormComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelCropFormComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelCropFormComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
