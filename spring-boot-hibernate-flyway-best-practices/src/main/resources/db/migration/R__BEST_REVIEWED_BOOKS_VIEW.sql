DROP VIEW IF EXISTS best_reviewed_books;

CREATE VIEW best_reviewed_books AS
SELECT 
	book.id AS book_id, 
	book.name AS book_name, 
	round(avg(review.stars),2) as avg_stars,
	max(review.stars) as max_stars,
	min(review.stars) as min_stars,
	count(review.id) as total_reviews
FROM book 
JOIN review ON review.book_id = book.id 
GROUP BY book.id
ORDER BY avg_stars desc;