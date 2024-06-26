CREATE TABLE IF NOT EXISTS students (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(250) NOT NULL,
    last_name VARCHAR(250) NOT NULL,
    age INTEGER CHECK (age >= 0 AND age <= 100),
    email VARCHAR(250),
    address VARCHAR(250) NOT NULL,
    date_of_birth DATE NOT NULL,
    created_at DATE NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at DATE NOT NULL DEFAULT CURRENT_TIMESTAMP
);