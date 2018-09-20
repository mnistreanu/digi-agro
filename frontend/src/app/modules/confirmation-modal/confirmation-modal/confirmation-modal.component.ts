import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';

@Component({
    selector: 'app-confirmation-modal',
    templateUrl: './confirmation-modal.component.html',
    styleUrls: ['./confirmation-modal.component.scss']
})
export class ConfirmationModalComponent implements OnInit {

    @Input() id;
    @Input() body = 'confirmation.confirm-removing';

    @Output() canceled: EventEmitter<void> = new EventEmitter();
    @Output() confirmed: EventEmitter<void> = new EventEmitter();

    constructor() {
    }

    ngOnInit() {
    }

    cancel() {
        this.canceled.emit();
    }

    confirm() {
        this.confirmed.emit();
    }

}
