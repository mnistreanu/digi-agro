import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { MachineGroupListComponent } from './machine-group-list.component';

describe('MachineGroupListComponent', () => {
  let component: MachineGroupListComponent;
  let fixture: ComponentFixture<MachineGroupListComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ MachineGroupListComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(MachineGroupListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
