create table payments_historical_table (
    payment_history_id uuid primary key default uuid_generate_v4(),
    payment_id uuid default null,
    user_id uuid default null,
    product_id uuid default null,
    order_id uuid default null,
    total_payment_amount double precision default null,
    payment_status varchar(20) default null,
    date_index int8 default 0,
    creation_at timestamp default current_timestamp,
    creation_by varchar(100) default null,
    last_updated_at timestamp default current_timestamp,
    last_updated_by varchar(100) default null
);