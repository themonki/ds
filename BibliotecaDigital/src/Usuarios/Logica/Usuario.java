package Usuarios.Logica;

/*
 * 
 * Nombre: Yerminson Gonzalez Munoz
 * Responsalidad: Clase que representa a un usuario.
 * 
 * */
import java.sql.Date;
import java.util.Vector;

import GestionDocumento.Logica.AreaConocimiento;

public class Usuario {

	// Constructor por defecto.
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

	// Constructor.
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
