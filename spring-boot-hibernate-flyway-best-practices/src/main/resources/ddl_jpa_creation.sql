create table best_reviewed_books (book_id  bigserial not null, avg_stars numeric(19, 2), book_name varchar(255), max_stars int4, min_stars int4, total_reviews int8, primary key (book_id))
create table book (id  bigserial not null, name varchar(255) not null, pages int4 not null, published_at timestamp not null, publisher varchar(255) not null, primary key (id))
create table review (id  bigserial not null, review_date timestamp not null, review_text varchar(255) not null, reviewer_email varchar(255) not null, stars int4 not null, book_id int8, primary key (id))
alter table if exists review add constraint FK70yrt09r4r54tcgkrwbeqenbs foreign key (book_id) references book
