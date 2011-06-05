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
 * Una clase que permite representar un usuario de una biblioteca digital.
 * 
 * @version 1.0 24/04/2011
 * @author Yerminson Gonzalez
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
	 * @param vinculoConUnivalle
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
	 * @return el Login del usuario
	 */
	public String getLogin() {
		return login;
	}

	/**
	 * @return
	 */
	public String getContrasena() {
		return contrasena;
	}

	/**
	 * @return
	 */
	public String getNombre1() {
		return nombre1;
	}

	/**
	 * @return
	 */
	public String getNombre2() {
		return nombre2;
	}

	/**
	 * @return
	 */
	public String getApellido1() {
		return apellido1;
	}

	/**
	 * @return
	 */
	public String getApellido2() {
		return apellido2;
	}

	/**
	 * @return
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @return
	 */
	public String getNivelEscolaridad() {
		return nivelEscolaridad;
	}

	/**
	 * @return
	 */
	public String getVinculoUnivalle() {
		return vinculoUnivalle;
	}

	/**
	 * @return
	 */
	public String getPreguntaSecreta() {
		return preguntaSecreta;
	}

	/**
	 * @return
	 */
	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}

	/**
	 * @return
	 */
	public String getGenero() {
		return genero;
	}

	/**
	 * @return
	 */
	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * @return
	 */
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	/**
	 * @return
	 */
	public Date getFechaUltimoAcceso() {
		return fechaUltimoAcceso;
	}

	/**
	 * @return
	 */
	public String getTipo() {
		return tipo;
	}

	/**
	 * @return
	 */
	public boolean getEstado() {
		return estado;
	}

	/**
	 * @return
	 */
	public Vector<AreaConocimiento> getAreas() {
		return areasInteres;
	}

	/**
	 * @param value
	 */
	public void setLogin(String value) {
		login = value;
	}

	/**
	 * @param value
	 */
	public void setContrasena(String value) {
		contrasena = value;
	}

	/**
	 * @param value
	 */
	public void setNombre1(String value) {
		nombre1 = value;
	}

	/**
	 * @param value
	 */
	public void setNombre2(String value) {
		nombre2 = value;
	}

	/**
	 * @param value
	 */
	public void setApellido1(String value) {
		apellido1 = value;
	}

	/**
	 * @param value
	 */
	public void setApellido2(String value) {
		apellido2 = value;
	}

	/**
	 * @param value
	 */
	public void setEmail(String value) {
		email = value;
	}

	/**
	 * @param value
	 */
	public void setNivelEscolaridad(String value) {
		nivelEscolaridad = value;
	}

	/**
	 * @param value
	 */
	public void setVinculoUnivalle(String value) {
		vinculoUnivalle = value;
	}

	/**
	 * @param value
	 */
	public void setPreguntaSecreta(String value) {
		preguntaSecreta = value;
	}

	/**
	 * @param value
	 */
	public void setRespuestaSecreta(String value) {
		respuestaSecreta = value;
	}

	/**
	 * @param value
	 */
	public void setGenero(String value) {
		genero = value;
	}

	/**
	 * @param value
	 */
	public void setFechaRegistro(Date value) {
		fechaRegistro = value;
	}

	/**
	 * @param value
	 */
	public void setFechaNacimiento(Date value) {
		fechaNacimiento = value;
	}

	/**
	 * @param value
	 */
	public void setFechaUltimoAcceso(Date value) {
		fechaUltimoAcceso = value;
	}

	/**
	 * @param value
	 */
	public void setTipo(String value) {
		tipo = value;
	}

	/**
	 * @param value
	 */
	public void setEstado(boolean value) {
		estado = value;
	}

	/**
	 * @param value
	 */
	public void setAreas(Vector<AreaConocimiento> value) {
		areasInteres = value;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		String usuario;
		usuario = login + " " + nombre1;
		return usuario;
	}

}
