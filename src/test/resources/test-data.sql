DELETE FROM users;
DELETE FROM books;

INSERT INTO books VALUES (1, 'Title 1', 'Author 1', 'ISBN 1', 'Publisher 1', now());
INSERT INTO books VALUES (2, 'Title 2', 'Author 2', 'ISBN 2', 'Publisher 2', now());


INSERT INTO users VALUES (1, 'First Name', 'Last Name', 'handrei@handrei.com', 'hashed_password');