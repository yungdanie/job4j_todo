create table todo_task (
    id serial primary key,
    description text not null,
    created timestamp,
    done boolean not null
)