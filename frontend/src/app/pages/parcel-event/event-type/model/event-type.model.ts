export class EventTypeModel {
    id: number;
    name: String;
    description: String;

    parentId: number;
    children: EventTypeModel[];
}
