CREATE TABLE IF NOT EXISTS users (
    id BIGSERIAL PRIMARY KEY,
    email VARCHAR (100) UNIQUE,
    password VARCHAR (100),
    role VARCHAR (20)
);