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
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;

import Utilidades.Button;
import Utilidades.Estilos;

import Documento.Gui.GuiCatalogarModificar;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;

public class GuiIngresarArea extends JFrame {

	JLabel nombre, descripcionArea, areaPadre, indicacion;
	JTextField campoNombre;
	JComboBox campoAreaPadre;
	JTextArea campoDescripcionArea;
	Button botonIngresarArea;
	GuiCatalogarModificar guiCatalogarModi;

	
	JPanel panel, panel2, panel3, panel4, panel5;

	public GuiIngresarArea() {
		super("Ingresar Area");
		initComponents();
		
	}
	public GuiIngresarArea(GuiCatalogarModificar guiCatalogarModi) {
		super("Ingresar Area");
		initComponents();
		this.guiCatalogarModi=guiCatalogarModi;
		
	}

	public void initComponents() {
		panel = new JPanel();
		panel2 = new JPanel(new GridLayout(2, 2, 10, 10));
		panel3 = new JPanel();
		panel5 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new BorderLayout());
		// --------------------------------------------------------
		panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel5.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		panel.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));

		// ---------------------------------------------
		iniciarLabels();
		// ---------------------------------------------
		iniciarCampos();
		// --------------------------------------------

		panel2.add(nombre);
		panel2.add(campoNombre);
		panel2.add(areaPadre);
		panel2.add(campoAreaPadre);
		panel3.add(panel2);
		JScrollPane scroll = new JScrollPane(campoDescripcionArea);
		scroll
				.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll
				.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		panel5.add(scroll);

		panel.add(botonIngresarArea);

		panel4.add(descripcionArea, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);
		
		setLayout(new BorderLayout());
		add(indicacion, BorderLayout.NORTH);
		add(panel3, BorderLayout.CENTER);
		add(panel4, BorderLayout.SOUTH);
		setSize(820, 320);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarCampos() {

		ManejadorJTextField manejador = new ManejadorJTextField();
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoNombre.setFocusable(true);
		campoDescripcionArea = new JTextArea(5, 20);
		campoDescripcionArea.addKeyListener(manejador);
		campoAreaPadre = new JComboBox();
		botonIngresarArea = new Button("Registrar Area");
		botonIngresarArea.addActionListener(new ManejadorBoton());
		
		ControladorAreaConocimiento conArea = new ControladorAreaConocimiento();
		Vector <AreaConocimiento> vac = conArea.obtenerAreas();
		int cantidad = vac.size();
		
		for(int i = 0; i < cantidad; i++){
			campoAreaPadre.addItem(vac.get(i).getNombre());
		}
		
	}

	private void iniciarLabels() {
		

		indicacion = new JLabel("Registrar  Area ", JLabel.CENTER);
		nombre = new JLabel("Nombre Del Area :");
		descripcionArea = new JLabel("Descripcion.", JLabel.CENTER);
		areaPadre = new JLabel("Area Padre :");

		indicacion.setFont(Estilos.fontLabels);
		areaPadre.setFont(Estilos.fontLabels);
		descripcionArea.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);

		indicacion.setFont(Estilos.fontSubtitulos);
		indicacion.setForeground(Estilos.colorLabels);
		areaPadre.setForeground(Estilos.colorLabels);
		descripcionArea.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);
	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent e) {
			
			AreaConocimiento area = new AreaConocimiento();
			
			if(validarDatos()){
			
			ControladorAreaConocimiento controlador = new ControladorAreaConocimiento();
			String nombre = campoNombre.getText();
			String descripcion = campoDescripcionArea.getText();
			int padreSeleccionado = campoAreaPadre.getSelectedIndex();
			String padre, contador = ""+campoAreaPadre.getItemCount();;// este coso esta raro por que dependes del orden .
			System.out.println(padreSeleccionado);
			if(padreSeleccionado == 0){
				padre = "";
				}
			else{
				padre= ""+padreSeleccionado;
			}
			

			if(controlador.insertarAreaConocimiento(contador, nombre, descripcion, padre)>=1)
			{
			guiCatalogarModi.vectoresParaComboBox();
			guiCatalogarModi.actualizarAreas();
			JOptionPane.showMessageDialog(null, "Se ingreso el Area de Conocimiento correctamente");			

			}

			
			dispose();
				guiCatalogarModi.vectoresParaComboBox();
				guiCatalogarModi.actualizarAreas();
				JOptionPane.showMessageDialog(null, "Se ingreso el Area de Conocimiento correctamente");			
				dispose();
			}else{
				JOptionPane.showMessageDialog(null, "El Area de Conocimiento ya existe","ERROR", JOptionPane.ERROR_MESSAGE);
			}	
			
			}
		}		
	
	
	private class ManejadorJTextField implements KeyListener{

		@Override
		public void keyPressed(KeyEvent e) {
			
			if( campoNombre == e.getSource()){
				
				if(campoNombre.getText().length()>50)
				{
					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoNombre.setText(campoNombre.getText().substring(0,49));
					}
				}
			}
			
			if(e.getSource()== campoDescripcionArea){
				if(campoDescripcionArea.getText().length()>200)
				{

					if(e.getKeyCode()!=KeyEvent.VK_BACK_SPACE){
						getToolkit().beep();//sonido
						campoDescripcionArea.setText(campoDescripcionArea.getText().substring(0,199));
					}
				}					
			}}

	
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}

		
		public void keyTyped(KeyEvent e) {
			// TODO Auto-generated method stub
			
		}
	}	
	
	boolean validarDatos()
	{
		String nombre = campoNombre.getText();
		String descripcionArea = campoDescripcionArea.getText();
	
		String advertencia = "";
		boolean respuesta = true;
		
		
		if(nombre.isEmpty())
		{		
			advertencia += "Debe de proporcionar un nombre para el area de conocimiento \n";
			respuesta = false;
		}
		if(descripcionArea.isEmpty())
		{
			
			advertencia += "Debe de proporcionar una descripcion para el area de conocimiento \n";
			respuesta = false;
		}
		
		
		if(!respuesta)
		JOptionPane.showMessageDialog(this, advertencia);
	
		return respuesta;
	}
	

}