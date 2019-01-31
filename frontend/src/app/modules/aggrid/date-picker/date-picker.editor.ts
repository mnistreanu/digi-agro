declare const $: any;

/**
 * JQuery DatePicker
 */
export class DatePicker {

    element: any;

    init(params) {

        const date = params.value;
        const dateStr = this.formatDate(date);

        this.element = document.createElement('input');
        this.element.value = dateStr;

        this.element.style.width = '100%';
        this.element.style.height = '100%';

        $(this.element).datepicker({dateFormat: 'dd/mm/yy'});
    }

    getGui() {
        return this.element;
    }

    afterGuiAttached() {
        this.element.focus();
        this.element.select();
    }

    getValue() {
        const dateStr = this.element.value;
        return this.parseDate(dateStr);
    }

    isPopup() {
        return false;
    }

    private formatDate(date) {
        if (!date) {
            return null;
        }

        return date.getDate() + '/' + (date.getMonth() + 1) + '/' + date.getFullYear();
    }

    private parseDate(dateStr) {
        const parts = dateStr.split('/');

        if (parts.length !== 3) {
            return null;
        }

        const date = +parts[0];
        const month = +parts[1] - 1;
        const year = +parts[2];

        return new Date(year, month, date);
    }

}
