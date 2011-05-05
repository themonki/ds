package Usuarios.Controlador;

import java.sql.Date;
import java.util.Vector;

import Usuarios.Dao.DaoUsuario;
import Usuarios.Logica.Usuario;
import GestionDocumento.Logica.AreaConocimiento;

public class ControladorUsuario {
	
	public void insertarUsuario(String login, String contrasena, String nom1, String nom2, String apll1,
			String apll2, String email, String nivel, String vinculo, String pregunta, String respuesta,
			String genero, String registro, String nacimiento, int tipo, boolean estado){
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
        Date f_registro = Date.valueOf(registro); //formato yyyy-mm-dd
        u.setFechaRegistro(f_registro);
        Date f_nacimiento = Date.valueOf(nacimiento); //formato yyyy-mm-dd
        u.setFechaNacimiento(f_nacimiento);
        u.setTipo(tipo);
        u.setEstado(estado);
        
        insertarUsuario(u);
        

        //por seguridad
        u=null;       

    }
	
	public void insertarUsuario(Usuario u){
		DaoUsuario daoUs=new DaoUsuario();
		daoUs.guardarUsuario(u);
		
		System.out.println("Se inserto el usuario");
		daoUs=null;
		
	}
	
	public void modificarDocumento(String login, String contrasena, String nom1, String nom2, String apll1,
			String apll2, String email, String nivel, String vinculo, String pregunta, String respuesta,
			String genero, String registro, String nacimiento, int tipo, boolean estado){
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
        Date f_registro = Date.valueOf(registro); //formato yyyy-mm-dd
        u.setFechaRegistro(f_registro);
        Date f_nacimiento = Date.valueOf(nacimiento); //formato yyyy-mm-dd
        u.setFechaNacimiento(f_nacimiento);
        u.setTipo(tipo);
        u.setEstado(estado);
        
        modificarUsuario(u);

        //por seguridad		
        u=null;
	}

	public void modificarUsuario(Usuario u){
		DaoUsuario daoUs=new DaoUsuario();
		daoUs.guardarUsuario(u);
		
		System.out.println("Se inserto el usuario");
		daoUs=null;
		
	}
	/*METODO QUE PERMITE INSERTAR LAS AREAS A LA Q LE INTERESA EL USUARIO
	 * 
	 * */
	public void insertarUsuarioAreas(Vector <AreaConocimiento> va, Usuario u){
		
		int cantidad = va.size();
		String login = u.getLogin();
		DaoUsuario daoUs=new DaoUsuario();
		daoUs.insertarUsuarioAreas(login,va, cantidad );
		System.out.println("Se inserto las areas del usuario");
		daoUs=null;
	}

}
