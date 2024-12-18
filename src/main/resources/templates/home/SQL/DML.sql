INSERT INTO users (username, password, role)
VALUES
    ('admin', '1', 'ADMIN'),   -- ADMIN
    ('dataUser', 'password', 'EMPLOYEE'),   -- DATA_USER
    ('user', 'password', 'USER');   -- USER

INSERT INTO Cars (VIN, Brand, Model, FuelType, PricePrMonth, Status) VALUES
                                                                         ('1HGCM82633A123456', 'Honda', 'Accord', 'Benzin', 3500.00, 'Klar til udlejning'),
                                                                         ('1VWBP7A3XEC123457', 'Volkswagen', 'Passat', 'Diesel', 4000.00, 'Udlejet'),
                                                                         ('WDBJF65J4YB123458', 'Mercedes', 'E-Class', 'Benzin', 5500.00, 'Tilbageleveret'),
                                                                         ('3VWDP7AJ9DM123459', 'Volkswagen', 'Golf', 'El', 3000.00, 'Skadet'),
                                                                         ('5YJ3E1EA7HF123460', 'Tesla', 'Model 3', 'El', 5000.00, 'Klar til udlejning'),
                                                                         ('1FAHP2F82DG123461', 'Ford', 'Taurus', 'Hybrid', 4200.00, 'Udlejet'),
                                                                         ('1HGCM82633A789012', 'Toyota', 'Corolla', 'Benzin', 3200.00, 'Klar til udlejning'),
                                                                         ('2T1BURHE0GC456123', 'Mazda', 'CX-5', 'Diesel', 4500.00, 'Klar til udlejning'),
                                                                         ('3VWDP7AJ9DM987654', 'Volkswagen', 'Polo', 'El', 3100.00, 'Klar til udlejning'),
                                                                         ('5YJ3E1EA7HF112233', 'Tesla', 'Model S', 'El', 6200.00, 'Klar til udlejning'),
                                                                         ('1FAHP2F82DG665544', 'Ford', 'Focus', 'Hybrid', 4000.00, 'Klar til udlejning'),
                                                                         ('WDBJF65J4YB223344', 'Mercedes', 'C-Class', 'Benzin', 5200.00, 'Klar til udlejning'),
                                                                         ('4T1BE46K87U543210', 'Toyota', 'Camry', 'Benzin', 3700.00, 'Klar til udlejning'),
                                                                         ('1HGEM229XWL123789', 'Honda', 'Civic', 'Diesel', 3300.00, 'Klar til udlejning'),
                                                                         ('2HGFG1B8XAH234567', 'Hyundai', 'i30', 'El', 3400.00, 'Klar til udlejning'),
                                                                         ('1C3CCBAB8DN987321', 'Chrysler', '200', 'Hybrid', 3800.00, 'Klar til udlejning');

INSERT INTO DamageTypes (Name, Description, Cost) VALUES
                                                      ('Lakskade', 'Reparation af lak på køretøjet', 1500.00),
                                                      ('Skadet sidespejl', 'Udskiftning eller reparation af sidespejl', 3000.00),
                                                      ('Ridset alufælg', 'Polering og udbedring af ridser på alufælge', 800.00),
                                                      ('Stenslag i forrude', 'Reparation af mindre stenslag i forruden', 1200.00),
                                                      ('Buler i karrosseri', 'Reparation af buler i bilens karrosseri', 2500.00),
                                                      ('Ødelagt kofanger', 'Udskiftning eller reparation af kofanger', 4000.00),
                                                      ('Skadet baglygter', 'Udskiftning eller reparation af baglygter', 900.00),
                                                      ('Skadet forlygter', 'Udskiftning eller reparation af forlygter', 1300.00),
                                                      ('Ødelagt dæk', 'Udskiftning af beskadiget dæk', 1000.00),
                                                      ('Skader på interiør', 'Reparation eller udskiftning af beskadiget interiør', 2000.00);

INSERT INTO Customer (fname, lname, email, phone, address) VALUES
                                                               ('John', 'Doe', 'john.doe@example.com', '1234567890', '123 Elm Street, Copenhagen'),
                                                               ('Jane', 'Smith', 'jane.smith@example.com', '0987654321', '456 Oak Road, Aarhus'),
                                                               ('Alice', 'Johnson', 'alice.johnson@example.com', '1122334455', '789 Pine Avenue, Odense'),
                                                               ('Bob', 'Williams', 'bob.williams@example.com', '6677889900', '321 Birch Boulevard, Aalborg'),
                                                               ('Charlie', 'Brown', 'charlie.brown@example.com', '5544332211', '654 Maple Street, Esbjerg');