package Documento.Controlador;

import Documento.Dao.*;
import Documento.Logica.*;


public class ControladorDocumento {
	
	public void insertarDocumento(String id, String idioma, String derechos, String descripcion, 
			String software, String resolucion, String editorial, String formato, 
			String titulo_principal, String titulo_secundario, String link, String creacion, 
			String publicacion, String catalogacion, String login){
        Documento d = new Documento();
        DaoDocumento daoDoc=new DaoDocumento();

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
        d.setFecha_creacion(creacion);
        d.setFecha_publicacion(publicacion);
        d.setFechaDeCatalogacion(catalogacion);
        d.setCatalogadorLogin(login);
        
        //Se llama al dao para guardar
        //System.out.println("Se va a insertar un programa");
        
        daoDoc.guardarDocumento(d);

        System.out.println("Se inserto el documento");

        //por seguridad
        d=null;
        daoDoc=null;

    }

}
