import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {CustomImageRendererComponent} from './custom-image-renderer.component';

describe('CustomImageRendererComponent', () => {
    let component: CustomImageRendererComponent;
    let fixture: ComponentFixture<CustomImageRendererComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [CustomImageRendererComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CustomImageRendererComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
