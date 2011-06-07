/**
 * Usuario.java
 * 
 * Clase que representa un Usuario es decir una persona que puede
 * hacer uso de la biblioteca digital,por lo tanto, es importante 
 * guardar sus atributos informacion que nos permita disitinguirlos
 * de otros usuarios y asi mismo manejar su ingreso al sistema 
 * adecuadamente.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  Yerminson Gonzalez Munoz
 * Version:   4.0
 */

package Usuarios.Logica;

import java.sql.Date;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;

/**
 * @author Yerminson Gonzalez Munoz
 * 
 */
public class Usuario {

	/**
	 * Constructor por defecto sin parametros.
	 */
	public Usuario() {
	}

	/**
	 * String que contiene la llave del Usuario
	 */
	String login;
	/**
	 * String que contiene la contraseña del Usuario
	 */
	String contrasena;
	/**
	 * String que contiene el primer nombre del Usuario
	 */
	String nombre1;
	/**
	 * String que contiene el segundo nombre del Usuario
	 */
	String nombre2;
	/**
	 * String que contiene el primer apellido del Usuario
	 */
	String apellido1;
	/**
	 * String que contiene el segundo apellido del Usuario
	 */
	String apellido2;
	/**
	 * String que contiene el email del Usuario
	 */
	String email;
	/**
	 * String que contiene el nivel de escolaridad del Usuario
	 */
	String nivelEscolaridad;
	/**
	 * String que contiene el vinculo con la universidad del Usuario
	 */
	String vinculoUnivalle;
	/**
	 * String que contiene la pregunta secreta del Usuario
	 */
	String preguntaSecreta;
	/**
	 * String que contiene la respuesta a la pregunta secreta del Usuario
	 */
	String respuestaSecreta;
	/**
	 * String que contiene el genero del Usuario
	 */
	String genero;
	/**
	 * Date que contiene la fecha de registro del Usuario
	 */
	Date fechaRegistro;
	/**
	 * Date que contiene la fecha de Nacimiento del Usuario
	 */
	Date fechaNacimiento;
	/**
	 * Date que contiene la fecha del ultimo acceso del Usuario
	 */
	Date fechaUltimoAcceso;
	/**
	 * String que contiene el tipo del Usuario
	 */
	String tipo;
	/**
	 * boolean que contiene el estado (activo o inactivo) del Usuario
	 */
	boolean estado;
	/**
	 * Vector<AreaConocimiento> que contiene las areas de interes del Usuario
	 */
	Vector<AreaConocimiento> areasInteres;

	/**
	 * Crea un Usuario a partir de sus atributos basicos.
	 * 
	 * @param login
	 *            - String con el login de acceso del usuario.
	 * @param contrasena
	 *            - String con la clave que le permite el ingreso al sistema.
	 * @param nombre1
	 *            - String con el primer nombre del usuario.
	 * @param nombre2
	 *            - String con el segundo nombre del usuario.
	 * @param apellido1
	 *            - String con el primer apellido del usuario.
	 * @param apellido2
	 *            - String con el segundo apellido del usuario.
	 * @param email
	 *            - String con el correo electronico del usuario del usuario.
	 * @param nivelEscolaridad
	 *            - String con el nivel de estudios del usuario.
	 * @param vinculoUnivalle
	 *            - String con la relacion con la Universidad del valle que
	 *            tiene el usuario.
	 * @param preguntaSecreta
	 *            - String con la pregunta que permitirar recuperar la clave del
	 *            usuario.
	 * @param respuestaSecreta
	 *            - String con la respuesta a la pregunta secreta del usuario.
	 * @param genero
	 *            - String con el genero del usuario.
	 * @param fechaRegistro
	 *            - Date con la fecha en la que se registro el usuario al
	 *            sistema.
	 * @param fechaNacimiento
	 *            - Date con la fecha en la que nacio el usuario
	 * @param fechaUltimoAcceso
	 *            - Date con la fecha de ultimo ingreso al sistema
	 * @param tipo
	 *            - String con el valor que indica el tipo de usuario: Usuario
	 *            Normal, Catalogador o Administrador.
	 * @param estado
	 *            - boolean que indica si esta habilitado para el ingreso al
	 *            sistema
	 * 
	 */

	public Usuario(String login, String contrasena, String nombre1,
			String nombre2, String apellido1, String apellido2, String email,
			String nivelEscolaridad, String vinculoUnivalle,
			String preguntaSecreta, String respuestaSecreta, String genero,
			Date fechaRegistro, Date fechaNacimiento, Date fechaUltimoAcceso,
			String tipo, boolean estado, Vector<AreaConocimiento> areasInteres) {

		this.login = login;
		this.contrasena = contrasena;
		this.nombre1 = nombre1;
		this.nombre2 = nombre2;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.email = email;
		this.nivelEscolaridad = nivelEscolaridad;
		this.vinculoUnivalle = vinculoUnivalle;
		this.preguntaSecreta = preguntaSecreta;
		this.respuestaSecreta = respuestaSecreta;
		this.genero = genero;
		this.fechaRegistro = fechaRegistro;
		this.fechaNacimiento = fechaNacimiento;
		this.fechaUltimoAcceso = fechaUltimoAcceso;
		this.tipo = tipo;
		this.estado = estado;
		this.areasInteres = areasInteres;
	}

