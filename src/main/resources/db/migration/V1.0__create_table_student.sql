CREATE TABLE if not exists students (
    id integer,
    first_name varchar(250),
    last_name varchar(250),
    age integer,
    designation varchar(250),
    phone_number varchar(250),
    address varchar(250),
    date_of_birth date,
    created_at timestamp,
    updated_at timestamp,
    PRIMARY KEY (id)
    );