import {GeoItem} from './geo-item.interface';
import {FieldMapper} from '../common/field.mapper';

export class GeoLocalizedItem {

    id: any;
    name: string;

    constructor(item: GeoItem, locale: string) {
        this.id = item.id;
        const fieldMapper = new FieldMapper(locale);
        this.name = item[fieldMapper.get('name')];
    }

}
