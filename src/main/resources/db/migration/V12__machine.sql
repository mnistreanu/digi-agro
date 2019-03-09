create table machine (
    id  bigserial not null,
    active boolean default true,
    consumption float8,
    fabrication_country varchar(255),
    fabrication_year int4,
    identifier varchar(255),
    model varchar(255),
    motor_type varchar(255),
    power float8,
    speed_in_work float8,
    speed_on_road float8,
    type varchar(255),
    brand_id int8,
    machine_group_id int8,
    tenant_id int8,
    primary key (id)
);

create table machine_group (
    id  bigserial not null,
    active boolean default true,
    name varchar(255),
    tenant_id int8,
    primary key (id)
);

create table machine_employee (
    machine_id int8 not null,
    employee_id int8 not null
);

create table machine_work_type (
    machine_id int8 not null,
    work_type_id int8 not null
);

create table machine_telemetry (
    id  bigserial not null,
    active boolean default true,
    created_at timestamp,
    latitude Decimal(16, 6) default '0.000000',
    longitude Decimal(16, 6) default '0.000000',
    machine_id int8,
    user_account_id int8,
    primary key (id)
);

alter table machine 
    add constraint fk_tenant_id 
    foreign key (tenant_id) 
    references tenant;

alter table machine 
    add constraint fk_brand_id 
    foreign key (brand_id) 
    references brand;

alter table machine 
    add constraint fk_machine_group_id 
    foreign key (machine_group_id) 
    references machine_group;

alter table machine_employee 
    add constraint fk_employee_id 
    foreign key (employee_id) 
    references employee;

alter table machine_employee 
    add constraint fk_machine_id 
    foreign key (machine_id) 
    references machine;

alter table machine_work_type 
    add constraint fk_work_type_id 
    foreign key (work_type_id) 
    references agro_work_type;

alter table machine_work_type 
    add constraint fk_machine_id 
    foreign key (machine_id) 
    references machine;

alter table machine_telemetry 
    add constraint fk_machine_id 
    foreign key (machine_id) 
    references machine;

alter table machine_telemetry 
    add constraint fk_user_account_id 
    foreign key (user_account_id) 
    references user_account;
