create table branch (
    id  bigserial not null,
    address varchar(1024),
    city varchar(128),
    county_id varchar(2),
    description varchar(1024),
    name varchar(128) not null,
    phones varchar(128),
    parent_id int8,
    tenant_id int8,
    primary key (id)
);

create table user_branch (
    user_id int8 not null,
    branch_id int8 not null
);

alter table branch 
    add constraint fk_county_id 
    foreign key (county_id) 
    references county;

alter table branch 
    add constraint fk_parent_id 
    foreign key (parent_id) 
    references branch;

alter table branch 
    add constraint fk_tenant_id 
    foreign key (tenant_id) 
    references tenant;

alter table user_branch 
    add constraint fk_branch_id 
    foreign key (branch_id) 
    references branch;

alter table user_branch 
    add constraint fk_user_id 
    foreign key (user_id) 
    references user_account;