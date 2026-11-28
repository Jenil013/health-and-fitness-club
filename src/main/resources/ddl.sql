CREATE TABLE Admin (
    admin_id    SERIAL  PRIMARY KEY,
    name    VARCHAR(255)    NOT NULL  
);

CREATE TABLE Equipment (
    equipment_id    SERIAL  PRIMARY KEY,
    equipment_name    VARCHAR(255)    NOT NULL,
    status          VARCHAR(255)    NOT NULL

);

CREATE TABLE Fitness_Goal (
    equipment_id    SERIAL  PRIMARY KEY,
    equipment_name    VARCHAR(255)    NOT NULL,
    status          VARCHAR(255)    NOT NULL
         
);

CREATE TABLE Payment_Record (
    payment_id    SERIAL  PRIMARY KEY,
    payment_date    DATE    NOT NULL,
    amount          DOUBLE PRECISION    NOT NULL,
         
);

CREATE TABLE Fitness_Goal (
    equipment_id    SERIAL  PRIMARY KEY,
    equipment_name    VARCHAR(255)    NOT NULL,
    status          VARCHAR(255)    NOT NULL,
         
);

-- NOT COMPLETE