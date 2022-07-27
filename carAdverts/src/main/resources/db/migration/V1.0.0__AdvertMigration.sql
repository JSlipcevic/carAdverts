--CREATE SEQUENCE IF NOT EXISTS advert_generator START with 50 INCREMENT BY 1;

CREATE TABLE advert
(
    id integer primary key,
    title varchar(255) not null,
    fuel_type varchar(20) not null,
    price integer,
    is_new varchar(5) not null,
    mileage integer,
    first_registration date,
    created_at timestamptz not null,
    updated_at timestamptz not null
)