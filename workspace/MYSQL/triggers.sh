#!/bin/bash
sudo mysql <<BASH_QUERY
# start of sql query
# create database and tables within the database
DROP DATABASE IF EXISTS Employee_db;
CREATE DATABASE Employee_db;
# create tables
USE Employee_db;
# Create the Employee table
CREATE TABLE Employee (
  Id int NOT NULL AUTO_INCREMENT,
  Name varchar(255) NOT NULL,
  Age int NOT NULL,
  PRIMARY KEY (Id)
);
# Create the Employee_Audit_Test table
CREATE TABLE Employee_Audit_Test (
  Id int NOT NULL AUTO_INCREMENT,
  Audit_Action text,
  PRIMARY KEY (Id)
);
# end of sql query
BASH_QUERY

