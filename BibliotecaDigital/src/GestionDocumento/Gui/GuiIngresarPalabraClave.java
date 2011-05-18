package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Documento.Gui.GuiCatalogar;
import GestionDocumento.Controlador.ControladorPalabraClave;
import Utilidades.Button;
import Utilidades.Estilos;
public class GuiIngresarPalabraClave extends JFrame {

	JLabel nombre, descripcion;
	JTextField campoNombre;
	JTextArea campoDescripcion;
	Button botonIngresarPalabra;

	JPanel panel1, panel, panel2, panel3;
	
	GuiCatalogar guicatalogarModi ;

	public GuiIngresarPalabraClave() {
		super(":::Ingresar Palabra Clave:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
	}
	
	public GuiIngresarPalabraClave(GuiCatalogar guicatalogarModi) {
		super(":::Ingresar Palabra Clave:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
		this.guicatalogarModi=guicatalogarModi;
	}
	

	public void initComponents() {
		// ---------------------------------------------------------------
		JPanel panel5 = new JPanel(new FlowLayout()), panel4 = new JPanel(
				new BorderLayout()), panel = new JPanel();
		JPanel panel2 = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel panel3 = new JPanel();
		JPanel panelPrincipal = new JPanel(new BorderLayout());
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Registrar Palabra Clave::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		panelPrincipal.setBorder(borde);
		
		// ----------------------------------------------------------------
		panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel5.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		
		
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

		setLayout(new GridBagLayout());
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel4, BorderLayout.SOUTH);
		add(panelPrincipal);
		setSize(400, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarCampos() {
		ManejadorJTextField manejador  = new ManejadorJTextField();
		
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoDescripcion = new JTextArea(5, 20);
		campoDescripcion.addKeyListener(manejador);
		botonIngresarPalabra = new Button("Registrar Palabra");
		botonIngresarPalabra.addActionListener(new ManejadorBoton());

	}

	private void iniciarLabels() {

		nombre = new JLabel("  Palabra Clave: ");
		descripcion = new JLabel("Descripcion.", JLabel.CENTER);

		descripcion.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);

		descripcion.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);
	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			
			if(validarDatos()){
			
				ControladorPalabraClave conPalabra = new ControladorPalabraClave();
				if(conPalabra.insertarPalabraClave(campoNombre.getText(), campoDescripcion.getText())>=1)
				{
				guicatalogarModi.vectoresParaComboBox();
				guicatalogarModi.actualizarPalabras();
				JOptionPane.showMessageDialog(null, "Se ingreso la Palabra Clave correctamente");
				dispose();
				}else{
					JOptionPane.showMessageDialog(null, "La Palabra Clave ya existe","ERROR", JOptionPane.ERROR_MESSAGE);
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