export class FieldMapper {
    private lang;

    constructor(lang) {
        // en -> En
        this.lang = lang[0].toUpperCase() + lang.substring(1);
    }

    public get(field) {
        return field + this.lang;
    }
}
