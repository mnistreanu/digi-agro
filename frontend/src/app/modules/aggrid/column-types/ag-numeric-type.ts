import {NumericUtil} from '../../../common/numericUtil';

export class AgNumericColumnType {

    public static getType() {
        return {
            valueFormatter: (params) => NumericUtil.format(params.value),
            valueParser: (params) => {
                const newValue = params.newValue;
                if (!NumericUtil.isNumeric(newValue)) {
                    return params.oldValue;
                }
                return +newValue;
            },
            filter: 'agNumberColumnFilter',
            cellClass: 'ag-numeric-field'
        };
    }

}
