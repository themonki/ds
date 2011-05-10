package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
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
import GestionDocumento.Controlador.ControladorPalabraClave;
public class GuiIngresarPalabraClave extends JFrame {

	JLabel nombre, descripcion, indicacion;
	JTextField campoNombre;
	JTextArea campoDescripcion;
	JButton botonIngresarPalabra;

	JPanel panel1, panel, panel2, panel3;
	
	GuiCatalogarModificar guicatalogarModi ;

	public GuiIngresarPalabraClave() {
		initComponents();
	}
	
	public GuiIngresarPalabraClave(GuiCatalogarModificar guicatalogarModi) {
		initComponents();
		this.guicatalogarModi=guicatalogarModi;
	}
	

	public void initComponents() {
		// ---------------------------------------------------------------
		JPanel panel5 = new JPanel(new FlowLayout()), panel4 = new JPanel(
				new BorderLayout()), panel = new JPanel();
		JPanel panel2 = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel panel3 = new JPanel();
		// ----------------------------------------------------------------
		panel2.setBorder(BorderFactory.createLineBorder(Color.black));
		panel5.setBorder(BorderFactory.createLineBorder(Color.black));
		panel.setBorder(BorderFactory.createLineBorder(Color.black));
		// ------------------------------------------------------------
		iniciarLabels();
		// ------------------------------------------
		iniciarCampos();

		panel2.add(nombre);
		panel2.add(campoNombre);
		panel3.add(panel2);

		JScrollPane scroll = new JScrollPane(campoDescripcion);
		scroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panel5.add(scroll);

		panel.add(botonIngresarPalabra);

		panel4.add(descripcion, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);

		setLayout(new BorderLayout());
		add(indicacion, BorderLayout.NORTH);
		add(panel3, BorderLayout.CENTER);
		add(panel4, BorderLayout.SOUTH);
		setSize(400, 300);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setVisible(true);
	}

	private void iniciarCampos() {
		ManejadorJTextField manejador  = new ManejadorJTextField();
		
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoDescripcion = new JTextArea(5, 20);
		campoDescripcion.addKeyListener(manejador);
		botonIngresarPalabra = new JButton("Registrar Palabra");
		botonIngresarPalabra.addActionListener(new ManejadorBoton());

	}

	private void iniciarLabels() {
		Font font1 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 17);
		Font font3 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 25);
		Color colorletras = new Color(0, 60, 0);

		indicacion = new JLabel("Registrar Palabra Clave ", JLabel.CENTER);
		nombre = new JLabel("Palabra Clave: ");
		descripcion = new JLabel("Descripcion.", JLabel.CENTER);

		indicacion.setFont(font3);
		descripcion.setFont(font1);
		nombre.setFont(font1);

		indicacion.setForeground(colorletras);
		descripcion.setForeground(colorletras);
		nombre.setForeground(colorletras);
	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			
			if(validarDatos()){
			
				ControladorPalabraClave conPalabra = new ControladorPalabraClave();
				if(conPalabra.insertarPalabraClave(campoNombre.getText(), campoDescripcion.getText())>=1)
				{
				guicatalogarModi.vectoresParaComboBox();
				guicatalogarModi.actualizarPalabras();
				}
				dispose();
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
			advertencia += "Debe de proporcionar un nombre para la palabra clave \n";
			respuesta = false;
		}
		if(descripcion.isEmpty())
		{
			
			advertencia += "Debe de proporcionar una descripcion para la palabra clave \n";
			respuesta = false;
		}
		
		if(!respuesta)
		JOptionPane.showMessageDialog(this, advertencia);
	
		return respuesta;
	}
	

}