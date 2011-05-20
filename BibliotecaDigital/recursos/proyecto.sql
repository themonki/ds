/*
Scrib biblioteca digital
Maria Andrea Cruz Blandon - 0831816
Yerminson Doney Gonzalez Muños - 0843846
Cristian Leonardo Rios Lopez - 0814239
Luis Felipe Vargas Rojas - 0836342
Edgar Andres Moncada - 0832294
*/
DROP TABLE Usuario CASCADE;
DROP TABLE Area_Conocimiento CASCADE;
DROP TABLE Interesa_Usuario_Area_Conocimiento;
DROP TABLE TipoMaterial CASCADE;
DROP TABLE Documento CASCADE;
DROP SEQUENCE id_documento_seq CASCADE;
DROP TABLE Descarga_Usuario_Documento CASCADE;
DROP SEQUENCE id_autor_seq CASCADE;
DROP TABLE Autor CASCADE;
DROP TABLE Escribe_Autor_Documento CASCADE;
DROP TABLE Consulta CASCADE;
DROP TABLE Palabra_Clave CASCADE;
DROP TABLE Tiene_Documento_Palabra_Clave CASCADE;
DROP TABLE Pertenece_Documento_Area_Conocimiento CASCADE;
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
--DROP TABLE Area_Conocimiento CASCADE;
CREATE TABLE Area_Conocimiento
(
	id_area VARCHAR(3),
	nombre VARCHAR(50) NOT NULL UNIQUE,
	descripcion VARCHAR(200),
	area_padre VARCHAR(3),
	CONSTRAINT id_area_pk PRIMARY KEY(id_area),
	CONSTRAINT area_padre_fk FOREIGN KEY (area_padre) REFERENCES Area_Conocimiento(id_area)
	ON DELETE RESTRICT ON UPDATE CASCADE
);

--necesita Usuario OK
--necesita Area_Conocimiento OK
--DROP TABLE Interesa_Usuario_Area_Conocimiento;
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

--DROP TABLE TipoMaterial CASCADE;
CREATE TABLE TipoMaterial
(
	tipo_nombre VARCHAR(20),
	descripcion VARCHAR(200) NOT NULL,
	CONSTRAINT tipoNombre_pk PRIMARY KEY (tipo_nombre)
);

--DROP SEQUENCE id_documento_seq CASCADE;
CREATE SEQUENCE id_documento_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
	START WITH 10000
    CACHE 1;

--necesita TipoMaterial OK
--necesita Usuario OK
--DROP TABLE Documento CASCADE;
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
--DROP TABLE Descarga_Usuario_Documento CASCADE;
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

--DROP SEQUENCE id_autor_seq CASCADE;
CREATE SEQUENCE id_autor_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
	START WITH 10000
    CACHE 1;

--DROP TABLE Autor CASCADE;
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
--DROP TABLE Escribe_Autor_Documento CASCADE;
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
--DROP TABLE Consulta CASCADE;
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

--DROP TABLE Palabra_Clave CASCADE;
CREATE TABLE Palabra_Clave
(
	nombre VARCHAR(20),
	descripcion VARCHAR(200),
 	CONSTRAINT nombre_pk PRIMARY KEY (nombre) 
);

