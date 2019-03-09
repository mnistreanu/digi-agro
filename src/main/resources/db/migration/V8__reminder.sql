create table reminder (
    id  bigserial not null,
    created_by int8,
    description varchar(255),
    ending timestamp,
    starting timestamp,
    tenant_id int8,
    title varchar(255),
    agro_work_type_id int8,
    primary key (id)
);

alter table reminder 
    add constraint fk_agro_work_type_id 
    foreign key (agro_work_type_id) 
    references agro_work_type;
