CREATE TABLE persons (
    id BIGSERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    day_of_birth DATE NOT NULL
);

INSERT INTO persons (first_name, last_name, day_of_birth) VALUES ('Java', 'Duke', '1995-05-23');
INSERT INTO persons (first_name, last_name, day_of_birth) VALUES ('John', 'Doe', '2001-04-04');
INSERT INTO persons (first_name, last_name, day_of_birth) VALUES ('Mike', 'Paul', '2006-08-08');