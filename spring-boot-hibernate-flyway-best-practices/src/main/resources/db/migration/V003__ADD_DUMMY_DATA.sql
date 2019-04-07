INSERT INTO book(id, name, publisher, pages, published_at) VALUES (1, 'Java EE', 'Oracle Press', 42, NOW());
INSERT INTO book(id, name, publisher, pages, published_at) VALUES (2, 'Spring Boot', 'Pivotal Press', 123, NOW());
INSERT INTO book(id, name, publisher, pages, published_at) VALUES (3, 'MicroProfile', 'Eclipse Foundation Press', 12, NOW());
INSERT INTO book(id, name, publisher, pages, published_at) VALUES (4, 'Jakarta EE', 'Eclipse Foundation Press', 42, NOW());
INSERT INTO book(id, name, publisher, pages, published_at) VALUES (5, 'Java SE 11', 'Oracle Press', 333, NOW());

INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Great book', 'duke@oracle.com', 4, NOW(), 1);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Must-read book', 'oak@oracle.com', 5, NOW(), 1);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Spring is better', 'josh@pivotal.com', 1, NOW(), 1);

INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Spring is best', 'ceo@pivotal.com', 5, NOW(), 2);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Spring ftw!', 'cfo@pivotal.com', 5, NOW(), 2);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Java EE is better', 'duke@oracle.com', 1, NOW(), 2);

INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Great technology', 'duke@oracle.com', 3, NOW(), 3);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('The future of Java', 'mike@eclipse.org', 4, NOW(), 3);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Looks great', 'tom@gmail.com', 4, NOW(), 3);

INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Java EE is dead, long live Jakarta EE', 'mike@eclipse.org', 5, NOW(), 4);

INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Must read for Java devs', 'duke@oracle.com', 4, NOW(), 5);
INSERT INTO review( review_text, reviewer_email, stars, review_date, book_id) VALUES ('Must-read book', 'oak@oracle.com', 4, NOW(), 5);
