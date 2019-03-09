create table weather_forecast_daily (
    id  bigserial not null,
    city_id int4,
    cnt int4,
    code varchar(255),
    country_code varchar(255),
    lat float8,
    lon float8,
    message float8,
    name varchar(255),
    primary key (id)
);

create table weather_forecast_daily_item (
    id  bigserial not null,
    day float8,
    description varchar(255),
    dt timestamp,
    eve float8,
    forecast_daily_id int8,
    humidity int4,
    icon varchar(255),
    main varchar(255),
    max float8,
    min float8,
    morn float8,
    night float8,
    pressure float8,
    weather_id int4,
    primary key (id)
);

create table weather_forecast_hour (
    id  bigserial not null,
    city_id int4,
    cnt int4,
    code varchar(255),
    country_code varchar(255),
    lat float8,
    lon float8,
    message float8,
    name varchar(255),
    primary key (id)
);

create table weather_forecast_hour_item (
    id  bigserial not null,
    clouds int4,
    deg float8,
    description varchar(255),
    dt timestamp,
    dt_txt varchar(255),
    forecast_hour_id int8,
    grnd_level float8,
    humidity int4,
    icon varchar(255),
    main varchar(255),
    pod varchar(255),
    pressure float8,
    rain_3h float8,
    sea_level float8,
    speed float8,
    temp float8,
    temp_kf int4,
    temp_max float8,
    temp_min float8,
    weather_id int4,
    primary key (id)
);

create table weather_history (
    id  bigserial not null,
    clouds int4,
    deg float8,
    description varchar(255),
    dt timestamp,
    humidity_air int4,
    humidity_soil int4,
    icon varchar(255),
    main varchar(255),
    open_weather_id int4,
    parcel_id int8,
    pressure float8,
    rain float8,
    snow float8,
    speed float8,
    temp_max float8,
    temp_min float8,
    uvi float8,
    weather_location_id int4,
    primary key (id)
);

create table weather_location (
    id int4 not null,
    country varchar(255),
    county varchar(255),
    lat float8,
    lon float8,
    name varchar(255),
    primary key (id)
);

create table weather_snapshot (
    id  bigserial not null,
    base varchar(255),
    clouds int4,
    cod int4,
    country_code varchar(255),
    county_id varchar(255),
    day_timestamp int8,
    deg float8,
    description varchar(255),
    dt timestamp,
    grnd_level float8,
    humidity int4,
    humidity_air int4,
    humidity_soil int4,
    icon varchar(255),
    lat float8,
    lon float8,
    main varchar(255),
    message float8,
    name varchar(255),
    openweather_id int4,
    parcel_id int8,
    pressure float8,
    rain_3h float8,
    sea_level float8,
    source_id int4,
    speed float8,
    sunrise timestamp,
    sunset timestamp,
    sys_id int4,
    sys_type int4,
    temp float8,
    temp_max float8,
    temp_min float8,
    weather_id int4,
    primary key (id)
);

alter table weather_history 
    add constraint uq_weather_location_id 
    unique (weather_location_id, dt);

alter table weather_forecast_daily_item 
    add constraint fk_forecast_daily_id 
    foreign key (forecast_daily_id) 
    references weather_forecast_daily;

alter table weather_forecast_hour_item 
    add constraint fk_forecast_hour_id 
    foreign key (forecast_hour_id) 
    references weather_forecast_hour;

alter table weather_history 
    add constraint fk_weather_location_id 
    foreign key (weather_location_id) 
    references weather_location;