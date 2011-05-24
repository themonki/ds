DROP TABLE facultad CASCADE;
DROP TABLE profesor CASCADE;
DROP TABLE proyecto CASCADE;
DROP TABLE profesores_proyecto CASCADE;
DROP TABLE evaluador CASCADE;
DROP TABLE evaluadores_proyecto CASCADE;

CREATE TABLE facultad (
codigo_facultad CHAR(7),
nombre VARCHAR(30),
telefono CHAR(7),
direccion VARCHAR(25),
email VARCHAR (25),
pagina_Web VARCHAR(30),
profesor_id CHAR(8) NOT NULL,
PRIMARY KEY (codigo_facultad)
);

CREATE TABLE profesor(
profesor_id CHAR(8),
nombre VARCHAR(30),
telefono CHAR(7),
direccion VARCHAR(25),
email VARCHAR (25),
titulo_obtenido VARCHAR(40),
facultad_id CHAR(7) NOT NULL,
FOREIGN KEY (facultad_id) REFERENCES facultad(codigo_facultad) ON DELETE RESTRICT ON UPDATE CASCADE,
PRIMARY KEY (profesor_id)
);

CREATE TABLE proyecto(
proyecto_id CHAR(6),
nombre VARCHAR(25),
descripcion VARCHAR(70),
presupuesto INTEGER,
fecha_inicio DATE,
fecha_fin DATE,
estado VARCHAR(10),
facultad_id CHAR(7),
FOREIGN KEY (facultad_id) REFERENCES facultad(codigo_facultad) ON DELETE RESTRICT ON UPDATE CASCADE,
PRIMARY KEY (proyecto_id)
);

