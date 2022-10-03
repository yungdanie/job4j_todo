create table todo_task (
    id serial primary key,
    description text not null,
    created timestamp without time zone default now(),
    done boolean not null
)