create table expense (
    id  bigserial not null,
    category_id int8,
    cost numeric(19, 2),
    crop_season_id int8,
    date timestamp,
    description varchar(255),
    tenant_id int8,
    title varchar(255),
    primary key (id)
);

create table expense_category (
    id  bigserial not null,
    active boolean default true,
    description varchar(255),
    name varchar(255),
    parent_id int8,
    tenant_id int8,
    primary key (id)
);

alter table expense 
    add constraint fk_crop_season_id 
    foreign key (crop_season_id) 
    references crop_season;

alter table expense
    add constraint fk_expense_category_id
    foreign key (category_id)
    references expense_category;

alter table expense_category
    add constraint fk_parent_id 
    foreign key (parent_id) 
    references expense_category;