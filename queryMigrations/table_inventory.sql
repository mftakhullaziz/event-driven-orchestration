create table inventory_products (
    product_id uuid primary key default uuid_generate_v4(),
    product_name varchar(20) default null,
    product_price_per_unit double precision default null,
    product_amount double precision default null,
    product_status varchar(20) default null,
    date_index int8 default 0,
    creation_at timestamp default current_timestamp,
    creation_by varchar(100) default null,
    last_updated_at timestamp default current_timestamp,
    last_updated_by varchar(100) default null
);