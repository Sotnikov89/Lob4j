CREATE TABLE engine (
    id serial primary key,
    name varchar (50) not null,
    car_id int not null unique references car(id)
);

CREATE TABLE car (
    id serial primary key,
    model varchar (50) not null,
    engine_id int not null unique references engine(id)
);

CREATE TABLE driver (
    id serial primary key,
    firstName varchar (50) not null,
    lastName varchar (50) not null
);

CREATE TABLE history_owner (
    id serial primary key,
    driver_id int not null references driver(id),
    car_id int not null references car(id)
);