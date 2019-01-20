CREATE TABLE author (
    id BIGSERIAL PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    member_since TIMESTAMP NOT NULL
);

CREATE TABLE post (
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT,
    published_at TIMESTAMP NOT NULL,
    last_modification TIMESTAMP NOT NULL,
    author_id BIGINT REFERENCES author(id)
);

CREATE TABLE post_comment (
    id BIGSERIAL PRIMARY KEY,
    content TEXT NOT NULL,
    posted_at TIMESTAMP NOT NULL,
    post_id BIGINT REFERENCES post(id)
);

CREATE TABLE tag (
    id BIGSERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE post_tag (
    id BIGSERIAL PRIMARY KEY,
    post_id BIGINT REFERENCES post(id),
    tag_id BIGINT REFERENCES tag(id)
);

INSERT INTO author (username, email, member_since) VALUES ('duke', 'duke@java.ee', NOW());
INSERT INTO author (username, email, member_since) VALUES ('mike', 'mike@java.ee', NOW());
INSERT INTO author (username, email, member_since) VALUES ('rieckpil', 'rieckpil@java.ee', NOW());

INSERT INTO post (title, content, published_at, last_modification, author_id) VALUES ('SchemaSpy', 'Use SchemaSpy for simple database documentation', NOW(), NOW(), 1);
INSERT INTO post (title, content, published_at, last_modification, author_id) VALUES ('Jakarta EE', 'Read about the Java EE transition for Oracle to the Eclipse Foundation', NOW(), NOW(), 2);
INSERT INTO post (title, content, published_at, last_modification, author_id) VALUES ('Java 11', 'Read about the latest features in Java 11', NOW(), NOW(), 3);

INSERT INTO tag (name) VALUES ('Java');
INSERT INTO tag (name) VALUES ('Database');
INSERT INTO tag (name) VALUES ('Enterprise Development');

INSERT INTO post_tag (post_id, tag_id) VALUES (1, 2);
INSERT INTO post_tag (post_id, tag_id) VALUES (2, 1);
INSERT INTO post_tag (post_id, tag_id) VALUES (2, 2);
INSERT INTO post_tag (post_id, tag_id) VALUES (3, 1);

INSERT INTO post_comment (content, posted_at, post_id) VALUES ('Nice blog post!', NOW(), 1);