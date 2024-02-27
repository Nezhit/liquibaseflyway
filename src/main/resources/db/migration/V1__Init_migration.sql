CREATE TABLE Employees (
                           ID_Emp SERIAL PRIMARY KEY CHECK (ID_Emp BETWEEN 1 AND 9999),
                           Name VARCHAR(15) NOT NULL CHECK (Name ~ '^[А-я-]+$'),
                           Surname VARCHAR(20) NOT NULL CHECK (Surname ~ '^[А-я-]+$'),
                           Papaname VARCHAR(20) CHECK (Papaname ~ '^[А-я-]*$'),
                           Birthdate DATE NOT NULL CHECK (Birthdate BETWEEN '1980-01-01' AND '2024-12-31'),
                           Address VARCHAR(20) NOT NULL CHECK (Address ~ '^[А-я .]+$'),
                           Phone CHAR(11) NOT NULL CHECK (Phone ~ '^[0-9]+$'),
                           Passport INT NOT NULL CHECK (Passport BETWEEN 1 AND 999999)
);
CREATE TABLE Types (
                       ID_Type SERIAL PRIMARY KEY CHECK (ID_Type BETWEEN 1 AND 9999),
                       Title VARCHAR(15) NOT NULL CHECK (Title ~ '^[А-я-]+$')
);
CREATE TABLE Producers (
                           ID_Prod SERIAL PRIMARY KEY CHECK (ID_Prod BETWEEN 1 AND 9999),
                           Title VARCHAR(15) NOT NULL CHECK (Title ~ '^[А-я-]+$'),
                           Adress VARCHAR(20) NOT NULL CHECK (Adress ~ '^[А-я .]+$'),
                           Phone CHAR(11) NOT NULL CHECK (Phone ~ '^[0-9]+$')
);
CREATE TABLE Customers (
                           ID_Cust SERIAL PRIMARY KEY CHECK (ID_Cust BETWEEN 1 AND 9999),
                           Title VARCHAR(15) NOT NULL CHECK (Title ~ '^[А-я-]+$'),
                           Adress VARCHAR(20) NOT NULL CHECK (Adress ~ '^[А-я .]+$'),
                           Phone CHAR(11) NOT NULL CHECK (Phone ~ '^[0-9]+$')
);