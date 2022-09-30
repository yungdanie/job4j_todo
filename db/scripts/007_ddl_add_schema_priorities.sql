CREATE TABLE priorities (
    id SERIAL PRIMARY KEY,
    name TEXT unique,
    position int unique
);