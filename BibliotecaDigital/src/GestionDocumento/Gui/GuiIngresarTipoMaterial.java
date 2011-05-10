package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Documento.Gui.GuiCatalogarModificar;
import GestionDocumento.Controlador.ControladorTipoMaterial;

public class GuiIngresarTipoMaterial extends JFrame {

	JLabel nombre, descripcion, indicacion;
	JTextField campoNombre;

	JTextArea campoDescripcion;
	JButton botonIngresarTipo;

	JPanel panel, panel1, panel2, panel3, panel4, panel5;
	
	GuiCatalogarModificar guicatalogarModi;

	public GuiIngresarTipoMaterial() {
		initComponents();
	}
	public GuiIngresarTipoMaterial(GuiCatalogarModificar guicatalogarModi) {
		this.guicatalogarModi=guicatalogarModi;
		initComponents();
	}

	public void initComponents() {
		panel2 = new JPanel(new GridLayout(1, 1, 10, 10));
		panel3 = new JPanel();
		panel5 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new BorderLayout());
		panel = new JPanel();
		// ----------------------------------------------------------
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel5.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
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

		setLayout(new BorderLayout());
		add(indicacion, BorderLayout.NORTH);
		add(panel3, BorderLayout.CENTER);
		add(panel4, BorderLayout.SOUTH);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarLabels() {
		Font font1 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 17);
		Font font3 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 25);
		Color colorletras = new Color(0, 60, 0);

		indicacion = new JLabel("Registrar Tipo de Material", JLabel.CENTER);
		nombre = new JLabel("Nombre Del Tipo :");
		descripcion = new JLabel("Descripcion.", JLabel.CENTER);

		indicacion.setFont(font3);
		descripcion.setFont(font1);
		nombre.setFont(font1);

		indicacion.setForeground(colorletras);
		descripcion.setForeground(colorletras);
		nombre.setForeground(colorletras);

	}

	private void iniciarCampos() {
		ManejadorJTextField manejador = new ManejadorJTextField();
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoDescripcion = new JTextArea(5, 20);
		campoDescripcion.addKeyListener(manejador);
		botonIngresarTipo = new JButton("Registrar Tipo Material");
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
				}

				conMaterial.insertarTipoMaterial(campoNombre.getText(), campoDescripcion.getText());				

				dispose();
				JOptionPane.showMessageDialog(null, "Se ingreso el Tipo de Material Correctamente");
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