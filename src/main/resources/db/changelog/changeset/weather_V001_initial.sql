create table sensor
(
    id         bigserial primary key,
    name       varchar(30) unique,
    created_at timestamptz default current_timestamp
);

create table measurements
(
    id         bigserial primary key,
    value      double precision not null,
    raining    boolean          not null,
    sensor_id  bigint references sensor (id) not null   ,
    created_at timestamptz default current_timestamp
);