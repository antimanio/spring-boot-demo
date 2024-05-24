CREATE TABLE users (
   id SERIAL PRIMARY KEY,
   name VARCHAR,
   email VARCHAR UNIQUE,
   password VARCHAR,
   role VARCHAR
);

CREATE TABLE refreshtoken (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    token VARCHAR UNIQUE NOT NULL,
    expiryDate DATE NOT NULL
);

CREATE TABLE cuttersqueue (
    id SERIAL PRIMARY KEY,
    user_id INTEGER REFERENCES users(id),
    name VARCHAR,
    estimated_time VARCHAR,
    queue_number INTEGER
);
