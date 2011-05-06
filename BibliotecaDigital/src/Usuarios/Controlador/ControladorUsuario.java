package Usuarios.Controlador;

import java.sql.Date;
import java.util.Vector;

import Usuarios.Dao.DaoUsuario;
import Usuarios.Logica.Usuario;
import GestionDocumento.Logica.AreaConocimiento;

public class ControladorUsuario {

	public int insertarUsuario(String login, String contrasena, String nom1,
			String nom2, String apll1, String apll2, String email,
			String nivel, String vinculo, String pregunta, String respuesta,
			String genero, String registro, String nacimiento, String tipo,
			boolean estado) {
		Usuario u = new Usuario();

		u.setLogin(login);
		u.setContrasena(contrasena);
		u.setNombre1(nom1);
		u.setNombre2(nom2);
		u.setApellido1(apll1);
		u.setApellido2(apll2);
		u.setEmail(email);
		u.setNivelEscolaridad(nivel);
		u.setVinculoUnivalle(vinculo);
		u.setPreguntaSecreta(pregunta);
		u.setRespuestaSecreta(respuesta);
		u.setGenero(genero);
		Date f_registro = Date.valueOf(registro); // formato yyyy-mm-dd
		u.setFechaRegistro(f_registro);
		Date f_nacimiento = Date.valueOf(nacimiento); // formato yyyy-mm-dd
		u.setFechaNacimiento(f_nacimiento);
		u.setTipo(tipo);
		u.setEstado(estado);

		int value = insertarUsuario(u);

		// por seguridad
		u = null;
		return value;

	}

	public int insertarUsuario(Usuario u) {
		DaoUsuario daoUs = new DaoUsuario();
		int value = daoUs.guardarUsuario(u);

		System.out.println("Se inserto el usuario");
		daoUs = null;
		return value;
	}

	public int modificarUsuario(String login, String contrasena, String nom1,
			String nom2, String apll1, String apll2, String email,
			String nivel, String vinculo, String pregunta, String respuesta,
			String genero, String registro, String nacimiento, String tipo,
			boolean estado) {
		Usuario u = new Usuario();

		u.setLogin(login);
		u.setContrasena(contrasena);
		u.setNombre1(nom1);
		u.setNombre2(nom2);
		u.setApellido1(apll1);
		u.setApellido2(apll2);
		u.setEmail(email);
		u.setNivelEscolaridad(nivel);
		u.setVinculoUnivalle(vinculo);
		u.setPreguntaSecreta(pregunta);
		u.setRespuestaSecreta(respuesta);
		u.setGenero(genero);
		Date f_registro = Date.valueOf(registro); // formato yyyy-mm-dd
		u.setFechaRegistro(f_registro);
		Date f_nacimiento = Date.valueOf(nacimiento); // formato yyyy-mm-dd
		u.setFechaNacimiento(f_nacimiento);
		u.setTipo(tipo);
		u.setEstado(estado);

		int value = modificarUsuario(u);

		// por seguridad
		u = null;
		return value;
	}

	public int modificarUsuario(Usuario u) {
		DaoUsuario daoUs = new DaoUsuario();
		int value = daoUs.modificarUsuario(u);

		System.out.println("Se modifico el usuario");
		daoUs = null;
		return value;
	}

	/*
	 * METODO QUE PERMITE INSERTAR LAS AREAS A LA QUE LE INTERESA EL USUARIO se
	 * necesita un objeto usuario que tenga el login y un vector con areas que
	 * contengan los ids de las areas respectivas
	 */
	public int insertarUsuarioAreas(Vector<AreaConocimiento> va, Usuario u) {

		int cantidad = va.size();
		String login = u.getLogin();
		DaoUsuario daoUs = new DaoUsuario();
		int value = daoUs.insertarUsuarioAreas(login, va, cantidad);
		System.out.println("Se inserto las areas del usuario");
		daoUs = null;
		return value;
	}

	/*
	 * METODO PARA OBTENER LAS AREAS DE INTERES DE UN USUARIO
	 */
	public Vector<AreaConocimiento> obtenerUsuarioAreas(String login) {
		Vector<AreaConocimiento> areas;

		DaoUsuario daoUs = new DaoUsuario();
		areas = daoUs.consultarUsuarioAreas(login);
		System.out.println("Se obtuvieron las areas del usuario");
		daoUs = null;

		return areas;
	}
	//metodo que va a agregar las nuevas areas y va a quitar las otras seleccionadas
	public int modificarUsuarioArea(Usuario u, Vector <AreaConocimiento> areasNuevas, Vector <AreaConocimiento> areasQuitar){
		DaoUsuario daoUs = new DaoUsuario();
		/*se insertan las areas nuevas, si por alguna razon se agrego, se quito entonces se borra despues,
		 * si se agrego y se quito, entonces dira que no se puede insertar y no inserta los demas
		 * */
		int value;
		value = daoUs.insertarAreasModificadas(u.getLogin(), areasNuevas);
		
		value += daoUs.quitarAreasModificadas(u.getLogin(), areasQuitar);
		return value;
	}
	
	/*añadico por cristian*/
	/*metodo que dado un login retorna un obejto usuario asociado al login*/
	public Usuario consultarUsuario(String login)
	{
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.consultarUsuario(login);
		daoUsuario = null;
		return usuario;
	}
	
	/*añadido cristian*/
	/*metodo que retorna todos los usaurios*/
	public Vector<Usuario> consultarUsuarios()
	{
		DaoUsuario daoUsuario = new DaoUsuario();
		Vector<Usuario> usuarios = daoUsuario.consultarUsuarios();
		daoUsuario = null;
		return usuarios;
	}
	/*
	 * public static void main(String args[]){ ControladorUsuario cu = new
	 * ControladorUsuario(); Date fechaRegistro = Date.valueOf("6666-06-06");
	 * Date fechaNacimiento = Date.valueOf("7777-07-07"); AreaConocimiento area1
	 * = new AreaConocimiento(); AreaConocimiento area2 = new
	 * AreaConocimiento(); area1.setIdArea("1"); area2.setIdArea("2");
	 * Vector<AreaConocimiento> areasInteres = new Vector<AreaConocimiento>();
	 * areasInteres.add(area1); areasInteres.add(area2); Usuario u = new
	 * Usuario("444", "contrasena", "nombre1",
	 * "nombre2","apellido1","apellido2", "este", "nivel",
	 * "vinculo","preguntaSecreta","respuestaSecreta","m", fechaRegistro,
	 * fechaNacimiento, 1, true, areasInteres); //no puede haber emails iguales
	 * System.out.println(cu.insertarUsuario(u)); u.setEmail("hola");
	 * //System.out.println(cu.modificarUsuario(u));
	 * System.out.println("insertar areas interes");
	 * cu.insertarUsuarioAreas(areasInteres, u);
	 * 
	 * 
	 * } /*
	 */
}
