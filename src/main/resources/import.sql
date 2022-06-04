-- insert admin (username a, password aa)
INSERT INTO IWUser (id, enabled, roles, username, first_name, last_name, password)
VALUES (1, TRUE, 'ADMIN,USER', 'a', 'Admin', 'FilmAdmin',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, first_name, last_name, password)
VALUES (2, TRUE, 'USER', 'b', 'Ramon', 'Rosa',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, first_name, last_name, password)
VALUES (3, TRUE, 'USER', 'c', 'Daniel', 'Lucas',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO IWUser (id, enabled, roles, username, first_name, last_name, password)
VALUES (4, TRUE, 'USER', 'd', 'Jose', 'Otegui',
    '{bcrypt}$2a$10$2BpNTbrsarbHjNsUWgzfNubJqBRf.0Vz9924nRSHBqlbPKerkgX.W');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (1,'Madrid','40.369008,-3.599046','C/Adolfo Bioy Casares, 2', '10:00', '22:00', 'lagavia.jpg', 'La Gavia', '914255401');
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 0);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 1);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 2);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 3);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 4);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 5);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (1, 6);
INSERT INTO cine_festivos_cierre(cine_id, festivo_cierre) VALUES (1, '2000-12-25');
INSERT INTO cine_festivos_cierre(cine_id, festivo_cierre) VALUES (1, '2000-01-01');
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (2,'Madrid','40.39083,-3.70144','Av del Manzanares, 210', '9:00', '23:00', 'plazario2.jpg', 'Plaza Rio 2', '911374548');
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (2, 1);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (2, 2);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (2, 3);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (2, 4);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (2, 5);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (2, 6);
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (3,'Barcelona','41.29555,2.00805','Carrer del Progres, 69', '10:00', '23:00', 'barnasud.jpg', 'Barnasud', '936625656');
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (3, 0);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (3, 2);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (3, 3);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (3, 4);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (3, 5);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (3, 6);
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (4,'Barcelona','41.40997,2.21648','Avinguda Diagonal, 3', '11:00', '00:00', 'diagonal.png', 'Diagonal', '935677637');
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (4, 0);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (4, 1);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (4, 2);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (4, 4);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (4, 5);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (4, 6);
INSERT INTO Cine (id,ciudad,coordenadas,direccion,hora_apertura,hora_cierre,imagen,nombre,telefono)
VALUES (5,'Valencia','39.47172,-0.48867','Autovia del Este, Km. 345', '17:00', '22:30', 'bonaire.jpg', 'Bonaire', '961579224');
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (5, 2);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (5, 3);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (5, 4);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (5, 5);
INSERT INTO cine_dias_apertura(cine_id, dia_apertura) VALUES (5, 6);
INSERT INTO Pelicula (id,duraccion,genero,img,titulo)
VALUES (1, 120, 'Comedia', 'img1.jpg', 'El buen patron');
INSERT INTO Pelicula (id,duraccion,genero,img,titulo)
VALUES (2, 112, 'Comedia-Historia', 'Delicioso-886756010-mmed.jpg', 'Delicioso');
INSERT INTO Pelicula (id,duraccion,genero,img,titulo)
VALUES (3, 97, 'Drama', 'Belfast-622346571-large.jpg', 'Belfast');
INSERT INTO Pelicula (id,duraccion,genero,img,titulo)
VALUES (4, 110, 'Animacion-Aventura', 'Encanto-153413687-large.jpg', 'Encanto');
INSERT INTO Pelicula (id,duraccion,genero,img,titulo)
VALUES (5, 120, 'Ciencia-ficcion', 'Moonfall-138176660-large.jpg', 'Moonfall');
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (1,12,4,'Sala 1',1);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (2,12,4,'Sala 2',1);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (3,12,4,'Sala 1',2);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (4,12,4,'Sala 2',2);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (5,12,4,'Sala 1',3);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (6,12,4,'Sala 2',3);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (7,12,4,'Sala 1',4);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (8,12,4,'Sala 2',4);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (9,12,4,'Sala 1',5);
INSERT INTO Sala (id,columnas,filas,nombre,cine_id)
VALUES (10,12,4,'Sala 2',5);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (1,1);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (1,2);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (2,3);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (2,4);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (3,5);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (3,6);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (4,7);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (4,8);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (5,9);
INSERT INTO Cine_Salas (cine_id,salas_id)
VALUES (5,10);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (1,1,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (2,2,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (3,3,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (4,4,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (5,5,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (6,6,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (7,7,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (8,8,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (9,9,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (10,10,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (11,11,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (12,12,1,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (13,1,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (14,2,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (15,3,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (16,4,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (17,5,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (18,6,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (19,7,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (20,8,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (21,9,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (22,10,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (23,11,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (24,12,2,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (25,1,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (26,2,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (27,3,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (28,4,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (29,5,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (30,6,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (31,7,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (32,8,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (33,9,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (34,10,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (35,11,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (36,12,3,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (37,1,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (38,2,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (39,3,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (40,4,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (41,5,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (42,6,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (43,7,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (44,8,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (45,9,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (46,10,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (47,11,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (48,12,4,1);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (49,1,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (50,2,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (51,3,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (52,4,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (53,5,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (54,6,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (55,7,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (56,8,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (57,9,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (58,10,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (59,11,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (60,12,1,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (61,1,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (62,2,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (63,3,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (64,4,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (65,5,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (66,6,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (67,7,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (68,8,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (69,9,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (70,10,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (71,11,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (72,12,2,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (73,1,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (74,2,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (75,3,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (76,4,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (77,5,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (78,6,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (79,7,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (80,8,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (81,9,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (82,10,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (83,11,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (84,12,3,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (85,1,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (86,2,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (87,3,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (88,4,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (89,5,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (90,6,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (91,7,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (92,8,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (93,9,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (94,10,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (95,11,4,2);
INSERT INTO Asiento (id,columna,fila,sala_id)
VALUES (96,12,4,2);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,1);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,2);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,3);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,4);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,5);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,6);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,7);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,8);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,9);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,10);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,11);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,12);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,13);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,14);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,15);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,16);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,17);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,18);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,19);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,20);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,21);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,22);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,23);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,24);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,25);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,26);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,27);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,28);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,29);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,30);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,31);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,32);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,33);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,34);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,35);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,36);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,37);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,38);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,39);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,40);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,41);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,42);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,43);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,44);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,45);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,46);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,47);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (1,48);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,49);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,50);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,51);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,52);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,53);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,54);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,55);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,56);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,57);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,58);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,59);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,60);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,61);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,62);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,63);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,64);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,65);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,66);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,67);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,68);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,69);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,70);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,71);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,72);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,73);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,74);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,75);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,76);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,77);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,78);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,79);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,80);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,81);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,82);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,83);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,84);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,85);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,86);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,87);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,88);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,89);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,90);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,91);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,92);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,93);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,94);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,95);
INSERT INTO Sala_Asientos (sala_id,asientos_id)
VALUES (2,96);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (1,38,'2022-03-15 12:00',1,1,1,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (2,48,'2022-03-15 15:00',1,1,1,12);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (3,48,'2022-03-15 18:00',1,1,1,13.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (4,48,'2022-03-15 21:00',1,1,1,10.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (5,48,'2022-03-15 15:00',1,2,2,9.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (6,48,'2022-03-15 18:00',1,2,2,6.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (7,48,'2022-03-16 14:00',1,3,1,9.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (8,48,'2022-03-16 19:00',1,3,1,10.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (9,48,'2022-03-16 22:00',1,3,1,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (10,48,'2022-03-16 11:00',1,4,2,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (11,48,'2022-03-16 14:00',1,4,2,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (12,48,'2022-03-16 17:00',1,4,2,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (13,48,'2022-03-16 20:00',1,4,2,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (14,48,'2022-03-17 12:00',1,5,1,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (15,48,'2022-03-17 15:00',1,5,1,11.50);
INSERT INTO Sesion (id,asientos_libres,dia_hora,cine_id,pelicula_id,sala_id,precio_entrada)
VALUES (16,48,'2022-03-17 21:00',1,5,1,11.50);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,1);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,2);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,3);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,4);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,5);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,6);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,7);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,8);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,9);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,10);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,11);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,12);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,13);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,14);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,15);
INSERT INTO Cine_Sesiones (cine_id,sesiones_id)
VALUES (1,16);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (1,1);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (1,2);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (1,3);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (1,4);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (2,5);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (2,6);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (3,7);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (3,8);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (3,9);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (4,10);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (4,11);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (4,12);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (4,13);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (5,14);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (5,15);
INSERT INTO Pelicula_Sesiones (pelicula_id,sesiones_id)
VALUES (5,16);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,1);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,2);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,3);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,4);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (2,5);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (2,6);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,7);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,8);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,9);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (2,10);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (2,11);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (2,12);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (2,13);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,14);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,15);
INSERT INTO Sala_Sesiones (sala_id,sesiones_id)
VALUES (1,16);
INSERT INTO Entrada (id,codigo,preciofinal,sesion_id,user_id,validate)
VALUES (1,'67G5HJ4F',110.00,1,2,false);
INSERT INTO IWUser_Entradas (user_id,entradas_id)
VALUES (2, 1);
INSERT INTO Sesion_Entradas (sesion_id,entradas_id)
VALUES (1,1);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,1);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,2);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,3);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,4);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,5);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,6);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,7);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,8);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,9);
INSERT INTO Entrada_Asientos (entradas_id,asientos_id)
VALUES (1,10);