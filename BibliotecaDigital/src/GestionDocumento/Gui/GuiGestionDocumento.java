package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import Documento.Gui.GuiCatalogar;
import Usuarios.Gui.GuiRegistroModificar;
import Utilidades.Button;

public class GuiGestionDocumento extends JScrollPane{
	private Button botonIngresarArea;
	private Button botonIngresarAutor;
	private Button botonIngresarPalabra;
	private Button botonIngresarTipoMaterial;
	private Button botonModificarArea;
	private Button botonModificarAutor;
	private Button botonModificarPalabra;
	private Button botonModificarTipoMaterial;
	
	private GuiCatalogar panelCatalogar;
	private GuiRegistroModificar guiRegistroModi;
	
	private Manejador manejador;
	
	private JLabel add, update;
	
	public GuiGestionDocumento() {
		initComponent();
	}
	public GuiGestionDocumento(GuiCatalogar g) {
		initComponent();
		panelCatalogar=g;
	}
	public void setGuiRegistroModi(GuiRegistroModificar guiUsuario){
		this.guiRegistroModi=guiUsuario;
	}
	
	private void initComponent(){
		manejador = new Manejador();
		
		botonIngresarArea = initBoton("Insertar Area Conocimiento");
		botonIngresarAutor = initBoton("Insertar Autor");
		botonIngresarPalabra = initBoton("Insertar Palabra Clave");
		botonIngresarTipoMaterial = initBoton("Insertar Tipo de Material");
		botonModificarArea = initBoton("Modificar Area Conocimiento");
		botonModificarAutor = initBoton("Modificar Autor");
		botonModificarPalabra = initBoton("Modificar Palabra Clave");
		botonModificarTipoMaterial = initBoton("Modificar Tipo de Material");
		
		add = new JLabel();
		update = new JLabel();
		ImageIcon iconAdd = new ImageIcon("recursos/iconos/newadd.png");
		ImageIcon iconUpdate = new ImageIcon("recursos/iconos/update.png");
		add.setIcon(iconAdd);
		update.setIcon(iconUpdate);
		
		
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		
		JPanel panel = new JPanel(new GridBagLayout());
		GridBagConstraints restriccion = new GridBagConstraints();

		restriccion.insets = new Insets(1, 20, 10, 50);// espacios entre
		restriccion.gridy=0;
		restriccion.gridx=0;
		restriccion.anchor = GridBagConstraints.WEST;
				
		int pos = 1;
		restriccion.anchor = GridBagConstraints.CENTER;
		panel.add(add,restriccion);
		restriccion.gridy=pos++;
		restriccion.anchor = GridBagConstraints.WEST;
		panel.add(botonIngresarArea, restriccion);
		restriccion.gridy=pos++;
		restriccion.ipadx=100;
		panel.add(botonIngresarAutor, restriccion);
		restriccion.gridy=pos++;
		restriccion.ipadx=40;
		panel.add(botonIngresarPalabra, restriccion);
		restriccion.gridy=pos++;
		restriccion.ipadx=20;
		panel.add(botonIngresarTipoMaterial, restriccion);
		pos=1;
		restriccion.gridy=0;
		restriccion.gridx=1;
		restriccion.ipadx=0;
		restriccion.anchor = GridBagConstraints.CENTER;
		panel.add(update,restriccion);
		restriccion.gridy=pos++;
		restriccion.anchor = GridBagConstraints.WEST;
		panel.add(botonModificarArea, restriccion);
		restriccion.gridy=pos++;
		restriccion.ipadx=100;
		panel.add(botonModificarAutor, restriccion);
		restriccion.gridy=pos++;
		restriccion.ipadx=40;
		panel.add(botonModificarPalabra, restriccion);
		restriccion.gridy=pos++;
		restriccion.ipadx=20;
		panel.add(botonModificarTipoMaterial, restriccion);
		
		panelPrincipal.add(panel, BorderLayout.CENTER);
		this.setViewportView(panelPrincipal);
		this.setVisible(true);
	}
	
	private Button initBoton(String titulo){
		Button b = new Button(titulo);
		b.addActionListener(manejador);
		return b;
		
	}
	
	private class Manejador implements ActionListener{

		public void actionPerformed(ActionEvent e) {
			
			if(e.getSource()==botonIngresarArea){
				GuiIngresarArea gui = new GuiIngresarArea(panelCatalogar);
				gui.setGuiRegistroModi(guiRegistroModi);
				gui.setVisible(true);
			}
			if(e.getSource()==botonIngresarAutor){
				GuiIngresarAutor gui = new GuiIngresarAutor(panelCatalogar);
				gui.setVisible(true);
			}
			if(e.getSource()==botonIngresarPalabra){
				GuiIngresarPalabraClave gui = new GuiIngresarPalabraClave(panelCatalogar);
				gui.setVisible(true);
			}
			if(e.getSource()==botonIngresarTipoMaterial){
				GuiIngresarTipoMaterial gui = new GuiIngresarTipoMaterial(panelCatalogar);
				gui.setVisible(true);
			}
			if(e.getSource()==botonModificarArea){
				GuiModificarArea gui = new GuiModificarArea(panelCatalogar);
				gui.setGuiRegistroModi(guiRegistroModi);
				gui.setVisible(true);
			}
			if(e.getSource()==botonModificarAutor){
				GuiModificarAutor gui = new GuiModificarAutor(panelCatalogar);
				gui.setVisible(true);
			}
			if(e.getSource()==botonModificarPalabra){
				GuiModificarPalabraClave gui = new GuiModificarPalabraClave(panelCatalogar);
				gui.setVisible(true);
			}
			if(e.getSource()==botonModificarTipoMaterial){
				GuiModificarTipoMaterial gui = new GuiModificarTipoMaterial(panelCatalogar);
				gui.setVisible(true);
			}
		}
		
	}
	


}
