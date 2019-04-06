CREATE TABLE review (
	id BIGSERIAL PRIMARY KEY,
	review_text TEXT NOT NULL,
	reviewer_email VARCHAR(255) NOT NULL,
	stars INTEGER NOT NULL CHECK (stars >= 1 AND stars <= 5),
	review_date TIMESTAMP NOT NULL,
	book_id BIGINT REFERENCES book(id)
);

