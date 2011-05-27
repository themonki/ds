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
 * Clase que permite la inserccion, actualizacion y consulta de lo que tenga que ver 
 * con los usuarios
 * @author yerminson
 *
 */
public class DaoUsuario {

	/**
	 * Permite la conexion con la base de datos
	 */
	FachadaBD fachada;

	/**
	 * Constructor por defecto que inicia la variable fachada
	 */
	public DaoUsuario() {
		fachada = new FachadaBD();
	}

	/**
	 * Metodo que inserta en la tabla usuario parametro por parametro
	 * @param login - String con la llave del Usuario
	 * @param contrasena - String con la contraseña del Usuario
	 * @param nom1 - String con el primer nombre del Usuario
	 * @param nom2 - String con el segundo nombre del Usuario
	 * @param apll1 - String con el primer apellido del Usuario
	 * @param apll2 - String con el segundo apellido del Usuario
	 * @param email - String con el email del Usuario
	 * @param nivel - String con con el nivel de escolaridad del Usuario
	 * @param vinculo - String con el vinculo con la universidad del Usuario
	 * @param pregunta - String con la pregunta secreta del Usuario
	 * @param respuesta - String con la respuesta a al pregunta secreta del Usuario
	 * @param genero - String con el genero del Usuario
	 * @param registro - Date con la fecha de registro del Usuario
	 * @param nacimiento - Date con la fecha de nacimiento del Usuario
	 * @param tipo - String con el tipo del Usuario
	 * @param estado - boolean con el estado (activo o inactivo) del Usuario
	 * @return 1 si se inserto correctamente, -1 de ser lo contrario
	 * @author 
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
	 * Metodo que inserta en la tabla usuario los datos en el documento u
	 * @param u - Usuario con los datos a insertar
	 * @return 1 si se inserto correctamente, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
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
	 * Metodo que permite modificar los datos de un usuario parametro por parametro
	 * @param login - String con la llave del Usuario
	 * @param contrasena - String con la contraseña del Usuario
	 * @param nom1 - String con el primer nombre del Usuario
	 * @param nom2 - String con el segundo nombre del Usuario
	 * @param apll1 - String con el primer apellido del Usuario
	 * @param apll2 - String con el segundo apellido del Usuario
	 * @param email - String con el email del Usuario
	 * @param nivel - String con con el nivel de escolaridad del Usuario
	 * @param vinculo - String con el vinculo con la universidad del Usuario
	 * @param pregunta - String con la pregunta secreta del Usuario
	 * @param respuesta - String con la respuesta a al pregunta secreta del Usuario
	 * @param genero - String con el genero del Usuario
	 * @param registro - Date con la fecha de registro del Usuario
	 * @param nacimiento - Date con la fecha de nacimiento del Usuario
	 * @param tipo - String con el tipo del Usuario
	 * @param estado - boolean con el estado (activo o inactivo) del Usuario
	 * @return 1 si se inserto correctamente, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
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
	 * Metodo que permite modificar los datos de un usuario 
	 * @param u - Usuario con los datos a modificar
	 * @return 1 si se inserto correctamente, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
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
	 * Metodo que permite insertar las areas de interes de un usuario
	 * @param login - String con el login del usuario
	 * @param va - Vector<AreaConocimiento> con las areas de interes del usuario
	 * @return int con el numero de areas de interes insertadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
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
	/**
	 * Metodo que retorna las areas de interes de un usuario
	 * @param login - String con el login del usuario
	 * @return Vector<AreaConocimiento> con las areas de interes del usuario
	 * @author Edgar Andres Moncada
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
	/**
	 * Metodo que retorna un vector con todos los usuarios que coincidan en algun atributo
	 * @param atributo - Vector<String> los atributos de la tabla a buscar
	 * @param valor - Vector<String> con los parametros a buscar
	 * @return Vector<Usuario> que contiene los usuarios que coincidieron con la busqueda
	 * @author Cristian Leonardo Rios
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
				usuario.setFechaUltimoAcceso(tabla.getDate("fecha_ultimo_acceso"));
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
	/**
	 * Metodo que retorna el usuario identificado con el login
	 * @param login - String con el login del usuario
	 * @return Usuario con todos sus datos
	 * @author Cristian Leonardo Rios
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
	    usuario.setFechaUltimoAcceso(tabla.getDate("fecha_ultimo_acceso"));
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
	/**
	 * Metodo que remueve todas las areas de interes del usuario
	 * @param login - String con el login del usuario
	 * @return 1 si se elimino correctamente todas las areas, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
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
	/**
	 * Metodo que actualiza el perfil de usuario y el estado del usuario, realizado por el
	 * Administrador
	 * @param login - String con el login del usuario a modificar
	 * @param tipo - String con el tipo de usuario (1 para Administrador, 2 para Catalogador y
	 *  3 para Usuario Normal)
	 * @param estado - String con el estado (activo o inactivo) del usuario
	 * @return 1 si se modifico correctamente, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int modificarPerfilEstado(String login, String tipo, String estado){
		String sql_actualizar;
		int value;
		sql_actualizar = "UPDATE Usuario SET tipo = '"+tipo+"' , estado = '"+estado+"'" +
				" WHERE login = '"+login+"';";

		try {
			Connection conn = fachada.conectar();
			Statement sentencia = conn.createStatement();
			System.out.println(sql_actualizar);
			value = sentencia.executeUpdate(sql_actualizar);
			conn.close();
			return value;
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
	 * @author Yerminson Gonzalez
	 */
	public int actualizarFechaAcceso(String login, String fecha){
		String sql_actualizar;
		int numFilas;
		sql_actualizar = "UPDATE Usuario SET fecha_ultimo_acceso = '"+fecha+"'" +
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
	
	/**
	 * Metodo que retorna un vector con las consultas de los ultimos documentos catalogados despues
	 * del ultimo acceso del usuario que tengan que ver con sus areas de interes 
	 * @param parametro - String con el login del usuario
	 * @return Vector<Consulta> con los documentos que pertenescan a las areas de interes del usuario
	 * @author Yerminson Gonzalez
	 */
	public Vector<Consulta> consultaDocumentosInteresUsuario(String login)
	{
		Vector<Consulta> consultas = new Vector<Consulta>();
		String consultaFechaUltimoAcceso, consultaAreasInteresUsuario, consultaDocumentosAreaConocimientoUsuario, consultaCatalogadosDespuesUltimoAcceso;
		
		consultaFechaUltimoAcceso = "SELECT d.fecha_ultimo_acceso "+
		"FROM Usuario AS d " +
		"WHERE d.login = '"+login+"'";
		
	
		consultaAreasInteresUsuario = "SELECT e.id_area "+
		"FROM interesa_usuario_area_conocimiento AS e " +
		"WHERE e.login = '"+login+"'";
		
		consultaDocumentosAreaConocimientoUsuario = "SELECT id_documento "+
		"FROM pertenece_documento_area_conocimiento AS f NATURAL JOIN ("+consultaAreasInteresUsuario+") AS C ";
		
		consultaCatalogadosDespuesUltimoAcceso = "SELECT * "+
		"FROM ("+consultaDocumentosAreaConocimientoUsuario+") as B NATURAL JOIN " +
				"documento " +
		"WHERE fecha_catalogacion >= ("+consultaFechaUltimoAcceso+");";
		
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
