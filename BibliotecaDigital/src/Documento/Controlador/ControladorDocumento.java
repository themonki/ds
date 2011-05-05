package Documento.Controlador;

import java.sql.Date;

import Documento.Dao.*;
import Documento.Logica.*;


public class ControladorDocumento {
	
	public void insertarDocumento(String id, String idioma, String derechos, String descripcion, 
			String software, String resolucion, String editorial, String formato, 
			String titulo_principal, String titulo_secundario, String link, String creacion, 
			String publicacion, String catalogacion, String login){
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
        Date f_creacion = Date.valueOf(creacion);//formato yyyy-mm-dd
        d.setFecha_creacion(f_creacion);
        Date f_publicacion = Date.valueOf(publicacion);//formato yyyy-mm-dd
        d.setFecha_publicacion(f_publicacion);
        Date F_catalogacion = Date.valueOf(catalogacion);//formato yyyy-mm-dd
        d.setFechaDeCatalogacion(F_catalogacion);
        d.setCatalogadorLogin(login);
        
        insertarDocumento(d);

        //por seguridad
        d=null;       

    }
	
	public void insertarDocumento(Documento d){
		DaoDocumento daoDoc=new DaoDocumento();
		daoDoc.guardarDocumento(d);
		
		System.out.println("Se inserto el documento");
		daoDoc=null;
		
	}
	
	public void modificarDocumento(String id, String idioma, String derechos, String descripcion, 
			String software, String resolucion, String editorial, String formato, 
			String titulo_principal, String titulo_secundario, String link, String creacion, 
			String publicacion, String catalogacion, String login){
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
        Date f_creacion = Date.valueOf(creacion);//formato yyyy-mm-dd
        d.setFecha_creacion(f_creacion);
        Date f_publicacion = Date.valueOf(publicacion);//formato yyyy-mm-dd
        d.setFecha_publicacion(f_publicacion);
        Date F_catalogacion = Date.valueOf(catalogacion);//formato yyyy-mm-dd
        d.setFechaDeCatalogacion(F_catalogacion);
        d.setCatalogadorLogin(login);
        
        modificarDocumento(d);

        //por seguridad
        d=null;
	}

	public void modificarDocumento(Documento d){
		DaoDocumento daoDoc=new DaoDocumento();
		daoDoc.modificarDocumento(d);
		
		System.out.println("Se inserto el documento");
		daoDoc=null;
		
	}
}
