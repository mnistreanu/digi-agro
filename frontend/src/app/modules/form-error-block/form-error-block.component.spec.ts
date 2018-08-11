import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {FormErrorBlockComponent} from './form-error-block.component';

describe('FormErrorBlockComponent', () => {
    let component: FormErrorBlockComponent;
    let fixture: ComponentFixture<FormErrorBlockComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [FormErrorBlockComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(FormErrorBlockComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
