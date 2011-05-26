/*
 * AUTOR: EDGAR ANDRES MONCADA
 * 
 * */

package Usuarios.Dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import Consultas.Dao.DaoConsulta;
import Consultas.Logica.Consulta;
import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Logica.Usuario;
import Utilidades.FachadaBD;


/**
 * @author yerminson
 *
 */
public class DaoUsuario {

	FachadaBD fachada;

	/**
	 * Constructor por defecto
	 */
	public DaoUsuario() {
		fachada = new FachadaBD();
	}

	/**
	 * @param login - String con
	 * @param contrasena - String con
	 * @param nom1 - String con
	 * @param nom2 - String con
	 * @param apll1 - String con
	 * @param apll2 - String con
	 * @param email - String con
	 * @param nivel - String con
	 * @param vinculo - String con
	 * @param pregunta - String con
	 * @param respuesta - String con
	 * @param genero - String con
	 * @param registro - Date con
	 * @param nacimiento - Date con
	 * @param tipo - String con
	 * @param estado - boolean con
	 * @return
	 */
	public int guardarUsuario(String login, String contrasena, String nom1,
			String nom2, String apll1, String apll2, String email,
			String nivel, String vinculo, String pregunta, String respuesta,
			String genero, Date registro, Date nacimiento, String tipo,
			boolean estado) {

		String sql_guardar;
		int numFilas;
		sql_guardar = "INSERT INTO Usuario (login, contrasena, nombre1, nombre2, apellido1, " +
				"apellido2, email, nivel_escolaridad, pregunta_secreta, respuesta_secreta, vinculo_univalle," +
				"genero, fecha_nacimiento, fecha_registro, tipo, estado)" +
				"VALUES ('" + login + "', '"
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

	/**
	 * @param u
	 * @return
	 */
	public int guardarUsuario(Usuario u) {
		int value = guardarUsuario(u.getLogin(), u.getContrasena(), u
				.getNombre1(), u.getNombre2(), u.getApellido1(), u
				.getApellido2(), u.getEmail(), u.getNivelEscolaridad(), u
				.getVinculoUnivalle(), u.getPreguntaSecreta(), u
				.getRespuestaSecreta(), u.getGenero(), u.getFechaRegistro(), u
				.getFechaNacimiento(), u.getTipo(), u.getEstado());
		return value;

	}

	/**
	 * @param login
	 * @param contrasena
	 * @param nom1
	 * @param nom2
	 * @param apll1
	 * @param apll2
	 * @param email
	 * @param nivel
	 * @param vinculo
	 * @param pregunta
	 * @param respuesta
	 * @param genero
	 * @param registro
	 * @param nacimiento
	 * @param tipo
	 * @param estado
	 * @return
	 */
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

	/**
	 * @param u
	 * @return
	 */
	public int modificarUsuario(Usuario u) {
		int value = modificarUsuario(u.getLogin(), u.getContrasena(), u
				.getNombre1(), u.getNombre2(), u.getApellido1(), u
				.getApellido2(), u.getEmail(), u.getNivelEscolaridad(), u
				.getVinculoUnivalle(), u.getPreguntaSecreta(), u
				.getRespuestaSecreta(), u.getGenero(), u.getFechaRegistro(), u
				.getFechaNacimiento(), u.getTipo(), u.getEstado());
		return value;

	}

	/**
	 * @param login
	 * @param va
	 * @return
	 */
	public int insertarUsuarioAreas(String login, Vector<AreaConocimiento> va) {
		int numFilas = 0, cantidad = va.size();
		String sql_guardar = "INSERT INTO Interesa_Usuario_Area_Conocimiento (login, id_area)"
				+ " VALUES ", id_area = "";

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
	/**
	 * @param login
	 * @return
	 */
	public Vector<AreaConocimiento> consultarUsuarioAreas(String login) {

		String consulta_sql = "SELECT ac.id_area, ac.nombre FROM Area_Conocimiento ac "
				+ "NATURAL JOIN Interesa_Usuario_Area_Conocimiento "
				+ "WHERE login = '" + login + "';";
		ResultSet resultado;
		Vector<AreaConocimiento> areas = new Vector<AreaConocimiento>();
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();

			resultado = sentencia.executeQuery(consulta_sql);

			while (resultado.next()) {
				AreaConocimiento area = new AreaConocimiento();
				area.setIdArea(resultado.getString("id_area"));
				area.setNombre(resultado.getString("nombre"));
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
	
	/*añadido por cristian, retorna un vector con todos los usuarios que coincidan en algun atributo*/
	/**
	 * @param atributo
	 * @param valor
	 * @return
	 */
	public Vector<Usuario> consultarUsuarios(Vector<String> atributo, Vector<String> valor)
	{
		Vector<Usuario> usuarios = new Vector<Usuario>();
		String sqlSelect;
		
		sqlSelect = "SELECT * FROM Usuario WHERE ";
		for(int i=0; i < atributo.size()-1;i++)
			sqlSelect += atributo.elementAt(i) + " LIKE " + "'%" + valor.elementAt(i) + "%'" + " AND ";
		sqlSelect += atributo.lastElement() + " LIKE " + "'%" + valor.lastElement() + "%'";
		sqlSelect += "AND login != 'anonimo';";
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
				
				usuario.setAreas(consultarUsuarioAreas(usuario.getLogin()));

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
	
	/*retorna un usuario bscado por login*/
	/**
	 * @param login
	 * @return
	 */
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
	    
	    usuario.setAreas(consultarUsuarioAreas(usuario.getLogin()));

	   }
	   this.fachada.cerrarConexion(conn);

	  } catch (SQLException se) {
	   se.printStackTrace();
	  } catch (Exception e) {
	   e.printStackTrace();
	  }
	  return usuario;
	 }
		
//remueve todas las areas de un usuario
	/**
	 * @param login
	 * @return
	 */
	public int quitarUsuarioAreas(String login){		
		String sql_borrar;
		sql_borrar = "DELETE FROM Interesa_Usuario_Area_Conocimiento WHERE login = '"+login+"';";		
		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			sentencia.executeUpdate(sql_borrar);				
			conn.close();
			return 1;
		} catch (SQLException e) {
			System.out.println(e);
		} catch (Exception e) {
			System.out.println(e);
		}
		return -1;		
	}
	
	//actualizar el perfil y el estado de un usuario
	/**
	 * @param login
	 * @param tipo
	 * @param estado
	 * @return
	 */
	public int modificarPerfilEstado(String login, String tipo, String estado){
		String sql_actualizar;
		int numFilas;
		sql_actualizar = "UPDATE Usuario SET tipo = '"+tipo+"' , estado = '"+estado+"'" +
				" WHERE login = '"+login+"';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			System.out.println(sql_actualizar);
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
	
	
	/**
	 * Metodo que actualiza en la base de datos el ultimo acceso de un usuario
	 * @param login - String con la llave dell usuario
	 * @param fecha - String con la fecha de acceso al sistema
	 * @return 1 si se efectuo la actualizacion -1 si no se efectuo correctamente
	 * @author Edgar Andres Moncada
	 */
	public int actualizarFechaAcceso(String login, String fecha){
		String sql_actualizar;
		int numFilas;
		sql_actualizar = "UPDATE Usuario SET fecha_ultimo_acceso = '"+fecha+"" +
				" WHERE login = '"+login+"'";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			System.out.println(sql_actualizar);
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
	
	
	
	
	//metodo de consulta general
	public Vector<Consulta> consultaDocumentosInteresUsuario(String parametro)
	{
		Vector<Consulta> consultas = new Vector<Consulta>();
		String consultaFechaUltimoAcceso, consultaAreasInteresUsuario, consultaDocumentosAreaConocimientoUsuario, consultaCatalogadosDespuesUltimoAcceso;
		
		consultaFechaUltimoAcceso = "SELECT d.fecha_ultimo_acceso "+
		"FROM Usuario AS d " +
		"WHERE d.login = '"+parametro+"'";
		
	
		consultaAreasInteresUsuario = "SELECT e.id_area "+
		"FROM interesa_usuario_area_conocimiento AS e " +
		"WHERE e.login = '"+parametro+"'";
		
		consultaDocumentosAreaConocimientoUsuario = "SELECT id_documento "+
		"FROM pertenece_documento_area_conocimiento AS f NATURAL JOIN ("+consultaAreasInteresUsuario+") AS C ";
		
		consultaCatalogadosDespuesUltimoAcceso = "SELECT * "+
		"FROM ("+consultaDocumentosAreaConocimientoUsuario+") as B NATURAL JOIN " +
				"documento " +
		"WHERE fecha_catalogacion > ("+consultaFechaUltimoAcceso+");";
		
		
		
		System.out.println(consultaCatalogadosDespuesUltimoAcceso);


		
		ResultSet resultado;		

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();			
			resultado = sentencia.executeQuery(consultaCatalogadosDespuesUltimoAcceso);
			DaoConsulta daoConsulta = new DaoConsulta();
			while (resultado.next())
			{
				Consulta consulta = new Consulta();
				
				consulta.setIdDocumento(resultado.getString("id_documento"));
				consulta.setTituloDocuemto(resultado.getString("titulo_principal"));
				consulta.setNombresAutoresDocumento(daoConsulta.consultarAutoresDocumento(resultado.getString("id_documento")));
				consultas.add(consulta);
			}
			conn.close();
			} catch (SQLException e) {			
				System.out.println(e);
			} catch (Exception e) {
				System.out.println(e);					
			}
			System.out.println(consultas);
		return consultas;
	}
	
	
}
