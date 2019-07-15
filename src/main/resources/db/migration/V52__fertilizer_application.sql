create table fertilizer_application (
    id  bigserial not null,
    parcel_id int8,
    application_date timestamp,
    comments varchar(4096),
    placement_type varchar(255),
    fertilizer_id int8,
    tone_price numeric(19, 2),
    fertilized_area float8,
    rate float8,
    rate_unit_of_measure varchar(255),
    hectare_cost numeric(19, 2),
    primary key (id)
);