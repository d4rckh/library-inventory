DELETE FROM books;
DELETE FROM users;

INSERT INTO users VALUES (1, 'First Name', 'Last Name', 'handrei@handrei.com', 'hashed_password');

INSERT INTO books VALUES (1, 'Title 1', 'Author 1', 'ISBN 1', 'Publisher 1', now(), 1);
INSERT INTO books VALUES (2, 'Title 2', 'Author 2', 'ISBN 2', 'Publisher 2', now(), 1);


