CREATE TABLE department(
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);
INSERT INTO department(id, name)
VALUES(1, 'IT');