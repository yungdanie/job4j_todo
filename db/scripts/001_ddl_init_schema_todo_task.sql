create table todo_task (
    id serial primary key,
    description text,
    created timestamp without time zone default now(),
    done boolean
)