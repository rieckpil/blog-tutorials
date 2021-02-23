CREATE TABLE messages
(
    ID      BIGSERIAL PRIMARY KEY,
    CONTENT VARCHAR(255) NOT NULL
);

INSERT INTO messages (content) VALUES ('Hello World From Init Script!');
