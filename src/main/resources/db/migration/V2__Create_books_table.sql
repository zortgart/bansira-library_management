CREATE TABLE books
(
    id               INT AUTO_INCREMENT PRIMARY KEY,
    title            VARCHAR(255) NOT NULL,
    author           VARCHAR(255) NOT NULL,
    isbn             VARCHAR(255)  NOT NULL UNIQUE,
    genre            VARCHAR(255),
    publication_year INT,
    availability     BOOLEAN      NOT NULL,
    department_id    INT,
    FOREIGN KEY (department_id) REFERENCES departments (id)
);