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

SET statement_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = off;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET escape_string_warning = off;

SET search_path = public, pg_catalog;

SET default_tablespace = '';

SET default_with_oids = false;

--
-- Name: area_conocimiento; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE area_conocimiento (
    id_area character varying(3) NOT NULL,
    nombre character varying(50) NOT NULL,
    descripcion character varying(200),
    area_padre character varying(3)
);


--ALTER TABLE public.area_conocimiento OWNER TO yerdogm;

--
-- Name: id_autor_seq; Type: SEQUENCE; Schema: public; Owner: yerdogm
--

CREATE SEQUENCE id_autor_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--ALTER TABLE public.id_autor_seq OWNER TO yerdogm;

--
-- Name: id_autor_seq; Type: SEQUENCE SET; Schema: public; Owner: yerdogm
--

SELECT pg_catalog.setval('id_autor_seq', 10030, true);


--
-- Name: autor; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE autor (
    id_autor integer DEFAULT nextval('id_autor_seq'::regclass) NOT NULL,
    nombre character varying(100) NOT NULL,
    email character varying(50),
    apellido character varying(30) NOT NULL,
    acronimo character varying(20)
);


--ALTER TABLE public.autor OWNER TO yerdogm;

--
-- Name: consulta; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE consulta (
    id_documento integer NOT NULL,
    login character varying(10) NOT NULL,
    fecha date NOT NULL,
    hora time without time zone NOT NULL
);


--ALTER TABLE public.consulta OWNER TO yerdogm;