CREATE TABLE profesores_proyecto(
profesor_id CHAR(8) NOT NULL, 
proyecto_id CHAR(6) NOT NULL,
FOREIGN KEY (profesor_id) REFERENCES profesor (profesor_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (proyecto_id) REFERENCES proyecto (proyecto_id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (profesor_id, proyecto_id)
);

CREATE TABLE evaluador(
evaluador_id CHAR(10),
nombre VARCHAR(30),
telefono CHAR(7),
direccion VARCHAR(25),
email VARCHAR (25),
titulo_academico VARCHAR(20),
anos_experiencia VARCHAR(3),
PRIMARY KEY (evaluador_id)
);

CREATE TABLE evaluadores_proyecto(
proyecto_id CHAR(6) NOT NULL,
evaluador_id CHAR(8) NOT NULL,
FOREIGN KEY (proyecto_id) REFERENCES proyecto (proyecto_id) ON DELETE CASCADE ON UPDATE CASCADE,
FOREIGN KEY (evaluador_id) REFERENCES evaluador (evaluador_id) ON DELETE CASCADE ON UPDATE CASCADE,
PRIMARY KEY (proyecto_id, evaluador_id)
);



/*las nuevas adiciones*/
ALTER TABLE proyecto 
ADD COLUMN calificacion NUMERIC(2,1);

ALTER TABLE profesor
ADD COLUMN categoria VARCHAR(9) NOT NULL;


/*INSERTS FACULTAD*/
INSERT INTO facultad (codigo_facultad, nombre, direccion, telefono, email, pagina_web, profesor_id)
VALUES ('ing-001', 'Ingenieria', 'cra 2 # 24 - 4' , '4657848','ing@gmail.com', 'ingenieria.uni.edu' , '56789012');
INSERT INTO facultad (codigo_facultad, nombre, direccion, telefono, email, pagina_web, profesor_id)
VALUES ('cie-001', 'Ciencias', 'calle 3 # 56 - 234' , '4672314','ciencias@unimail.com', 'ciencias.uni.edu' , '90123456');
INSERT INTO facultad (codigo_facultad, nombre, direccion, telefono, email, pagina_web, profesor_id)
VALUES ('art-001', 'Artes', 'cra 2 # 24 - 4' , '4768923','art@gmail.com', 'artesintegradas.uni.edu' , '34567890');
/*INSERT INTO facultad (codigo_facultad, nombre, direccion, telefono, email, pagina_web, profesor_id)
VALUES ('soc-001', 'Ciencias Sociales', 'cra 2 # 24 - 4' , '4768900','soc@unimail.com', 'sociales.uni.edu' , '45671238');*/

/*INSERTS PROFESOR*/
INSERT INTO profesor (profesor_id, nombre, direccion, telefono, email, titulo_obtenido, facultad_id, categoria)
VALUES ('56789012', 'juan', 'cll 3 # 45 - 4', '4561234', 'juan@unimail.com', 'ingeniero', 'ing-001', 'asociado');
INSERT INTO profesor (profesor_id, nombre, direccion, telefono, email, titulo_obtenido, facultad_id, categoria)
VALUES ('90123456', 'andrea', 'cra 4 # 5 - 23', '4789230', 'andrea@unimail.com', 'quimico', 'ing-001', 'titular');
INSERT INTO profesor (profesor_id, nombre, direccion, telefono, email, titulo_obtenido, facultad_id, categoria)
VALUES ('34567890', 'pedro', 'cll 33 # 2 - 56', '5783492', 'pedro@gmail.com', 'diseñador', 'ing-001', 'auxiliar');

/*INSERTS PROYECTO*/
INSERT INTO proyecto (proyecto_id, nombre, descripcion, presupuesto, fecha_inicio, fecha_fin, estado, calificacion, facultad_id)
VALUES ('354678', 'fenix', 'investigacion de especies en via de extincion', 2000000, '2-2-2001', '14-1-2005', 'inactivo', 4.3, 'cie-001');
INSERT INTO proyecto (proyecto_id, nombre, descripcion, presupuesto, fecha_inicio, fecha_fin, estado, calificacion,  facultad_id)
VALUES ('675434', 'rednural', 'investigacion de redes neuronales', 5000000, '2-12-2004', '14-5-2012', 'activo', 3.9,  'ing-001');
INSERT INTO proyecto (proyecto_id, nombre, descripcion, presupuesto, fecha_inicio, fecha_fin, estado, calificacion, facultad_id)
VALUES ('745623', 'fed', 'desarrollo de tecnicas de optica', 7000000, '5-5-2009', '12-12-2010', 'activo', 4.2, 'art-001');

/*INSERTS EVALUADOR*/
INSERT INTO evaluador (evaluador_id, nombre, telefono, direccion, email, titulo_academico, anos_experiencia) 
VALUES ('25673462', 'carlos','2738674', 'cra 1 # 55 - 66', 'carl@gmail.com', 'ingeniero', 5 );
INSERT INTO evaluador (evaluador_id, nombre, telefono, direccion, email, titulo_academico, anos_experiencia) 
VALUES ('68239740', 'maria','5673284', 'cll 1 # 3 - 132', 'mar@gmail.com', 'biologo', 5 );
INSERT INTO evaluador (evaluador_id, nombre, telefono, direccion, email, titulo_academico, anos_experiencia) 
VALUES ('34343434', 'alberto','6773285', 'cra 5 # 67 - 34', 'alb@gmail.com', 'fisico', 5 );
INSERT INTO evaluador (evaluador_id, nombre, telefono, direccion, email, titulo_academico, anos_experiencia) 
VALUES ('98402846', 'andres','4782938', 'cll 34 # 12 - 12', 'and@gmail.com', 'quimica', 5 );
INSERT INTO evaluador (evaluador_id, nombre, telefono, direccion, email, titulo_academico, anos_experiencia) 
VALUES ('13962784', 'paul','4723894', 'cll 56 # 89 - 5', 'pagdr@gmail.com', 'diseñador', 5 );
INSERT INTO evaluador (evaluador_id, nombre, telefono, direccion, email, titulo_academico, anos_experiencia) 
VALUES ('09473892', 'laura','4573829', 'cra 23 # 45 - 134', 'l34jj@gmail.com', 'diseñador', 5 );

/*INSERTS PROFESORES_PROYECTO*/
INSERT INTO profesores_proyecto (profesor_id, proyecto_id) VALUES ('56789012','354678');
INSERT INTO profesores_proyecto (profesor_id, proyecto_id) VALUES ('90123456','354678');
INSERT INTO profesores_proyecto (profesor_id, proyecto_id) VALUES ('56789012','675434');
INSERT INTO profesores_proyecto (profesor_id, proyecto_id) VALUES ('90123456','675434');
INSERT INTO profesores_proyecto (profesor_id, proyecto_id) VALUES ('34567890','745623');


/*INSERTS EVALUADORES_PROYECTO*/
INSERT INTO evaluadores_proyecto (proyecto_id, evaluador_id) VALUES ('745623','13962784');
INSERT INTO evaluadores_proyecto (proyecto_id, evaluador_id) VALUES ('745623','09473892');
INSERT INTO evaluadores_proyecto (proyecto_id, evaluador_id) VALUES ('354678','68239740');
INSERT INTO evaluadores_proyecto (proyecto_id, evaluador_id) VALUES ('675434','25673462');
INSERT INTO evaluadores_proyecto (proyecto_id, evaluador_id) VALUES ('675434','34343434');

/*PARA ADICIONAR LA LLAVE FORANEA EN FACULTAD*/
ALTER TABLE facultad 
ADD FOREIGN KEY (profesor_id) REFERENCES profesor(profesor_id) ON DELETE RESTRICT ON UPDATE CASCADE;


