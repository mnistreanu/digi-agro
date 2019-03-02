import {EditRendererComponent} from '../edit-renderer/edit-renderer.component';

export class AgEditColumnType {

    public static getType() {
        return  {
            field: 'edit',
            width: 24,
            minWidth: 24,
            maxWidth: 30,
            editable: false,
            suppressResize: true,
            suppressMenu: true,
            cellRendererFramework: EditRendererComponent,
            cellStyle: () => {
                return {padding: 0};
            }
        };
    }

}
