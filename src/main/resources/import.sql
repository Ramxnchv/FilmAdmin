-- insert admin (username a, password aa)
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (1, TRUE, 'ADMIN,USER', 'a',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, password)
VALUES (2, TRUE, 'USER', 'b',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (1,'Madrid','40.369008,-3.599046','C/Adolfo Bioy Casares, 2', '10:00', '22:00', 'lagavia.jpg', 'La Gavia', '914255401');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (2,'Madrid','40.39083,-3.70144','Av del Manzanares, 210', '9:00', '23:00', 'plazario2.jpg', 'Plaza Rio 2', '911374548');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (3,'Barcelona','41.29555,2.00805','Carrer del Progres, 69', '10:00', '23:00', 'barnasud.jpg', 'Barnasud', '936625656');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (4,'Barcelona','41.40997,2.21648','Avinguda Diagonal, 3', '11:00', '00:00', 'diagonal.png', 'Diagonal', '935677637');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (5,'Valencia','39.47172,-0.48867','Autovia del Este, Km. 345', '17:00', '22:30', 'bonaire.jpg', 'Bonaire', '961579224');