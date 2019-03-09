create table map_event (
    id  bigserial not null,
    active boolean default true,
    created_at timestamp,
    latitude Decimal(16, 6) default '0.000000',
    longitude Decimal(16, 6) default '0.000000',
    message varchar(255),
    user_account_id int8,
    primary key (id)
);

alter table map_event 
    add constraint fk_user_account_id 
    foreign key (user_account_id) 
    references user_account;