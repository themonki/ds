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


	//Constructor por defecto.
	public Usuario(){}
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
	int tipo;
	boolean estado;
	Vector<AreaConocimiento> areasInteres;
	
	// Constructor.
	public Usuario(String login, String contrasena, String nombre1,
			String nombre2, String apellido1, String apellido2, String email,
			String nivelEscolaridad, String vinculoUnivalle,
			String preguntaSecreta, String respuestaSecreta, String genero,
			Date fechaRegistro, Date fechaNacimiento, int tipo, boolean estado,
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

	
	String getLogin(){return login;}
	String getContrasena(){ return contrasena;}
	String getNombre1(){ return nombre1; }
	String getNombre2(){ return nombre2; }
	String getApellido1(){ return apellido1; }
	String getApellido2(){ return apellido2; }
	String getEmail(){ return email; }
    String getNivelEscolaridad(){ return nivelEscolaridad;}
    String getVinculoUnivalle(){ return vinculoUnivalle;}
    String getPreguntaSecreta(){ return preguntaSecreta;}
    String getRespuestaSecreta(){ return preguntaSecreta;}
    String getGenero(){return genero;}
    Date getFechaRegistro(){return fechaRegistro;}
    Date getFechaNacimiento(){return fechaNacimiento;}
    int getTipo(){return tipo;}
    boolean getEstado() {return estado;}
    Vector<AreaConocimiento> getAreas(){ return areasInteres;}
    
    void setLogin(String value){}
	void setContrasena(String value){contrasena = value;}
	void setNombre1(String value){ nombre1=value;}
	void setNombre2(String value){ nombre2=value;}
	void setApellido1(String value){apellido1=value;}
	void setApellido2(String value){apellido2=value;}
	void setEmail(String value){email = value;}
	void setNivelEscolaridad(String value){nivelEscolaridad=value;}
	void setVinculoUnivalle(String value){vinculoUnivalle = value;} 
	void setPreguntaSecreta(String value){ preguntaSecreta = value;}
	void setRespuestaSecreta(String value){ respuestaSecreta = value;}
	void setGenero(String value){genero=value;}
	void setFechaRegistro(Date value){fechaRegistro = value;}
	void etFechaNacimiento(Date value){fechaNacimiento = value; }
	void setTipo(int value){tipo = value;}
	void setEstado(boolean value){estado = value;}
	void setAreas(Vector<AreaConocimiento> value){areasInteres = value;}

}
