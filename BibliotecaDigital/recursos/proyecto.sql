--script biblioteca digital
--María Andrea Cruz Blandón código 0831816
--Yerminson Doney Gonzalez Muñoz código 0843846
--Cristian Leonardo Ríos López código 0814239
--Luis Felipe Vargas Rojas
--Edgar Andrés Moncada Taborda

DROP TABLE Usuario CASCADE;
CREATE TABLE Usuario
(
	login VARCHAR(10),
	contrasena VARCHAR(20) NOT NULL,
	nombre1 VARCHAR(30) NOT NULL,
	nombre2 VARCHAR(30),
	apellido1 VARCHAR(30) NOT NULL,
	apellido2 VARCHAR(30),
	email VARCHAR(50) NOT NULL UNIQUE,
	nivel_escolaridad VARCHAR(30),
	pregunta_secreta VARCHAR(50) NOT NULL,
	respuesta_secreta VARCHAR(30) NOT NULL,
	vinculo_univalle VARCHAR(50),
	genero CHAR(1) NOT NULL,
	fecha_nacimiento DATE,
	fecha_registro DATE NOT NULL,
	tipo CHAR(1) DEFAULT '3' NOT NULL,
	estado BOOLEAN DEFAULT TRUE NOT NULL,
	CONSTRAINT usuario_pk PRIMARY KEY(login)
);


--necesita Area_Conocimiento OK
DROP TABLE Area_Conocimiento CASCADE;
CREATE TABLE Area_Conocimiento
(
	id_area VARCHAR(3),
	nombre VARCHAR(35) NOT NULL UNIQUE,
	descripcion VARCHAR(200),
	area_padre VARCHAR(3),
	CONSTRAINT id_area_pk PRIMARY KEY(id_area),
	CONSTRAINT area_padre_fk FOREIGN KEY (area_padre) REFERENCES Area_Conocimiento(id_area)
	ON DELETE RESTRICT ON UPDATE CASCADE
);

