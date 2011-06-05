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

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Documento.Gui.GuiCatalogar;
import GestionDocumento.Controlador.ControladorAutor;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiIngresarAutor extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel nombre, apellido, correoElectronico, acronimo;
	JTextField campoNombre, campoApellido, campoCorreoElectronico,
			campoAcronimo;
	Button botonIngresarAutor;
	
	GuiCatalogar 	guiCatalogarModi;

	public GuiIngresarAutor() {
		super(":::Ingresar Autor:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
	}
	public GuiIngresarAutor(GuiCatalogar 	guiCatalogarModi ) {
		super(":::Ingresar Autor:::");
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
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Registrar  Autor::");
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

		panel2.add(nombre, restriccionEtiquetas);
		panel2.add(campoNombre, restriccionCampo);

		restriccionEtiquetas.gridy = 1;
		restriccionCampo.gridy = 1;

		panel2.add(apellido, restriccionEtiquetas);
		panel2.add(campoApellido, restriccionCampo);

		restriccionEtiquetas.gridy = 2;
		restriccionCampo.gridy = 2;

		panel2.add(acronimo, restriccionEtiquetas);
		panel2.add(campoAcronimo, restriccionCampo);

		restriccionEtiquetas.gridy = 3;
		restriccionCampo.gridy = 3;

		panel2.add(correoElectronico, restriccionEtiquetas);
		panel2.add(campoCorreoElectronico, restriccionCampo);

		panel3.add(panel2);

		panel.add(botonIngresarAutor);

		
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel, BorderLayout.SOUTH);

		add(panelPrincipal);
		setSize(500, 280);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarCampos() {
		ManejadorJTextField manejador = new ManejadorJTextField();
	
		campoNombre = new JTextField(20);
		campoNombre.addKeyListener(manejador);
		campoApellido = new JTextField(20);
		campoApellido.addKeyListener(manejador);
		campoCorreoElectronico = new JTextField(17);
		campoCorreoElectronico.addKeyListener(manejador);
		campoAcronimo = new JTextField(12);
		campoAcronimo.addKeyListener(manejador);
		botonIngresarAutor = new Button("Ingresar Autor");
		botonIngresarAutor.addActionListener(new ManejadorBoton());

	}

	private void iniciarLabels() {

		nombre = new JLabel("  Nombre  :");
		apellido = new JLabel("  Apellido :");
		correoElectronico = new JLabel("  Correo Electronico :");
		acronimo = new JLabel("  Acronimo :");

		apellido.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);
		acronimo.setFont(Estilos.fontLabels);
		correoElectronico.setFont(Estilos.fontLabels);

		apellido.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);
		acronimo.setForeground(Estilos.colorLabels);
		correoElectronico.setForeground(Estilos.colorLabels);

	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			ControladorAutor conAutor = new ControladorAutor();	
			if(validarDatos()){
				String mensaje="Esta a punto de ingresar al sistema el autor:\n";
				mensaje+=campoNombre.getText()+ " " + campoApellido.getText()+"\n";
				mensaje+="con el acronimo: "+ campoAcronimo.getText()+"\n";
				mensaje+="con el email: "+campoCorreoElectronico.getText()+"\n";
				mensaje+="¿esta completamente seguro de que desea ingresarlo?";
				int value = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Insertar Autor",
						JOptionPane.YES_NO_OPTION);
				//1 para no y 0 para si		
				if(value==0){
					if(conAutor.insertarAutor(campoNombre.getText(), campoApellido.getText(),
							campoAcronimo.getText(), campoCorreoElectronico.getText())>=1)
					{
					guiCatalogarModi.vectoresParaComboBox();
					guiCatalogarModi.actualizarAutores();
					JOptionPane.showMessageDialog(null, "Se ingreso el Autor correctamente");
					dispose();

					}else{				
						JOptionPane.showMessageDialog(null, "El autor ya existe","ERROR", JOptionPane.ERROR_MESSAGE);
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
}