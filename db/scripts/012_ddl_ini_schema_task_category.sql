create table task_category (
    id serial primary key,
    task_id int references todo_task(id),
    category_id int references category(id)
)