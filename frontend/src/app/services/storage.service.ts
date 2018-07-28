import {Injectable} from '@angular/core';
import {Constants} from "../common/constants";
import {Authorities} from "../common/authorities";

@Injectable({
    providedIn: 'root'
})
export class StorageService {

    private storage;

    constructor() {
        this.storage = localStorage;
    }

    public clear() {
        this.storage.removeItem(Constants.USER_DATA);
        this.storage.removeItem(Constants.TENANT);
        this.storage.removeItem(Authorities.AUTHORITY_OBJECT);
        this.storage.removeItem(Constants.LANGUAGE_KEY);
    }

    public setItem(key, value) {
        this.storage.setItem(key, value);
    }

    public removeItem(key) {
        this.storage.removeItem(key);
    }

    public getItem(key) {
        return this.storage.getItem(key);
    }

}
