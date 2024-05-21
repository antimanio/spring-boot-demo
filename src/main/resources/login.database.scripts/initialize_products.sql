-- Create table users, drop if exists
DROP TABLE IF EXISTS products;
CREATE TABLE products (
   id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

-- Delete all products
DELETE FROM products;
ALTER SEQUENCE prducts_id_seq RESTART WITH 1;
