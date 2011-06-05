 /**
 * GuiModificarAutor.java
 * 
 * Clase que representa la interfaz que permite modificar un
 * Autor para los documentos digitales de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Edgar Andres Moncada
 * Version:   4.0
 */
package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Documento.Gui.GuiCatalogar;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Logica.Autor;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiModificarAutor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel consultar, nombre, apellido, correoElectronico, acronimo;
	JTextField campoNombre, campoApellido, campoCorreoElectronico,
			campoAcronimo;
	JComboBox campoConsulta;
	Button botonModificarAutor;
	ManejadorCombo manejadorCombo;
	Vector <Autor> va ;
	int pos_autor;
	
	GuiCatalogar 	guiCatalogarModi;

	public GuiModificarAutor() {
		super(":::Modificar Autor:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
	}
	public GuiModificarAutor(GuiCatalogar 	guiCatalogarModi ) {
		super(":::Modificar Autor:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
		this.guiCatalogarModi= guiCatalogarModi;
	}

	public void initComponents() {
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel(new GridBagLayout());
		JPanel panel3 = new JPanel();
		JPanel panelPrincipal = new JPanel(new BorderLayout());

		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Modificar  Autor::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		panelPrincipal.setBorder(borde);
		
		// --------------------------------------------------------
		panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------

		GridBagConstraints restriccionCampo = new GridBagConstraints(), restriccionEtiquetas = new GridBagConstraints();

		restriccionCampo.ipadx = 10;
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(5, 40, 5, 0);

		restriccionEtiquetas.gridy = 0;
		restriccionEtiquetas.gridx = 0;

		restriccionEtiquetas.anchor = GridBagConstraints.WEST;
		restriccionCampo.anchor = GridBagConstraints.WEST;
		
		panel2.add(consultar, restriccionEtiquetas);
		panel2.add(campoConsulta, restriccionCampo);	
		
		restriccionEtiquetas.gridy = 1;
		restriccionCampo.gridy = 1;
		
		panel2.add(nombre, restriccionEtiquetas);
		panel2.add(campoNombre, restriccionCampo);

		restriccionEtiquetas.gridy = 2;
		restriccionCampo.gridy = 2;

		panel2.add(apellido, restriccionEtiquetas);
		panel2.add(campoApellido, restriccionCampo);

		restriccionEtiquetas.gridy = 3;
		restriccionCampo.gridy = 3;

		panel2.add(acronimo, restriccionEtiquetas);
		panel2.add(campoAcronimo, restriccionCampo);

		restriccionEtiquetas.gridy = 4;
		restriccionCampo.gridy = 4;

		panel2.add(correoElectronico, restriccionEtiquetas);
		panel2.add(campoCorreoElectronico, restriccionCampo);

		panel3.add(panel2);

		panel.add(botonModificarAutor);

		
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel, BorderLayout.SOUTH);

		add(panelPrincipal);
		setSize(600, 280);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarCampos() {
		ManejadorJTextField manejador = new ManejadorJTextField();
		manejadorCombo = new ManejadorCombo();
		campoConsulta = new JComboBox();
		campoConsulta.addActionListener(manejadorCombo);
		campoNombre = new JTextField(20);
		campoNombre.addKeyListener(manejador);
		campoNombre.setEditable(false);
		campoApellido = new JTextField(20);
		campoApellido.addKeyListener(manejador);
		campoApellido.setEditable(false);
		campoCorreoElectronico = new JTextField(17);
		campoCorreoElectronico.addKeyListener(manejador);
		campoCorreoElectronico.setEditable(false);
		campoAcronimo = new JTextField(12);
		campoAcronimo.addKeyListener(manejador);
		campoAcronimo.setEditable(false);
		botonModificarAutor = new Button("Ingresar Autor");
		botonModificarAutor.addActionListener(new ManejadorBoton());
		botonModificarAutor.setEnabled(false);
		
		ControladorAutor conAutor = new ControladorAutor();
		va = conAutor.obtenerAutores();
		int cantidad = va.size();
		campoConsulta.addItem("");
		for(int i = 0; i < cantidad; i++){
			campoConsulta.addItem(va.get(i).getNombre());
		}

	}

	private void iniciarLabels() {
		
		consultar = new JLabel("Seleccione el Autor a Modificar");
		
		nombre = new JLabel("  Nombre  :");
		apellido = new JLabel("  Apellido :");
		correoElectronico = new JLabel("  Correo Electronico :");
		acronimo = new JLabel("  Acronimo :");

		consultar.setFont(Estilos.fontLabels);
		apellido.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);
		acronimo.setFont(Estilos.fontLabels);
		correoElectronico.setFont(Estilos.fontLabels);

		consultar.setForeground(Estilos.colorLabels);
		apellido.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);
		acronimo.setForeground(Estilos.colorLabels);
		correoElectronico.setForeground(Estilos.colorLabels);

	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			ControladorAutor conAutor = new ControladorAutor();	
			if(validarDatos()){
				String mensaje="Esta a punto de modificar al sistema el autor:\n";
				mensaje+=campoNombre.getText()+ " " + campoApellido.getText()+"\n";
				mensaje+="con el acronimo: "+ campoAcronimo.getText()+"\n";
				mensaje+="con el email: "+campoCorreoElectronico.getText()+"\n";
				mensaje+="¿esta completamente seguro de que desea ingresarlo?";
				int value = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Modificar Autor",
						JOptionPane.YES_NO_OPTION);
				//1 para no y 0 para si		
				if(value==0){
					Autor a = new Autor();
					a.setId(va.get(pos_autor).getId());
					a.setNombre(campoNombre.getText());
					a.setApellido(campoApellido.getText());
					a.setAcronimo(campoAcronimo.getText());
					a.setCorreo(campoCorreoElectronico.getText());
					if(conAutor.actualizarAutor(a)>=1)
					{
					guiCatalogarModi.vectoresParaComboBox();
					guiCatalogarModi.actualizarAutores();
					JOptionPane.showMessageDialog(null, "Se Modifico el Autor correctamente");
					dispose();

					}else{				
						JOptionPane.showMessageDialog(null, "No se pudo modificar el autor","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}
	}
	
	private class ManejadorJTextField implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			if( campoNombre == e.getSource()){
				
				if(campoNombre.getText().length()>100)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoNombre.setText(campoNombre.getText().substring(0,99));
					}
				}
			}
			
			if(e.getSource()== campoApellido){
				if(campoApellido.getText().length()>30)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoApellido.setText(campoApellido.getText().substring(0,29));
					}
				}					
			}
			if(e.getSource()== campoAcronimo){
				if(campoAcronimo.getText().length()>20)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoAcronimo.setText(campoAcronimo.getText().substring(0,19));
					}
				}					
			}
			if(e.getSource()== campoCorreoElectronico){
				if(campoCorreoElectronico.getText().length()>50)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoCorreoElectronico.setText(campoCorreoElectronico.getText().substring(0,49));
					}
				}					
			}
			
		}

		public void keyReleased(KeyEvent e) {
		}
		public void keyTyped(KeyEvent e) {			
		}
	}
	
	boolean validarDatos()
	{
		String nombre = campoNombre.getText();
		String apellido = campoApellido.getText();
		String acronimo = campoAcronimo.getText();
		String email = campoCorreoElectronico.getText();
		String advertencia = "";
		
		
		boolean respuesta = true;
		
		if(nombre.isEmpty())
		{		
			advertencia += "Debe de proporcionar un nombre para la palabra clave \n";
			respuesta = false;
		}
		if(apellido.isEmpty())
		{
			
			advertencia += "Debe de proporcionar un apellido para el autor \n";
			respuesta = false;
		}
		if(acronimo.isEmpty()){
			advertencia += "Debe de proporcionar un acronimo para el autor \n";
			respuesta = false;
		}
		if(email.isEmpty() || !email.contains("@")){
			advertencia += "Debe de proporcionar un email valido, ejemplo: autor@correo.com\n";
			respuesta = false;
		}
		
		if(!respuesta)
		JOptionPane.showMessageDialog(this, advertencia);
	
		return respuesta;
	}
	private class ManejadorCombo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==campoConsulta){
				int index = campoConsulta.getSelectedIndex();
				if(index>0){
					pos_autor = index-1;
					campoNombre.setText(va.get(pos_autor).getNombre());
					campoNombre.setEditable(true);
					campoApellido.setText(va.get(pos_autor).getApellido());
					campoApellido.setEditable(true);
					campoAcronimo.setText(va.get(pos_autor).getAcronimo());
					campoAcronimo.setEditable(true);
					campoCorreoElectronico.setText(va.get(pos_autor).getCorreo());
					campoCorreoElectronico.setEditable(true);					
					
					botonModificarAutor.setEnabled(true);
				}else{
					pos_autor = 0;
					campoNombre.setText("");
					campoNombre.setEditable(false);
					campoApellido.setText("");
					campoApellido.setEditable(false);
					campoAcronimo.setText("");
					campoAcronimo.setEditable(false);
					campoCorreoElectronico.setText("");
					campoCorreoElectronico.setEditable(false);
					
					botonModificarAutor.setEnabled(false);
					
				}
			}
		}
		
		
	}
	public static void main (String []args){
		GuiModificarAutor a = new GuiModificarAutor(new GuiCatalogar("admin"));
		a.setVisible(true);
	}
}