CREATE TABLE car
(
    id           SERIAL PRIMARY KEY,
    model        VARCHAR(255),
    price        DOUBLE PRECISION,
    manufacturer VARCHAR(255),
    year         INT,
    mileage      INT,
    available    BOOLEAN
);

CREATE TABLE role
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(255)
);

CREATE TABLE users
(
    username VARCHAR(255) PRIMARY KEY,
    password VARCHAR(255)
);

CREATE TABLE users_cars
(
    id     SERIAL PRIMARY KEY,
    owner  VARCHAR(255),
    car_id BIGINT REFERENCES car (id)
);
create table users_roles
(
    user_username character varying(255) not null,
    roles_id      bigint                 not null,
    foreign key (roles_id) references role (id)
        match simple on update no action on delete no action,
    foreign key (user_username) references users (username)
        match simple on update no action on delete no action
);

