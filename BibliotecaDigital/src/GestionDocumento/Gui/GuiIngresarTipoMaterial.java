package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;

import Utilidades.Button;
import Utilidades.Estilos;

import Documento.Gui.GuiCatalogarModificar;
import GestionDocumento.Controlador.ControladorTipoMaterial;

public class GuiIngresarTipoMaterial extends JFrame {

	JLabel nombre, descripcion;
	JTextField campoNombre;

	JTextArea campoDescripcion;
	Button botonIngresarTipo;

	JPanel panel, panel1, panel2, panel3, panel4, panel5, panelPrincipal;
	
	GuiCatalogarModificar guicatalogarModi;

	public GuiIngresarTipoMaterial() {
		super(":::Ingresar Tipo Material:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
	}
	public GuiIngresarTipoMaterial(GuiCatalogarModificar guicatalogarModi) {
		super(":::Ingresar Tipo Material:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		this.guicatalogarModi=guicatalogarModi;
		initComponents();
	}

	public void initComponents() {
		panel2 = new JPanel(new GridLayout(1, 1, 10, 10));
		panel3 = new JPanel();
		panel5 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new BorderLayout());
		panel = new JPanel();
		panelPrincipal = new JPanel(new BorderLayout());
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Registrar Tipo de Material::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		panelPrincipal.setBorder(borde);
		
		// ----------------------------------------------------------
		panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel5.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		// ------------------------------------------------------------
		iniciarLabels();
		iniciarCampos();
		// -------------------------------------------------------------
		panel2.add(nombre);
		panel2.add(campoNombre);
		panel3.add(panel2);

		JScrollPane scroll = new JScrollPane(campoDescripcion);
		scroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panel5.add(scroll);

		panel.add(botonIngresarTipo);

		panel4.add(descripcion, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);

		setLayout(new GridBagLayout());
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel4, BorderLayout.SOUTH);
		add(panelPrincipal);
		setSize(430, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarLabels() {

		nombre = new JLabel(" Nombre Del Tipo :");
		descripcion = new JLabel(" Descripcion.", JLabel.CENTER);

		descripcion.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);

		descripcion.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);

	}

	private void iniciarCampos() {
		ManejadorJTextField manejador = new ManejadorJTextField();
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoDescripcion = new JTextArea(5, 20);
		campoDescripcion.addKeyListener(manejador);
		botonIngresarTipo = new Button("Registrar Tipo Material");
		botonIngresarTipo.addActionListener(new ManejadorBoton());

	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			if(validarDatos()){
		
				ControladorTipoMaterial conMaterial = new ControladorTipoMaterial();
				if(conMaterial.insertarTipoMaterial(campoNombre.getText(), campoDescripcion.getText())>=1)
				{
				guicatalogarModi.vectoresParaComboBox();
				guicatalogarModi.actualizarTipoMaterial();
				JOptionPane.showMessageDialog(null, "Se ingreso el Tipo de Material Correctamente");
				dispose();
				}else{
					JOptionPane.showMessageDialog(null, "El Tipo de Material ya existe","ERROR", JOptionPane.ERROR_MESSAGE);
				}
			}
		}		
	}
	
	private class ManejadorJTextField implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			if( campoNombre == e.getSource()){
				
				if(campoNombre.getText().length()>20)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoNombre.setText(campoNombre.getText().substring(0,19));
					}
				}
			}
			
			if(e.getSource()== campoDescripcion){
				if(campoDescripcion.getText().length()>200)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoDescripcion.setText(campoDescripcion.getText().substring(0,199));
					}
				}					
			}
			
			

		
			
				
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}
	
	boolean validarDatos()
	{
		String nombre = campoNombre.getText();
		String descripcion = campoDescripcion.getText();
		String advertencia = "";
		boolean respuesta = true;
		
		if(nombre.isEmpty())
		{		
			advertencia += "Debe de proporcionar un nombre para el tipo de material \n";
			respuesta = false;
		}
		if(descripcion.isEmpty())
		{
			
			advertencia += "Debe de proporcionar una descripcion para el tipo de material \n";
			respuesta = false;
		}
		if(!respuesta)
		JOptionPane.showMessageDialog(this, advertencia);
	
		return respuesta;
	}
	

}