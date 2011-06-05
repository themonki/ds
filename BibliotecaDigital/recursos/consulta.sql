--area de conocimiento al que pertenece un documerno
(SELECT a.nombre FROM area_conocimiento AS a NATURAL JOIN pertenece_documento_area_conocimiento AS p WHERE p.id_documento = 10001)

--documentos descargados por fecha
SELECT x.id_documento, x.editorial, x. titulo_principal, y.fecha, y.cuantos FROM (SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x;

--usuarios por fecha de nacimiento para todos los anio y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--usuarios por fecha de nacimiento para todos los anio y todos los meses totales


--consulta por fecha de registro para todos los anio y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  u.fecha_registro);

--consulta por fecha de nacimiento para anios restringidos y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_nacimiento) BETWEEN 1989 AND 2010 ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--consulta por fecha de resgistro para anios restringidos y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_registro) BETWEEN 1989 AND 2011 ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  fecha_registro);

--consulta por fecha nacimiento para todos los anio y mese restringidos
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(MONTH FROM u.fecha_nacimiento) BETWEEN 3 AND 8 ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--consulta por fecha registro para todos los anio y meses restringidos
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(MONTH FROM u.fecha_registro) BETWEEN 03 AND 08 ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  u.fecha_registro);

--consulta por fecha nacimiento con años y mes restringidos
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_nacimiento) BETWEEN 1989 AND 2011 AND EXTRACT(MONTH FROM u.fecha_nacimiento) BETWEEN 5 AND 8 ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--consulta por fecha registro con años y mes restringidos
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_registro) BETWEEN 1989 AND 2011 AND EXTRACT(MONTH FROM u.fecha_registro) BETWEEN 5 AND 8 ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  u.fecha_registro);



--areas del conocimiento con sus respectivas areas padres
--SELECT A.nombre, B.nombre AS nombre_Area_Padre FROM area_conocimiento AS A JOIN area_conocimiento AS B ON A.area_padre = B.id_area ORDER BY nombre_Area_Padre;

--areas padres con la cantidad de areas hijas(Si el area_padre de A es igual al id_area de B es por que B es padre de A)
--SELECT B.nombre AS Areas_Padre, count(B.nombre) AS Cantidad FROM area_conocimiento AS A JOIN area_conocimiento AS B ON A.area_padre = B.id_area GROUP BY Areas_Padre ORDER BY Areas_Padre;

--documentos(titulo, descripcion, autores, editorial) con las areas a  la que pertenece
SELECT doc.titulo_principal, doc.editorial, autor_area.nombre_area, autor_area.nombre_autor FROM 
(SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc
NATURAL JOIN
((SELECT x.id_documento, x.nombre AS nombre_autor FROM (escribe_autor_documento NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s) AS x) AS autor
NATURAL JOIN
(SELECT y.id_documento, y.nombre AS nombre_area FROM (pertenece_documento_area_conocimiento NATURAL JOIN (SELECT a.id_area, a.nombre FROM area_conocimiento AS a) AS t) AS y)AS area) AS autor_area ORDER BY autor_area.nombre_area;

--documentos(titulo, descripcion,editorial) con las areas a  la que pertenece
SELECT doc.id_documento, doc.titulo_principal, doc.editorial, area.nombre_area FROM 
(SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc
NATURAL JOIN
(SELECT y.id_documento, y.nombre AS nombre_area FROM (pertenece_documento_area_conocimiento NATURAL JOIN (SELECT a.id_area, a.nombre FROM area_conocimiento AS a) AS t) AS y)AS area  ORDER BY area.nombre_area;

--documentos(titulo, descripcion,editorial) con los tipos a los que pertenece
SELECT doc.id_documento, doc.titulo_principal, doc.editorial, doc.tipo_nombre FROM documento AS doc ORDER BY doc.tipo_nombre;

--documentos(titulo, descripcion,editorial) con los formatos a los que pertenece
SELECT doc.id_documento, doc.titulo_principal, doc.editorial, doc.formato FROM documento AS doc ORDER BY doc.formato;

--documentos(titulo, descripcion,editorial) con los autores
SELECT doc.titulo_principal, doc.editorial, autor.nombre_autor FROM 
(SELECT d.id_documento, d.titulo_principal, d.editorial FROM documento AS d) AS doc
NATURAL JOIN
(SELECT x.id_documento, x.nombre AS nombre_autor FROM (escribe_autor_documento NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s) AS x) AS autor ORDER BY autor.nombre_autor;

--autor de documento
SELECT s.nombre AS nombre_autor FROM (SELECT e.id_autor FROM escribe_autor_documento AS e WHERE e.id_documento = 10001) AS t NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s;

--cantidad de documentos por formato
SELECT d.formato AS agrupado, count(d.formato) AS cantidad FROM documento as d GROUP BY agrupado ORDER BY agrupado;

--cantidad de documentos por tipo
SELECT d.tipo_nombre AS agrupado, count(d.tipo_nombre) AS cantidad FROM documento AS d GROUP BY agrupado ORDER BY agrupado;

--cantidad de documentos por areas
SELECT area.nombre_area AS agrupado, count(area.nombre_area) AS cantidad FROM 
(SELECT d.id_documento FROM documento AS d) AS doc 
NATURAL JOIN 
(SELECT y.id_documento, y.nombre AS nombre_area FROM (pertenece_documento_area_conocimiento NATURAL JOIN (SELECT a.id_area, a.nombre FROM area_conocimiento AS a) AS t) AS y)AS area  GROUP BY agrupado ORDER BY agrupado;

--cantidad de documentos por autor
SELECT autor.nombre_autor AS agrupado, count(autor.nombre_autor) AS cantidad FROM 
(SELECT d.id_documento FROM documento AS d) AS doc
NATURAL JOIN
(SELECT x.id_documento, x.nombre AS nombre_autor FROM (escribe_autor_documento NATURAL JOIN (SELECT a.id_autor, a.nombre FROM autor AS a) AS s) AS x) AS autor GROUP BY agrupado ORDER BY agrupado;
