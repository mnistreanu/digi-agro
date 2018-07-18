import {Injectable} from '@angular/core';
import {TranslateService} from "@ngx-translate/core";
import {Constants} from "../common/constants";

@Injectable({
    providedIn: 'root'
})
export class LangService {

    constructor(private translate: TranslateService) {
    }

    setupDefault() {
        this.translate.setDefaultLang('en');
        this.translate.use('en');
    }

    restoreLanguage() {
        let language = localStorage.getItem(Constants.LANGUAGE_KEY);
        if (language) {
            this.translate.use(language);
        }
        else {
            this.setupDefault();
        }
    }

    setLanguage(language) {
        if (language) {
            localStorage.setItem(Constants.LANGUAGE_KEY, language);
            this.translate.use(language);
        }
        else {
            this.setupDefault();
        }
    }

    clear() {
        localStorage.removeItem(Constants.LANGUAGE_KEY);
        this.translate.use(this.translate.getDefaultLang());
    }

    get(key) {
        return this.translate.get(key);
    }
}
