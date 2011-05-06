/*
 * AUTOR: EDGAR ANDRES MONCADA
 * 
 * */

package Usuarios.Dao;

import java.sql.*;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Logica.Usuario;
import Utilidades.FachadaBD;

public class DaoUsuario {

	FachadaBD fachada;

	public DaoUsuario() {
		fachada = new FachadaBD();
	}

	public int guardarUsuario(String login, String contrasena, String nom1,
			String nom2, String apll1, String apll2, String email,
			String nivel, String vinculo, String pregunta, String respuesta,
			String genero, Date registro, Date nacimiento, String tipo,
			boolean estado) {

		String sql_guardar;
		int numFilas;
		sql_guardar = "INSERT INTO Usuario VALUES ('" + login + "', '"
				+ contrasena + "', '" + nom1 + "', '" + nom2 + "', '" + apll1
				+ "', '" + apll2 + "', '" + email + "', '" + nivel + "', '"
				+ pregunta + "', '" + respuesta + "', '" + vinculo + "', '"
				+ genero + "', '" + nacimiento.toString() + "', '"
				+ registro.toString() + "', '" + tipo + "', ";

		if (estado) {
			sql_guardar += "'t'";
		} else {
			sql_guardar += "'f'";
		}

		sql_guardar += ");";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_guardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;
	}

	public int guardarUsuario(Usuario u) {
		int value = guardarUsuario(u.getLogin(), u.getContrasena(), u
				.getNombre1(), u.getNombre2(), u.getApellido1(), u
				.getApellido2(), u.getEmail(), u.getNivelEscolaridad(), u
				.getVinculoUnivalle(), u.getPreguntaSecreta(), u
				.getRespuestaSecreta(), u.getGenero(), u.getFechaRegistro(), u
				.getFechaNacimiento(), u.getTipo(), u.getEstado());
		return value;

	}

	public int modificarUsuario(String login, String contrasena, String nom1,
			String nom2, String apll1, String apll2, String email,
			String nivel, String vinculo, String pregunta, String respuesta,
			String genero, Date registro, Date nacimiento, String tipo,
			boolean estado) {

		String sql_actualizar;
		int numFilas;

		sql_actualizar = "UPDATE Usuario SET contrasena = '" + contrasena
				+ "', " + "nombre1 = '" + nom1 + "', nombre2 = '" + nom2
				+ "', apellido1 = '" + apll1 + "', apellido2 = '" + apll2
				+ "', " + "email = '" + email + "', nivel_escolaridad = '"
				+ nivel + "', pregunta_secreta = '" + pregunta + "', "
				+ "respuesta_secreta = '" + respuesta
				+ "', vinculo_univalle = '" + vinculo + "', genero = '"
				+ genero + "', " + "fecha_nacimiento = '"
				+ nacimiento.toString() + "', fecha_registro = '" + registro
				+ "', " + "tipo = '" + tipo + "', estado = '";

		if (estado) {
			sql_actualizar += "t'";
		} else {
			sql_actualizar += "f'";
		}
		sql_actualizar += " WHERE login = '" + login + "';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_actualizar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;

	}

	public int modificarUsuario(Usuario u) {
		int value = modificarUsuario(u.getLogin(), u.getContrasena(), u
				.getNombre1(), u.getNombre2(), u.getApellido1(), u
				.getApellido2(), u.getEmail(), u.getNivelEscolaridad(), u
				.getVinculoUnivalle(), u.getPreguntaSecreta(), u
				.getRespuestaSecreta(), u.getGenero(), u.getFechaRegistro(), u
				.getFechaNacimiento(), u.getTipo(), u.getEstado());
		return value;

	}

	public int insertarUsuarioAreas(String login, Vector<AreaConocimiento> va,
			int cantidad) {
		int numFilas = 0;
		String sql_guardar = "INSERT INTO Interesa_Usuario_Area_Conocimiento (login, id_area)"
				+ "VALUES ", id_area = "";

		for (int i = 0; i < cantidad; i++) {
			id_area = va.get(i).getIdArea();
			if (i == cantidad - 1) {// ultimo valor
				sql_guardar += "('" + login + "','" + id_area + "')";
			} else {
				sql_guardar += "('" + login + "','" + id_area + "'),";
			}
		}
		sql_guardar += ";";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			numFilas = sentencia.executeUpdate(sql_guardar);
			conn.close();
			return numFilas;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;

	}

	/*
	 * METODO QUE RETORNA TODAS LAS AREAS A LAS QUE ESTE RELACIONADO EL USUARIO
	 */
	public Vector<AreaConocimiento> consultarUsuarioAreas(String login) {

		String consulta_sql = "SELECT ac.id_area, ac.nombre FROM Area_Conocimiento ac"
				+ "NATURAL JOIN Interesa_Usuario_Area_Conocimiento i"
				+ "WHERE i.login = '" + login + "'";
		ResultSet resultado;
		Vector<AreaConocimiento> areas = new Vector<AreaConocimiento>();

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			resultado = sentencia.executeQuery(consulta_sql);

			while (resultado.next()) {
				AreaConocimiento area = new AreaConocimiento();
				area.setIdArea(resultado.getString(1));
				area.setNombre(resultado.getString(1));
				areas.add(area);
			}

			conn.close();

		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return areas;

	}
	