--necesita Palabra_Clave OK
--necesita Documento OK
--DROP TABLE Tiene_Documento_Palabra_Clave CASCADE;
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
--DROP TABLE Pertenece_Documento_Area_Conocimiento CASCADE;
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
INSERT INTO Area_Conocimiento VALUES ('', 'super','','');
INSERT INTO Area_Conocimiento VALUES ('1', 'computacion centrada en la red','','');
INSERT INTO Area_Conocimiento VALUES ('2', 'construccion de aplicaciones web','','1');
INSERT INTO Area_Conocimiento VALUES ('3', 'metodos numericos computacionales', '','');
INSERT INTO Area_Conocimiento VALUES ('4', 'modelos de simulacion','','3');
INSERT INTO Area_Conocimiento VALUES ('5', 'investigacion de operaciones','','3');
INSERT INTO Area_Conocimiento VALUES ('6', 'ingenieria de software','','');
INSERT INTO Area_Conocimiento VALUES ('7', 'diseno de software','','6');
INSERT INTO Area_Conocimiento VALUES ('8', 'gestion de informacion','','');
INSERT INTO Area_Conocimiento VALUES ('9', 'hipermedia e hipertexto','','8');
INSERT INTO Area_Conocimiento VALUES ('10', 'modelo de sistemas de informacion','','8');
INSERT INTO Area_Conocimiento VALUES ('11', 'lenguajes de consulta de bases de datos','','8');
INSERT INTO Area_Conocimiento VALUES ('12', 'bibliotecas digitales','','8');
INSERT INTO Area_Conocimiento VALUES ('13', 'recuperacion de informacion','','8');
INSERT INTO Area_Conocimiento VALUES ('14', 'redes de comunicacion','','1');
INSERT INTO Area_Conocimiento VALUES ('15', 'diseno fisico de bases de datos','','8');
INSERT INTO Area_Conocimiento VALUES ('16', 'lenguajes de programacion','','');
INSERT INTO Area_Conocimiento VALUES ('17', 'programacion orientada a objetos','','17');
INSERT INTO Area_Conocimiento VALUES ('18', 'sistemas inteligentes','','');
INSERT INTO Area_Conocimiento VALUES ('19', 'busqueda por satisfaccion de reestricciones','','18');
INSERT INTO Area_Conocimiento VALUES ('20', 'entornos de desarrollo de software','','6');
INSERT INTO Area_Conocimiento VALUES ('21', 'diseno de bases de datos relacionales','','8');
INSERT INTO Area_Conocimiento VALUES ('22', 'validacion de software','','6');
INSERT INTO Area_Conocimiento VALUES ('23', 'computacion web cliente servidor','','1');
INSERT INTO Area_Conocimiento VALUES ('24', 'tecnologias multimedia','','1');
INSERT INTO Area_Conocimiento VALUES ('25', 'computacion inalambrica','','1');
INSERT INTO Area_Conocimiento VALUES ('26', 'procesamiento de lenguaje natural','','18');
INSERT INTO Area_Conocimiento VALUES ('27', 'sistemas multimedia','','8');
INSERT INTO Area_Conocimiento VALUES ('28', 'fundamentos de programacion','','');
INSERT INTO Area_Conocimiento VALUES ('29', 'programacion orientada por eventos','','28');
INSERT INTO Area_Conocimiento VALUES ('30', 'robotica','','18');
INSERT INTO Area_Conocimiento VALUES ('31', 'algoritmos y complejidad','','');
INSERT INTO Area_Conocimiento VALUES ('32', 'algoritmos paralelos','','31');
INSERT INTO Area_Conocimiento VALUES ('33', 'interaccion humano computador','','');
INSERT INTO Area_Conocimiento VALUES ('34', 'aspectos de comunicacion','','33');
INSERT INTO Area_Conocimiento VALUES ('35', 'desarrollo de software centrado en humano','','33');
INSERT INTO Area_Conocimiento VALUES ('36', 'diseno de interfaces graficas de usuario','','33');
INSERT INTO Area_Conocimiento VALUES ('37', 'procesamiento de transacciones','','8');
INSERT INTO Area_Conocimiento VALUES ('38', 'mineria de datos','','8');
INSERT INTO Area_Conocimiento VALUES ('39', 'computacion visual y grafica','','');
INSERT INTO Area_Conocimiento VALUES ('40', 'rendering avanzado','','39');
INSERT INTO Area_Conocimiento VALUES ('41', 'animacion por computador','','39');
INSERT INTO Area_Conocimiento VALUES ('42', 'realidad virtual','','39');
INSERT INTO Area_Conocimiento VALUES ('43', 'arquitectura y organizacion','','');
INSERT INTO Area_Conocimiento VALUES ('44', 'arquitectura para redes en sistemas distribuidos','','43');
INSERT INTO Area_Conocimiento VALUES ('45', 'analisis numerico','','3');
INSERT INTO Area_Conocimiento VALUES ('46', 'modelamiento de datos','','8');
INSERT INTO Area_Conocimiento VALUES ('47', 'bases de datos relacionales','','8');
INSERT INTO Area_Conocimiento VALUES ('48', 'bases de datos distribuidas','','8');
INSERT INTO Area_Conocimiento VALUES ('49', 'seguridad en redes','','1');
INSERT INTO Area_Conocimiento VALUES ('50', 'bioinformatica','','');
INSERT INTO Area_Conocimiento VALUES ('51', 'estructuras discretas','','');
INSERT INTO Area_Conocimiento VALUES ('52', 'grafos y arboles','','51');
INSERT INTO Area_Conocimiento VALUES ('53', 'sistemas de bases de datos','','8');
INSERT INTO Area_Conocimiento VALUES ('54', 'representacion de conocimiento','','18');
INSERT INTO Area_Conocimiento VALUES ('55', 'probabilidad discreta','','51');
INSERT INTO Area_Conocimiento VALUES ('56', 'visualizacion','','39');
INSERT INTO Area_Conocimiento VALUES ('57', 'sistemas operativos','','');
INSERT INTO Area_Conocimiento VALUES ('58', 'sistemas en tiempo real','','57');
INSERT INTO Area_Conocimiento VALUES ('59', 'tolerancia a fallas','','57');
INSERT INTO Area_Conocimiento VALUES ('60', 'algoritmos criptograficos','','31');
INSERT INTO Area_Conocimiento VALUES ('61', 'maquinas virtuales','','17');
INSERT INTO Area_Conocimiento VALUES ('62', 'programacion funcional','','17');
INSERT INTO Area_Conocimiento VALUES ('63', 'aprendizaje redes neuronales','','18');