	/**
	 * Metodo que permite obtener el login de usuario
	 * 
	 * @return login 
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * Metodo que permite obtener la contrasena de un usuario
	 * @return contrasena
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * Metodo que permite obtener el primer nombre de un usuario
	 * @return nombre1
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * Metodo que permite obtener el segundo nombre de un usuario
	 * @return nombre2
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * Metodo que permite obtener el primer apellido de un usuario
	 * @return apellido1
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * Metodo que permite obtener el segundo apellido de un usuario
	 * @return apellido2
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * Metodo que permite obtener el correo de un usuario
	 * @return email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Metodo que permite obtener el nivel de escolaridad de un usuario
	 * @return nivelEscolaridad
	 */
	public String getNivelEscolaridad() {
		return nivelEscolaridad;
	}

	/**
	 * Metodo que permite obtener el vinculo univalle de un usuario
	 * @return vinculoUnivalle
	 */
	public String getVinculoUnivalle() {
		return vinculoUnivalle;
	}

	/**
	 * Metodo que permite obtener la pregunta secreta de un usuario
	 * @return preguntaSecreta
	 */
	public String getPreguntaSecreta() {
		return preguntaSecreta;
	}

	/**
	 * Metodo que permite obtener la respuesta secreta de un usuario
	 * @return respuestaSecreta
	 */
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}

	/**
	 * Metodo que permite obtener el genero de un usuario
	 * @return genero
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * Metodo que permite obtener la fecha de registro un usuario
	 * @return fechaRegistro
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Metodo que permite obtener la fecha de nacimiento de un usuario
	 * @return fechaNacimiento
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * Metodo que permite obtener la fecha de ultimo acceso de un usuario
	 * @return fechaUltimoAcceso
	 */
	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	/**
	 * Metodo que permite obtener el tipo de un usuario
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * Metodo que permite obtener el estado de un usuario
	 * @return estado
	 */
	public boolean getEstado() {
		return estado;
	}

	/**
	 * Metodo que permite obtener las areas de interes de un usuario
	 * @return areasInteres
	 */
	public Vector<AreaConocimiento> getAreas() {
		return areasInteres;
	}

	/**
	 * Metodo que permite cambiar el login de un usuario
	 * @param value
	 */
	public void setLogin(String value) {
		login = value;
	}

	/**
	 * Metodo que permite cambiar la  contrasena de un usuario
	 * @param value
	 */
	public void setContrasena(String value) {
		contrasena = value;
	}

	/**
	 * Metodo que permite cambiar el primer nombre de un usuario
	 * @param value
	 */
	public void setNombre1(String value) {
		nombre1 = value;
	}

	/**
	 * Metodo que permite cambiar el segundo nombre de un usuario
	 *   
	 * @param value
	 */
	public void setNombre2(String value) {
		nombre2 = value;
	}

	/**
	 * 
	 *  Metodo que permite cambiar el primer apellido de un usuario
	 * @param value
	 */
	public void setApellido1(String value) {
		apellido1 = value;
	}

	/**
	 * Metodo que permite cambiar el segundo apellido de un usuario
	 * @param value
	 */
	public void setApellido2(String value) {
		apellido2 = value;
	}

	/**
	 *  Metodo que permite cambiar el correo de un usuario
	 * @param value
	 */
	public void setEmail(String value) {
		email = value;
	}

	/**
	 * Metodo que permite cambiar el nivel de escolaridad de un usuario
	 * @param value
	 */
	public void setNivelEscolaridad(String value) {
		nivelEscolaridad = value;
	}

	/**
	 *  Metodo que permite cambiar el vinculo con univalle de un usuario
	 * @param value
	 */
	public void setVinculoUnivalle(String value) {
		vinculoUnivalle = value;
	}

	/**
	 *  Metodo que permite cambiar la pregunta secreta de un usuario
	 * @param value
	 */
	public void setPreguntaSecreta(String value) {
		preguntaSecreta = value;
	}

	/**
	 *  Metodo que permite cambiar el respuesta secreta de un usuario
	 * @param value
	 */
	public void setRespuestaSecreta(String value) {
		respuestaSecreta = value;
	}

	/**
	 *  Metodo que permite cambiar el genero de un usuario
	 * @param value
	 */
	public void setGenero(String value) {
		genero = value;
	}

	/**
	 *  Metodo que permite cambiar el fecha de registro de un usuario
	 * @param value
	 */
	public void setFechaRegistro(Date value) {
		fechaRegistro = value;
	}

	/**
	 *  Metodo que permite cambiar la fecha de nacimiento de un usuario
	 * @param value
	 */
	public void setFechaNacimiento(Date value) {
		fechaNacimiento = value;
	}

	/**
	 *  Metodo que permite cambiar el fecha ultimo acceso de un usuario
	 * @param value
	 */
	public void setFechaUltimoAcceso(Date value) {
		fechaUltimoAcceso = value;
	}

	/**
	 *  Metodo que permite cambiar el tipo de un usuario
	 * @param value
	 */
	public void setTipo(String value) {
		tipo = value;
	}

	/**
	 *  Metodo que permite cambiar el estado de un usuario
	 * @param value
	 */
	public void setEstado(boolean value) {
		estado = value;
	}

	/**
	 *  Metodo que permite cambiar las areas de interes de un usuario
	 * @param value
	 */
	public void setAreas(Vector<AreaConocimiento> value) {
		areasInteres = value;
	}

	/**
	 * Metodo sobreescrito para mostrar el resultado de la consulta de usuarios en 
	 * {@link Usuarios.Gui.GuiConsultarUsuarios GuiConsultarUsuarios}
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String usuario;
		usuario = login + " " + nombre1;
		return usuario;
	}

}
