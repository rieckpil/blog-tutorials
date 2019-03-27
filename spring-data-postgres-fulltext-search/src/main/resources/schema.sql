CREATE TABLE comment (
 id BIGSERIAL PRIMARY KEY,
 comment_text TSVECTOR,
 created_at TIMESTAMP
);
