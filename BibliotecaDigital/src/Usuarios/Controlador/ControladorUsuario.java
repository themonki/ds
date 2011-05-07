package Usuarios.Controlador;

import java.sql.Date;
import java.util.Vector;

import Usuarios.Dao.DaoUsuario;
import Usuarios.Logica.Usuario;
import GestionDocumento.Logica.AreaConocimiento;

public class ControladorUsuario {
	//metodo que inserta los datos de un usuario en la tabla usuario, recibiendo atributo por atributo
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
	//metodo que inserta los datos de un usuario en la tabla usuario, recibiendo un objeto usuario
	public int insertarUsuario(Usuario u) {
		DaoUsuario daoUs = new DaoUsuario();
		int value = daoUs.guardarUsuario(u);

		System.out.println("Se inserto el usuario");
		daoUs = null;
		return value;
	}
	//modifica los datos en la tabla Usuario recibiendo atributo por atributo
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
	//modifica los datos en la tabla Usuario recibiendo un objeto Usuario
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
	public int insertarUsuarioAreas(Usuario u) {
		Vector<AreaConocimiento> va = u.getAreas();
		String login = u.getLogin();
		DaoUsuario daoUs = new DaoUsuario();
		int value = daoUs.insertarUsuarioAreas(login, va);
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
	//metodo que va a agregar las nuevas areas y va a quitar las modificarUsuarioAreaotras seleccionadas
	public int modificarUsuarioArea(Usuario u){
		DaoUsuario daoUs = new DaoUsuario();
		Vector <AreaConocimiento> areasNuevas = u.getAreas();
		if(areasNuevas.size()>0){
			System.out.println("ahi algoooo");
			System.out.println(areasNuevas.get(0).getIdArea());
		}
		/*se insertan las areas nuevas, si por alguna razon se agrego, se quito entonces se borra despues,
		 * si se agrego y se quito
		 * */
		int value=0;
		//se quitan todas las areas que hagan referencia a ese login
		value += daoUs.quitarUsuarioAreas(u.getLogin());
		//inserta las areas a las que ahora hace referencia
		value = daoUs.insertarUsuarioAreas(u.getLogin(), areasNuevas);
				
		return value;
	}
	
	/*a�adico por cristian*/
	/*metodo que dado un login retorna un obejto usuario asociado al login*/
	public Usuario consultarUsuario(String login)
	{
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.consultarUsuario(login);
		daoUsuario = null;
		return usuario;
	}
	
	/*a�adido cristian*/
	/*metodo que retorna todos los usaurios*/
	public Vector<Usuario> consultarUsuarios()
	{
		DaoUsuario daoUsuario = new DaoUsuario();
		Vector<Usuario> usuarios = daoUsuario.consultarUsuarios();
		daoUsuario = null;
		return usuarios;
	}
	//metodo que sirve para actualizar todos los datos del usuario y sus areas, recibe un objeto usuario
	public int modificarDatosUsuario(Usuario u){
		int value =0;
		value = this.modificarUsuario(u);
		value +=this.modificarUsuarioArea(u);
		return value;
	}
	
	//metodo que sirve para registar los datos del usuario y sus areas, recibe un objeto unusuario
	public int insertarDatosUsuario(Usuario u){
		int value =0;
		value = this.insertarUsuario(u);
		value +=this.insertarUsuarioAreas(u);
		return value;
	}
	
	public int modificarPerfilEstado(Usuario u){
		int value =0;
		DaoUsuario daoUs = new DaoUsuario(); 
		String estado = "f";
		if(u.getEstado()){
			estado = "t";
		}
		value = daoUs.modificarPerfilEstado(u.getLogin(), u.getTipo(), estado );
		return value;
	} 

	
	  public static void main(String args[])
	  { ControladorUsuario cu = new ControladorUsuario();
	 /* Date fechaRegistro = Date.valueOf("6666-06-06");
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
	 **/
	  //Usuario u = cu.consultarUsuario("clrl");
	  //System.out.print(u.getNombre1());
	 }
}
