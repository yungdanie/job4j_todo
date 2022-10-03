create table task_category (
    id serial primary key,
    task_id int references todo_task(id) not null,
    category_id int references category(id) not null
)