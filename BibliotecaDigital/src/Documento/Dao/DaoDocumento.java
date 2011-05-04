package Documento.Dao;

import java.sql.*;
import Utilidades.FachadaBD;
import Documento.Logica.*;
public class DaoDocumento {
	
	FachadaBD fachada;
	
	DaoDocumento(){
		fachada = new FachadaBD();
	}
	
	public void guardarDocumento(String id, String idioma, String derechos, String descripcion,
			String software,String resolucion,String editorial ,String formato,String titulo_principal,
			String titulo_secundario,String link, ,String creacion,String publicacion,String catalogacion,
			String login){
		
	
	}
	
	public void guardarDocumento(Documento d){}
	

}
