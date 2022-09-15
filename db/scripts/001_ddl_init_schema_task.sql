create table task (
    task_id serial primary key,
    description text,
    created timestamp,
    done boolean
)