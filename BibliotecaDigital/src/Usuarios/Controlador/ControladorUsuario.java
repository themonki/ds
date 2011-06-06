/**
 * ControladorUsuario.java
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con las consultas de los
 * usuarios de la biblioteca digital involucrando todas las clases en el
 * paquete y subpaquetes de Usuarios.
 * 
 * JAVA version "1.6.0"
 *  
 * Autor:  Cristian Leornando Rios 
 * Version:   4.0
 */





package Usuarios.Controlador;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Vector;
import javax.swing.JOptionPane;
import Consultas.Logica.Consulta;
import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Dao.DaoUsuario;
import Usuarios.Logica.Usuario;

/**
 * Clase que permite la comunicación entre la Clase {@link Consultas.Dao.DaoUsuario DaoUsuario} y las Clases
 * de aplicación: {@link Usuario.Gui.GuiRegistroModificar GuiRegistroModificar}, 
 * {@link Usuario.GuiAutentificar GuiAuntentificar},
 * {@link Usuario.Gui.GuiRecuperarPassword GuiRecuperarPassword},
 * {@link Usuario.Gui.GuiConsultarUsuarios GuiConsultarUsuarios} y {@link Usuario.Gui.GuiNovedades GuiNovedades} 
 * para el intercambio de datos.

 * @author Yerminson
 * 
 */
