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

	
	public void verificarUsuario(String login, String password)
	{
		
		ControladorUsuario controladorUsuario = new ControladorUsuario();
		Usuario usuario = controladorUsuario.consultarUsuario(login);
		if(usuario.getNombre1() == null)
		{
			GuiPrincipal guiPrincipal = new GuiPrincipal();
			guiPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}else if(password.equals(usuario.getContrasena()))
		{
			if(usuario.getTipo().equals("1"))
			{
				GuiAdministrador guiAdministrador = new GuiAdministrador();
				//guiAdministrador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
			}else if(usuario.getTipo().equals("2"))
			{
				GuiCatalogador guiCatalogador = new GuiCatalogador();
				guiCatalogador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				
				
			}else if(usuario.getTipo().equals("3"))
			{
				
				GuiUsuarioNormal guiUsuarioNormal = new GuiUsuarioNormal();
				guiUsuarioNormal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			}
			
		}
		else
		{
		
			JOptionPane.showMessageDialog(null, "La contraseña no es valida para el usuario: "+usuario.getLogin());
			GuiPrincipal guiPrincipal = new GuiPrincipal();
			guiPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		}
		
		
		
		
	}
}
