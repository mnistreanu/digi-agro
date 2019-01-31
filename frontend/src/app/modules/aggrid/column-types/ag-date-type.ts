import {DateUtil} from '../../../common/dateUtil';
import {DatePicker} from '../date-picker/date-picker.editor';


export class AgDateColumnType {

    public static getType() {
        return {
            width: 90,
            minWidth: 90,
            valueFormatter: (params) => DateUtil.formatDate(params.value),
            filter: 'agDateColumnFilter',
            filterParams: {
                comparator: (d1, d2) => DateUtil.compareWithoutTime(d1, d2),
                browserDatePicker: true
            },
            cellEditor: DatePicker
        };
    }

}
