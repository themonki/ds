--area de conocimiento al que pertenece un documerno
(SELECT a.nombre FROM area_conocimiento AS a NATURAL JOIN pertenece_documento_area_conocimiento AS p WHERE p.id_documento = 10001)

--************************************************************************************
--cantidad documentos(id_documento, editorial, titulo_principal) descargados por fecha -5 atributos
SELECT x.id_documento, x.editorial, x.titulo_principal, y.fecha, y.cuantos FROM (SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY y.fecha;

--cantidad documentos(id_documento, editorial, titulo_principal) descargados por un intervalo de fecha - 5 atributos
SELECT x.id_documento, x.editorial, x. titulo_principal, y.fecha, y.cuantos FROM (SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d WHERE d.fecha BETWEEN '1980-02-05' AND '2010-07-20' GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY y.fecha;

--cantidad de documentos descargados por area del conocimiento - 6 atributos
SELECT x.id_documento, x.editorial, x. titulo_principal, m.fecha, m.cuantos, m.nombre_area FROM (((SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN pertenece_documento_area_conocimiento) AS w NATURAL JOIN (SELECT a.id_area, a.nombre AS nombre_area FROM area_conocimiento AS a) AS s) AS m NATURAL JOIN (SELECT t.id_documento, t.titulo_principal, t.editorial FROM documento AS t) AS x ORDER BY m.nombre_area;

--cantidad de documento descargados por area del conocimiento restringido por fecha - 6 atibutos
SELECT x.id_documento, x.editorial, x. titulo_principal, m.fecha, m.cuantos, m.nombre_area FROM (((SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d WHERE d.fecha  BETWEEN '1980-02-05' AND '2010-07-20' GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN pertenece_documento_area_conocimiento) AS w NATURAL JOIN (SELECT a.id_area, a.nombre AS nombre_area FROM area_conocimiento AS a) AS s) AS m NATURAL JOIN (SELECT t.id_documento, t.titulo_principal, t.editorial FROM documento AS t) AS x ORDER BY m.nombre_area;

--cantidad de documentos descargados por usuario - 8 atibutos
SELECT x.id_documento, x.editorial, x. titulo_principal, w.fecha, w.cuantos, w.login, w.nombre1, w.apellido1 FROM ((SELECT d.id_documento, d.login, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d GROUP BY d.id_documento,d.fecha,d.login) AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS s) AS w NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY w.login;

--cantidad de documentos descargados por usuario restringidos por fecha - 8 atibutos
SELECT x.id_documento, x.editorial, x. titulo_principal, w.fecha, w.cuantos, w.login, w.nombre1, w.apellido1 FROM ((SELECT d.id_documento, d.login, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d WHERE d.fecha BETWEEN '1980-02-05' AND '2010-07-20' GROUP BY d.id_documento,d.fecha,d.login) AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS s) AS w NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY w.login;

--************************************************************************************
--cantidad documentos(id_documento, editorial, titulo_principal) consultados por fecha -5 atributos
SELECT x.id_documento, x.editorial, x. titulo_principal, y.fecha, y.cuantos FROM (SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM consulta AS d GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY y.fecha;

--cantidad documentos(id_documento, editorial, titulo_principal) consultados por un intervalo de fecha - 5 atributos
SELECT x.id_documento, x.editorial, x. titulo_principal, y.fecha, y.cuantos FROM (SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM consulta AS d WHERE d.fecha BETWEEN '1980-02-05' AND '2010-07-20' GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY y.fecha;

--cantidad de documentos consultados por area del conocimiento - 6 atributos
SELECT x.id_documento, x.editorial, x. titulo_principal, m.fecha, m.cuantos, m.nombre_area FROM (((SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM consulta AS d GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN pertenece_documento_area_conocimiento) AS w NATURAL JOIN (SELECT a.id_area, a.nombre AS nombre_area FROM area_conocimiento AS a) AS s) AS m NATURAL JOIN (SELECT t.id_documento, t.titulo_principal, t.editorial FROM documento AS t) AS x ORDER BY m.nombre_area;

--cantidad de documento consultados por area del conocimiento restringido por fecha - 6 atibutos
SELECT x.id_documento, x.editorial, x. titulo_principal, m.fecha, m.cuantos, m.nombre_area FROM (((SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM consulta AS d WHERE d.fecha  BETWEEN '1980-02-05' AND '2010-07-20' GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN pertenece_documento_area_conocimiento) AS w NATURAL JOIN (SELECT a.id_area, a.nombre AS nombre_area FROM area_conocimiento AS a) AS s) AS m NATURAL JOIN (SELECT t.id_documento, t.titulo_principal, t.editorial FROM documento AS t) AS x ORDER BY m.nombre_area;

--cantidad de documentos consultados por usuario - 8 atibutos
SELECT x.id_documento, x.editorial, x. titulo_principal, w.fecha, w.cuantos, w.login, w.nombre1, w.apellido1 FROM ((SELECT d.id_documento, d.login, d.fecha, count(*) AS cuantos FROM consulta AS d GROUP BY d.id_documento,d.fecha,d.login) AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS s) AS w NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY w.login;

--cantidad de documentos consultados por usuario restringidos por fecha - 8 atributos
SELECT x.id_documento, x.editorial, x. titulo_principal, w.fecha, w.cuantos, w.login, w.nombre1, w.apellido1 FROM ((SELECT d.id_documento, d.login, d.fecha, count(*) AS cuantos FROM consulta AS d WHERE d.fecha BETWEEN '1980-02-05' AND '2010-07-20' GROUP BY d.id_documento,d.fecha,d.login) AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS s) AS w NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x ORDER BY w.login;

--************************************************************************************

--documentos catalogados por fecha - atributos 7
SELECT d.id_documento, d.editorial, d.titulo_principal, d.fecha_catalogacion, d.login_catalogador, x.nombre1, x.apellido1 FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login ORDER BY d.fecha_catalogacion;

--documentos catalogados por intervalo de fecha -a tributos 7
SELECT d.id_documento, d.editorial, d.titulo_principal, d.fecha_catalogacion, d.login_catalogador, x.nombre1, x.apellido1 FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login WHERE d.fecha_catalogacion BETWEEN '1989-08-15' AND '2010-10-21' ORDER BY d.fecha_catalogacion;

--documento catalogado por intervalo de anio - atributos 8
SELECT d.id_documento, d.editorial, d.titulo_principal, d.login_catalogador, x.nombre1, x.apellido1, EXTRACT(YEAR FROM d.fecha_catalogacion) AS anio, EXTRACT(MONTH FROM d.fecha_catalogacion) AS mes FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login WHERE EXTRACT(YEAR FROM d.fecha_catalogacion) BETWEEN '1989' AND '2010' ORDER BY d.fecha_catalogacion;

--documetno catalogado por intervalo de mes - atributos
SELECT d.id_documento, d.editorial, d.titulo_principal, d.login_catalogador, x.nombre1, x.apellido1, EXTRACT(YEAR FROM d.fecha_catalogacion) AS anio, EXTRACT(MONTH FROM d.fecha_catalogacion) AS mes FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login WHERE EXTRACT(MONTH FROM d.fecha_catalogacion) BETWEEN '03' AND '10' ORDER BY d.fecha_catalogacion;

--documento catalogado por intervalo de año y mes
SELECT d.id_documento, d.editorial, d.titulo_principal, d.login_catalogador, x.nombre1, x.apellido1, EXTRACT(YEAR FROM d.fecha_catalogacion) AS anio, EXTRACT(MONTH FROM d.fecha_catalogacion) AS mes FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login WHERE EXTRACT(MONTH FROM d.fecha_catalogacion) BETWEEN '03' AND '10' AND EXTRACT(YEAR FROM d.fecha_catalogacion) BETWEEN '1989' AND '2010' ORDER BY d.fecha_catalogacion;

--documentos catalogados por area - atributos 8
SELECT y.id_documento, y.editorial, y.titulo_principal, y.fecha_catalogacion, y.login_catalogador, x.nombre1, x.apellido1, y.nombre_area FROM ((SELECT d.id_documento, d.editorial, d.titulo_principal, d.fecha_catalogacion, d.login_catalogador, c.id_area FROM documento AS d NATURAL JOIN pertenece_documento_area_conocimiento AS c) AS s NATURAL JOIN (SELECT a.id_area, a. nombre AS nombre_area FROM area_conocimiento AS a) AS t) AS y JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON y.login_catalogador = x.login ORDER BY y.nombre_area;

--documento catalogado por area por intervalo de fecha  - atributos 8
SELECT y.id_documento, y.editorial, y.titulo_principal, y.fecha_catalogacion, y.login_catalogador, x.nombre1, x.apellido1, y.nombre_area FROM ((SELECT d.id_documento, d.editorial, d.titulo_principal, d.fecha_catalogacion, d.login_catalogador, c.id_area FROM documento AS d NATURAL JOIN pertenece_documento_area_conocimiento AS c) AS s NATURAL JOIN (SELECT a.id_area, a. nombre AS nombre_area FROM area_conocimiento AS a) AS t) AS y JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON y.login_catalogador = x.login WHERE y.fecha_catalogacion BETWEEN '1989-08-15' AND '2010-10-21' ORDER BY y.nombre_area;

--documento catalogado por usuario catalogador - atributos 7
SELECT d.id_documento, d.editorial, d.titulo_principal, d.fecha_catalogacion, d.login_catalogador, x.nombre1, x.apellido1 FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login ORDER BY d.login_catalogador;

--documento catalogado por usuario catalogador por intervalo de fecha - atributos 7
SELECT d.id_documento, d.editorial, d.titulo_principal, d.fecha_catalogacion, d.login_catalogador, x.nombre1, x.apellido1 FROM documento AS d JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ON d.login_catalogador = x.login WHERE d.fecha_catalogacion BETWEEN '1989-08-15' AND '2010-10-21' ORDER BY d.login_catalogador;

--************************************************************************************

--usuarios por fecha de nacimiento para todos los anio y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--usuarios por fecha de registro para todos los anio y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  u.fecha_registro);

--usuarios por fecha de nacimiento para anios restringidos y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_nacimiento) BETWEEN 1989 AND 2010 ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--usuarios por fecha de resgistro para anios restringidos y todos los meses organiado
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_registro) BETWEEN 1989 AND 2011 ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  fecha_registro);

--usuairios por fecha nacimiento para todos los anio y mese restringidos
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(MONTH FROM u.fecha_nacimiento) BETWEEN 3 AND 8 ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--usuarios por fecha registro para todos los anio y meses restringidos
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(MONTH FROM u.fecha_registro) BETWEEN 03 AND 08 ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  u.fecha_registro);

--usuarios por fecha nacimiento con años y mes restringidos
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_nacimiento) BETWEEN 1989 AND 2011 AND EXTRACT(MONTH FROM u.fecha_nacimiento) BETWEEN 5 AND 8 ORDER BY date_trunc('year', u.fecha_nacimiento), date_trunc('month',  u.fecha_nacimiento);

--usuarios por fecha registro con años y mes restringidos
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, u.login, u.nombre1, u.apellido1, u.email, u.vinculo_univalle, u.tipo FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_registro) BETWEEN 1989 AND 2011 AND EXTRACT(MONTH FROM u.fecha_registro) BETWEEN 5 AND 8 ORDER BY date_trunc('year', u.fecha_registro), date_trunc('month',  u.fecha_registro);
---------------------------------
--usuarios por fecha de nacimiento para todos los anio y todos los meses totales
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, count(u.login) AS cuantos FROM usuario AS u GROUP BY anio,mes ORDER BY anio, mes;

--usuarios por fecha de registro para todos los anio y todos los meses totales
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, count(u.login) AS cuantos FROM usuario AS u GROUP BY anio, mes ORDER BY anio, mes;

--usuarios por fecha de nacimiento para anios restringidos y todos los meses totales
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, count(u.login) AS cuantos FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_nacimiento) BETWEEN 1989 AND 2010 Group anio,mes BY ORDER BY anio, mes;

