create table if not exists users_1(
    id serial primary key,
    login varchar(250) not null,
    password varchar(250) not null,
    name varchar(250),
    lastname varchar(250)
)