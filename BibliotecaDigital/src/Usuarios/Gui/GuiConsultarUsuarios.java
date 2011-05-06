package Usuarios.Gui;

import java.awt.Color;
import java.awt.Font;
import java.util.Vector;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Usuarios.Logica.Usuario;

public class GuiConsultarUsuarios extends JScrollPane{
	
	JLabel login, nombre;
	JTextField campoLogin, campoNombre;
	JScrollPane scrolResultados;
	JButton consultar;
	JPanel panelResultado, panelOpciones, panelPrincipal;
	
	//Resultados
	JList resultadoLista;
	Vector<Usuario> usuariosVector;
	
	//Estilos.
	//-------------------------------fuentes letras-------------------------
	Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
	Font fontSubtitulos = new Font("Book Antiqua",Font.BOLD, 15);
	Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
	
	//-------------------------------Color letras----------------------------
	Color colorTitulo = new Color(0,50,0);
	Color colorSubtitulo= new Color(0,50,10);
	Color colorLabels= new Color(0,60,0);	
	
	public GuiConsultarUsuarios(){
		iniComponents();
	}
	
	private void iniComponents(){
		
		//InicializarLabels
		login = inicializarLabel("Login: ");
		nombre = inicializarLabel("Nombre: ");
		
		//InicializarJTextField
		campoLogin = new JTextField(10);
		campoNombre= new JTextField(15);
		
		
		
	}
	private JLabel inicializarLabel(String titulo){
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setFont(fontLabels);
		label.setForeground(colorLabels);
		return label;
	}

}
