import {DecimalPipe} from '@angular/common';

declare const $: any;


export class NumericUtil {

    public static isNumeric(number) {
        return $.isNumeric(number);
    }

    public static isInteger(number) {
        if (!this.isNumeric(number)) {
            return false;
        }

        return number % 1 === 0;
    }

    public static formatBoolean(val: boolean) {
        return (val == true ? '\u2713' : '');
    }

    public static format(number: number) {
        const pipe = new DecimalPipe('en-US');
        return pipe.transform(number, '1.0-2');
    }

    public static safeDiv(a: number, b: number) {
        return b == null ? null : a / b;
    }

}
