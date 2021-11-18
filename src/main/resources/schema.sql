DROP TABLE IF EXISTS s_comment&&
DROP TABLE IF EXISTS book&&
DROP TABLE IF EXISTS author&&
DROP TABLE IF EXISTS ganre&&
DROP TABLE IF EXISTS s_user_roles&&
DROP TABLE IF EXISTS s_user&&
DROP TABLE IF EXISTS s_role&&

CREATE TABLE `author` (
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    firstname VARCHAR(20) NOT NULL,
    lastname VARCHAR(40) NOT NULL,
    birthday DATE NOT NULL,
    date_of_death DATE,
    count INT DEFAULT 0
)&&

CREATE TABLE `ganre` (
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(20) NOT NULL,
    count INT DEFAULT 0
)&&

CREATE TABLE `book` (
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    name VARCHAR(40) NOT NULL,
    description VARCHAR(200),
    release_date VARCHAR(4) NOT NULL,
    author_id INT UNSIGNED NOT NULL,
    ganre_id INT UNSIGNED NOT NULL,
    CONSTRAINT ganre_id FOREIGN KEY(ganre_id) 
    REFERENCES ganre(id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT author_id FOREIGN KEY(author_id) 
    REFERENCES author(id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE
)&&

-- Ganre
CREATE TRIGGER `ganre_count_insert` AFTER INSERT ON `book` FOR EACH ROW 
    BEGIN
        UPDATE `ganre` g SET g.count = g.count + 1 WHERE g.id = NEW.ganre_id;
    END&&

CREATE TRIGGER `ganre_count_delete` BEFORE DELETE ON `book` FOR EACH ROW 
    BEGIN
        UPDATE `ganre` SET count = count - 1 WHERE id = OLD.ganre_id;
    END&&

-- Author
CREATE TRIGGER `author_count_insert` AFTER INSERT ON `book` FOR EACH ROW 
    BEGIN
        UPDATE `author` SET count = count + 1 WHERE id = NEW.author_id;
    END&&

CREATE TRIGGER `author_count_delete` BEFORE DELETE ON `book` FOR EACH ROW 
    BEGIN
        UPDATE `author` SET count = count - 1 WHERE id = OLD.author_id;
    END&&

-- Update ganre and author
CREATE TRIGGER `ganre_count_update` AFTER UPDATE ON `book` FOR EACH ROW 
    BEGIN
        UPDATE `ganre` SET count = count - 1 WHERE id = OLD.ganre_id;
        UPDATE `ganre` SET count = count + 1 WHERE id = NEW.ganre_id;
    END&&

CREATE TRIGGER `author_count_update` AFTER UPDATE ON `book` FOR EACH ROW 
    BEGIN
        UPDATE `author` SET count = count - 1 WHERE id = OLD.author_id;
        UPDATE `author` SET count = count + 1 WHERE id = NEW.author_id;
    END&&

CREATE TABLE s_role (
    id SMALLINT UNSIGNED PRIMARY KEY,
    name CHARACTER VARYING(20) NOT NULL
)&&

CREATE TABLE s_user (
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    username CHARACTER VARYING(30) NOT NULL,
    email CHARACTER VARYING(100),
    password CHARACTER VARYING(100) NOT NULL,
    enabled BOOLEAN NOT NULL
)&&

CREATE TABLE s_user_roles (
    user_id INT UNSIGNED NOT NULL,
    roles_id SMALLINT UNSIGNED NOT NULL,
    CONSTRAINT user_id_fk FOREIGN KEY(user_id) 
    REFERENCES s_user(id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT role_id_fk FOREIGN KEY(roles_id) 
    REFERENCES s_role(id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE    
)&&

CREATE TABLE s_comment (
    id INT UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    user_id INT UNSIGNED NOT NULL,
    book_id INT UNSIGNED NOT NULL,
    text CHARACTER VARYING(200) NOT NULL,
    CONSTRAINT user_id_fk_comment FOREIGN KEY(user_id) 
    REFERENCES s_user(id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE,
    CONSTRAINT book_id_fk_comment FOREIGN KEY(book_id) 
    REFERENCES book(id) 
    ON DELETE CASCADE
    ON UPDATE CASCADE
)&&