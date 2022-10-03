CREATE TABLE priorities (
    id SERIAL PRIMARY KEY,
    name TEXT unique not null,
    position int unique not null
);