public class ControladorUsuario {
	/**
	 * Metodo que inserta los datos de un usuario en la tabla usuario,
	 * recibiendo atributo por atributo
	 * 
	 * @param login
	 *            - String con la llave del usuario
	 * @param contrasena
	 *            - String con la contraseña del usuario
	 * @param nom1
	 *            - String con el primer nombre del usuario
	 * @param nom2
	 *            - String con el segundo nombre del usuario
	 * @param apll1
	 *            - String con el primer apellido del usuario
	 * @param apll2
	 *            - String con el segundo apellido del usuario
	 * @param email
	 *            - String con el email del usuario
	 * @param nivel
	 *            - String con el nivel de escolaridad del usuario
	 * @param vinculo
	 *            - String con el vinculo de la universidad del usuario
	 * @param pregunta
	 *            - String con la pregunta secreta del usuario
	 * @param respuesta
	 *            - String con la respuesta a la pregunta secreta del usuario
	 * @param genero
	 *            - String con el genero del usuario
	 * @param registro
	 *            - String con la fecha de registro del usuario
	 * @param nacimiento
	 *            - String con la fecha de nacimiento del usuario
	 * @param tipo
	 *            - String con el tipo de usuario
	 * @param estado
	 *            - boolean con el estado (activo o inactivo) del usuario
	 * @return retorna 1 si se insertaron los datos del usuario en la base de
	 *         datos, -1 de ser lo contrario u
	 * @author
	 */
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
		u = null;
		return value;

	}

	/**
	 * Metodo que inserta los datos de un usuario en la tabla usuario,
	 * recibiendo un objeto usuario
	 * 
	 * @param u
	 *            - Usuario con todos los datos
	 * @return retorna 1 si se insertaron los datos del usuario en la base de
	 *         datos, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarUsuario(Usuario u) {
		u.setLogin(u.getLogin().toLowerCase());
		u.setNombre1(u.getNombre1().toLowerCase());
		u.setNombre2(u.getNombre2().toLowerCase());
		u.setApellido1(u.getApellido1().toLowerCase());
		u.setApellido2(u.getApellido2().toLowerCase());
		u.setEmail(u.getEmail().toLowerCase());

		DaoUsuario daoUs = new DaoUsuario();
		int value = 0;
		if (verificarDatosInsertar(u)) {
			value = daoUs.guardarUsuario(u);
		}
		daoUs = null;
		return value;
	}

	/**
	 * Metodo que permite verificar que se estan mandando los datos para
	 * registrar el usuario correctamente
	 * 
	 * @param u
	 *            - Usuario con los datos a verificar
	 * @return true si cumple con todo, de lo contrario false y muestra un
	 *         mensaje con los errores respectivos
	 * @author Maria Andrea Cruz
	 * @author Edgar Andres Moncada
	 */
	private boolean verificarDatosInsertar(Usuario u) {
		Vector<String> atributo = new Vector<String>();
		Vector<String> valor = new Vector<String>();
		DaoUsuario du = new DaoUsuario();
		Vector<Usuario> respuesta;
		Usuario uRespuesta;
		String mensaje = "";
		boolean estado = true;
		if (u.getLogin().equals("")) {
			mensaje += "Debe de proporcionar un Login\n";
			estado = false;
		}
		if (u.getContrasena().equals("")) {
			mensaje += "Debe de proporcionar una contrasena\n";
			estado = false;
		}
		if (u.getNombre1().equals("")) {
			mensaje += "Debe de proporcionar el primer nombre\n";
			estado = false;
		}
		if (u.getApellido1().equals("")) {
			mensaje += "Debe de proporcionar el primer apellido\n";
			estado = false;
		}
		if (u.getEmail().equals("")) {
			mensaje += "Debe de proporcionar un email\n";
			estado = false;
		}
		if (!u.getEmail().contains("@")) {
			mensaje += "Por favor digite un correo valido. ej: usuario@mail.com\n";
			estado = false;
		}
		if (u.getRespuestaSecreta().equals("")) {
			mensaje += "Debe de proporcionar una respuesta a la pregunta secreta\n";
			estado = false;
		}
		uRespuesta = du.consultarUsuario(u.getLogin());
		if (uRespuesta.getLogin() != null) {
			mensaje += "El Login ya existe, por favor seleccione uno distinto\n";
			estado = false;
		}
		atributo.add("email");
		valor.add(u.getEmail());
		respuesta = du.consultarUsuarios(atributo, valor);
		if (respuesta.size() != 0) {
			mensaje += "El email ya existe, por favor seleccione uno distinto\n";
			estado = false;
		}
		atributo = null;
		valor = null;
		du = null;
		respuesta = null;
		uRespuesta = null;
		if (!estado)
			JOptionPane.showMessageDialog(null, mensaje,
					"Error al Registrarse", JOptionPane.ERROR_MESSAGE);
		return estado;
	}

	/**
	 * Metodo que permite verificar que se estan mandando los datos para
	 * modificar el usuario correctamente
	 * 
	 * @param u
	 *            - Usuario con los datos a verificar
	 * @return true si cumple con todo, de lo contrario false y muestra un
	 *         mensaje con los errores respectivos
	 * @author Maria Andrea Cruz
	 * @author Edgar Andres Moncada
	 */
	private boolean verificarDatosModificar(Usuario u) {
		String mensaje = "";
		boolean estado = true;

		if (u.getContrasena().equals("")) {
			mensaje += "Debe de proporcionar una contrasena\n";
			estado = false;
		}
		if (u.getNombre1().equals("")) {
			mensaje += "Debe de proporcionar el primer nombre\n";
			estado = false;
		}
		if (u.getApellido1().equals("")) {
			mensaje += "Debe de proporcionar el primer apellido\n";
			estado = false;
		}
		if (u.getEmail().equals("")) {
			mensaje += "Debe de proporcionar un email\n";
			estado = false;
		}
		if (!u.getEmail().contains("@")) {
			mensaje += "Por favor digite un correo valido. ej: usuario@mail.com\n";
			estado = false;
		}
		if (u.getRespuestaSecreta().equals("")) {
			mensaje += "Debe de proporcionar una respuesta a la pregunta secreta\n";
			estado = false;
		}
		if (!estado)
			JOptionPane.showMessageDialog(null, mensaje, "Error al Modificar",
					JOptionPane.ERROR_MESSAGE);
		return estado;
	}

	/**
	 * Metodo que modifica los datos en la tabla Usuario recibiendo atributo por
	 * atributo
	 * 
	 * @param login
	 *            - String con la llave del usuario
	 * @param contrasena
	 *            - String con la contraseña del usuario
	 * @param nom1
	 *            - String con el primer nombre del usuario
	 * @param nom2
	 *            - String con el segundo nombre del usuario
	 * @param apll1
	 *            - String con el primer apellido del usuario
	 * @param apll2
	 *            - String con el segundo apellido del usuario
	 * @param email
	 *            - String con el email del usuario
	 * @param nivel
	 *            - String con el nivel de escolaridad del usuario
	 * @param vinculo
	 *            - String con el vinculo de la universidad del usuario
	 * @param pregunta
	 *            - String con la pregunta secreta del usuario
	 * @param respuesta
	 *            - String con la respuesta a la pregunta secreta del usuario
	 * @param genero
	 *            - String con el genero del usuario
	 * @param registro
	 *            - String con la fecha de registro del usuario
	 * @param nacimiento
	 *            - String con la fecha de nacimiento del usuario
	 * @param tipo
	 *            - String con el tipo de usuario
	 * @param estado
	 *            - boolean con el estado (activo o inactivo) del usuario
	 * @return retorna 1 si se modificaron los datos del usuario en la base de
	 *         datos, -1 de ser lo contrario
	 */
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
		u = null;
		return value;
	}

	/**
	 * Metodo que modifica los datos en la tabla Usuario recibiendo un objeto
	 * Usuario
	 * 
	 * @param u
	 *            - Usuario con los datos a modificar
	 * @return retorna 1 si se modificaron los datos del usuario en la base de
	 *         datos, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int modificarUsuario(Usuario u) {

		u.setLogin(u.getLogin().toLowerCase());
		u.setNombre1(u.getNombre1().toLowerCase());
		u.setNombre2(u.getNombre2().toLowerCase());
		u.setApellido1(u.getApellido1().toLowerCase());
		u.setApellido2(u.getApellido2().toLowerCase());
		u.setEmail(u.getEmail().toLowerCase());

		DaoUsuario daoUs = new DaoUsuario();
		int value = 0;
		if (verificarDatosModificar(u)) {
			value = daoUs.modificarUsuario(u);
			System.out.println("Se modifico el usuario");
		}
		daoUs = null;
		return value;
	}

	/**
	 * Metodo que permite insertar las areas a las que le interesa el usuario,
	 * se necesita un objeto Usuario que tenga como minimo el login y el vector
	 * con areas de conocimiento que contengan los ids de las areas respectivas
	 * 
	 * @param u
	 *            - Usuario con la llave y areas de conocimiento
	 * @return -1 sino se insertaron las areas de interes, de lo contrario
	 *         retorna el numero de areas de interes que se insertaron
	 * @author Edgar Andres Moncada
	 */
	public int insertarUsuarioAreas(Usuario u) {
		Vector<AreaConocimiento> va = u.getAreas();
		String login = u.getLogin();
		DaoUsuario daoUs = new DaoUsuario();
		int value = daoUs.insertarUsuarioAreas(login, va);
		daoUs = null;
		return value;
	}

	/**
	 * Metodo que devuelve todas las areas de interes de un usuario
	 * 
	 * @param login
	 *            - String con la llave del usuario
	 * @return Vector<AreaConocimiento> con las areas de conocimiento del
	 *         usuario
	 * @author Edgar Andres Moncada
	 */
	public Vector<AreaConocimiento> obtenerUsuarioAreas(String login) {
		Vector<AreaConocimiento> areas;
		DaoUsuario daoUs = new DaoUsuario();
		areas = daoUs.consultarUsuarioAreas(login);
		daoUs = null;
		return areas;
	}

	/**
	 * Metodo que actualiza las areas de interes del usuario
	 * 
	 * @param u
	 *            - Usuario con las areas de interes y el login
	 * @return 0 si el usuario ya no tiene areas de interes, de lo contrario
	 *         retorna el numero de las areas de interes del usuario
	 * @author Edgar Andres Moncada
	 */
	public int modificarUsuarioArea(Usuario u) {
		DaoUsuario daoUs = new DaoUsuario();
		Vector<AreaConocimiento> areasNuevas = u.getAreas();
		/*
		 * se insertan las areas nuevas, si por alguna razon se agrego, se quito
		 * entonces se borra despues, si se agrego y se quito
		 */
		int value = 0;
		// se quitan todas las areas que hagan referencia a ese login
		daoUs.quitarUsuarioAreas(u.getLogin());

		// inserta las areas a las que ahora hace referencia
		if (u.getAreas().size() >= 1)
			value += daoUs.insertarUsuarioAreas(u.getLogin(), areasNuevas);

		return value;
	}

	/**
	 * Metodo que retorna un vector con todos los usuarios que concidan con
	 * alguno de los atributos
	 * 
	 * @param atributo
	 *            - Vector<String> con
	 * @param valor
	 *            - Vector<String> con
	 * @return Vector<Usuario> con los usuarios que coincidieron con el
	 *         parametro de busqueda
	 * @author Cristian Leonardo Rios
	 */
	public Vector<Usuario> consultarUsuarios(Vector<String> atributo,
			Vector<String> valor) {
		for (int i = 0; i < valor.size(); i++) {
			valor.elementAt(i).toLowerCase();
		}

		DaoUsuario daoUsuario = new DaoUsuario();
		Vector<Usuario> usuarios = daoUsuario
				.consultarUsuarios(atributo, valor);
		daoUsuario = null;
		return usuarios;
	}

	/**
	 * Metodo que retorma un usuario que concida con el login
	 * 
	 * @param login
	 *            - String con la llave del usuario
	 * @return el Usuario con sus datos que coincido con el login
	 * @author Cristian Leonardo Rios
	 */
	public Usuario consultarUsuario(String login) {
		login.toLowerCase();
		DaoUsuario daoUsuario = new DaoUsuario();
		Usuario usuario = daoUsuario.consultarUsuario(login);
		daoUsuario = null;
		return usuario;
	}

	/**
	 * Metodo que sirve para actualizar todos los datos del usuario y sus areas,
	 * recibe un objeto usuario
	 * 
	 * @param u
	 *            - Usuario con todos los datos a modificar
	 * @return int mayor que uno si se modificaron los datos del usuario
	 *         correctamente
	 * @author Edgar Andres Moncada
	 */
	public int modificarDatosUsuario(Usuario u) {
		int value = 0;
		value = this.modificarUsuario(u);
		if (value != 0)
			value += this.modificarUsuarioArea(u);
		return value;
	}

	/**
	 * Metodo que sirve para registar los datos del usuario y sus areas, recibe
	 * un objeto un usuario
	 * 
	 * @param u
	 *            - Usuario con todos los datos
	 * @return int mayor que uno si se inserto correctamente
	 * @author Edgar Andres Moncada
	 */
	public int insertarDatosUsuario(Usuario u) {
		int value = 0;
		value = this.insertarUsuario(u);
		if ((value != 0) && (u.getAreas().size() >= 1))
			value += this.insertarUsuarioAreas(u);
		return value;
	}

	/**
	 * Metodo que permite modificar el perfil y el estado del Usuario (realizado
	 * por el administrador)
	 * 
	 * @param u
	 *            - Usuario con el nuevo perfil y estado
	 * @return 1 si se modifico correctamente -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int modificarPerfilEstado(Usuario u) {
		int value = 0;
		DaoUsuario daoUs = new DaoUsuario();
		String estado = "f";
		if (u.getEstado()) {
			estado = "t";
		}
		value = daoUs.modificarPerfilEstado(u.getLogin(), u.getTipo(), estado);
		return value;
	}

	/**
	 * Metodo que permite que se envie el login del usuario para actualizar su
	 * ultimo acceso al sistema
	 * 
	 * @param login
	 *            - String con la llave del usuario
	 * @return 1 si fue correcta la actualizacion y -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int actualizarAccesoUsuario(String login) {
		DaoUsuario daoUs = new DaoUsuario();
		java.util.Date fecha = new java.util.Date();
		SimpleDateFormat formatoFecha = new SimpleDateFormat("yyyy-MM-dd");
		String fechaString = formatoFecha.format(fecha); // la fecha en que
															// accedio al
															// sistema
		return daoUs.actualizarFechaAcceso(login, fechaString);
	}

	/**
	 * Metodo que recive el login de usuario y devuelve un vector de objetos
	 * consulta correspondientes a los documentos catalogados recientemente que
	 * pueden ser de su interes.
	 * 
	 * @param login
	 * @return
	 */
	public Vector<Consulta> consultaDocumentosInteresArea(String login) {

		login = login.toLowerCase();

		DaoUsuario consulta = new DaoUsuario();
		Vector<Consulta> resultado = consulta
				.consultaDocumentosInteresUsuario(login);

		return resultado;
	}
}
