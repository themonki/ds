package Documento.Controlador;

import java.sql.Date;
import java.util.Vector;
import Documento.Dao.*;
import Documento.Logica.*;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;

public class ControladorDocumento {

	public int insertarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login) {
		Documento d = new Documento();
		int value;
		d.setId_doc(id);
		d.setIdioma(idioma);
		d.setDerechosDeAutor(derechos);
		d.setDescripcion(descripcion);
		d.setSoftware_recomentado(software);
		d.setResolucion(resolucion);
		d.setEditorial(editorial);
		d.setFormato(formato);
		d.setTituloppal(titulo_principal);
		d.setTitulo_secundario(titulo_secundario);
		d.setUrl(link);
		Date f_creacion = Date.valueOf(creacion);// formato yyyy-mm-dd
		d.setFecha_creacion(f_creacion);
		Date f_publicacion = Date.valueOf(publicacion);// formato yyyy-mm-dd
		d.setFecha_publicacion(f_publicacion);
		Date F_catalogacion = Date.valueOf(catalogacion);// formato yyyy-mm-dd
		d.setFechaDeCatalogacion(F_catalogacion);
		d.setCatalogadorLogin(login);

		value = insertarDocumento(d);

		// por seguridad
		d = null;
		return value;

	}

	public int insertarDocumento(Documento d) {
		DaoDocumento daoDoc = new DaoDocumento();
		int value = daoDoc.guardarDocumento(d);

		System.out.println("Se inserto el documento");
		daoDoc = null;
		return value;
	}

	public int modificarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login) {
		Documento d = new Documento();

		d.setId_doc(id);
		d.setIdioma(idioma);
		d.setDerechosDeAutor(derechos);
		d.setDescripcion(descripcion);
		d.setSoftware_recomentado(software);
		d.setResolucion(resolucion);
		d.setEditorial(editorial);
		d.setFormato(formato);
		d.setTituloppal(titulo_principal);
		d.setTitulo_secundario(titulo_secundario);
		d.setUrl(link);
		Date f_creacion = Date.valueOf(creacion);// formato yyyy-mm-dd
		d.setFecha_creacion(f_creacion);
		Date f_publicacion = Date.valueOf(publicacion);// formato yyyy-mm-dd
		d.setFecha_publicacion(f_publicacion);
		Date F_catalogacion = Date.valueOf(catalogacion);// formato yyyy-mm-dd
		d.setFechaDeCatalogacion(F_catalogacion);
		d.setCatalogadorLogin(login);

		int value = modificarDocumento(d);

		// por seguridad
		d = null;
		return value;
	}

	// /////////

	public int modificarDocumento(Documento d) {
		DaoDocumento daoDoc = new DaoDocumento();
		int value = daoDoc.modificarDocumento(d);
		System.out.println("Se modifico el documento");
		daoDoc = null;
		return value;
	}

	// ////
	public int insertarDocumentoAreas(Vector<AreaConocimiento> vac, Documento d) {
		DaoDocumento daoDoc = new DaoDocumento();

		int value = 0;

		return value;
	}
	// /////

	/*
	 * public static void main(String args[]){ ControladorDocumento cd = new
	 * ControladorDocumento(); Vector<Autor> autores = new Vector<Autor> ();
	 * Vector<AreaConocimiento> areas = new Vector<AreaConocimiento>();
	 * Vector<PalabraClave> palabrasClave = new Vector<PalabraClave>(); Date
	 * fechaCreacion; Date fechaPublicacion; Date fechaCatalogacion; try{
	 * fechaCreacion = Date.valueOf("1111-01-01"); fechaPublicacion =
	 * Date.valueOf("2222-02-02"); fechaCatalogacion =
	 * Date.valueOf("3333-03-03");
	 * 
	 * Documento d = new Documento("10002", "idioma", "derechosDeAutor",
	 * "descripcion", "softwareRecomendado", "resolucion", "editorial","txt",
	 * "tituloPrincipal", "tituloSecundario", "link5", fechaCreacion,
	 * fechaPublicacion, fechaCatalogacion, "444", "guia", autores, areas,
	 * palabrasClave ); //System.out.println(""+ cd.insertarDocumento(d));
	 * //d.setIdioma("ingles"); //System.out.println(cd.modificarDocumento(d));
	 * 
	 * }catch(Exception e){ System.out.println(e.toString());
	 * 
	 * } }
	 */
}
