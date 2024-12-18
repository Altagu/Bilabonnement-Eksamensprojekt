DROP DATABASE IF EXISTS Gruppe5;
CREATE DATABASE Gruppe5;
USE Gruppe5;

CREATE TABLE users (
                       userID INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(20) UNIQUE NOT NULL,
                       password VARCHAR(20) NOT NULL,
                       role ENUM('ADMIN', 'EMPLOYEE', 'USER') NOT NULL DEFAULT 'USER'
);

CREATE TABLE Customer (
                          customerID INT AUTO_INCREMENT PRIMARY KEY,
                          fname VARCHAR(255) NOT NULL,
                          lname VARCHAR(255) NOT NULL,
                          email VARCHAR(255) NOT NULL,
                          phone VARCHAR(15),
                          address VARCHAR(255) NOT NULL
);

CREATE TABLE Cars (
                      CarID INT AUTO_INCREMENT PRIMARY KEY,
                      VIN VARCHAR(17) UNIQUE NOT NULL,
                      Brand VARCHAR(10),
                      Model VARCHAR(100),
                      Fueltype VARCHAR(10),
                      PricePrMonth DECIMAL(10, 2),
                      Status ENUM('Udlejet', 'Tilbageleveret', 'Klar til udlejning', 'Skadet') DEFAULT 'Klar til udlejning',
                      ImagePath VARCHAR(255)
);

CREATE TABLE DamageTypes (
                             DamageTypeID INT AUTO_INCREMENT PRIMARY KEY,
                             Name VARCHAR(255) NOT NULL,
                             Description TEXT,
                             Cost DECIMAL(10, 2) NOT NULL
);


CREATE TABLE DamageReports (
                               DamageReportID INT AUTO_INCREMENT PRIMARY KEY,
                               CarID INT NOT NULL,
                               ReportDate DATE,
                               Status ENUM('Afventer reparation', 'Under reparation', 'Repareret') DEFAULT 'Afventer reparation',
                               TotalCost DECIMAL(10, 2),
                               FOREIGN KEY (CarID) REFERENCES Cars(CarID)
);

CREATE TABLE DamageReportDetails (
                                     ReportDetailID INT AUTO_INCREMENT PRIMARY KEY,
                                     DamageReportID INT NOT NULL,
                                     DamageTypeID INT NOT NULL,
                                     Quantity INT DEFAULT 1,
                                     LineCost DECIMAL(10, 2),
                                     FOREIGN KEY (DamageReportID) REFERENCES DamageReports(DamageReportID),
                                     FOREIGN KEY (DamageTypeID) REFERENCES DamageTypes(DamageTypeID)
);

CREATE TABLE RentalContracts (
                                 contractID INT AUTO_INCREMENT PRIMARY KEY,
                                 customerID INT,
                                 carID INT,
                                 startDate DATE NOT NULL,
                                 endDate DATE,
                                 rentalFee DECIMAL(10, 2),
                                 status ENUM('Active', 'Passive') DEFAULT 'Active',
                                 FOREIGN KEY (customerID) REFERENCES Customer(customerID),
                                 FOREIGN KEY (CarID) REFERENCES Cars(CarID)
);