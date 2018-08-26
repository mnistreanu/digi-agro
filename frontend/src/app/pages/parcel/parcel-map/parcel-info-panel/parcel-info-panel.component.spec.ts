import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelInfoPanelComponent } from './parcel-info-panel.component';

describe('ParcelInfoPanelComponent', () => {
  let component: ParcelInfoPanelComponent;
  let fixture: ComponentFixture<ParcelInfoPanelComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelInfoPanelComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelInfoPanelComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
