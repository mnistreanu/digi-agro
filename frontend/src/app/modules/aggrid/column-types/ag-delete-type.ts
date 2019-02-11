import {DeleteRendererComponent} from '../delete-renderer/delete-renderer.component';

export class AgDeleteColumnType {

    public static getType() {
        return             {
            field: 'delete',
            headerName: '',
            width: 24,
            minWidth: 24,
            maxWidth: 30,
            editable: false,
            suppressResize: true,
            suppressMenu: true,
            cellRendererFramework: DeleteRendererComponent,
            cellStyle: () => {
                return {padding: 0};
            }
        };
    }

}
