import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { PesticideListComponent } from './pesticide-list.component';

describe('PesticideListComponent', () => {
  let component: PesticideListComponent;
  let fixture: ComponentFixture<PesticideListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ PesticideListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(PesticideListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
