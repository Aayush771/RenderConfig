#!/bin/bash
sudo mysql <<BASH_QUERY
# start of sql query
# create database and tables within the database
DROP DATABASE IF EXISTS BookStore;
CREATE DATABASE BookStore;
# create tables
USE BookStore;
CREATE TABLE Books (
Id INT AUTO_INCREMENT PRIMARY KEY,
Name VARCHAR (50) NOT NULL,
Price INT,
CategoryId INT, 
AuthorId INT
);
USE BookStore;
CREATE TABLE Categories (
Id INT PRIMARY KEY,
Name VARCHAR (50) NOT NULL
);
USE BookStore;
CREATE TABLE Authors (
Id INT PRIMARY KEY,
Name VARCHAR (50) NOT NULL
);
# insert a few dummy records in the Categories table:
INSERT INTO Categories (Id, Name)
VALUES 
    (1, 'Cat-A'),
    (2, 'Cat-B'),
    (3, 'Cat-C'),
    (7, 'Cat-D'),
    (8, 'Cat-E'),
    (4, 'Cat-F'),
    (10,'Cat-G'),
    (12,'Cat-H'),
    (6, 'Cat-I');
# insert records in the Authors table:
INSERT INTO Authors (Id, Name)
VALUES 
    (1, 'Author-A'),
    (2, 'Author-B'),
    (3, 'Author-C'),
    (10, 'Author-D'),
    (12, 'Author-E');
#  add dummy records in the Books table
INSERT INTO Books (Name, Price, CategoryId, AuthorId)
VALUES 
    ('Book-A', 100, 1, 2),
    ('Book-B', 200, 2, 2),
    ('Book-C', 150, 3, 2),
    ('Book-D', 100, 3, 1),
    ('Book-E', 200, 3, 1),
    ('Book-F', 150, 4, 1),
    ('Book-G', 100, 5, 5),
    ('Book-H', 200, 5, 6),
    ('Book-I', 150, 7, 8);
# end of sql query
BASH_QUERY

