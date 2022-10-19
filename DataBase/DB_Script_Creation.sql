USE master
GO
 
DROP DATABASE IF EXISTS SistemaAlumbradoPublico
GO

CREATE DATABASE SistemaAlumbradoPublico
GO

USE SistemaAlumbradoPublico
GO

CREATE TABLE EMPLOYEE (
	code int IDENTITY(0,1) PRIMARY KEY,
	DPI bigint,
	name varchar(200),
	dateOfBirth date,
	phone varchar(15),
	email varchar(100),
	password varchar(300),
	status bit
)
GO

INSERT INTO EMPLOYEE (DPI, name, dateOfBirth, phone, email, password, status) VALUES ('1234567891234','Crystian Rafael López Santay','1995-11-26','35378121','cryraf2095@gmail.com','JTdvRjIqVWg=','1')
GO
