export class CropFieldMapper {

    private lang;

    constructor(lang) {
        // en -> En
        this.lang = lang[0].toUpperCase() + lang.substring(1);
    }

    public getName() {
        const field = 'name'; // nameEn
        return field + this.lang;
    }

    public getDescription() {
        const field = 'description';
        return field + this.lang;
    }
}
