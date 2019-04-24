create table parcel_event (
    id bigserial not null,

    parcel_id int8,
    event_type_id int8,
    event_type_detail_id int8,

    date timestamp,
    title varchar(255),
    description varchar(255),

    primary key (id)
);

alter table parcel_event
    add constraint fk_parcel_id
    foreign key (parcel_id)
    references parcel;

alter table parcel_event
    add constraint fk_event_type_id
    foreign key (event_type_id)
    references event_type;

alter table parcel_event
    add constraint fk_event_type_detail_id
    foreign key (event_type_detail_id)
    references event_type;
