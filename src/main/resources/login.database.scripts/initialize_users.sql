-- Create table users, drop if exists, create enum
DROP TABLE IF EXISTS users;
CREATE TYPE user_role AS ENUM ('ADMIN', 'USER', 'MODERATOR');
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    role user_role NOT NULL
);

-- Delete all users
DELETE FROM users;
ALTER SEQUENCE users_id_seq RESTART WITH 1;