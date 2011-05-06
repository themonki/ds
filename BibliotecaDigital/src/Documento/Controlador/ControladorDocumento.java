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
	public int insertarDocumentoAreas(Documento d) {
		DaoDocumento daoDoc = new DaoDocumento();
		Vector <AreaConocimiento> vac = d.getAreas();
		Vector <String> area_ids = new Vector<String>();
		int value, cantidad = vac.size();
		for(int i =0; i < cantidad; i++ ){
			
			area_ids.add(vac.get(i).getIdArea());
		}
		value = daoDoc.guardarDocumentoAreas(d.getId_doc(), area_ids);
		return value;
	}
	// /////
	
	public int insertarDocumentoAreas(Vector<String> area_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		value = daoDoc.guardarDocumentoAreas(id_doc, area_ids);
		return value;
	}
	
	public int insertarDocumentoPalabrasClave(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector <PalabraClave> vac = d.getPalabrasClave();
		Vector <String> palabras_ids = new Vector<String>();
		int value, cantidad = vac.size();
		for(int i =0; i < cantidad; i++ ){
			
			palabras_ids.add(vac.get(i).getNombre());
		}
		value = daoDoc.guardarDocumentoPalabrasClave(d.getId_doc(), palabras_ids);
		return value;
	}
	public int insertarDocumentoPalabrasClave(Vector<String> palabras_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		value = daoDoc.guardarDocumentoAreas(id_doc, palabras_ids);
		return value;
	}
	public int insertarDocumentoAutores(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector <Autor> vac = d.getAutores();
		Vector <String> autores_ids = new Vector<String>();
		int value, cantidad = vac.size();
		for(int i =0; i < cantidad; i++ ){
			
			autores_ids.add(vac.get(i).getId());
		}
		value = daoDoc.guardarDocumentoPalabrasClave(d.getId_doc(), autores_ids);
		return value;
	}
	public int insertarDocumentoAutores(Vector<String> autores_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		value = daoDoc.guardarDocumentoAreas(id_doc, autores_ids);
		return value;
	}
	
	public int catalogarDocumento(Documento d){
		
		this.insertarDocumento(d);
		this.insertarDocumentoAreas(d);
		this.insertarDocumentoPalabrasClave(d);
		this.insertarDocumentoAutores(d);
		
		
		return 1;
	}
	
	public int catalogarDocumento(Documento d, Vector<String> areas_ids, 
			Vector<String> autores_ids, Vector<String> palabras_ids ){
		
		String id_doc = d.getId_doc();
		this.insertarDocumento(d);
		//this.insertarDocumentoAreas(areas_ids, id_doc);
		//this.insertarDocumentoPalabrasClave(palabras_ids, id_doc);
		//this.insertarDocumentoAutores(autores_ids, id_doc);
		return 1;
	}
	
	  public static void main(String args[]){ 
		  ControladorDocumento cd = new  ControladorDocumento(); 
		  
		  Vector<String> area_ids = new Vector<String>();
		  area_ids.add("1");
		  area_ids.add("4");
		  
		 System.out.println(cd.insertarDocumentoAreas(area_ids, "10002" ));
		  /*Vector<Autor> autores = new Vector<Autor> ();
	  Vector<AreaConocimiento> areas = new Vector<AreaConocimiento>();
	  Vector<PalabraClave> palabrasClave = new Vector<PalabraClave>(); Date
	  fechaCreacion; Date fechaPublicacion; Date fechaCatalogacion; try{
	  fechaCreacion = Date.valueOf("1111-01-01"); fechaPublicacion =
	  Date.valueOf("2222-02-02"); fechaCatalogacion =
	  Date.valueOf("3333-03-03");
	  
	  Documento d = new Documento("10002", "idioma", "derechosDeAutor",
	  "descripcion", "softwareRecomendado", "resolucion", "editorial","txt",
	  "tituloPrincipal", "tituloSecundario", "link5", fechaCreacion,
	  fechaPublicacion, fechaCatalogacion, "444", "guia", autores, areas,
	  palabrasClave ); //System.out.println(""+ cd.insertarDocumento(d));
	  //d.setIdioma("ingles"); //System.out.println(cd.modificarDocumento(d));
	  
	  }catch(Exception e){ System.out.println(e.toString());
	  
	  } */
		  }
	 
}
