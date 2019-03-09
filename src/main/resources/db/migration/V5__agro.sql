create table fertilizer (
    id  bigserial not null,
    deleted_at timestamp,
    description_ro varchar(4096),
    description_ru varchar(4096),
    fertilizer_type varchar(255),
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);

create table pest (
    id  bigserial not null,
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);

create table pesticide (
    id  bigserial not null,
    active_substance varchar(255),
    description_ro varchar(4096),
    description_ru varchar(4096),
    name_ro varchar(255),
    name_ru varchar(255),
    pesticide_type varchar(255),
    pests_ro varchar(4098),
    pests_ru varchar(4098),
    toxicity_group int4,
    primary key (id)
);

create table pesticide_pest (
    pesticide_id int8 not null,
    pest_id int8 not null
);

alter table pesticide_pest 
    add constraint fk_pest_id 
    foreign key (pest_id) 
    references pest;

alter table pesticide_pest 
    add constraint fk_pesticide_id 
    foreign key (pesticide_id) 
    references pesticide;