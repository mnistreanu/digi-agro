create table forecast (
    id  bigserial not null,
    created_at timestamp,
    created_by int8,
    crop_category_id int8,
    crop_id int8,
    crop_variety_id int8,
    deleted_at timestamp,
    deleted_by int8,
    description varchar(1024),
    harvesting_year int4,
    name varchar(256),
    tenant_id int8,
    primary key (id)
);

create table forecast_harvest (
    id  bigserial not null,
    created_at timestamp,
    created_by int8,
    factor_name varchar(255),
    forecast_snapshot_id int8,
    quantity float8,
    primary key (id)
);

create table forecast_parcel (
    id  bigserial not null,
    forecast_snapshot_id int8,
    parcel_id int8,
    primary key (id)
);

create table forecast_snapshot (
    id  bigserial not null,
    created_at timestamp,
    currency varchar(255),
    forecast_id int8,
    unit_of_measure varchar(255),
    unit_price float8,
    primary key (id)
);
