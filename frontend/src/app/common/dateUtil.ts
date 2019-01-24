import {DatePipe} from '@angular/common';

export class DateUtil {

    public static formatDateDB(date: Date): string {
        const datePipe = new DatePipe('en-US');
        return datePipe.transform(date, 'yyyyMMdd');
    }

    public static formatDateISO(date: Date): string {
        const datePipe = new DatePipe('en-US');
        return datePipe.transform(date, 'yyyy-MM-dd');
    }

    public static formatDateWithTime(date: Date): string {
        const datePipe = new DatePipe('en-US');
        return datePipe.transform(date, 'dd/MM/yyyy hh:mm');
    }


    public static formatDate(date: Date): string {
        const datePipe = new DatePipe('en-US');
        return datePipe.transform(date, 'dd.MM.yyyy');
    }

    public static formatLocalizedDate(value) {
        if (!value) {
            return '';
        }
        return moment(value).format('D MMMM , hh:mm');
    }

    public static compareWithoutTime(d1, d2) {

        if (!(d1 instanceof Date)) {
            d1 = new Date(d1);
        }
        if (!(d2 instanceof Date)) {
            d2 = new Date(d2);
        }

        d1 = new Date(d1.getFullYear(), d1.getMonth(), d1.getDate());
        d2 = new Date(d2.getFullYear(), d2.getMonth(), d2.getDate());

        const a = d2.getTime();
        const b = d1.getTime();

        if (a == b) {
            return 0;
        }

        return a > b ? 1 : -1;
    }

}
