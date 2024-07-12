INSERT INTO departments (name)
VALUES ('Science'),
       ('Arts'),
       ('History');

INSERT INTO books (title, author, isbn, genre, publication_year, availability, department_id)
VALUES ('Effective Java', 'Joshua Bloch', '978-0134685991', 'Programming', 2018, TRUE, 1),
       ('Clean Code', 'Robert C. Martin', '978-0132350884', 'Programming', 2008, TRUE, 1),
       ('The Pragmatic Programmer', 'Andrew Hunt', '978-0201616224', 'Programming', 1999, TRUE, 1),
       ('War and Peace', 'Leo Tolstoy', '978-0199232765', 'Historical Fiction', 1869, TRUE, 2);
