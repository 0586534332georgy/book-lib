CREATE TABLE book_library (
	id_book serial PRIMARY KEY,
	author_surname varchar(255),
	author_name varchar(255),
	bookname TEXT
);


CREATE TYPE condition_status_enum AS ENUM ('NEW', 'WELL', 'NOT_BAD', 'BAD');
create table book_status (
	id_book integer NOT NULL PRIMARY KEY,
	reserved_status boolean,
	reserved_date timestamp,
	condition_status condition_status_enum,
	FOREIGN KEY (id_book) REFERENCES book_library(id_book)
);

ALTER TABLE book_status
ALTER COLUMN reserved_status SET DEFAULT false;


create type book_genre_enum AS ENUM ('Fantasy', 'Horror', 'Drama', 'Comedy', 'Non-Fiction' );
create table book_credential (
	id_book integer NOT NULL PRIMARY KEY,
	book_genre book_genre_enum,
	pages_amount integer,
	FOREIGN KEY (id_book) REFERENCES book_library(id_book)
);

select * from book_library;
select * from book_status;
select * from book_credential;
drop table book_library CASCADE;

===========================================================================
-- Fantasy
INSERT INTO book_library (author_surname, author_name, bookname) VALUES
('Tolkien', 'John Ronald Reuel', 'The Lord of the Rings'),
('Martin', 'George Raymond Richard', 'A Game of Thrones'),
('Le Guin', 'Ursula Kroeber', 'A Wizard of Earthsea');
-- Horror
INSERT INTO book_library (author_surname, author_name, bookname) VALUES
('King', 'Stephen', 'It'),
('Jackson', 'Shirley', 'The Haunting of Hill House'),
('Stoker', 'Bram', 'Dracula');
-- Drama
INSERT INTO book_library (author_surname, author_name, bookname) VALUES
('Miller', 'Arthur', 'Death of a Salesman'),
('Williams', 'Tennessee', 'A Streetcar Named Desire'),
('Chekhov', 'Anton', 'The Cherry Orchard');
-- Comedy
INSERT INTO book_library (author_surname, author_name, bookname) VALUES
('Adams', 'Douglas', 'The Hitchhiker''s Guide to the Galaxy'),
('Wodehouse', 'Pelham Grenville', 'Right Ho, Jeeves'),
('Fielding', 'Helen', 'Bridget Jones''s Diary');
-- Non-Fiction
INSERT INTO book_library (author_surname, author_name, bookname) VALUES
('Harari', 'Yuval Noah', 'Sapiens: A Brief History of Humankind'),
('Obama', 'Michelle', 'Becoming'),
('Hawking', 'Stephen', 'A Brief History of Time');

==========================================================================

INSERT INTO book_status(id_book, condition_status) VALUES
(1, 'NEW'),
(2, 'NEW'),
(3, 'NEW'),
(4, 'WELL'),
(5, 'NEW'),
(6, 'NOT_BAD'),
(7, 'NEW'),
(8, 'WELL'),
(9, 'NEW'),
(10, 'NEW'),
(11, 'NOT_BAD'),
(12, 'NEW'),
(13, 'WELL'),
(14, 'NEW'),
(15, 'NEW');

==========================================================================
-- 'Fantasy', 'Horror', 'Drama', 'Comedy', 'Non-Fiction'
INSERT INTO book_credential(id_book, book_genre, pages_amount) VALUES
(1, 'Fantasy', 1216),
(2, 'Fantasy', 720),
(3, 'Fantasy', 183),
(4, 'Horror', 1168),
(5, 'Horror', 208),
(6, 'Horror', 432),
(7, 'Drama', 144),
(8, 'Drama', 144),
(9, 'Drama', 64),
(10, 'Comedy', 224),
(11, 'Comedy', 248),
(12, 'Comedy', 288),
(13, 'Non-Fiction', 464),
(14, 'Non-Fiction', 448),
(15, 'Non-Fiction', 212);

============================================================================

CREATE VIEW all_about_book AS
SELECT 
    b.id_book AS id_book,
    b.bookname AS bookname,
	b.author_surname AS author_surname,
    b.author_name AS author_name,    
    c.book_genre AS book_genre,
    c.pages_amount AS pages_amount
FROM 
    book_library b
JOIN 
    book_credential c ON b.id_book = c.id_book;
	
select * from all_about_book;
drop view all_about_book;