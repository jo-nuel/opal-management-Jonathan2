# Project Name: OPAL CARD MANAGEMENT SOFTWARE 

## 41026 Advanced Software Development - Spring 2024

## Group 5:
- Jerry.F 24821466 
- Louis Daniel Effendi
- Brandon Maverick Winarto
- Jonathan Immanuel
- Fatin adas

## RUNNING THE RPROJECT
- must download the project locally
- have mysql downloaded and the databases created as defined below
- Modify the DB user and password variables to match those you use to login to mysql locally
In the terminal run:
- mvn clean package
- mvn jetty:run

## DATABASE NOTES
- in order to run the project and be able to access the database you need to have mysql installed on your local machine and run the following commands:

-- Create the ocms database
CREATE DATABASE ocms;

-- Use the ocms database
USE ocms;

-- Create the events table
CREATE TABLE events (
    id INT NOT NULL AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    category VARCHAR(100),
    description TEXT,
    PRIMARY KEY (id)
);

-- Create the users table
CREATE TABLE users (
    id VARCHAR(10) NOT NULL,
    name VARCHAR(100),
    email VARCHAR(100) UNIQUE,
    password VARCHAR(100),
    status VARCHAR(50),
    role VARCHAR(50),
    PRIMARY KEY (id)
);

-- Create the access table (based on your database structure)
CREATE TABLE access (
    email VARCHAR(100),
    action VARCHAR(255),
    date DATE,
    time TIME,
    PRIMARY KEY (email, date, time),
    FOREIGN KEY (email) REFERENCES users(email)
);


