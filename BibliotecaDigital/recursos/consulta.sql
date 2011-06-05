(SELECT a.nombre FROM area_conocimiento AS a NATURAL JOIN pertenece_documento_area_conocimiento AS p WHERE p.id_documento = 10001)

--documentos descargados por fecha
SELECT x.id_documento, x.editorial, x. titulo_principal, y.fecha, y.cuantos FROM (SELECT d.id_documento, d.fecha, count(*) AS cuantos FROM descarga_usuario_documento AS d GROUP BY d.id_documento,d.fecha) AS y NATURAL JOIN (SELECT a.id_documento, a.titulo_principal, a.editorial FROM documento AS a) AS x;

--CONSULTA PARA MES .. AÑO EN FIN .. LUEGO ORGANIZAMOS.
select extract(year from fecha_nacimiento) año,extract(month from fecha_nacimiento) mes, login, nombre1
from usuario order by date_trunc('year',  fecha_nacimiento), date_trunc('month',  fecha_nacimiento);

