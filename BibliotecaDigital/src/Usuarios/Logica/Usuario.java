package Usuarios.Logica;

/**
 * Una clase que permite representar un usuario de una biblioteca digital.
 * 
 * @version 1.0 24/04/2011
 * @author Yerminson Gonzalez
 */

import java.sql.Date;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;


public class Usuario {

	 /** 
     * Constructor por defecto sin parametros.
     */

	public Usuario() {
	}

	String login;
	String contrasena;
	String nombre1;
	String nombre2;
	String apellido1;
	String apellido2;
	String email;
	String nivelEscolaridad;
	String vinculoUnivalle;
	String preguntaSecreta;
	String respuestaSecreta;
	String genero;
	Date fechaRegistro;
	Date fechaNacimiento;
	String tipo;
	boolean estado;
	Vector<AreaConocimiento> areasInteres;

	  /** 
     * Crea un Usuario a partir de sus atributos basicos.
     * @param login Login de acceso del usuario.
     * @param contrasena Clave que le permite el ingreso al sistema.
     * @param nombre1 Primer nombre del usuario.
     * @param nombre2 Segundo nombre del usuario.
     * @param apellido1 Primer apellido del usuario.
     * @param apellido2 Segundo apellido del usuario.
     * @param email Correo electronico del usuario del usuario.
     * @param nivelEscolaridad Nivel de estudios del usuario.
     * @param vinculoConUnivalle Relacion con la Universidad del valle que tiene  el usuario.
     * @param preguntaSecreta Pregunta que permitirar recuperar la clave del usuario.
     * @param respuestaSecreta Respuesta a la pregunta secreta del usuario.
     * @param genero Genero del usuario.
     * @param fechaRegistro Fecha en la que se registro el usuario al sistema.
     * @param fechaNacimiento Fecha en la que nacio el usuario
     * @param tipo Valor que indica el tipo de usuario: Usuario Normal, Catalogador o Administrador.
     * @param estado Indica si esta habilitado para el ingreso al sistema
     * 
     */

	public Usuario(String login, String contrasena, String nombre1,
			String nombre2, String apellido1, String apellido2, String email,
			String nivelEscolaridad, String vinculoUnivalle,
			String preguntaSecreta, String respuestaSecreta, String genero,
			Date fechaRegistro, Date fechaNacimiento, String tipo, boolean estado,
			Vector<AreaConocimiento> areasInteres) {

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
		this.tipo = tipo;
		this.estado = estado;
		this.areasInteres = areasInteres;
	}

	/**
	 * Metodo que permite obtener el login de usuario
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
	public void setLogin(String value)
	{
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
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String usuario;
		usuario = login + " " + nombre1;
		return usuario;
	}

}
