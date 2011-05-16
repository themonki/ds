package Consultas.Dao;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Documento.Logica.Documento;
import Utilidades.FachadaBD;

public class DaoConsulta {
	
	FachadaBD fachada;
	
	DaoConsulta(){
		fachada = new FachadaBD();
	}
	
	
	//metodo de consulta general
	public Vector<Documento> consultaGeneral(String parametro){
		
		Vector<Documento> vdoc = new Vector<Documento>();
		
		String consulta_sql = "SELECT " +
				"FROM " +
				"WHERE ;";
		ResultSet resultado;		
		//System.out.println(consulta_sql);
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			resultado = sentencia.executeQuery(consulta_sql);
			while (resultado.next()) {
				
			}
			conn.close();			
			} catch (SQLException e) {			
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);					
			}
		return vdoc;
	}
}
