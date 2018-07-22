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
        const language = 'en';
        this.translate.setDefaultLang(language);
        this.translate.use(language);
        localStorage.setItem(Constants.LANGUAGE_KEY, language);
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

    getLanguage() {
        return localStorage.getItem(Constants.LANGUAGE_KEY);
    }

    clear() {
        localStorage.removeItem(Constants.LANGUAGE_KEY);
        this.translate.use(this.translate.getDefaultLang());
    }

    get(key) {
        return this.translate.get(key);
    }
}
