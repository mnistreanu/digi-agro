create table event_type (
    id bigserial not null,
    tenant_id int8,
    name varchar(255),
    description varchar(255),
    parent_id int8,
    active boolean default true,
    primary key (id)
);

alter table event_type
    add constraint fk_tenant_id
    foreign key (tenant_id)
    references tenant;

alter table event_type
    add constraint fk_parent_id
    foreign key (parent_id)
    references event_type;