--usuarios por fecha de resgistro para anios restringidos y todos los meses totales
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, count(u.login) AS cuantos FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_registro) BETWEEN 1989 AND 2011 GROUP BY anio, mes ORDER BY anio, mes;

--usuarios por fecha nacimiento para todos los anio y mese restringidos totales
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, count(u.login) AS cuantos FROM usuario AS u WHERE EXTRACT(MONTH FROM u.fecha_nacimiento) BETWEEN 3 AND 8 GROUP BY anio,mes ORDER BY anio,mes;

--usuarios por fecha registro para todos los anio y meses restringidos totales
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, count(u.login) AS cuantos FROM usuario AS u WHERE EXTRACT(MONTH FROM u.fecha_registro) BETWEEN 03 AND 08 GROUP BY anio,mes ORDER BY anio,mes;

--usuarios por fecha nacimiento con años y mes restringidos totales
SELECT EXTRACT(YEAR FROM u.fecha_nacimiento) AS anio, EXTRACT(MONTH FROM u.fecha_nacimiento) AS mes, count(u.login) AS cuantos FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_nacimiento) BETWEEN 1989 AND 2011 AND EXTRACT(MONTH FROM u.fecha_nacimiento) BETWEEN 5 AND 8 GROUP BY anio,mes ORDER BY anio,mes;

