create table agro_work (
    id  bigserial not null,
    created_at timestamp,
    created_by int8,
    parcel_crop_id int8,
    tenant_id int8,
    title varchar(255),
    work_date timestamp,
    work_type_id int8,
    primary key (id)
);

create table agro_work_item (
    id  bigserial not null,
    agro_work_id int8,
    item_title varchar(255),
    item_value varchar(2048),
    primary key (id)
);

create table agro_work_type (
    id  bigserial not null,
    name_ro varchar(255),
    name_ru varchar(255),
    parent_id int4,
    tenant_id int8,
    primary key (id)
);

alter table agro_work 
    add constraint fk_work_type
    foreign key (work_type_id) 
    references agro_work_type;