create table crop (
    id  bigserial not null,
    crop_category_id int8,
    icon varchar(255),
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);

create table crop_category (
    id  bigserial not null,
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);

create table crop_subculture (
    id  bigserial not null,
    description_ro varchar(4000),
    description_ru varchar(4000),
    name_ro varchar(255),
    name_ru varchar(255),
    crop_id int8,
    primary key (id)
);

create table crop_variety (
    id  bigserial not null,
    description_ro varchar(4000),
    description_ru varchar(4000),
    name_ro varchar(255),
    name_ru varchar(255),
    crop_id int8,
    crop_subculture_id int8,
    primary key (id)
);

alter table crop_subculture 
    add constraint fk_crop_id 
    foreign key (crop_id) 
    references crop;

alter table crop_variety 
    add constraint fk_crop_id 
    foreign key (crop_id) 
    references crop;

alter table crop_variety 
    add constraint fk_crop_subculture_id 
    foreign key (crop_subculture_id) 
    references crop_subculture;