import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelEventListComponent } from './parcel-event-list.component';

describe('ParcelEventListComponent', () => {
  let component: ParcelEventListComponent;
  let fixture: ComponentFixture<ParcelEventListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelEventListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelEventListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
