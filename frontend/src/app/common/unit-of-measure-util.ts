import {UnitOfMeasure} from '../enums/unit-of-measure.enum';
export class UnitOfMeasureUtil {

    public static formatKgHa(value: number): string {
        if (value) {
            return value + ' ' + UnitOfMeasure.Kilogram + '/' + UnitOfMeasure.Hectare;
        } else {
            return '';
        }
    }
}
