import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { ParcelMapEditorComponent } from './parcel-map-editor.component';

describe('ParcelMapEditorComponent', () => {
  let component: ParcelMapEditorComponent;
  let fixture: ComponentFixture<ParcelMapEditorComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ ParcelMapEditorComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(ParcelMapEditorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
