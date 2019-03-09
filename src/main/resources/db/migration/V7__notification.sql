create table notification (
    id  bigserial not null,
    created_at timestamp,
    date_to timestamp,
    message varchar(1024),
    seen_at timestamp,
    user_id int8,
    notification_type_id int8,
    primary key (id)
);

create table notification_subscription (
    id  bigserial not null,
    user_id int8,
    notification_type_id int8,
    primary key (id)
);

create table notification_type (
    id  bigserial not null,
    translation_key varchar(256),
    primary key (id)
);

alter table notification 
    add constraint fk_notification_type_id 
    foreign key (notification_type_id) 
    references notification_type;

alter table notification_subscription 
    add constraint fk_notification_type_id 
    foreign key (notification_type_id) 
    references notification_type;