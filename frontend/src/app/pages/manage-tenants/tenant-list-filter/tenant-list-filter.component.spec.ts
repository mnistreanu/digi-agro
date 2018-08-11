import {async, ComponentFixture, TestBed} from '@angular/core/testing';

import {TenantListFilterComponent} from './tenant-list-filter.component';

describe('TenantListFilterComponent', () => {
    let component: TenantListFilterComponent;
    let fixture: ComponentFixture<TenantListFilterComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            declarations: [TenantListFilterComponent]
        })
            .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(TenantListFilterComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
