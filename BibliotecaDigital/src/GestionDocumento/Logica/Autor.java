package GestionDocumento.Logica;
/*
 * Nombre: Yerminson Gonzalez Munoz
 * Responsabilidad: Clase que respresenta un autor.
 * 
 * */
public class Autor {
	

	String nombre;
	String apellido;
	String acronimo;
	String correo;
	String id;
	
	//Constructor
	public Autor(String nombre, String apellido, String acronimo,
			String correo, String id) {
		
		this.nombre = nombre;
		this.apellido = apellido;
		this.acronimo = acronimo;
		this.correo = correo;
		this.id = id;
	}
	String getNombre(){return nombre;}
	String apellido(){return apellido;}
	String getAcronimo(){return acronimo;}
	String getCorreo(){return correo;}
	String getId(){ return id;}
	

}