--necesita Usuario OK
--necesita Area_Conocimiento OK
DROP TABLE Interesa_Usuario_Area_Conocimiento;
CREATE TABLE Interesa_Usuario_Area_Conocimiento
(
	login VARCHAR(10),
	id_area VARCHAR(3),
	CONSTRAINT login_idArea_pk PRIMARY KEY (login, id_area),
	CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES Usuario(login)
	ON DELETE RESTRICT ON UPDATE CASCADE, --DUDAS!!!
	CONSTRAINT id_area_FK FOREIGN KEY (id_area) REFERENCES Area_Conocimiento(id_area)
	ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE TipoMaterial CASCADE;
CREATE TABLE TipoMaterial
(
	tipo_nombre VARCHAR(20),
	descripcion VARCHAR(200) NOT NULL,
	CONSTRAINT tipoNombre_pk PRIMARY KEY (tipo_nombre)
);

DROP SEQUENCE id_documento_seq CASCADE;
CREATE SEQUENCE id_documento_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
	START WITH 10000
    CACHE 1;

--necesita TipoMaterial OK
--necesita Usuario OK
DROP TABLE Documento CASCADE;
CREATE TABLE Documento
(
	id_Documento INT DEFAULT nextval('id_documento_seq'::regclass),
	idioma VARCHAR(15) NOT NULL,
	derechos_autor VARCHAR(20),
	descripcion VARCHAR(200) NOT NULL,
	software_recomendado VARCHAR(20),
	resolucion VARCHAR(15),
	editorial VARCHAR(30),
	formato VARCHAR(5) NOT NULL,
	titulo_principal VARCHAR(50) NOT NULL,
	titulo_secundario VARCHAR(50),
	link VARCHAR(200) NOT NULL UNIQUE,
	fecha_creacion DATE,
	fecha_publicacion DATE,
	tipo_nombre VARCHAR(20),
	login_catalogador VARCHAR(10),
	fecha_catalogacion DATE NOT NULL,
	CONSTRAINT idDocumento_pk PRIMARY KEY (id_documento),
	CONSTRAINT tipo_fk FOREIGN KEY (tipo_nombre) REFERENCES TipoMaterial (tipo_nombre)
	ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT loginCatalogador_fk FOREIGN KEY (login_catalogador) REFERENCES Usuario(login)
	ON DELETE RESTRICT ON UPDATE CASCADE
);
 
--necesita Documento OK
--necesita Usuario OK
DROP TABLE Descarga_Usuario_Documento CASCADE;
CREATE TABLE Descarga_Usuario_Documento
(
	fecha DATE,
	hora TIME,
	login VARCHAR(10),
	id_documento INT,
	CONSTRAINT fecha_hora_login_idDocumento_fk PRIMARY KEY(fecha,hora,login,id_Documento),
	CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES Usuario(login)
	ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT idDocumento_fk FOREIGN KEY (id_documento) REFERENCES Documento(id_documento)
	ON DELETE RESTRICT ON UPDATE CASCADE --REVISAR LOS DELETE CON RESTRICT
);

DROP SEQUENCE id_autor_seq CASCADE;
CREATE SEQUENCE id_autor_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
	START WITH 10000
    CACHE 1;

DROP TABLE Autor CASCADE;
CREATE TABLE Autor
(
	id_autor INT DEFAULT nextval('id_autor_seq'::regclass),
	nombre VARCHAR(100) NOT NULL,
	email VARCHAR(50),
	apellido VARCHAR(30) NOT NULL,
	acronimo VARCHAR(20),
	CONSTRAINT idAutor_pk PRIMARY KEY (id_autor)
);

--necesita Autor OK
--necesita Documento OK
DROP TABLE Escribe_Autor_Documento CASCADE;
CREATE TABLE Escribe_Autor_Documento
(
	id_autor INT,
	id_documento INT,
	CONSTRAINT idAutor_idDocumento_pk PRIMARY KEY (id_autor,id_documento),
	CONSTRAINT idAutor_fk FOREIGN KEY (id_autor) REFERENCES Autor(id_autor)
	ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT idDocumento_fk FOREIGN KEY (id_documento) REFERENCES Documento(id_documento)
	ON DELETE RESTRICT ON UPDATE CASCADE
); 

--necesita Documento OK
--necesita Usuario OK
DROP TABLE Consulta CASCADE;
CREATE TABLE Consulta
(
	id_documento INT,
	login VARCHAR(10) ,
  	fecha DATE,
  	hora TIME,
  	CONSTRAINT idDocumento_login_pk PRIMARY KEY (id_documento,login,fecha,hora),
  	CONSTRAINT idDocumento_fk FOREIGN KEY (id_documento) REFERENCES Documento(id_documento)
  	ON DELETE RESTRICT ON UPDATE CASCADE,
	CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES Usuario(login)
  	ON DELETE RESTRICT ON UPDATE CASCADE
);

DROP TABLE Palabra_Clave CASCADE;
CREATE TABLE Palabra_Clave
(
	nombre VARCHAR(20),
	descripcion VARCHAR(200),
 	CONSTRAINT nombre_pk PRIMARY KEY (nombre) 
);

--necesita Palabra_Clave OK
--necesita Documento OK
DROP TABLE Tiene_Documento_Palabra_Clave CASCADE;
CREATE TABLE Tiene_Documento_Palabra_Clave
(
	nombre VARCHAR(20),
 	id_documento INT,
 	CONSTRAINT nombre_idDocumento_pk PRIMARY KEY (nombre,id_Documento),
 	CONSTRAINT idDocumento_fk FOREIGN KEY (id_documento) REFERENCES Documento(id_documento)
  	ON DELETE RESTRICT ON UPDATE CASCADE,
  	CONSTRAINT nombre_fk FOREIGN KEY (nombre) REFERENCES Palabra_Clave(nombre)
  	ON DELETE RESTRICT ON UPDATE CASCADE 
);

--necesita Documento OK
--necesita Area_Conocimiento OK
DROP TABLE Pertenece_Documento_Area_Conocimiento CASCADE;
CREATE TABLE Pertenece_Documento_Area_Conocimiento
(
	id_area VARCHAR(3),
 	id_documento INT,
 	CONSTRAINT idArea_idDocumento_pk PRIMARY KEY (id_area,id_documento),
 	CONSTRAINT idDocumento FOREIGN KEY (id_documento) REFERENCES Documento(id_documento)
  	ON DELETE RESTRICT ON UPDATE CASCADE,
  	CONSTRAINT idArea_fk FOREIGN KEY (id_area) REFERENCES Area_Conocimiento(id_area)
  	ON DELETE RESTRICT ON UPDATE CASCADE
);


-------------------------------------Insertar datos-------------------------------------------------
--lo siguiente es un machetazo para poder insertar las demas areas sin alterar la tabla, me imagino que quedara entre nosotros xD

--insertando areas
INSERT INTO Area_Conocimiento(id_area,nombre,descripcion,area_padre) VALUES('','Super','','');
INSERT INTO Area_Conocimiento(id_area,nombre,descripcion,area_padre) VALUES('1','Computacion centrada en la red','','');
INSERT INTO Area_Conocimiento(id_area,nombre,descripcion,area_padre) VALUES('2','Construccion de Aplicaciones web','','1');
INSERT INTO Area_Conocimiento VALUES ('3', 'Metodos Numericos Computacionales', '','');
INSERT INTO Area_Conocimiento VALUES ('4', 'Modelos de Simulacion','','3');
INSERT INTO Area_Conocimiento VALUES ('5', 'Investigacion de Operaciones','','3');
INSERT INTO Area_Conocimiento VALUES ('6', 'Ingenieria de Software','','');
INSERT INTO Area_Conocimiento VALUES ('7', 'Diseno de Software','','6');
INSERT INTO Area_Conocimiento VALUES ('8', 'Gestion de Informacion','','');
INSERT INTO Area_Conocimiento VALUES ('9', 'Hipermedia e Hipertexto','','8');

--insertando autores
INSERT INTO Autor (nombre, apellido, acronimo, email) VALUES('EDGAR','MONCADA','MONKI','MONKI@GMAIL.COM');
INSERT INTO Autor (nombre, apellido, acronimo, email) VALUES('FELIPE','VARGAS','FELIPEX','FELIPEX@HOTMAIL.COM');
INSERT INTO Autor VALUES (DEFAULT,'Lorazo','Perez','Lope','lorazo@loro.com');
	
--insertando palabras clave
INSERT INTO Palabra_Clave (nombre, descripcion) VALUES('red','todo lo que tenga que ver con redes de computadoras');
INSERT INTO Palabra_Clave VALUES('programacion','programacion de computadores');
INSERT INTO Palabra_Clave VALUES('grafos','referente a teoria de grafos');

--insertando tipos de material
INSERT INTO TipoMaterial (tipo_nombre, descripcion) VALUES
	('Libro','documento extenso creado por un especifico tema');
INSERT INTO TipoMAterial VALUES('Articulo','documento pequeno creado por un especifico tema');
INSERT INTO TipoMAterial VALUES('Memoria','biografia');
INSERT INTO TipoMAterial VALUES('Trabajo de Grado','trabajo presentado para el grado por estudiantes de pregrado de ultimos semestres');
INSERT INTO TipoMAterial VALUES('Tesis de Maestria','trabajo presentado para el grado por estudiantes de maestria de ultimos semestres');
INSERT INTO TipoMAterial VALUES('Tesis de Doctorado','trabajo presentado para el grado por estudiantes de doctorado de ultimos semestres');
INSERT INTO TipoMAterial VALUES('Material de Clase','diapositivas, talleres, ejercicios y documentos del profesor');

--insertando usuarios
INSERT INTO Usuario VALUES('444', 'contrasena', 'nombre1', 'nombre2', 'apellido1', 'apellido2', 'email', 'nivel', 'pregunta', 'respuesta', 'vinculo' ,'m', '777-07-07', '6666-06-06', '1', 't');
INSERT INTO Usuario VALUES('clrl','clrl','cristian','leoanrdo','rios','lopez','dragonblanco452@gmail.com','tecnico','pregunta','respuesta','estudiante','m','1989-06-09','1989-06-09','1','t');

--insertando documentos
INSERT INTO Documento VALUES(DEFAULT, 'español', 'si', 'documento', 'adobe', '200','ninguno', 'pdf', 'Libro', 'librito', 'enlace no disponible', '1111-01-01', '2222-02-02', 'Libro', '444', '3333-03-03');
INSERT INTO Documento VALUES(DEFAULT,'ingles','si','paraiso','writer','150','mas alla','doc','en el paraiso','lo mismo','sin enlace','2000-01-15','2002-02-20','Articulo','clrl','2010-06-07');
