import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class ModalService {

    constructor() {
    }

    public open(modalId: string) {
        jQuery('#' + modalId).modal();
    }
}
