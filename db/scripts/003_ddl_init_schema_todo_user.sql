create table todo_user (
    id serial primary key,
    name text not null,
    login text unique,
    password text not null,
    time_zone text not null
);