/**
 * ControladorVentanaPrincipal
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con las Interfaces de usuario
 * de la biblioteca digital involucrando todas las clases en el
 * paquete y subpaquetes de Principal.
 * 
 * JAVA version "1.6.0"
 *  
 * Autor:  Cristian Leornando Rios 
 * Version:   4.0
 */



package Principal.Controlador;


import java.util.Vector;

import javax.swing.JOptionPane;

import Consultas.Logica.Consulta;
import Principal.Gui.GuiAdministrador;
import Principal.Gui.GuiCatalogador;
import Principal.Gui.GuiUsuarioNormal;
import Usuarios.Controlador.ControladorUsuario;
import Usuarios.Logica.Usuario;

public class ControladorVentanaPrincipal {
	
	private int error =0; //indica que tipo de exception no permite ingresar al sistema.
	// y se usa para hacer foco y seleccionar el campo que no permit entrar.
	// 1 login incorrecto, 2 contrasena incorrecta. 0 valor por defecto no significa un error.

	
	public boolean verificarUsuario(String login, String password)
	{
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		Usuario usuario = controladorUsuario.consultarUsuario(login);
		boolean respuesta = false;
		
		
				
		if(login.equals(""))
		{	
			
			JOptionPane.showMessageDialog(null, "El campo login esta vacio");
			
			
		} else if(password.equals(""))
		{
			JOptionPane.showMessageDialog(null, "El campo password esta vacio");
			
		} else if (usuario.getNombre1() == null)
		{
			JOptionPane.showMessageDialog(null, "Login de usuario incorrecto");
			error = 1; 
			
			
		}else if(usuario.getEstado()==false){ //usuario Desactivado.
			JOptionPane.showMessageDialog(null, "USUARIO DESACTIVADO\nComuniquese con el administrador\n para ser activado de nuevo.", "Usuario Desactivado", JOptionPane.WARNING_MESSAGE);
			
		}
		else if(password.equals(usuario.getContrasena()))
		{
			if(usuario.getTipo().equals("1"))
			{
				GuiAdministrador guiAdministrador = new GuiAdministrador(usuario);
				guiAdministrador.setNovededadesUsuario(controladorUsuario.consultaDocumentosInteresArea(usuario.getLogin()));
				controladorUsuario.actualizarAccesoUsuario(usuario.getLogin());
				//guiAdministrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}else if(usuario.getTipo().equals("2"))
			{
				GuiCatalogador guiCatalogador = new GuiCatalogador(usuario);
				guiCatalogador.setNovededadesUsuario(controladorUsuario.consultaDocumentosInteresArea(usuario.getLogin()));
				controladorUsuario.actualizarAccesoUsuario(usuario.getLogin());
				//guiCatalogador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}else if(usuario.getTipo().equals("3"))
			{
				
				GuiUsuarioNormal guiUsuarioNormal = new GuiUsuarioNormal(usuario);
				Vector<Consulta> documentos = controladorUsuario.consultaDocumentosInteresArea(usuario.getLogin());
				
				guiUsuarioNormal.setNovededadesUsuario(documentos);
				controladorUsuario.actualizarAccesoUsuario(usuario.getLogin());
				//guiUsuarioNormal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
			respuesta = true;
			
		}
		else
		{
		
			JOptionPane.showMessageDialog(null, "La contraseña no es valida para el usuario: "+usuario.getLogin());
			error = 2;
			respuesta = false;
		}
		
	
		
		return respuesta;
		
	}
	
	public int getError(){
		return error;
	}
}
