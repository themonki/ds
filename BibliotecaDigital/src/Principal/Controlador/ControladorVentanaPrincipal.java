package Principal.Controlador;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Principal.Gui.GuiAdministrador;
import Principal.Gui.GuiCatalogador;
import Principal.Gui.GuiPrincipal;
import Principal.Gui.GuiUsuarioNormal;
import Usuarios.Controlador.ControladorUsuario;
import Usuarios.Logica.Usuario;

public class ControladorVentanaPrincipal {

	
	public boolean verificarUsuario(String login, String password)
	{
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		Usuario usuario = controladorUsuario.consultarUsuario(login);
		boolean respuesta = false;
		
		if(login.equals("") || password.equals(""))
		{	
			
			JOptionPane.showMessageDialog(null, "El campo login o password esta vacio");
			
		}
		
		if(usuario.getNombre1() == null)
		{
			JOptionPane.showMessageDialog(null, "Login de usuario incorrecto");
			
			
		}else if(usuario.getEstado()==false){ //usuario Desactivado.
			JOptionPane.showMessageDialog(null, "USUARIO DESACTIVADO\nComuniquese con el administrador\n para ser activado de nuevo.", "Usuario Desactivado", JOptionPane.WARNING_MESSAGE);
			
		}
		else if(password.equals(usuario.getContrasena()))
		{
			System.out.println("entre password iguales");
			if(usuario.getTipo().equals("1"))
			{
				GuiAdministrador guiAdministrador = new GuiAdministrador(usuario);
				guiAdministrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}else if(usuario.getTipo().equals("2"))
			{
				GuiCatalogador guiCatalogador = new GuiCatalogador(usuario);
				guiCatalogador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}else if(usuario.getTipo().equals("3"))
			{
				
				GuiUsuarioNormal guiUsuarioNormal = new GuiUsuarioNormal(usuario);
				guiUsuarioNormal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
			respuesta = true;
			
		}
		else
		{
		
			JOptionPane.showMessageDialog(null, "La contraseña no es valida para el usuario: "+usuario.getLogin());
			respuesta = false;
		}
		
		return respuesta;
		
	}
}
