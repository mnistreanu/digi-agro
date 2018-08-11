export class CropFieldMapper {

    private lang;

    constructor(lang) {
        // en -> En
        this.lang = lang[0].toUpperCase() + lang.substring(1);
    }

    public getName() {
        let field = 'name'; // nameEn
        return field + this.lang;
    }

    public getDescription() {
        let field = 'description';
        return field + this.lang;
    }
}