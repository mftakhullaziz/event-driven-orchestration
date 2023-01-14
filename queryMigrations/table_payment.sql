create table payments_table (
    payment_id uuid primary key default uuid_generate_v4(),
    user_id uuid default null,
    credit_name varchar(100),
    credit_number int8,
    credit_amount double precision default null,
    credit_status varchar(20) default null,
    date_index int8 default 0,
    creation_at timestamp default current_timestamp,
    creation_by varchar(100) default null,
    last_updated_at timestamp default current_timestamp,
    last_updated_by varchar(100) default null
);