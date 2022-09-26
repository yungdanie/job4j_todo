create table todo_task (
    id serial primary key,
    description text,
    created timestamp,
    done boolean
)