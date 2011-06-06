 /**
 * GuiModificarArea.java
 * 
 * Clase que representa la interfaz que permite modificar un Area de
 * Conocimiento para los documentos digitales de la Biblioteca Digital.
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
import java.awt.FlowLayout;
import java.awt.GridLayout;
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
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiModificarArea extends JFrame{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel consultar, nombre, descripcionArea, areaPadre;
	JTextField campoNombre;
	JComboBox campoAreaPadre, campoConsulta;
	JTextArea campoDescripcionArea;
	Button botonModificarArea;
	GuiCatalogar guiCatalogarModi;
	ManejadorCombo manejadorCombo;
	Vector <AreaConocimiento> vac ;
	String id_area;
	int posicion;
	JPanel panel, panel2, panel3, panel4, panel5, panelPrincipal;

	public GuiModificarArea() {
		super(":::Modificar Area:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
		
	}
	public GuiModificarArea(GuiCatalogar guiCatalogarModi) {
		super(":::Modificar Area:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
		this.guiCatalogarModi=guiCatalogarModi;
		
	}

	public void initComponents() {
		panel = new JPanel();
		panel2 = new JPanel(new GridLayout(3, 2, 10, 10));
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
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Modificar  Area::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.CENTER);
		panelPrincipal.setBorder(borde);
		// ---------------------------------------------
		iniciarLabels();
		// ---------------------------------------------
		iniciarCampos();
		// --------------------------------------------
		panel2.add(consultar);
		panel2.add(campoConsulta);
		panel2.add(nombre);
		panel2.add(campoNombre);
		panel2.add(areaPadre);
		panel2.add(campoAreaPadre);
		panel3.add(panel2);
		JScrollPane scroll = new JScrollPane(campoDescripcionArea);
		
		panel5.add(scroll);

		panel.add(botonModificarArea);

		panel4.add(descripcionArea, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);
		
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel4, BorderLayout.SOUTH);
		add(panelPrincipal);
		setSize(800, 340);
		//setResizable(false);
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
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoNombre.setFocusable(true);
		campoNombre.setEditable(false);
		campoDescripcionArea = new JTextArea(5, 20);
		campoDescripcionArea.addKeyListener(manejador);
		campoDescripcionArea.setEditable(false);
		campoDescripcionArea.setLineWrap(true);
		campoAreaPadre = new JComboBox();
		campoAreaPadre.setEnabled(false);
		botonModificarArea = new Button("Modificar Area");
		botonModificarArea.addActionListener(new ManejadorBoton());
		botonModificarArea.setEnabled(false);
		
		ControladorAreaConocimiento conArea = new ControladorAreaConocimiento();
		vac = conArea.obtenerAreas();
		int cantidad = vac.size();
		
		for(int i = 0; i < cantidad; i++){
			campoAreaPadre.addItem(vac.get(i).getNombre());
			if(i>0){
				campoConsulta.addItem(vac.get(i).getNombre());
			}else{
				campoConsulta.addItem("");
			}
		}
		
	}

	private void iniciarLabels() {
		
		consultar = new JLabel (" Seleccionar Area a modificar");
		nombre = new JLabel("  Nombre Del Area :");
		descripcionArea = new JLabel("Descripcion.", JLabel.CENTER);
		areaPadre = new JLabel("  Area Padre :");
		
		consultar.setFont(Estilos.fontLabels);
		areaPadre.setFont(Estilos.fontLabels);
		descripcionArea.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);

		consultar.setForeground(Estilos.colorLabels);
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
				String padre; 
				if (padreSeleccionado == 0) {
					padre = "";
				} else {
					padre = "" + padreSeleccionado;
				}
				String mensaje="Esta a punto de Modificar el area de conocimeinto:\n";
				mensaje+=nombre+"\n";
				mensaje+="con la descripcion: "+ descripcion+"\n";
				mensaje+="con el area padre: "+campoAreaPadre.getSelectedItem()+"\n";
				mensaje+="¿esta completamente seguro de que desea ingresarla?";
				int value = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Modificar Area de Conocimiento",
						JOptionPane.YES_NO_OPTION);
				//1 para no y 0 para si		
				if(value==0){
					//id = campoConsulta.getSelectedIndex();
					int comprobar  = 0;
					nombre.toLowerCase();
					descripcion.toLowerCase();
					if(!nombre.equals(vac.get(posicion).getNombre())){//si es el mismo nombre no lo actualiza,
						//para que no tire el error ya que el nombre debe ser UNICO
						comprobar= controlador.actualizarArea(id_area, "nombre", nombre);
					}
					comprobar += controlador.actualizarArea(id_area, "descripcion", descripcion);
					comprobar += controlador.actualizarArea(id_area, "area_padre", padre);
										
					if(comprobar >= 2) {
						guiCatalogarModi.vectoresParaComboBox();
						guiCatalogarModi.actualizarAreas();
						JOptionPane.showMessageDialog(null,
								"Se Modifico el Area de Conocimiento correctamente");
						dispose();

					} else {
						JOptionPane.showMessageDialog(null,
								"No se pudo modificar el area de conocimiento", "ERROR",
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
	
	private class ManejadorCombo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==campoConsulta){
				int index = campoConsulta.getSelectedIndex();
				if(index>0){
					posicion=index;//no es -1 porque contiene super vac
					id_area = vac.get(index).getIdArea();
					campoNombre.setText(vac.get(index).getNombre());
					campoNombre.setEditable(true);
					campoDescripcionArea.setText(vac.get(index).getDescripcion());
					campoDescripcionArea.setEditable(true);
					String padre = vac.get(index).getAreaPadre();
					int indexPadre;
					if(padre.isEmpty()){
						indexPadre = 0;
					}else{
						indexPadre = Integer.parseInt(padre);
					}
					campoAreaPadre.setSelectedIndex(indexPadre);
					campoAreaPadre.setEnabled(true);
					botonModificarArea.setEnabled(true);
				}else{
					id_area="";
					campoNombre.setText("");
					campoNombre.setEditable(false);
					campoDescripcionArea.setText("");
					campoDescripcionArea.setEditable(false);
					campoAreaPadre.setSelectedIndex(0);
					campoAreaPadre.setEnabled(false);
					botonModificarArea.setEnabled(false);					
				}
			}
		}
		
		
	}
	
	public static void main (String []args){
		GuiModificarArea a = new GuiModificarArea(new GuiCatalogar("admin"));
		a.setVisible(true);
	}
}
