create table order_table (
    order_id uuid primary key default uuid_generate_v4(),
    user_id int8 default null,
    product_id uuid,
    product_price double precision default null,
    product_amount double precision default null,
    order_status varchar(20) default null,
    date_index int8 default 0,
    creation_at timestamp default current_timestamp,
    creation_by varchar(100) default null,
    last_updated_at timestamp default current_timestamp,
    last_updated_by varchar(100) default null
);