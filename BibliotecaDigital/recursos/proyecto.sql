--script biblioteca digital
--María Andrea Cruz Blandón código 0831816
--Yerminson Doney Gonzalez Muñoz código 0843846
--Cristian Leonardo Ríos López código 0814239

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
