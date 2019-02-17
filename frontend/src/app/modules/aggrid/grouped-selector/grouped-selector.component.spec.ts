import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { GroupedSelectorComponent } from './grouped-selector.component';

describe('GroupedSelectorComponent', () => {
  let component: GroupedSelectorComponent;
  let fixture: ComponentFixture<GroupedSelectorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ GroupedSelectorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(GroupedSelectorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
