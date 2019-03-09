create table tenant (
    id  bigserial not null,
    address varchar(1024),
    blocked_at timestamp,
    blocked_by int8,
    blocked_reason varchar(256),
    city_id int8,
    country_id VARCHAR(2) not null,
    county_id VARCHAR (2),
    deleted_at timestamp,
    deleted_by int8,
    description varchar(1024),
    fiscal_code varchar(255),
    name varchar(256) not null,
    phones varchar(128),
    primary key (id)
);