--
-- Name: descarga_usuario_documento; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE descarga_usuario_documento (
    fecha date NOT NULL,
    hora time without time zone NOT NULL,
    login character varying(10) NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.descarga_usuario_documento OWNER TO yerdogm;

--
-- Name: id_documento_seq; Type: SEQUENCE; Schema: public; Owner: yerdogm
--

CREATE SEQUENCE id_documento_seq
    START WITH 1
    INCREMENT BY 1
    NO MAXVALUE
    NO MINVALUE
    CACHE 1;


--ALTER TABLE public.id_documento_seq OWNER TO yerdogm;

--
-- Name: id_documento_seq; Type: SEQUENCE SET; Schema: public; Owner: yerdogm
--

SELECT pg_catalog.setval('id_documento_seq', 10017, true);


--
-- Name: documento; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
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


--ALTER TABLE public.documento OWNER TO yerdogm;

--
-- Name: escribe_autor_documento; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE escribe_autor_documento (
    id_autor integer NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.escribe_autor_documento OWNER TO yerdogm;

--
-- Name: interesa_usuario_area_conocimiento; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE interesa_usuario_area_conocimiento (
    login character varying(10) NOT NULL,
    id_area character varying(3) NOT NULL
);


--ALTER TABLE public.interesa_usuario_area_conocimiento OWNER TO yerdogm;

--
-- Name: palabra_clave; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE palabra_clave (
    nombre character varying(20) NOT NULL,
    descripcion character varying(200)
);


--ALTER TABLE public.palabra_clave OWNER TO yerdogm;

--
-- Name: pertenece_documento_area_conocimiento; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE pertenece_documento_area_conocimiento (
    id_area character varying(3) NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.pertenece_documento_area_conocimiento OWNER TO yerdogm;

--
-- Name: tiene_documento_palabra_clave; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE tiene_documento_palabra_clave (
    nombre character varying(20) NOT NULL,
    id_documento integer NOT NULL
);


--ALTER TABLE public.tiene_documento_palabra_clave OWNER TO yerdogm;

--
-- Name: tipomaterial; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
--

CREATE TABLE tipomaterial (
    tipo_nombre character varying(20) NOT NULL,
    descripcion character varying(200) NOT NULL
);


--ALTER TABLE public.tipomaterial OWNER TO yerdogm;

--
-- Name: usuario; Type: TABLE; Schema: public; Owner: yerdogm; Tablespace: 
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
    estado boolean DEFAULT true NOT NULL,
    fecha_ultimo_acceso date
);


--ALTER TABLE public.usuario OWNER TO yerdogm;

--
-- Data for Name: area_conocimiento; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY area_conocimiento (id_area, nombre, descripcion, area_padre) FROM stdin;
	ciencias de la computacion		
1	computacion centrada en la red		
2	construccion de aplicaciones web		1
3	metodos numericos computacionales		
4	modelos de simulacion		3
5	investigacion de operaciones		3
6	ingenieria de software		
7	diseño de software		6
8	gestion de informacion		
9	hipermedia e hipertexto		8
10	modelo de sistemas de informacion		8
11	lenguajes de consulta de bases de datos		8
12	bibliotecas digitales		8
13	recuperacion de informacion		8
14	redes de comunicacion		1
15	diseño fisico de bases de datos		8
16	lenguajes de programacion		
17	programacion orientada a objetos		17
18	sistemas inteligentes		
19	busqueda por satisfaccion de reestricciones		18
20	entornos de desarrollo de software		6
21	diseño de bases de datos relacionales		8
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
36	diseño de interfaces graficas de usuario		33
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
-- Data for Name: autor; Type: TABLE DATA; Schema: public; Owner: yerdogm
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
10008	javier ignacio	jrgoche@uaeh.edu.mx	naranjo marín	jwsx
10009	lillian eugenia	andre010686@hotmail.com	osorno gil	oxlr
10010	sixto gerardo	forsgare@hotmail.com	alzate agudelo	cuxr
10011	emilio jhony	goavjo@hotmail.com	correa ramírez	vkbp
10012	alberto germán	almadhf@yahoo.com.mx	berdugo lópez	yold
10013	antonio oscar	ady_gem2000@hotmail.com	ruiz ruiz	zccb
10014	darío augusto	jocampo@uaeh.edu.mx	muñoz ramírez	ianv
10015	césar oswaldo	oiviaverduzco@hotmail.com	gonzález castaño	rley
10016	gloria amparo	margotdm@hotmail.com	duque gutiérrez	mdaz
10017	héctor iván	jprice@libraries.claremont.edu	díaz pérez	mnie
10018	beatriz 	adamsc@bus.umich.edu	gómez cano	lkvy
10019	elena	Dorakom_Fiorixol816@gmail.com	osorio laverde	rmlm
10020	herman	CAchmadama@umma.edu	murillo gonzález	ardl
10021	carlos mario	ERillingeni@uva.edu	lotero upegui	okpy
10022	carlos iván	GHermelinddiret@lorenz.com	rodas monsalve	hdrz
10023	carlos alberto	kianamcsharrysbqe@cvsis.com	zárate yépez	kabs
10024	hernán darío	cynthiamaldaline@yahoocom	molina cano	bcqm
10025	jorge león	annabelshaeffergarw@tres.com	duque garcía	rbsf
10026	john jairo	cristintadlock2392@hotmail.com	sáenz hurtado	geri
10027	armid benjamín	originale574@yahoo.es	palacio martínez	lhef
10028	elkin octavio	tres3446-16@gmail.com	hurtado pérez	ymdk
10029	julio cesar  	qbhaqui@uman.edu.jp	osorio laverde	nbsx
\.


--
-- Data for Name: consulta; Type: TABLE DATA; Schema: public; Owner: yerdogm
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
10000	anonimo	2011-05-21	08:58:50
10000	anonimo	2011-05-21	08:59:01
10000	anonimo	2011-05-21	08:59:06
10000	anonimo	2011-05-21	08:59:24
10000	anonimo	2011-05-21	08:59:25
10000	anonimo	2011-05-21	09:00:58
10000	anonimo	2011-05-21	09:01:03
10000	anonimo	2011-05-21	09:01:09
10000	anonimo	2011-05-21	09:01:13
10000	anonimo	2011-05-21	09:01:14
10000	admin	2011-05-21	09:02:17
10000	admin	2011-05-21	09:02:22
10000	anonimo	2011-05-21	09:42:39
10000	anonimo	2011-05-21	09:58:55
10000	anonimo	2011-05-22	10:05:26
10000	anonimo	2011-05-22	10:05:36
10000	anonimo	2011-05-22	10:06:07
10000	anonimo	2011-05-22	10:54:24
10000	anonimo	2011-05-22	15:13:20
10000	admin	2011-05-22	15:19:27
10001	admin	2011-05-22	15:43:42
10001	admin	2011-05-22	15:43:43
10001	admin	2011-05-22	15:44:31
10002	admin	2011-05-23	15:48:58
10003	admin	2011-05-23	15:50:47
10004	admin	2011-05-23	15:51:36
10005	admin	2011-05-23	15:52:24
10006	admin	2011-05-23	15:53:34
10006	admin	2011-05-23	15:54:27
10007	admin	2011-05-24	15:54:41
10008	admin	2011-05-24	15:55:17
10008	admin	2011-05-24	15:55:55
10009	admin	2011-05-24	15:56:04
10010	admin	2011-05-24	15:56:30
10001	admin	2011-05-24	15:58:00
10007	admin	2011-05-24	15:58:02
10000	admin	2011-05-24	15:58:04
10006	admin	2011-05-24	15:58:12
10002	admin	2011-05-24	15:58:15
10001	admin	2011-05-24	15:58:17
10007	admin	2011-05-24	15:58:20
10001	admin	2011-05-24	15:58:23
10005	admin	2011-05-25	15:58:25
10002	admin	2011-05-25	16:00:49
10002	admin	2011-05-25	16:01:00
10002	admin	2011-05-25	16:02:01
10002	admin	2011-05-25	16:02:53
10005	admin	2011-05-25	16:06:13
10006	admin	2011-05-25	16:06:15
10007	admin	2011-05-25	16:06:18
10008	admin	2011-05-25	16:06:20
10009	admin	2011-05-25	16:06:22
10008	admin	2011-05-25	16:06:24
10000	admin	2011-05-25	16:10:54
10001	admin	2011-05-25	16:12:27
10004	admin	2011-05-25	16:12:31
10008	admin	2011-05-25	16:12:35
10005	admin	2011-05-25	16:13:28
10009	admin	2011-05-25	16:13:31
10002	admin	2011-05-25	16:13:37
10001	admin	2011-05-25	16:13:43
10004	admin	2011-05-25	16:13:45
10000	admin	2011-05-25	16:13:57
10002	admin	2011-05-25	16:14:34
10005	anonimo	2011-05-26	16:23:47
10004	anonimo	2011-05-26	16:23:50
10004	admin	2011-05-26	16:24:02
10009	anonimo	2011-05-26	16:33:39
10002	anonimo	2011-05-26	16:43:21
10002	anonimo	2011-05-26	16:43:32
10007	anonimo	2011-05-26	16:50:27
10010	anonimo	2011-05-26	16:50:29
10002	anonimo	2011-05-26	16:50:40
10002	anonimo	2011-05-26	16:51:13
10000	anonimo	2011-05-26	16:51:23
10009	anonimo	2011-05-26	16:51:29
10009	anonimo	2011-05-26	16:51:31
10011	anonimo	2011-05-26	16:51:33
10008	anonimo	2011-05-26	16:51:35
10010	anonimo	2011-05-26	16:51:37
10007	anonimo	2011-05-26	16:51:39
10006	anonimo	2011-05-26	16:51:44
10005	anonimo	2011-05-26	16:51:45
10006	anonimo	2011-05-26	16:51:50
10003	admin	2011-05-27	14:49:38
10001	admin	2011-05-27	14:50:41
10004	admin	2011-05-27	14:50:43
10000	admin	2011-05-27	14:50:45
10003	admin	2011-05-27	14:50:49
10001	marcela	2011-05-27	16:21:43
10003	marcela	2011-05-27	16:21:45
10006	marcela	2011-05-27	16:21:49
10001	yerdogm	2011-05-27	16:22:12
10004	yerdogm	2011-05-27	16:22:15
10013	yerdogm	2011-05-27	16:22:21
10005	felipex	2011-05-27	16:22:42
10009	felipex	2011-05-27	16:22:46
10015	felipex	2011-05-27	16:22:51
10006	maria	2011-05-27	16:23:13
10000	clrl	2011-05-27	16:23:28
10002	clrl	2011-05-27	16:23:30
10003	clrl	2011-05-27	16:23:32
10002	camilo	2011-05-27	16:23:46
10001	camilo	2011-05-27	16:23:53
10002	monki	2011-05-27	16:24:04
10001	monki	2011-05-27	16:24:05
10008	monki	2011-05-27	16:24:09
10012	monki	2011-05-27	16:24:12
10000	admin	2011-06-06	00:05:39
10000	admin	2011-06-06	00:09:02
10001	admin	2011-06-06	00:09:08
10002	admin	2011-06-06	00:12:17
10003	admin	2011-06-06	00:15:20
10004	admin	2011-06-06	00:17:38
10005	admin	2011-06-06	00:19:46
10003	admin	2011-06-06	00:20:29
10005	admin	2011-06-06	00:21:01
10006	admin	2011-06-06	00:23:13
10007	admin	2011-06-06	00:25:21
10008	admin	2011-06-06	00:27:25
10009	admin	2011-06-06	00:28:41
10010	admin	2011-06-06	00:30:20
10011	admin	2011-06-06	00:33:54
10004	osito32	2011-06-06	01:47:10
10006	osito32	2011-06-06	01:47:22
10010	osito32	2011-06-06	01:47:40
10003	elkin123	2011-06-06	01:49:23
10007	elkin123	2011-06-06	01:49:35
10006	elkin123	2011-06-06	01:49:47
10006	armidb45	2011-06-06	01:51:05
10004	armidb45	2011-06-06	01:51:19
10004	armidb45	2011-06-06	01:51:27
10007	armidb45	2011-06-06	01:51:34
10005	armidb45	2011-06-06	01:51:40
\.


--
-- Data for Name: descarga_usuario_documento; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY descarga_usuario_documento (fecha, hora, login, id_documento) FROM stdin;
2011-06-06	01:47:27	osito32	10006
2011-06-06	01:47:46	osito32	10010
2011-06-06	01:49:28	elkin123	10003
2011-06-06	01:49:40	elkin123	10007
2011-06-06	01:49:51	elkin123	10006
2011-06-06	01:51:10	armidb45	10006
2011-06-06	01:51:22	armidb45	10004
2011-06-06	01:51:29	armidb45	10004
2011-06-06	01:51:37	armidb45	10007
2011-06-06	01:51:42	armidb45	10005
\.


--
-- Data for Name: documento; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY documento (id_documento, idioma, derechos_autor, descripcion, software_recomendado, resolucion, editorial, formato, titulo_principal, titulo_secundario, link, fecha_creacion, fecha_publicacion, tipo_nombre, login_catalogador, fecha_catalogacion) FROM stdin;
10000	Ingles	Si	libro de redes de computadores	adobe	0	pearson	pdf	computing networking aproach top-down		repositorio/comp.pdf	2005-02-07	2006-09-22	libro	clrl	2011-05-29
10001	Ingles	Si	libro de bases de datos	adobe	0	pearson	pdf	data base	bases de datos	repositorio/libro1.pdf	1999-05-09	2003-07-14	libro	clrl	2011-05-29
10002	Español	No	libro de computadores	adobe	0	norma	pdf	introduccion a los computadores		repositorio/libro2.pdf	1994-05-09	1997-03-10	libro	clrl	2011-05-29
10003	Aleman	Si	libro de robotica	ninguno	0	pearson	otro	erste robotik	robotica incial	repositorio/libro3.djvu	1993-05-14	2001-05-14	libro	clrl	2011-05-29
10004	Portuges	Si	libro de bases de datos	adobe	0	pearson	pdf	bases de dados	bases de datos	repositorio/libro4.pdf	2002-05-14	2003-05-14	libro	clrl	2011-05-29
10005	Frances	Si	libro de computadores y redes	ninguno	0	norma	otro	ordinateurs et des reseaux	computadores y redes	repositorio/libro5.djvu	1996-05-14	2002-05-14	libro	clrl	2011-05-29
10006	Ingles	No	libro de programacion java	adobe	0	scrib	pdf	java programming	programacion en java	repositorio/libro6.pdf	2003-05-14	2004-11-06	libro	clrl	2011-05-29
10007	Español	Si	libro de programacion c++	adobe	0	sin editorial	pdf	programar en c++		repositorio/libro7.pdf	2000-05-14	2005-04-14	libro	clrl	2011-05-29
10008	Español	No	diapositivas curso base datos	writter	0	sin editorial	odt	diapositivas base datos		repositorio/ppt1.odt	2004-04-07	2005-09-14	material de clase	clrl	2011-05-29
10009	Español	No	articulo sobre computacion en la nube	word	0	sin editorial	doc	mas alla de la nube		repositorio/article.doc	2005-05-14	2005-05-14	libro	clrl	2011-05-29
10010	Ingles	No	trabajo de grado sobre redes	adobe	0	sin editorial	pdf	wireless networks	redes inalambricas	repositorio/archivo.pdf	2006-05-14	2006-05-14	libro	clrl	2011-05-29
10011	Ingles	No	libro de desarrollo de software	adobe	0	sin editorial	pdf	software project starting	iniciando proyecto de software	repositorio/documento.pdf	2006-05-14	2011-06-06	libro	clrl	2010-06-20
10012	Frances	No	procesar lenguaje natural	ninguno	0	sin editorial	otro	procesamiento de lenguaje natural		repositorio/libro3(10).djvu	1999-05-15	2005-01-24	material de clase	admin	2011-06-06
10013	Ingles	No	libro de programacion para la clase de flp	ninguno	0	sin editoral	otro	flp		repositorio/libro3(11).djvu	2001-05-15	2002-01-24	articulo	admin	2011-06-06
10014	Portuges	No	articulo	ninguno	0	pearson	otro	realidad virtual		repositorio/libro3(12).djvu	1993-05-15	1993-01-24	libro	admin	2011-06-07
10015	Español	Si	tesis sobre numeros y valores discretos	ninguno	0	sin editorial	otro	analisis de numeros discretos		repositorio/libro3(13).djvu	1987-05-15	1988-01-24	tesis de maestria	admin	2011-06-07
10016	Aleman	Si	animacion usando computadoras	ninguno	0	sin editorial	otro	animaciones graficas		repositorio/libro3(14).djvu	1983-05-15	1984-01-24	material de clase	admin	2011-06-07
\.


--
-- Data for Name: escribe_autor_documento; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY escribe_autor_documento (id_autor, id_documento) FROM stdin;
10006	10012
10004	10012
10002	10013
10006	10014
10005	10014
10005	10015
10001	10015
10005	10016
10006	10016
10000	10000
10001	10000
10000	10001
10005	10001
10007	10001
10000	10002
10002	10002
10004	10002
10006	10002
10002	10004
10005	10004
10006	10004
10000	10003
10003	10003
10006	10003
10005	10005
10007	10005
10026	10005
10006	10006
10029	10006
10027	10006
10002	10007
10003	10007
10006	10007
10007	10007
10004	10008
10006	10008
10007	10008
10025	10008
10005	10009
10027	10009
10029	10009
10002	10010
10024	10010
10007	10011
10021	10011
10026	10011
\.


--
-- Data for Name: interesa_usuario_area_conocimiento; Type: TABLE DATA; Schema: public; Owner: yerdogm
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
osito32	6
osito32	16
osito32	49
osito32	30
osito32	14
osito32	7
osito32	1
osito32	46
osito32	47
osito32	48
elkin123	6
elkin123	11
elkin123	12
elkin123	13
elkin123	17
elkin123	21
elkin123	25
elkin123	28
elkin123	31
elkin123	33
jhon7452	2
jhon7452	6
jhon7452	14
jhon7452	15
jhon7452	16
jhon7452	17
jhon7452	22
jhon7452	31
jhon7452	38
jhon7452	52
jhon7452	61
jhon7452	57
jhon7452	58
\.


--
-- Data for Name: palabra_clave; Type: TABLE DATA; Schema: public; Owner: yerdogm
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
java	lenguaje de programación java
c++	lenguaje de programación c++
postgres	motor de base de datos
mysql	motor de base de datos
oracol	motor de base de datos
oz	lenguaje de programación oz
emacs	editor, compilador.
editor	herramienta que permite modificar el contenido de archivos.
latex	lenguaje de programacion para generar documentos.
software	comprende el conjunto de los componentes lógicos necesarios que hacen posible la realización de tareas específicas
hardware	corresponde a todas las partes tangibles de una computadora: sus componentes eléctricos, electrónicos, electromecánicos y mecánicos.
programación	es un idioma artificial diseñado para expresar computaciones que pueden ser llevadas a cabo por máquinas como las computadoras.
internet	red de redes
network	red que comunica equipos de computadores
modelo	representacion abstracta de la realidad
robot	referente a la programacion de robots
\.


--
-- Data for Name: pertenece_documento_area_conocimiento; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY pertenece_documento_area_conocimiento (id_area, id_documento) FROM stdin;
22	10012
25	10012
28	10013
16	10013
42	10014
45	10015
41	10016
49	10000
2	10000
1	10000
21	10001
15	10001
53	10001
20	10002
6	10002
7	10002
13	10002
22	10002
48	10004
8	10004
13	10004
21	10004
6	10003
10	10003
17	10003
33	10003
42	10003
50	10003
30	10003
6	10005
7	10005
14	10005
17	10005
25	10005
23	10005
31	10005
43	10005
17	10006
16	10006
28	10006
17	10007
31	10007
16	10007
8	10008
12	10008
13	10008
48	10008
6	10009
2	10009
58	10009
49	10009
49	10010
14	10010
33	10010
40	10010
42	10010
54	10010
13	10010
30	10010
5	10010
6	10010
7	10011
20	10011
22	10011
35	10011
\.


--
-- Data for Name: tiene_documento_palabra_clave; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY tiene_documento_palabra_clave (nombre, id_documento) FROM stdin;
grafos	10012
programacion	10013
virtual	10014
discretas	10015
red	10000
internet	10000
network	10000
base datos	10001
modelo	10001
mysql	10001
postgres	10001
desarrollo software	10002
computacion	10002
base datos	10004
java	10004
mysql	10004
programacion	10003
desarrollo software	10003
robot	10003
programacion	10005
red	10005
desarrollo software	10005
computacion	10005
programacion	10006
programación	10006
java	10006
desarrollo software	10006
programacion	10007
computacion	10007
c++	10007
oracol	10007
base datos	10008
mysql	10008
oracol	10008
postgres	10008
computacion	10009
computacion nube	10009
virtual	10009
red	10009
network	10009
internet	10009
software	10009
computacion	10010
red	10010
network	10010
internet	10010
desarrollo software	10011
computacion	10011
software	10011
\.


--
-- Data for Name: tipomaterial; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY tipomaterial (tipo_nombre, descripcion) FROM stdin;
libro	documento extenso creado por un especifico tema
articulo	documento pequeno creado por un especifico tema
memoria	biografia
trabajo de grado	trabajo presentado para el grado por estudiantes de pregrado de ultimos semestres
tesis de maestria	trabajo presentado para el grado por estudiantes de maestria de ultimos semestres
tesis de doctorado	trabajo presentado para el grado por estudiantes de doctorado de ultimos semestres
material de clase	diapositivas, talleres, ejercicios y documentos del profesor
citas bibliográficas	es un conjunto mínimo de datos que permite la identificación de una publicación o de una parte de la misma (monografías, publicaciones en serie) y todo tipo de contenedor de información.
revistas	es una publicación de aparición periódica, a intervalos mayores a un día.
reportes	pueden incluir elementos persuasivos, tales como recomendaciones, sugerencias u otras conclusiones motivacionales que indican posibles acciones futuras que el lector del informe pudiera adoptar.
diccionarios	es una obra de consulta de palabras o términos que se encuentran ordenados alfabéticamente, de dichas palabras o términos se proporciona su significado, etimología, ortografía.
enciclopedias	es un texto que busca compendiar el conocimiento humano.
gráficos	una representación de datos, generalmente numéricos, mediante líneas, superficies o símbolos, para ver la relación que guardan entre sí.
\.


--
-- Data for Name: usuario; Type: TABLE DATA; Schema: public; Owner: yerdogm
--

COPY usuario (login, contrasena, nombre1, nombre2, apellido1, apellido2, email, nivel_escolaridad, pregunta_secreta, respuesta_secreta, vinculo_univalle, genero, fecha_nacimiento, fecha_registro, tipo, estado, fecha_ultimo_acceso) FROM stdin;
anonimo	anonimo	anonimo	anonimo	anonimo	univalle	anonimo@correo	Profesional/Universidad	Ciudad natal de la abuela	respuesta	Estudiante de pregrado	M	2011-05-08	2011-05-08	0	f	2010-05-29
admin	digital	biblioteca	digital	eisc	univalle	bibliotecaeisc@correo.univalle.co	Profesional/Universidad	Ciudad natal de la abuela	respuesta	Estudiante de pregrado	M	2011-05-08	2011-05-08	1	t	2011-06-06
clrl	clrl	cristian	leonardo	rios	lopez	dragonblanco452@gmail.com	Tecnología	Ciudad natal de la abuela	respuesta	Estudiante de pregrado	M	1989-06-09	2011-05-08	2	t	2011-06-06
monki	monki	edgar	andres	moncada	taborda	edgarandres29@gmail.com	Media Académica	Mejor amigo de la infancia	monki	Estudiante de pregrado	M	1991-05-29	2011-05-24	3	t	2011-05-27
maria	maria	maria	andrea	cruz		maria@gmail.com	Media Académica	Mejor amigo de la infancia	maria	Estudiante de pregrado	F	2011-05-24	2011-05-24	2	t	2011-05-27
felipex	felipex	luis	felipe	vargas	rojas	felipex@gmail.com	Media Académica	Mejor amigo de la infancia	felipex	Estudiante de pregrado	M	2011-05-24	2011-05-24	2	t	2011-05-27
yerdogm	yerdogm	yerminson	doney	gonzales	muños	yerdogm@gmail.com	Media Académica	Mejor amigo de la infancia	yerdogm	Estudiante de pregrado	M	2011-05-24	2011-05-24	2	t	2011-05-27
alberto	alberto	alberto		gonzales		alberto@gmail.com	Básica Primaria	Mejor amigo de la infancia	alberto	Estudiante de pregrado	M	2011-05-24	2011-05-24	3	t	2011-05-27
carlos	carlos	carlos	andres	valderrama	aguirre	carlos@gmail.com	Básica Primaria	Mejor amigo de la infancia	carlos	Estudiante de pregrado	M	2011-05-24	2011-05-24	3	t	2011-05-24
camilo	camilo	camilo		suarez		camilo@gmail.com	Tecnologí­a	Mejor amigo de la infancia	camilo	Egresado	M	2011-05-24	2011-05-24	3	t	2011-05-24
laura	laura	laura		rodrigues		laura@gmail.com	Media Técnica	Mejor amigo de la infancia	laura	Egresado	F	2011-05-24	2011-05-24	3	t	2011-05-24
marcela	marcela	marcela		lozano		marcela@yahoo.es	Maestrí­a	Mejor amigo de la infancia	marcela	Profesor activo	F	2011-05-24	2011-05-24	3	t	2011-05-24
alvaivan	alvaivan1	álvaro	iván	gómez	molina	alvaiva-4558@gmail.com	Tecnología	Mejor amigo de la infancia	carlos	Estudiante de pregrado	M	1987-05-12	2011-05-28	3	t	2011-05-28
albertc	albertc2	carlos	alberto	molina	lotero	albertcpenc13@hotmail.es	Tecnología	Ciudad natal de la abuela	cali	Jubilado	M	1980-12-12	2011-05-28	2	t	2011-05-28
herna08	herna083	hernán	darío	naranjo	naranjo	herna08_12ecw@yahoo.es	Tecnología	Superheroe preferido	superman	Estudiante de pregrado	M	1967-01-12	2011-05-28	3	t	2011-05-28
jo23leon	jo23leon4	jorge	león	osorio	osorio	jo23leonhero@univalle.edu	Tecnología	Ciudad donde pasa las vacaciones	cartagena	Egresado	M	1980-04-17	2011-05-28	3	t	2011-05-29
jhon7452	jhon74525	john	jairo	duque	alzate	jhon7452-super@correounivalle.edu.co	Especialización Técnica	Nombre de la primer mascota	lulu	Egresado	M	1979-07-28	2011-05-28	2	t	2011-05-29
armidb45	armidb456	armid	benjamín	palacio	duque	armidb45-yo@gmail.com	Profesional/Universidad	Cuento que mas veces ha leido	atalanta	Profesor activo	M	1980-11-10	2011-05-28	3	t	2011-06-06
elkin123	elkin1237	elkin	octavio	gonzález	gonzález	elkin123-12penm@gmail.com	Profesional/Universidad	Mejor amigo de la infancia	angie	Estudiante de postgrado	M	1981-04-24	2011-06-03	3	t	2011-06-06
shakespea	shakespear08	julio	cesar	lotero	gómez	shakespear_forever@hotmail.com	Especialización	Ciudad natal de la abuela	tulua	Ninguno	M	1961-02-28	2011-06-03	3	t	2011-06-03
osito32	osito321	gabriel	jaime	alzate	palacio	osito32-lomaslindo06@live.com	Técnica Profesional	Superheroe preferido	batman	Estudiante de postgrado	M	1992-02-03	2011-06-03	3	t	2011-06-06
marivicky	marivicky13	maria	victoria	sáenz	sáenz	marivicky13@voiture.fr	Especialización Tecnológica	Ciudad donde pasa las vacaciones	madrid	Profesor activo	F	1964-04-07	2011-06-03	2	f	2011-06-03
\.


--
-- Name: area_conocimiento_nombre_key; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT area_conocimiento_nombre_key UNIQUE (nombre);


--
-- Name: documento_link_key; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT documento_link_key UNIQUE (link);


--
-- Name: fecha_hora_login_iddocumento_fk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY descarga_usuario_documento
    ADD CONSTRAINT fecha_hora_login_iddocumento_fk PRIMARY KEY (fecha, hora, login, id_documento);


--
-- Name: id_area_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT id_area_pk PRIMARY KEY (id_area);


--
-- Name: idarea_iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY pertenece_documento_area_conocimiento
    ADD CONSTRAINT idarea_iddocumento_pk PRIMARY KEY (id_area, id_documento);


--
-- Name: idautor_iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY escribe_autor_documento
    ADD CONSTRAINT idautor_iddocumento_pk PRIMARY KEY (id_autor, id_documento);


--
-- Name: idautor_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY autor
    ADD CONSTRAINT idautor_pk PRIMARY KEY (id_autor);


--
-- Name: iddocumento_login_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT iddocumento_login_pk PRIMARY KEY (id_documento, login, fecha, hora);


--
-- Name: iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT iddocumento_pk PRIMARY KEY (id_documento);


--
-- Name: login_idarea_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY interesa_usuario_area_conocimiento
    ADD CONSTRAINT login_idarea_pk PRIMARY KEY (login, id_area);


--
-- Name: nombre_iddocumento_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY tiene_documento_palabra_clave
    ADD CONSTRAINT nombre_iddocumento_pk PRIMARY KEY (nombre, id_documento);


--
-- Name: nombre_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY palabra_clave
    ADD CONSTRAINT nombre_pk PRIMARY KEY (nombre);


--
-- Name: tiponombre_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY tipomaterial
    ADD CONSTRAINT tiponombre_pk PRIMARY KEY (tipo_nombre);


--
-- Name: usuario_email_key; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_email_key UNIQUE (email);


--
-- Name: usuario_pk; Type: CONSTRAINT; Schema: public; Owner: yerdogm; Tablespace: 
--

ALTER TABLE ONLY usuario
    ADD CONSTRAINT usuario_pk PRIMARY KEY (login);


--
-- Name: area_padre_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY area_conocimiento
    ADD CONSTRAINT area_padre_fk FOREIGN KEY (area_padre) REFERENCES area_conocimiento(id_area) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: id_area_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY interesa_usuario_area_conocimiento
    ADD CONSTRAINT id_area_fk FOREIGN KEY (id_area) REFERENCES area_conocimiento(id_area) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: idarea_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY pertenece_documento_area_conocimiento
    ADD CONSTRAINT idarea_fk FOREIGN KEY (id_area) REFERENCES area_conocimiento(id_area) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: idautor_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY escribe_autor_documento
    ADD CONSTRAINT idautor_fk FOREIGN KEY (id_autor) REFERENCES autor(id_autor) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY pertenece_documento_area_conocimiento
    ADD CONSTRAINT iddocumento FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY descarga_usuario_documento
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY escribe_autor_documento
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: iddocumento_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY tiene_documento_palabra_clave
    ADD CONSTRAINT iddocumento_fk FOREIGN KEY (id_documento) REFERENCES documento(id_documento) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: login_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY interesa_usuario_area_conocimiento
    ADD CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: login_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY descarga_usuario_documento
    ADD CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: login_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY consulta
    ADD CONSTRAINT login_fk FOREIGN KEY (login) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: logincatalogador_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT logincatalogador_fk FOREIGN KEY (login_catalogador) REFERENCES usuario(login) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: nombre_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY tiene_documento_palabra_clave
    ADD CONSTRAINT nombre_fk FOREIGN KEY (nombre) REFERENCES palabra_clave(nombre) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: tipo_fk; Type: FK CONSTRAINT; Schema: public; Owner: yerdogm
--

ALTER TABLE ONLY documento
    ADD CONSTRAINT tipo_fk FOREIGN KEY (tipo_nombre) REFERENCES tipomaterial(tipo_nombre) ON UPDATE CASCADE ON DELETE RESTRICT;


--
-- Name: public; Type: ACL; Schema: -; Owner: postgres
--
/*
REVOKE ALL ON SCHEMA public FROM PUBLIC;
REVOKE ALL ON SCHEMA public FROM postgres;
GRANT ALL ON SCHEMA public TO postgres;
GRANT ALL ON SCHEMA public TO PUBLIC;
*/

--
-- PostgreSQL database dump complete
--

