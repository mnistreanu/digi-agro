create table crop_season (
    id  bigserial not null,
    end_date timestamp,
    harvest_year int4,
    start_date timestamp,
    tenant_id int8,
    yield_goal float8,
    crop_id int8,
    crop_subculture_id int8,
    crop_variety_id int8,
    primary key (id)
);

create table parcel_crop_season (
    id  bigserial not null,
    crop_variety_id int8,
    parcel_id int8,
    planted_at timestamp,
    plants_on_row int4,
    rows_on_parcel int4,
    space_between_plants float8,
    space_between_rows float8,
    yield_goal float8,
    crop_season_id int8,
    primary key (id)
);

alter table crop_season 
    add constraint fk_crop_id
    foreign key (crop_id) 
    references crop;

alter table crop_season 
    add constraint fk_crop_subculture_id 
    foreign key (crop_subculture_id) 
    references crop_subculture;

alter table crop_season 
    add constraint fk_crop_variety_id 
    foreign key (crop_variety_id) 
    references crop_variety;

alter table parcel_crop_season 
    add constraint fk_crop_season_id 
    foreign key (crop_season_id) 
    references crop_season;