--insertando algunos datos

--lo siguiente es un machetazo para poder insertar las demas areas sin alterar la tabla, me imagino que quedara entre nosotros xD
INSERT INTO Area_Conocimiento(id_area,nombre,descripcion,area_padre) VALUES('','super','','');
INSERT INTO Area_Conocimiento(id_area,nombre,descripcion,area_padre) VALUES('1','Computacion centrada en la red','','');
INSERT INTO Area_Conocimiento(id_area,nombre,descripcion,area_padre) VALUES('2','Construccion de Aplicaciones web','','1');
INSERT INTO Area_Conocimiento VALUES ('3', 'Metodos Numericos Computacionales', '','');
INSERT INTO Area_Conocimiento VALUES ('4', 'Modelos de Simulacion','','3');
INSERT INTO Area_Conocimiento VALUES ('5', 'Investigacion de Operaciones','','3');
INSERT INTO Area_Conocimiento VALUES ('6', 'Ingenieria de Software','','');
INSERT INTO Area_Conocimiento VALUES ('7', 'Diseno de Software','','6');
INSERT INTO Area_Conocimiento VALUES ('8', 'Gestion de Informacion','','');
INSERT INTO Area_Conocimiento VALUES ('9', 'Hipermedia e Hipertexto','','8');

INSERT INTO Autor (nombre, apellido, acronimo, email) VALUES
	('EDGAR','MONCADA','MONKI','MONKI@GMAIL.COM'),
	('FELIPE','VARGAS','FELIPEX','FELIPEX@HOTMAIL.COM');

INSERT INTO Palabra_Clave (nombre, descripcion) VALUES
	('red','todo lo que tenga que ver con redes de computadoras'),
	('programacion','programacion de computadores'),
	('grafos','referente a teoria de grafos');

INSERT INTO TipoMaterial (tipo_nombre, descripcion) VALUES
	('Libro','documento extenso creado por un especifico tema'),
	('Articulo','documento pequeno creado por un especifico tema'),
	('Memoria','biografia'),
	('Trabajo de Grado','trabajo presentado para el grado por estudiantes de pregrado de ultimos semestres'),
	('Tesis de Maestria','trabajo presentado para el grado por estudiantes de maestria de ultimos semestres'),
	('Tesis de Doctorado','trabajo presentado para el grado por estudiantes de doctorado de ultimos semestres'),
	('Material de Clase','diapositivas, talleres, ejercicios y documentos del profesor');

INSERT INTO Usuario VALUES
('444', 'contrasena', 'nombre1', 'nombre2', 'apellido1', 'apellido2', 'email', 'nivel', 'pregunta', 'respuesta', 'vinculo' ,'m', '777-07-07', '6666-06-06', '1', 't');

INSERT INTO Documento VALUES
(DEFAULT, 'español', 'si', 'documento', 'adobe', '200','ninguno', 'pdf', 'Libro', 'librito', 'enlace no disponible', '1111-01-01', '2222-02-02', 'Libro', '444', '3333-03-03');


