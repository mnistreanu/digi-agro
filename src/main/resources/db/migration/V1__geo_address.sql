create table city (
    id  bigserial not null,
    country_id varchar(2),
    county_id varchar(2),
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);

create table country (
    id varchar(2) not null,
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);

create table county (
    id varchar(2) not null,
    country_id varchar(255),
    name_ro varchar(255),
    name_ru varchar(255),
    primary key (id)
);