--usuarios por fecha registro con años y mes restringidos totales
SELECT EXTRACT(YEAR FROM u.fecha_registro) AS anio, EXTRACT(MONTH FROM u.fecha_registro) AS mes, count(u.login) AS cuantos FROM usuario AS u WHERE EXTRACT(YEAR FROM u.fecha_registro) BETWEEN 1989 AND 2011 AND EXTRACT(MONTH FROM u.fecha_registro) BETWEEN 5 AND 8 GROUP BY anio,mes ORDER BY anio,mes;
--************************************************************************************


--areas del conocimiento con sus respectivas areas padres
--SELECT A.nombre, B.nombre AS nombre_Area_Padre FROM area_conocimiento AS A JOIN area_conocimiento AS B ON A.area_padre = B.id_area ORDER BY nombre_Area_Padre;

--areas padres con la cantidad de areas hijas(Si el area_padre de A es igual al id_area de B es por que B es padre de A)
--SELECT B.nombre AS Areas_Padre, count(B.nombre) AS Cantidad FROM area_conocimiento AS A JOIN area_conocimiento AS B ON A.area_padre = B.id_area GROUP BY Areas_Padre ORDER BY Areas_Padre;

--************************************************************************************
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
--************************************************************************************
--consutlas pora totales docuemtnos descargados por area
SELECT m.nombre_area, x.cuantos FROM (SELECT id_area, sum(total) AS cuantos FROM ((SELECT d.id_documento, count(*) AS total FROM descarga_usuario_documento AS d GROUP BY d.id_documento) AS y NATURAL JOIN pertenece_documento_area_conocimiento) AS b GROUP BY id_area) AS x NATURAL JOIN (SELECT a.id_area, a.nombre AS nombre_area FROM area_conocimiento AS a) AS m ORDER BY m.nombre_area;

