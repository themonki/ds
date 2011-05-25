--
-- PostgreSQL database dump
--

SET client_encoding = 'LATIN1';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

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

--
-- Name: area_conocimiento; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE area_conocimiento (
    id_area character varying(3) NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion character varying(200),
    area_padre character varying(3)
);


--ALTER TABLE public.area_conocimiento OWNER TO edgaramt;

--
-- Name: id_autor_seq; Type: SEQUENCE; Schema: public; Owner: edgaramt
--

CREATE SEQUENCE id_autor_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--ALTER TABLE public.id_autor_seq OWNER TO edgaramt;

--
-- Name: id_autor_seq; Type: SEQUENCE SET; Schema: public; Owner: edgaramt
--

SELECT pg_catalog.setval('id_autor_seq', 10007, true);


--
-- Name: autor; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE autor (
    id_autor integer DEFAULT nextval('id_autor_seq'::regclass) NOT NULL,
    nombre character varying(100) NOT NULL,
    email character varying(50),
    apellido character varying(30) NOT NULL,
    acronimo character varying(20)
);


--ALTER TABLE public.autor OWNER TO edgaramt;

--
-- Name: consulta; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE consulta (
    id_documento integer NOT NULL,
    login character varying(10) NOT NULL,
    fecha date NOT NULL,
    hora time without time zone NOT NULL
);


--ALTER TABLE public.consulta OWNER TO edgaramt;

