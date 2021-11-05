DROP SCHEMA IF EXISTS AMT_SQL;
CREATE SCHEMA AMT_SQL DEFAULT CHARSET = utf8mb4;
USE AMT_SQL;

alter user 'root'@'localhost' identified with mysql_native_password by 'root';

CREATE TABLE User (
     idUser INTEGER AUTO_INCREMENT,
     emailAddress VARCHAR(50) NOT NULL,
     password VARCHAR(255) NOT NULL, 
     firstName VARCHAR(50) NOT NULL,
     lastName VARCHAR(50) NOT NULL,
     CONSTRAINT PK_User PRIMARY KEY (idUser)
);


INSERT INTO User(emailaddress, password, firstname, lastname)
VALUES ('test@hello.ch', 1234, 'test', 'amt');

/*
CREATE TABLE UserAddress (
     idUserAddress INTEGER AUTO_INCREMENT,
     street VARCHAR(50) NOT NULL,
     houseNumber INTEGER NOT NULL,
     zipCode INTEGER NOT NULL,
     city VARCHAR(50) NOT NULL,
     country VARCHAR(50) NOT NULL,
     CONSTRAINT PK_UserAddress PRIMARY KEY (idUserAddress)
);

CREATE TABLE Product (
     idProduct INTEGER AUTO_INCREMENT,
     name VARCHAR(50) NOT NULL,
     description VARCHAR(255) NOT NULL,
     price FLOAT NOT NULL,
     CONSTRAINT PK_Product PRIMARY KEY (idProduct)
);

CREATE TABLE Order (
     idOrder INTEGER AUTO_INCREMENT,
     CONSTRAINT PK_Order PRIMARY KEY (idOrder)
);
CREATE TABLE Quantite (
);

CREATE TABLE Payment (
);

CREATE TABLE CreditCard (
);

CREATE TABLE Invoice (
);


*/




