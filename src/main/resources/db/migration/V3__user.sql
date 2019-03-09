create table user_account (
    id  bigserial not null,
    active boolean default true,
    address varchar(255),
    email varchar(255),
    first_name varchar(255),
    language varchar(255),
    last_name varchar(255),
    logo_url varchar(255),
    mobile_phone varchar(255),
    password varchar(255),
    phone varchar(255),
    username varchar(255),
    primary key (id)
);

create table authority (
    id  bigserial not null,
    name varchar(255),
    primary key (id)
);

create table user_authority (
    user_id int8 not null,
    authority_id int8 not null
);

create table tenant_user (
    user_id int8 not null,
    tenant_id int8 not null
);

alter table tenant_user 
    add constraint fk_tenant_id 
    foreign key (tenant_id) 
    references tenant;

alter table tenant_user 
    add constraint fk_user_id 
    foreign key (user_id) 
    references user_account;

alter table user_authority 
    add constraint fk_authority_id 
    foreign key (authority_id) 
    references authority;

alter table user_authority 
    add constraint fk_user_id 
    foreign key (user_id) 
    references user_account;