--conusltas para totales documentos descargados por usuario
SELECT x.login, x.nombre1, x.apellido1, y.cuantos FROM (SELECT d.login, count(*) AS cuantos FROM descarga_usuario_documento AS d GROUP BY d.login) AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ORDER BY x.login;

--consutlas pora totales docuemtnos consultados por area
SELECT m.nombre_area, x.cuantos FROM (SELECT id_area, sum(total) AS cuantos FROM ((SELECT d.id_documento, count(*) AS total FROM consulta AS d GROUP BY d.id_documento) AS y NATURAL JOIN pertenece_documento_area_conocimiento) AS b GROUP BY id_area) AS x NATURAL JOIN (SELECT a.id_area, a.nombre AS nombre_area FROM area_conocimiento AS a) AS m ORDER BY m.nombre_area;

--conusltas para totales documentos consultados por usuario
SELECT x.login, x.nombre1, x.apellido1, y.cuantos FROM (SELECT d.login, count(*) AS cuantos FROM consulta AS d GROUP BY d.login) AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ORDER BY x.login; 

--consulta area totales para documentos catalogados
SELECT y.nombre AS agrupado, x.cantidad FROM (SELECT id_area, count(id_area) AS cantidad FROM (SELECT d.id_documento FROM documento AS d) AS a NATURAL JOIN pertenece_documento_area_conocimiento GROUP BY id_area) AS x NATURAL JOIN (SELECT id_area, nombre FROM area_conocimiento) AS y ORDER BY y.nombre;

--consulta usuairo total para docuemtnos catalogados
SELECT d.login_catalogador AS login, count(d.login_catalogador) AS cantidad FROM documento AS d GROUP BY d.login_catalogador;

--consulta usuario total documentos descargados restringido por fecha
SELECT x.login AS agrupado, x.nombre1, x.apellido1, y.cantidad FROM (SELECT d.login, count(*) AS cantidad FROM descarga_usuario_documento AS d WHERE d.fecha BETWEEN '2011-07-04' AND '2011-07-04') AS y NATURAL JOIN (SELECT u.login, u.nombre1, u.apellido1 FROM usuario AS u) AS x ORDER BY x.login;

--consulta cantidad de documentos descargados por año
SELECT count(d.fecha) AS cuantos, EXTRACT(YEAR FROM d.fecha) AS anio FROM descarga_usuario_documento AS d GROUP BY EXTRACT(YEAR FROM d.fecha);

--consulta cantidad de documentos descargados por mes encada año
SELECT EXTRACT(YEAR FROM d.fecha) AS anio, EXTRACT(MONTH FROM d.fecha) AS mes, count(d.fecha) AS cuantos FROM  descarga_usuario_documento AS d GROUP BY anio,mes ORDER BY anio,mes;
