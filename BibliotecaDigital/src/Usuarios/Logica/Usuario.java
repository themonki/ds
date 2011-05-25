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
	 * Devuelve el Login del usuario
	 * @return login 
	 */
	public String getLogin() {
		return login;
	}

	public String getContrasena() {
		return contrasena;
	}

	public String getNombre1() {
		return nombre1;
	}

	public String getNombre2() {
		return nombre2;
	}

	public String getApellido1() {
		return apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public String getEmail() {
		return email;
	}

	public String getNivelEscolaridad() {
		return nivelEscolaridad;
	}

	public String getVinculoUnivalle() {
		return vinculoUnivalle;
	}

	public String getPreguntaSecreta() {
		return preguntaSecreta;
	}

	public String getRespuestaSecreta() {
		return respuestaSecreta;
	}

	public String getGenero() {
		return genero;
	}

	public Date getFechaRegistro() {
		return fechaRegistro;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public String getTipo() {
		return tipo;
	}

	public boolean getEstado() {
		return estado;
	}

	public Vector<AreaConocimiento> getAreas() {
		return areasInteres;
	}

	public void setLogin(String value)
	{
		login = value;
	}

	public void setContrasena(String value) {
		contrasena = value;
	}

	public void setNombre1(String value) {
		nombre1 = value;
	}

	public void setNombre2(String value) {
		nombre2 = value;
	}

	public void setApellido1(String value) {
		apellido1 = value;
	}

	public void setApellido2(String value) {
		apellido2 = value;
	}

	public void setEmail(String value) {
		email = value;
	}

	public void setNivelEscolaridad(String value) {
		nivelEscolaridad = value;
	}

	public void setVinculoUnivalle(String value) {
		vinculoUnivalle = value;
	}

	public void setPreguntaSecreta(String value) {
		preguntaSecreta = value;
	}

	public void setRespuestaSecreta(String value) {
		respuestaSecreta = value;
	}

	public void setGenero(String value) {
		genero = value;
	}

	public void setFechaRegistro(Date value) {
		fechaRegistro = value;
	}

	public void setFechaNacimiento(Date value) {
		fechaNacimiento = value;
	}

	public void setTipo(String value) {
		tipo = value;
	}

	public void setEstado(boolean value) {
		estado = value;
	}

	public void setAreas(Vector<AreaConocimiento> value) {
		areasInteres = value;
	}
	
	public String toString(){
		String usuario;
		usuario = login + " " + nombre1;
		return usuario;
	}

}
