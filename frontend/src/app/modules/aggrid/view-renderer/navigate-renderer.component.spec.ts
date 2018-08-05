import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { NavigateRendererComponent } from './navigate-renderer.component';

describe('NavigateRendererComponent', () => {
  let component: NavigateRendererComponent;
  let fixture: ComponentFixture<NavigateRendererComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ NavigateRendererComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(NavigateRendererComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
