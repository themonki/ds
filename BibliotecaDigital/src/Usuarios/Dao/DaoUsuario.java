/*
 * AUTOR: EDGAR ANDRES MONCADA
 * 
 * */

package Usuarios.Dao;

import java.sql.*;
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
	
	public int modificarUsuario(String login, String contrasena, String nom1, String nom2, String apll1,
			String apll2, String email, String nivel, String vinculo, String pregunta, String respuesta,
			String genero, Date registro, Date nacimiento, int tipo, boolean estado 
			){
		
		String sql_actualizar;
		int numFilas;
		sql_actualizar = "UPDATE Usuario SET login = '"+ login +"', contrasena = '"+contrasena+"', "+
		"nombre1 = '"+nom1+"', nombre2 = '"+nom2+"', apellido1 = '"+apll1+"', appellido2 = '"+apll2+"', "+
		"email = '"+email+"', niveel_escolaridad = '"+nivel+"', pregunta_secreta = '"+pregunta+"', "+
		"respuesta_secreta = '"+respuesta+"', vinculo_univalle = '"+vinculo+"', genero = '"+genero+"', "+
		"fecha_nacimiento = '"+nacimiento.toString()+"', fecha_registro = '"+registro+"', "+
		"tipo = '"+tipo+"', estado = '";
		
		if(estado){sql_actualizar+= "t'"; }else {sql_actualizar+= "f'";}
		sql_actualizar+=";";
		
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
		int value = modificarUsuario(u.getLogin(), u.getContrasena(), u.getNombre1(), u.getNombre2(),
				u.getApellido1(), u.getApellido2(), u.getEmail(), u.getNivelEscolaridad(),
				u.getVinculoUnivalle(),u.getPreguntaSecreta(), u.getRespuestaSecreta(), u.getGenero(),
				u.getFechaRegistro(), u.getFechaNacimiento(), u.getTipo(), u.getEstado()
		);
		return value;
		
	}
}
