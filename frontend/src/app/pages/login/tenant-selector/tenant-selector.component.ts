import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ListItem} from '../../../interfaces/list-item.interface';
import {StorageService} from '../../../services/storage.service';
import {Constants} from '../../../common/constants';

@Component({
    selector: 'app-tenant-selector',
    templateUrl: './tenant-selector.component.html',
    styleUrls: ['./tenant-selector.component.scss']
})
export class TenantSelectorComponent implements OnInit {

    @Input() tenants: ListItem[];

    @Output() tenantSelected: EventEmitter<void> = new EventEmitter();

    tenantId: number;

    constructor(private storageService: StorageService) {
    }

    ngOnInit() {
        this.tenantId = this.tenants[0].id;
    }

    selectTenant() {
        this.storageService.setItem(Constants.TENANT, this.tenantId);
        this.tenantSelected.emit();
    }

}