--
-- Name: descarga_usuario_documento; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE descarga_usuario_documento (
    fecha date NOT NULL,
    hora time without time zone NOT NULL,
    login character varying(10) NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.descarga_usuario_documento OWNER TO edgaramt;

--
-- Name: id_documento_seq; Type: SEQUENCE; Schema: public; Owner: edgaramt
--

CREATE SEQUENCE id_documento_seq
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--ALTER TABLE public.id_documento_seq OWNER TO edgaramt;

--
-- Name: id_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: edgaramt
--

SELECT pg_catalog.setval('id_documento_seq', 10011, true);


--
-- Name: documento; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE documento (
    id_documento integer DEFAULT nextval('id_documento_seq'::regclass) NOT NULL,
    idioma character varying(15) NOT NULL,
    derechos_autor character varying(20),
    descripcion character varying(200) NOT NULL,
    software_recomendado character varying(20),
    resolucion character varying(15),
    editorial character varying(30),
    formato character varying(5) NOT NULL,
    titulo_principal character varying(50) NOT NULL,
    titulo_secundario character varying(50),
    link character varying(200) NOT NULL,
    fecha_creacion date,
    fecha_publicacion date,
    tipo_nombre character varying(20),
    login_catalogador character varying(10),
    fecha_catalogacion date NOT NULL
);


--ALTER TABLE public.documento OWNER TO edgaramt;

--
-- Name: escribe_autor_documento; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE escribe_autor_documento (
    id_autor integer NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.escribe_autor_documento OWNER TO edgaramt;

--
-- Name: interesa_usuario_area_conocimiento; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE interesa_usuario_area_conocimiento (
    login character varying(10) NOT NULL,
    id_area character varying(3) NOT NULL
);


--ALTER TABLE public.interesa_usuario_area_conocimiento OWNER TO edgaramt;

--
-- Name: palabra_clave; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE palabra_clave (
    nombre character varying(20) NOT NULL,
    descripcion character varying(200)
);


--ALTER TABLE public.palabra_clave OWNER TO edgaramt;

--
-- Name: pertenece_documento_area_conocimiento; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE pertenece_documento_area_conocimiento (
    id_area character varying(3) NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.pertenece_documento_area_conocimiento OWNER TO edgaramt;

--
-- Name: tiene_documento_palabra_clave; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE tiene_documento_palabra_clave (
    nombre character varying(20) NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.tiene_documento_palabra_clave OWNER TO edgaramt;

--
-- Name: tipomaterial; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE tipomaterial (
    tipo_nombre character varying(20) NOT NULL,
    descripcion character varying(200) NOT NULL
);


--ALTER TABLE public.tipomaterial OWNER TO edgaramt;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: edgaramt; Tablespace: 
--

CREATE TABLE usuario (
    login character varying(10) NOT NULL,
    contrasena character varying(20) NOT NULL,
    nombre1 character varying(30) NOT NULL,
    nombre2 character varying(30),
    apellido1 character varying(30) NOT NULL,
    apellido2 character varying(30),
    email character varying(50) NOT NULL,
    nivel_escolaridad character varying(30),
    pregunta_secreta character varying(50) NOT NULL,
    respuesta_secreta character varying(30) NOT NULL,
    vinculo_univalle character varying(50),
    genero character(1) NOT NULL,
    fecha_nacimiento date,
    fecha_registro date NOT NULL,
    tipo character(1) DEFAULT '3'::bpchar NOT NULL,
    estado boolean DEFAULT true NOT NULL
);


--ALTER TABLE public.usuario OWNER TO edgaramt;

--
-- Data for Name: area_conocimiento; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY area_conocimiento (id_area, nombre, descripcion, area_padre) FROM stdin;
	super		
1	computacion centrada en la red		
2	construccion de aplicaciones web		1
3	metodos numericos computacionales		
4	modelos de simulacion		3
5	investigacion de operaciones		3
6	ingenieria de software		
7	diseno de software		6
8	gestion de informacion		
9	hipermedia e hipertexto		8
10	modelo de sistemas de informacion		8
11	lenguajes de consulta de bases de datos		8
12	bibliotecas digitales		8
13	recuperacion de informacion		8
14	redes de comunicacion		1
15	diseno fisico de bases de datos		8
16	lenguajes de programacion		
17	programacion orientada a objetos		17
18	sistemas inteligentes		
19	busqueda por satisfaccion de reestricciones		18
20	entornos de desarrollo de software		6
21	diseno de bases de datos relacionales		8
22	validacion de software		6
23	computacion web cliente servidor		1
24	tecnologias multimedia		1
25	computacion inalambrica		1
26	procesamiento de lenguaje natural		18
27	sistemas multimedia		8
28	fundamentos de programacion		
29	programacion orientada por eventos		28
30	robotica		18
31	algoritmos y complejidad		
32	algoritmos paralelos		31
33	interaccion humano computador		
34	aspectos de comunicacion		33
35	desarrollo de software centrado en humano		33
36	diseno de interfaces graficas de usuario		33
37	procesamiento de transacciones		8
38	mineria de datos		8
39	computacion visual y grafica		
40	rendering avanzado		39
41	animacion por computador		39
42	realidad virtual		39
43	arquitectura y organizacion		
44	arquitectura para redes en sistemas distribuidos		43
45	analisis numerico		3
46	modelamiento de datos		8
47	bases de datos relacionales		8
48	bases de datos distribuidas		8
49	seguridad en redes		1
50	bioinformatica		
51	estructuras discretas		
52	grafos y arboles		51
53	sistemas de bases de datos		8
54	representacion de conocimiento		18
55	probabilidad discreta		51
56	visualizacion		39
57	sistemas operativos		
58	sistemas en tiempo real		57
59	tolerancia a fallas		57
60	algoritmos criptograficos		31
61	maquinas virtuales		17
62	programacion funcional		17
63	aprendizaje redes neuronales		18
\.


--
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY autor (id_autor, nombre, email, apellido, acronimo) FROM stdin;
10000	james f	jfk@correo.com 	kurose	jfk
10001	keith w	kwr@correo.com	ross	kwr
10002	david	dp@com	perez	dp
10003	julian	jr@com	rodrigues	jr
10004	lina	lm@com	moncayo	lm
10005	francisco	fc@com	cerrano	fc
10006	juan	jd@com	dominguez	jd
10007	maria	mg@com	gutierres	mg
\.


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY consulta (id_documento, login, fecha, hora) FROM stdin;
10000	anonimo	2011-05-20	08:21:32
10000	admin	2011-05-20	08:21:46
10000	anonimo	2011-05-20	08:26:56
10000	admin	2011-05-20	08:27:34
10000	anonimo	2011-05-20	08:49:58
10000	anonimo	2011-05-20	08:50:29
10000	anonimo	2011-05-20	08:50:45
10000	anonimo	2011-05-20	08:57:11
10000	anonimo	2011-05-20	08:57:28
10000	anonimo	2011-05-20	08:58:03
10000	anonimo	2011-05-20	08:58:07
10000	anonimo	2011-05-20	08:58:08
10000	anonimo	2011-05-20	08:58:09
10000	anonimo	2011-05-20	08:58:10
10000	anonimo	2011-05-20	08:58:48
10000	anonimo	2011-05-20	08:58:49
10000	anonimo	2011-05-20	08:58:50
10000	anonimo	2011-05-20	08:59:01
10000	anonimo	2011-05-20	08:59:06
10000	anonimo	2011-05-20	08:59:24
10000	anonimo	2011-05-20	08:59:25
10000	anonimo	2011-05-20	09:00:58
10000	anonimo	2011-05-20	09:01:03
10000	anonimo	2011-05-20	09:01:09
10000	anonimo	2011-05-20	09:01:13
10000	anonimo	2011-05-20	09:01:14
10000	admin	2011-05-20	09:02:17
10000	admin	2011-05-20	09:02:22
10000	anonimo	2011-05-20	09:42:39
10000	anonimo	2011-05-20	09:58:55
10000	anonimo	2011-05-20	10:05:26
10000	anonimo	2011-05-20	10:05:36
10000	anonimo	2011-05-20	10:06:07
10000	anonimo	2011-05-20	10:54:24
10000	anonimo	2011-05-20	15:13:20
10000	admin	2011-05-20	15:19:27
10001	admin	2011-05-20	15:43:42
10001	admin	2011-05-20	15:43:43
10001	admin	2011-05-20	15:44:31
10002	admin	2011-05-20	15:48:58
10003	admin	2011-05-20	15:50:47
10004	admin	2011-05-20	15:51:36
10005	admin	2011-05-20	15:52:24
10006	admin	2011-05-20	15:53:34
10006	admin	2011-05-20	15:54:27
10007	admin	2011-05-20	15:54:41
10008	admin	2011-05-20	15:55:17
10008	admin	2011-05-20	15:55:55
10009	admin	2011-05-20	15:56:04
10010	admin	2011-05-20	15:56:30
10011	admin	2011-05-20	15:56:48
10001	admin	2011-05-20	15:58:00
10007	admin	2011-05-20	15:58:02
10000	admin	2011-05-20	15:58:04
10011	admin	2011-05-20	15:58:05
10002	admin	2011-05-20	15:58:10
10006	admin	2011-05-20	15:58:12
10002	admin	2011-05-20	15:58:15
10001	admin	2011-05-20	15:58:17
10011	admin	2011-05-20	15:58:19
10007	admin	2011-05-20	15:58:20
10001	admin	2011-05-20	15:58:23
10005	admin	2011-05-20	15:58:25
10011	admin	2011-05-20	15:58:26
10002	admin	2011-05-20	16:00:49
10002	admin	2011-05-20	16:01:00
10002	admin	2011-05-20	16:02:01
10002	admin	2011-05-20	16:02:53
10005	admin	2011-05-20	16:06:13
10006	admin	2011-05-20	16:06:15
10007	admin	2011-05-20	16:06:18
10008	admin	2011-05-20	16:06:20
10009	admin	2011-05-20	16:06:22
10008	admin	2011-05-20	16:06:24
10000	admin	2011-05-20	16:10:54
10001	admin	2011-05-20	16:12:27
10004	admin	2011-05-20	16:12:31
10008	admin	2011-05-20	16:12:35
10005	admin	2011-05-20	16:13:28
10009	admin	2011-05-20	16:13:31
10002	admin	2011-05-20	16:13:37
10001	admin	2011-05-20	16:13:43
10004	admin	2011-05-20	16:13:45
10000	admin	2011-05-20	16:13:57
10002	admin	2011-05-20	16:14:34
10005	anonimo	2011-05-20	16:23:47
10004	anonimo	2011-05-20	16:23:50
10004	admin	2011-05-20	16:24:02
10009	anonimo	2011-05-20	16:33:39
10002	anonimo	2011-05-20	16:43:21
10002	anonimo	2011-05-20	16:43:32
10007	anonimo	2011-05-20	16:50:27
10010	anonimo	2011-05-20	16:50:29
10002	anonimo	2011-05-20	16:50:40
10002	anonimo	2011-05-20	16:51:13
10000	anonimo	2011-05-20	16:51:23
10011	anonimo	2011-05-20	16:51:27
10009	anonimo	2011-05-20	16:51:29
10009	anonimo	2011-05-20	16:51:31
10011	anonimo	2011-05-20	16:51:33
10008	anonimo	2011-05-20	16:51:35
10010	anonimo	2011-05-20	16:51:37
10007	anonimo	2011-05-20	16:51:39
10006	anonimo	2011-05-20	16:51:44
10005	anonimo	2011-05-20	16:51:45
10006	anonimo	2011-05-20	16:51:50
10006	admin	2011-05-20	16:52:30
10000	admin	2011-05-20	16:52:55
10003	admin	2011-05-20	16:53:21
10008	admin	2011-05-20	16:58:00
10009	admin	2011-05-20	16:58:03
10010	admin	2011-05-20	16:58:05
10009	admin	2011-05-20	16:58:07
10006	admin	2011-05-20	16:58:09
10000	admin	2011-05-20	16:58:11
10002	admin	2011-05-20	16:58:13
10004	admin	2011-05-20	16:58:15
10007	admin	2011-05-20	16:58:16
10005	admin	2011-05-20	16:58:19
10003	admin	2011-05-20	16:58:22
10011	admin	2011-05-20	17:10:08
10011	admin	2011-05-20	17:10:35
10000	admin	2011-05-20	17:10:57
10002	anonimo	2011-05-20	17:14:58
10002	anonimo	2011-05-20	17:26:04
10002	anonimo	2011-05-20	17:28:16
10001	anonimo	2011-05-20	17:30:23
10003	anonimo	2011-05-20	17:30:35
10009	anonimo	2011-05-20	17:30:56
10001	anonimo	2011-05-20	17:31:15
10000	anonimo	2011-05-20	17:31:25
10001	anonimo	2011-05-20	17:31:28
10002	anonimo	2011-05-20	17:31:31
10003	anonimo	2011-05-20	17:31:45
10004	anonimo	2011-05-20	17:31:48
10004	anonimo	2011-05-20	17:31:55
10005	anonimo	2011-05-20	17:32:03
10006	anonimo	2011-05-20	17:32:07
10009	anonimo	2011-05-20	17:32:10
10010	anonimo	2011-05-20	17:32:12
10002	anonimo	2011-05-20	17:35:17
10004	anonimo	2011-05-20	17:35:20
10005	anonimo	2011-05-20	17:35:22
10011	anonimo	2011-05-20	17:35:25
10002	anonimo	2011-05-20	17:35:27
10002	anonimo	2011-05-20	17:35:37
10004	anonimo	2011-05-20	17:35:39
10005	anonimo	2011-05-20	17:35:40
10011	anonimo	2011-05-20	17:35:42
10004	anonimo	2011-05-20	17:36:04
10004	anonimo	2011-05-20	17:36:07
10000	anonimo	2011-05-20	17:36:15
10001	anonimo	2011-05-20	17:36:18
10002	anonimo	2011-05-20	17:36:21
10000	admin	2011-05-20	17:39:50
10000	admin	2011-05-20	17:39:56
10001	admin	2011-05-20	17:39:58
10001	admin	2011-05-20	17:40:11
10001	admin	2011-05-20	17:40:23
10000	anonimo	2011-05-20	17:44:42
10001	anonimo	2011-05-20	17:44:44
10003	anonimo	2011-05-20	17:44:48
10003	anonimo	2011-05-20	17:45:05
10001	anonimo	2011-05-20	17:45:15
10001	anonimo	2011-05-20	17:45:37
10001	anonimo	2011-05-20	17:46:58
10005	anonimo	2011-05-20	17:59:41
10004	anonimo	2011-05-20	17:59:50
10011	anonimo	2011-05-20	17:59:53
10004	anonimo	2011-05-20	17:59:55
10011	anonimo	2011-05-20	18:00:00
10004	anonimo	2011-05-20	18:00:08
10000	anonimo	2011-05-20	18:00:12
10001	anonimo	2011-05-20	18:00:16
10000	anonimo	2011-05-20	18:00:20
10000	anonimo	2011-05-20	18:07:26
10003	admin	2011-05-24	14:49:38
10001	admin	2011-05-24	14:50:41
10004	admin	2011-05-24	14:50:43
10000	admin	2011-05-24	14:50:45
10003	admin	2011-05-24	14:50:49
10001	marcela	2011-05-24	16:21:43
10003	marcela	2011-05-24	16:21:45
10006	marcela	2011-05-24	16:21:49
10001	yerdogm	2011-05-24	16:22:12
10004	yerdogm	2011-05-24	16:22:15
10013	yerdogm	2011-05-24	16:22:21
10005	felipex	2011-05-24	16:22:42
10009	felipex	2011-05-24	16:22:46
10015	felipex	2011-05-24	16:22:51
10006	maria	2011-05-24	16:23:13
10000	clrl	2011-05-24	16:23:28
10002	clrl	2011-05-24	16:23:30
10003	clrl	2011-05-24	16:23:32
10002	camilo	2011-05-24	16:23:46
10001	camilo	2011-05-24	16:23:53
10002	monki	2011-05-24	16:24:04
10001	monki	2011-05-24	16:24:05
10008	monki	2011-05-24	16:24:09
10012	monki	2011-05-24	16:24:12
\.


--
-- Data for Name: descarga_usuario_documento; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY descarga_usuario_documento (fecha, hora, login, id_documento) FROM stdin;
\.


--
-- Data for Name: documento; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY documento (id_documento, idioma, derechos_autor, descripcion, software_recomendado, resolucion, editorial, formato, titulo_principal, titulo_secundario, link, fecha_creacion, fecha_publicacion, tipo_nombre, login_catalogador, fecha_catalogacion) FROM stdin;
10000	Ingles	Si	libro de redes	adobe	0	pearson	pdf	computing networking aproach top-down		repositorio/Computer Networking - A Top-down Approach Featuring the Internet, 3rd Ed [by Kurose, Ross].pdf	2006-05-14	2006-05-14	libro	clrl	2010-06-07
10001	Ingles	Si	libro de bases de datos	adobe	0	pearson	pdf	data base	bases de datos	repositorio/libro1.pdf	1999-05-09	2003-07-14	libro	clrl	2010-06-10
10002	Ingles	No	libro de computadores	adobe	0	norma	pdf	introduccion a los computadores		repositorio/libro2.pdf	1994-05-09	1998-05-10	libro	clrl	2010-06-11
10004	Ingles	Si	libro de bases de datos	adobe	0	pearson	pdf	bases de dados	bases de datos	repositorio/libro4.pdf	2002-05-14	2003-05-14	libro	clrl	2010-06-13
10007	Ingles	Si	libro de programacion c++	adobe	0	sin editorial	pdf	programar en c++		repositorio/libro7.pdf	2000-05-14	2005-04-14	libro	clrl	2010-06-16
10008	Ingles	No	diapositivas curso base datos	writter	0	sin editorial	odt	diapositivas base datos		repositorio/ppt1.odt	2004-04-07	2005-09-14	libro	clrl	2010-06-17
10010	Ingles	No	trabajo de grado sobre redes	adobe	0	sin editorial	pdf	wireless networks	redes inalambricas	repositorio/archivo.pdf	2006-05-14	2006-05-14	libro	clrl	2010-06-19
10011	Ingles	No	libro de desarrollo de software	adobe	0	sin editorial	pdf	software project starting	iniciando proyecto de software	repositorio/documento.pdf	2006-05-14	2006-05-14	libro	clrl	2010-06-20
10009	Ingles	No	articulo sobre computacion en la nube	word	0	sin editorial	doc	mas haya de la nube		repositorio/article.doc	2005-05-14	2005-05-14	libro	clrl	2010-06-18
10006	Ingles	No	libro de programacion java	adobe	0	scrib	pdf	java programming	programacion en java	repositorio/libro6.pdf	2003-05-14	2004-11-06	libro	clrl	2010-06-15
10003	Aleman	Si	libro de robotica	otro	0	pearson	djvu	erste robotik	robotica incial	repositorio/libro3.djvu	1993-05-14	2001-05-14	libro	clrl	2010-06-12
10005	Frances	Si	libro de computadores y redes	otro	0	norma	djvu	ordinateurs et des reseaux	computadores y redes	repositorio/libro5.djvu	1996-05-14	2002-05-14	libro	clrl	2010-06-14
10012	Frances	No	procesar lenguaje natural	ninguno	0	sin editorial	otro	procesamiento de lenguaje natural	sin titulo secundario	repositorio/libro3(10).djvu	1999-05-15	2005-01-24	taterial de clase	admin	2011-05-24
10013	Ingles	No	libro de programacion para la clase de flp	ninguno	0	sin editoral	otro	flp	sin titulo secundario	repositorio/libro3(11).djvu	2001-05-15	2002-01-24	articulo	admin	2011-05-24
10014	Portuges	No	articulo	ninguno	0	pearson	otro	realidad virtual	sin titulo secundario	repositorio/libro3(12).djvu	1993-05-15	1993-01-24	libro	admin	2011-05-24
10015	Español	Si	tesis sobre numeros y valores discretos	ninguno	0	sin editorial	otro	analisis de numeros discretos	sin titulo secundario	repositorio/libro3(13).djvu	1987-05-15	1988-01-24	tesis de maestria	admin	2011-05-24
10016	Aleman	Si	animacion usando computadoras	0	0	sin editorial	otro	animaciones graficas	sin titulo secundario	repositorio/libro3(14).djvu	1983-05-15	1984-01-24	taterial de clase	admin	2011-05-24
\.


--
-- Data for Name: escribe_autor_documento; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY escribe_autor_documento (id_autor, id_documento) FROM stdin;
10000	10000
10001	10000
10000	10001
10005	10001
10007	10001
10000	10002
10004	10002
10002	10002
10006	10002
10005	10004
10006	10004
10002	10004
10005	10005
10007	10005
10006	10007
10007	10007
10003	10007
10002	10007
10004	10008
10006	10008
10007	10008
10005	10009
10002	10010
10007	10011
10001	10006
10006	10006
10000	10003
10003	10003
10006	10003
10006	10012
10004	10012
10002	10013
10006	10014
10005	10014
10005	10015
10001	10015
10005	10016
10006	10016
\.


--
-- Data for Name: interesa_usuario_area_conocimiento; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY interesa_usuario_area_conocimiento (login, id_area) FROM stdin;
monki	7
monki	14
monki	23
monki	49
monki	47
monki	57
monki	61
alberto	16
alberto	29
alberto	42
carlos	38
carlos	28
carlos	16
camilo	23
camilo	12
camilo	19
laura	16
laura	31
laura	47
laura	59
laura	55
marcela	61
marcela	62
marcela	57
marcela	52
marcela	43
\.


--
-- Data for Name: palabra_clave; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY palabra_clave (nombre, descripcion) FROM stdin;
red	todo lo que tenga que ver con redes de computadoras
programacion	programacion de computadores
grafos	referente a teoria de grafos
base datos	sobre bases de datos
computacion nube	sobre computacion en lanube\n
desarrollo software	sobre desarrollo de software
computacion	sobre computadores
virtual	medio representativo de algo
discretas	variables, valores, numeros no continuas
animacion	creacion de umagenes y graficos en movimiento
\.


--
-- Data for Name: pertenece_documento_area_conocimiento; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY pertenece_documento_area_conocimiento (id_area, id_documento) FROM stdin;
49	10000
5	10001
21	10001
41	10001
62	10001
60	10001
29	10002
20	10002
56	10002
62	10002
8	10004
4	10004
26	10004
48	10004
62	10004
63	10004
7	10005
30	10005
26	10007
48	10007
61	10007
57	10007
4	10008
6	10009
49	10010
26	10011
6	10006
22	10006
37	10006
57	10006
29	10003
35	10003
60	10003
22	10012
25	10012
28	10013
16	10013
42	10014
45	10015
41	10016
\.


--
-- Data for Name: tiene_documento_palabra_clave; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY tiene_documento_palabra_clave (nombre, id_documento) FROM stdin;
red	10000
programacion	10001
red	10001
computacion nube	10001
base datos	10001
computacion	10001
desarrollo software	10002
computacion	10002
grafos	10002
computacion nube	10004
desarrollo software	10004
grafos	10004
programacion	10005
desarrollo software	10005
computacion	10009
computacion	10010
desarrollo software	10011
base datos	10006
computacion	10006
red	10003
computacion nube	10003
computacion	10003
grafos	10012
programacion	10013
virtual	10014
discretas	10015
\.


--
-- Data for Name: tipomaterial; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY tipomaterial (tipo_nombre, descripcion) FROM stdin;
libro	documento extenso creado por un especifico tema
articulo	documento pequeno creado por un especifico tema
memoria	biografia
trabajo de grado	trabajo presentado para el grado por estudiantes de pregrado de ultimos semestres
tesis de maestria	trabajo presentado para el grado por estudiantes de maestria de ultimos semestres
tesis de doctorado	trabajo presentado para el grado por estudiantes de doctorado de ultimos semestres
taterial de clase	diapositivas, talleres, ejercicios y documentos del profesor
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: edgaramt
--

COPY usuario (login, contrasena, nombre1, nombre2, apellido1, apellido2, email, nivel_escolaridad, pregunta_secreta, respuesta_secreta, vinculo_univalle, genero, fecha_nacimiento, fecha_registro, tipo, estado) FROM stdin;
anonimo	anonimo	anonimo	anonimo	anonimo	univalle	anonimo@correo	Profesional/Universidad	Ciudad natal de la abuela	respuesta	Estudiante de pregrado	M	2011-05-08	2011-05-08	0	f
admin	digital	biblioteca	digital	eisc	univalle	bibliotecaeisc@correo.univalle.co	Profesional/Universidad	Ciudad natal de la abuela	respuesta	Estudiante de pregrado	M	2011-05-08	2011-05-08	1	t
clrl	clrl	cristian	leonardo	rios	lopez	dragonblanco452@gmail.com	TecnologÃ­a	Ciudad natal de la abuela	respuesta	Estudiante de pregrado	M	1989-06-09	2011-05-08	2	t
monki	monki	edgar	andres	moncada	taborda	edgarandres29@gmail.com	Media Académica	Mejor amigo de la infancia	monki	Estudiante de pregrado	M	1991-05-29	2011-05-24	3	t
maria	maria	maria	andrea	cruz		maria@gmail.com	Media Académica	Mejor amigo de la infancia	maria	Estudiante de pregrado	F	2011-05-24	2011-05-24	2	t
felipex	felipex	luis	felipe	vargas	rojas	felipex@gmail.com	Media Académica	Mejor amigo de la infancia	felipex	Estudiante de pregrado	M	2011-05-24	2011-05-24	2	t
yerdogm	yerdogm	yerminson	doney	gonzales	muños	yerdogm@gmail.com	Media Académica	Mejor amigo de la infancia	yerdogm	Estudiante de pregrado	M	2011-05-24	2011-05-24	2	t
alberto	alberto	alberto		gonzales		alberto@gmail.com	Básica Primaria	Mejor amigo de la infancia	alberto	Estudiante de pregrado	M	2011-05-24	2011-05-24	3	t
carlos	carlos	carlos	andres	valderrama	aguirre	carlos@gmail.com	Básica Primaria	Mejor amigo de la infancia	carlos	Estudiante de pregrado	M	2011-05-24	2011-05-24	3	t
camilo	camilo	camilo		suarez		camilo@gmail.com	Tecnología	Mejor amigo de la infancia	camilo	Egresado	M	2011-05-24	2011-05-24	3	t
laura	laura	laura		rodrigues		laura@gmail.com	Media Técnica	Mejor amigo de la infancia	laura	Egresado	F	2011-05-24	2011-05-24	3	t
marcela	marcela	marcela		lozano		marcela@yahoo.es	Maestría	Mejor amigo de la infancia	marcela	Profesor activo	F	2011-05-24	2011-05-24	3	t
\.


--
-- Name: area_conocimiento_nombre_key; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT area_conocimiento_nombre_key UNIQUE (nombre);


--
-- Name: documento_link_key; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT documento_link_key UNIQUE (link);


--
-- Name: fecha_hora_login_iddocumento_fk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY descarga_usuario_documento
    ADD CONSTRAINT fecha_hora_login_iddocumento_fk PRIMARY KEY (fecha, hora, login, id_documento);


--
-- Name: id_area_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT id_area_pk PRIMARY KEY (id_area);


--
-- Name: idarea_iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY pertenece_documento_area_conocimiento
    ADD CONSTRAINT idarea_iddocumento_pk PRIMARY KEY (id_area, id_documento);


--
-- Name: idautor_iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY escribe_autor_documento
    ADD CONSTRAINT idautor_iddocumento_pk PRIMARY KEY (id_autor, id_documento);


--
-- Name: idautor_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY autor
    ADD CONSTRAINT idautor_pk PRIMARY KEY (id_autor);


--
-- Name: iddocumento_login_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT iddocumento_login_pk PRIMARY KEY (id_documento, login, fecha, hora);


--
-- Name: iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT iddocumento_pk PRIMARY KEY (id_documento);


--
-- Name: login_idarea_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY interesa_usuario_area_conocimiento
    ADD CONSTRAINT login_idarea_pk PRIMARY KEY (login, id_area);


--
-- Name: nombre_iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY tiene_documento_palabra_clave
    ADD CONSTRAINT nombre_iddocumento_pk PRIMARY KEY (nombre, id_documento);


--
-- Name: nombre_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY palabra_clave
    ADD CONSTRAINT nombre_pk PRIMARY KEY (nombre);


--
-- Name: tiponombre_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tiponombre_pk PRIMARY KEY (tipo_nombre);


--
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- Name: usuario_pk; Type: CONSTRAINT; Schema: public; Owner: edgaramt; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (login);


--
-- Name: area_padre_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT area_padre_fk FOREIGN KEY (area_padre) REFERENCES area_conocimiento(id_area) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: id_area_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY interesa_usuario_area_conocimiento
    ADD CONSTRAINT id_area_fk FOREIGN KEY (id_area) REFERENCES area_conocimiento(id_area) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: idarea_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY pertenece_documento_area_conocimiento
    ADD CONSTRAINT idarea_fk FOREIGN KEY (id_area) REFERENCES area_conocimiento(id_area) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: idautor_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY escribe_autor_documento
    ADD CONSTRAINT idautor_fk FOREIGN KEY (id_autor) REFERENCES autor(id_autor) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY pertenece_documento_area_conocimiento
    ADD CONSTRAINT iddocumento FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY descarga_usuario_documento
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY escribe_autor_documento
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY tiene_documento_palabra_clave
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: login_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY interesa_usuario_area_conocimiento
    ADD CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: login_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY descarga_usuario_documento
    ADD CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: login_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: logincatalogador_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT logincatalogador_fk FOREIGN KEY (login_catalogador) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: nombre_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY tiene_documento_palabra_clave
    ADD CONSTRAINT nombre_fk FOREIGN KEY (nombre) REFERENCES palabra_clave(nombre) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: tipo_fk; Type: FK CONSTRAINT; Schema: public; Owner: edgaramt
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT tipo_fk FOREIGN KEY (tipo_nombre) REFERENCES tipomaterial(tipo_nombre) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--

--REVOKE ALL ON SCHEMA public FROM PUBLIC;
--REVOKE ALL ON SCHEMA public FROM postgres;
--GRANT ALL ON SCHEMA public TO postgres;
--GRANT ALL ON SCHEMA public TO PUBLIC;


--
-- PostgreSQL database dump complete
--