	/*a�adido por cristian*/
	public Vector<Usuario> consultarUsuarios()
	{
		Vector<Usuario> usuarios = new Vector<Usuario>();
		String sqlSelect;
		
		sqlSelect = "SELECT * FROM Usuario";
		
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			while (tabla.next())
			{
				Usuario usuario = new Usuario();

				usuario.setLogin(tabla.getString("login"));
				usuario.setContrasena(tabla.getString("contrasena"));
				usuario.setNombre1(tabla.getString("nombre1"));
				usuario.setNombre2(tabla.getString("nombre2"));
				usuario.setApellido1(tabla.getString("apellido1"));
				usuario.setApellido2(tabla.getString("apellido2"));
				usuario.setEmail(tabla.getString("email"));
				usuario.setNivelEscolaridad(tabla.getString("nivel_escolaridad"));
				usuario.setPreguntaSecreta(tabla.getString("pregunta_secreta"));
				usuario.setRespuestaSecreta(tabla.getString("respuesta_secreta"));
				usuario.setVinculoUnivalle(tabla.getString("vinculo_univalle"));
				usuario.setGenero(tabla.getString("genero"));
				usuario.setFechaNacimiento(tabla.getDate("fecha_nacimiento"));
				usuario.setFechaRegistro(tabla.getDate("fecha_registro"));
				usuario.setTipo(tabla.getString("tipo"));
				usuario.setEstado(tabla.getBoolean("estado"));
				
				/*probando*/
				System.out.println("Fecha: " + tabla.getDate("fecha_nacimiento"));
				System.out.println("Login: " + tabla.getString("login"));
				System.out.println("Contrasena: " + tabla.getString("contrasena"));
				System.out.println("Nombre1: " + tabla.getString("nombre1"));
				System.out.println("Nombre2: " + tabla.getString("nombre2"));
				System.out.println("Apellido1: " + tabla.getString("apellido1"));
				System.out.println("Apellido2: " + tabla.getString("apellido2"));
				System.out.println("Email: " + tabla.getString("email"));
				System.out.println("Escolaridad: " + tabla.getString("nivel_escolaridad"));
				System.out.println("Pregunta: " + tabla.getString("pregunta_secreta"));
				System.out.println("Respuesta: " + tabla.getString("respuesta_secreta"));
				System.out.println("Vinculo: " + tabla.getString("vinculo_univalle"));
				System.out.println("Genero: " + tabla.getString("genero"));
				System.out.println("Nacimiento: " + tabla.getDate("fecha_nacimiento"));
				System.out.println("Registro: " + tabla.getDate("fecha_registro"));
				System.out.println("Tipo: " + tabla.getString("tipo"));
				System.out.println("Estado: " + tabla.getBoolean("estado"));

				usuarios.add(usuario);
			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuarios;
	}
	
	public Usuario consultarUsuario(String login)
	{
		Usuario usuario = new Usuario();
		String sqlSelect;
		
		sqlSelect = "SELECT * FROM Usuario WHERE Usuario.login = '" + login + "'";
		
		try {
			Connection conn = this.fachada.conectar();
			Statement sentencia = conn.createStatement();
			ResultSet tabla = sentencia.executeQuery(sqlSelect);

			if(tabla.next())
			{

				usuario.setLogin(tabla.getString("login"));
				usuario.setContrasena(tabla.getString("contrasena"));
				usuario.setNombre1(tabla.getString("nombre1"));
				usuario.setNombre2(tabla.getString("nombre2"));
				usuario.setApellido1(tabla.getString("apellido1"));
				usuario.setApellido2(tabla.getString("apellido2"));
				usuario.setEmail(tabla.getString("email"));
				usuario.setNivelEscolaridad(tabla.getString("nivel_escolaridad"));
				usuario.setPreguntaSecreta(tabla.getString("pregunta_secreta"));
				usuario.setRespuestaSecreta(tabla.getString("respuesta_secreta"));
				usuario.setVinculoUnivalle(tabla.getString("vinculo_univalle"));
				usuario.setGenero(tabla.getString("genero"));
				usuario.setFechaNacimiento(tabla.getDate("fecha_nacimiento"));
				usuario.setFechaRegistro(tabla.getDate("fecha_registro"));
				usuario.setTipo(tabla.getString("tipo"));
				usuario.setEstado(tabla.getBoolean("estado"));
				
				/*probando*/
				System.out.println("Fecha: " + tabla.getDate("fecha_nacimiento"));
				System.out.println("Login: " + tabla.getString("login"));
				System.out.println("Contrasena: " + tabla.getString("contrasena"));
				System.out.println("Nombre1: " + tabla.getString("nombre1"));
				System.out.println("Nombre2: " + tabla.getString("nombre2"));
				System.out.println("Apellido1: " + tabla.getString("apellido1"));
				System.out.println("Apellido2: " + tabla.getString("apellido2"));
				System.out.println("Email: " + tabla.getString("email"));
				System.out.println("Escolaridad: " + tabla.getString("nivel_escolaridad"));
				System.out.println("Pregunta: " + tabla.getString("pregunta_secreta"));
				System.out.println("Respuesta: " + tabla.getString("respuesta_secreta"));
				System.out.println("Vinculo: " + tabla.getString("vinculo_univalle"));
				System.out.println("Genero: " + tabla.getString("genero"));
				System.out.println("Nacimiento: " + tabla.getDate("fecha_nacimiento"));
				System.out.println("Registro: " + tabla.getDate("fecha_registro"));
				System.out.println("Tipo: " + tabla.getString("tipo"));
				System.out.println("Estado: " + tabla.getBoolean("estado"));

			}
			this.fachada.cerrarConexion(conn);

		} catch (SQLException se) {
			se.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return usuario;
	}

	
	public static void main(String args[])
	{
		DaoUsuario da = new DaoUsuario();
		da.consultarUsuarios();
		da.consultarUsuario("444");
		
	}
}
