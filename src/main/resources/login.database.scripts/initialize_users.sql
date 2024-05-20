-- Create table users
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(50) NOT NULL
);

-- Delete all users
DELETE FROM users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;