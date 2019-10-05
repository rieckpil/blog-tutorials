CREATE TABLE book (
    id BIGINT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    excerpt TEXT,
    author VARCHAR(255) NOT NULL,
    isbn VARCHAR (20) NOT NULL,
    genre VARCHAR(255),
    published TIMESTAMP
);