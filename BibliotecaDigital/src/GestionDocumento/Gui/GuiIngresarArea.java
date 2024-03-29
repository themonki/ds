 /**
 * GuiIngresarArea.java
 * 
 * Clase que representa la interfaz que permite insertar una nueva
 * Area de Conocimiento para los documentos digitales de la Biblio-
 * teca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  
 * Version:   4.0
 */
package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
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
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import Documento.Gui.GuiCatalogar;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Logica.AreaConocimiento;
import Usuarios.Gui.GuiRegistroModificar;
import Utilidades.Button;
import Utilidades.Estilos;

/**
 * Clase que permite manejar los datos de las nuevas areas de conocimiento que son adicionadas
 * por los usuarios con perfil de catalogador y con perfil de administrador
 * de la Biblioteca Digital.
 * 
 * @author Luis Felipe Vargas
 * 
 */
public class GuiIngresarArea extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel nombre, descripcionArea, areaPadre;
	JTextField campoNombre;
	JComboBox campoAreaPadre;
	JTextArea campoDescripcionArea;
	Button botonIngresarArea;
	GuiCatalogar guiCatalogarModi;
	GuiRegistroModificar guiRegistroModi;

	
	JPanel panel, panel2, panel3, panel4, panel5, panelPrincipal;

	public GuiIngresarArea() {
		super(":::Ingresar Area:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
		
	}
	public GuiIngresarArea(GuiCatalogar guiCatalogarModi) {
		super(":::Ingresar Area:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
		this.guiCatalogarModi=guiCatalogarModi;
		
		
	}
	public void setGuiRegistroModi(GuiRegistroModificar guiUsuario){
		this.guiRegistroModi=guiUsuario;
	}

	public void initComponents() {
		panel = new JPanel();
		panel2 = new JPanel(new GridBagLayout());
		panel3 = new JPanel();
		panel5 = new JPanel(new FlowLayout());
		panel4 = new JPanel(new BorderLayout());
		// --------------------------------------------------------
		//panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		//panel5.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		//panel.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));

		panelPrincipal = new JPanel(new BorderLayout());
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Registrar  Area::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		panelPrincipal.setBorder(borde);
		// ---------------------------------------------
		iniciarLabels();
		// ---------------------------------------------
		iniciarCampos();
		// --------------------------------------------

		GridBagConstraints restricciones = new GridBagConstraints();
		restricciones.gridy=0;
		restricciones.anchor= GridBagConstraints.WEST;
		restricciones.insets= new Insets(10 ,10 ,10,10);
		panel2.add(nombre,restricciones);
		panel2.add(campoNombre,restricciones);
		restricciones.gridy++;
		panel2.add(areaPadre,restricciones);
		panel2.add(campoAreaPadre,restricciones);
		panel3.add(panel2);
		JScrollPane scroll = new JScrollPane(campoDescripcionArea);
		
		panel5.add(scroll);

		panel.add(botonIngresarArea);

		panel4.add(descripcionArea, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);
		
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel4, BorderLayout.SOUTH);
		add(panelPrincipal);
		setSize(600, 340);
		setResizable(false);
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
		campoDescripcionArea.setLineWrap(true);
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

		nombre = new JLabel("  Nombre Del Area :");
		descripcionArea = new JLabel("Descripcion.", JLabel.CENTER);
		areaPadre = new JLabel("  Area Padre :");

		areaPadre.setFont(Estilos.fontLabels);
		descripcionArea.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);

		areaPadre.setForeground(Estilos.colorLabels);
		descripcionArea.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);
	}
	
	private class ManejadorBoton implements ActionListener {

		public void actionPerformed(ActionEvent e) {

			if (validarDatos()) {
				ControladorAreaConocimiento controlador = new ControladorAreaConocimiento();
				String nombre = campoNombre.getText();
				String descripcion = campoDescripcionArea.getText();
				int padreSeleccionado = campoAreaPadre.getSelectedIndex();
				String padre, contador = "" + campoAreaPadre.getItemCount();				
				if (padreSeleccionado == 0) {
					padre = "";
				} else {
					padre = "" + padreSeleccionado;
				}
				String mensaje="Esta a punto de ingresar al sistema el area de conocimeinto:\n";
				mensaje+=nombre+"\n";
				mensaje+="con la descripcion: "+ descripcion+"\n";
				mensaje+="con el area padre: "+campoAreaPadre.getSelectedItem()+"\n";
				mensaje+="¿esta completamente seguro de que desea ingresarla?";
				int value = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Insertar Area de Conocimiento",
						JOptionPane.YES_NO_OPTION);
				//1 para no y 0 para si		
				if(value==0){
					if(controlador.insertarAreaConocimiento(contador, nombre,
							descripcion, padre) >= 1) {
						if(guiCatalogarModi!=null){
							guiCatalogarModi.vectoresParaComboBox();
							guiCatalogarModi.actualizarAreas();
						}
						if(guiRegistroModi!= null){
							guiRegistroModi.actualizarAreasInteres();
						}
						
						JOptionPane.showMessageDialog(null,
								"Se ingreso el Area de Conocimiento correctamente");
						dispose();

					} else {
						JOptionPane.showMessageDialog(null,
								"El Area de Conocimiento ya existe", "ERROR",
								JOptionPane.ERROR_MESSAGE);
					}					
				}
				

			}

		}
	}
	
	
	private class ManejadorJTextField implements KeyListener{

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
		}

		public void keyTyped(KeyEvent e) {			
		}
	}	
	
	boolean validarDatos()
	{
		String nombre = campoNombre.getText();
	
		String advertencia = "";
		boolean respuesta = true;
		
		
		if(nombre.isEmpty())
		{		
			advertencia += "Debe de proporcionar un nombre para el area de conocimiento \n";
			respuesta = false;
		}		
		
		if(!respuesta)
		JOptionPane.showMessageDialog(this, advertencia);
	
		return respuesta;
	}
}