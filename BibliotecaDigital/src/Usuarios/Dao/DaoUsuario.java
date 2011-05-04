package Usuarios.Dao;

import java.sql.*;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Logica.Usuario;
import Utilidades.FachadaBD;

public class DaoUsuario {

	FachadaBD fachada;
	
	public DaoUsuario(){
		fachada = new FachadaBD();
	}
	
	public int guardarUsuario(String login, String contrasena, String nom1, String nom2, String apll1,
			String apll2, String email, String nivel, String vinculo, String pregunta, String respuesta,
			String genero, Date registro, Date nacimiento, int tipo, boolean estado 
			){
		
		String sql_guardar;
		int numFilas;
		sql_guardar = "INSERT INTO Usuario VALUES ('"+
		login + "', '" + contrasena  + "', '" + nom1  + "', '" + nom2 + "', '" + apll1 + "', '" + apll2 +
		"', '"+email + "', '" + nivel + "', '" + pregunta + "', '" + respuesta + "', '" + vinculo +
		"', '"+genero + "', '" + nacimiento.toString() + "', '" + registro.toString() + "', '" + tipo  + "', ";
		
		if(estado){sql_guardar+= "'t'"; }else {sql_guardar+= "'f'";}
		
		sql_guardar+= ");";
		
		try{
            Connection conn= fachada.conectar();
            Statement sentencia = conn.createStatement();

            numFilas = sentencia.executeUpdate(sql_guardar);
            conn.close();
            return numFilas;
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        return -1;		
	
	}
	
	public int guardarUsuario(Usuario u){
		int value = guardarUsuario(u.getLogin(), u.getContrasena(), u.getNombre1(), u.getNombre2(),
				u.getApellido1(), u.getApellido2(), u.getEmail(), u.getNivelEscolaridad(),
				u.getVinculoUnivalle(), u.getPreguntaSecreta(), u.getRespuestaSecreta(),
				u.getGenero(), u.getFechaRegistro(), u.getFechaNacimiento(), u.getTipo(),
				u.getEstado()
		);
		return value;
		
	}
	
	public int modificarUsuario(){
		
		String sql_actualizar;
		int numFilas;
		sql_actualizar = "UPDATE Usuario SET "+
		"";
		
		try{
            Connection conn= fachada.conectar();
            Statement sentencia = conn.createStatement();

            numFilas = sentencia.executeUpdate(sql_actualizar);
            conn.close();
            return numFilas;
        }
        catch(SQLException e){ System.out.println(e); }
        catch(Exception e){ System.out.println(e); }
        return -1;		
	
	}
	
	public int modificarUsuario(Usuario u){
		int value = modificarUsuario();
		return value;
		
	}
}
