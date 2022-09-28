create table todo_user (
    id serial primary key,
    name text,
    login text unique,
    password text
);