--insertando autores
INSERT INTO Autor (nombre, apellido, acronimo, email) VALUES('james f','kurose','jfk','jfk@correo.com ');
INSERT INTO Autor (nombre, apellido, acronimo, email) VALUES('keith w','ross','kwr','kwr@correo.com');
	
--insertando palabras clave
INSERT INTO Palabra_Clave VALUES('red','todo lo que tenga que ver con redes de computadoras');
INSERT INTO Palabra_Clave VALUES('programacion','programacion de computadores');
INSERT INTO Palabra_Clave VALUES('grafos','referente a teoria de grafos');

--insertando tipos de material
INSERT INTO TipoMaterial VALUES('libro','documento extenso creado por un especifico tema');
INSERT INTO TipoMAterial VALUES('articulo','documento pequeno creado por un especifico tema');
INSERT INTO TipoMAterial VALUES('memoria','biografia');
INSERT INTO TipoMAterial VALUES('trabajo de grado','trabajo presentado para el grado por estudiantes de pregrado de ultimos semestres');
INSERT INTO TipoMAterial VALUES('tesis de maestria','trabajo presentado para el grado por estudiantes de maestria de ultimos semestres');
INSERT INTO TipoMAterial VALUES('tesis de doctorado','trabajo presentado para el grado por estudiantes de doctorado de ultimos semestres');
INSERT INTO TipoMAterial VALUES('taterial de clase','diapositivas, talleres, ejercicios y documentos del profesor');

--insertando usuarios
INSERT INTO usuario VALUES('anonimo', 'anonimo', 'anonimo', 'anonimo', 'anonimo', 'univalle', 'anonimo@correo', 'Profesional/Universidad', 'Ciudad natal de la abuela', 'respuesta', 'Estudiante de pregrado' ,'M', '2011-05-08', '2011-05-08', '0', 'f');
INSERT INTO Usuario VALUES('admin', 'digital', 'biblioteca', 'digital', 'eisc', 'univalle', 'bibliotecaeisc@correo.univalle.co', 'Profesional/Universidad', 'Ciudad natal de la abuela', 'respuesta', 'Estudiante de pregrado' ,'M', '2011-05-08', '2011-05-08', '1', 't');
INSERT INTO Usuario VALUES('clrl','clrl','cristian','leonardo','rios','lopez','dragonblanco452@gmail.com','Tecnología','Ciudad natal de la abuela','respuesta','Estudiante de pregrado','M','1989-06-09','2011-05-08','2','t');

--insertando interesa_usuario_area_conocimiento
--INSERT INTO interesa_usuario_area_conocimiento VALUES('admin','3');
--INSERT INTO interesa_usuario_area_conocimiento VALUES('admin','9');

--insertando documentos
--INSERT INTO Documento VALUES(DEFAULT, 'español', 'si', 'documento', 'adobe', '200','ninguno', 'pdf', 'Libro', 'librito', 'enlace no disponible', '1111-01-01', '2222-02-02', 'libro', 'admin', '3333-03-03');
--INSERT INTO Documento VALUES(DEFAULT,'ingles','si','paraiso','writer','150','mas alla','doc','en el paraiso','lo mismo','sin enlace','2000-01-15','2002-02-20','articulo','clrl','2010-06-07');
INSERT INTO Documento VALUES(DEFAULT,'ingles','si','libro de redes','adobe','0','pearson','pdf','computing networking aproach top-down','','repositorio/Computer Networking - A Top-down Approach Featuring the Internet, 3rd Ed [by Kurose, Ross].pdf', '2006-05-14','2006-05-14','libro','clrl','2010-06-07');

--insertando areas del documento
INSERT INTO pertenece_documento_area_conocimiento VALUES ('49', '10000');

--insertando autores del documento
INSERT INTO escribe_autor_documento VALUES ('10000', '10000');
INSERT INTO escribe_autor_documento VALUES ('10001', '10000');

--insertando palabras clave del documento
INSERT INTO tiene_documento_palabra_clave VALUES ('red', '10000');

