create table employee (
    id  bigserial not null,
    active boolean default true,
    first_name varchar(255),
    last_name varchar(255),
    title varchar(255),
    tenant_id int8,
    primary key (id)
);

alter table employee 
    add constraint fk_tenant_id 
    foreign key (tenant_id) 
    references tenant;


