create table parcel (
    id  bigserial not null,
    active boolean default true,
    area float8,
    cadaster_number varchar(128),
    city_id int8,
    country_id varchar(2),
    county_id varchar(2),
    created_at timestamp,
    description varchar(512),
    land_worthiness_points int4,
    last_work_type_id int8,
    name varchar(128),
    parent_id int8,
    tenant_id int8,
    irrigated boolean default false,
    primary key (id)
);

create table parcel_geometry (
    parcel_id int8 not null,
    coordinates varchar(4096),
    lat_center numeric(10, 6),
    lon_center numeric(10, 6),
    primary key (parcel_id)
);

