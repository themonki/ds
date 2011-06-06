 /**
 * GuiModificarTipoMaterial.java
 * 
 * Clase que representa la interfaz que permite modificar un Tipo
 * de Material de los documentos digitales de la Biblioteca Digital.
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
import java.awt.GridBagLayout;
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
import GestionDocumento.Controlador.ControladorTipoMaterial;
import GestionDocumento.Logica.TipoMaterial;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiModificarTipoMaterial extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel consultar, nombre, descripcion;
	JTextField campoNombre;
	JComboBox campoConsulta;
	JTextArea campoDescripcion;
	Button botonModificarTipo;
	Vector <TipoMaterial> vtp;
	int pos_tp;
	ManejadorCombo manejadorCombo;

	JPanel panel, panel1, panel2, panel3, panel4, panel5, panelPrincipal;
	
	GuiCatalogar guicatalogarModi;

	public GuiModificarTipoMaterial() {
		super("::Modificar Tipo Material::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
	}
	public GuiModificarTipoMaterial(GuiCatalogar guicatalogarModi) {
		super("::Modificar Tipo Material::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		this.guicatalogarModi=guicatalogarModi;
		initComponents();
	}

	public void initComponents() {
		panel2 = new JPanel(new GridLayout(1, 1, 1, 1));
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
		//panel2.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		//panel5.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		//panel.setBorder(BorderFactory.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder));
		// ------------------------------------------------------------
		iniciarLabels();
		iniciarCampos();
		// -------------------------------------------------------------
		panel2.add(consultar);
		panel2.add(campoConsulta);
		//panel2.add(nombre);
		//panel2.add(campoNombre);
		panel3.add(panel2);

		JScrollPane scroll = new JScrollPane(campoDescripcion);
		
		panel5.add(scroll);

		panel.add(botonModificarTipo);

		panel4.add(descripcion, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);

		setLayout(new GridBagLayout());
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel4, BorderLayout.SOUTH);
		add(panelPrincipal);
		setSize(650, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarLabels() {
		
		consultar = new JLabel("Seleccione el Tipo de Material: ");
		nombre = new JLabel(" Nombre Del Tipo: ");
		descripcion = new JLabel(" Descripcion.", JLabel.CENTER);
		
		consultar.setFont(Estilos.fontLabels);
		descripcion.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);
		
		consultar.setForeground(Estilos.colorLabels);
		descripcion.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);

	}

	private void iniciarCampos() {
		ManejadorJTextField manejador = new ManejadorJTextField();
		manejadorCombo = new ManejadorCombo();
		campoConsulta = new JComboBox();
		campoConsulta.addActionListener(manejadorCombo);
		campoNombre = new JTextField(15);
		campoNombre.addKeyListener(manejador);
		campoNombre.setEditable(false);
		campoDescripcion = new JTextArea(5, 20);
		campoDescripcion.addKeyListener(manejador);
		campoDescripcion.setEditable(false);
		campoDescripcion.setLineWrap(true);
		botonModificarTipo = new Button("Registrar Tipo Material");
		botonModificarTipo.addActionListener(new ManejadorBoton());
		botonModificarTipo.setEnabled(false);
		
		ControladorTipoMaterial conTipo = new ControladorTipoMaterial();
		vtp = conTipo.obtenerTiposMateriales();
		int cantidad = vtp.size();
		campoConsulta.addItem("");
		for(int i = 0; i < cantidad; i++){
			campoConsulta.addItem(vtp.get(i).getNombre());
		}

	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			if(validarDatos()){
				String mensaje="Esta a punto de modificar el tipo de material:\n";
				mensaje+=campoNombre.getText()+"\n";
				mensaje+="con la descripcion: "+ campoDescripcion.getText()+"\n";
				mensaje+="¿esta completamente seguro de que desea modificarlo?";
				int value = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Modificar tipo de material",
						JOptionPane.YES_NO_OPTION);
				//1 para no y 0 para si		
				if(value==0){
					ControladorTipoMaterial conMaterial = new ControladorTipoMaterial();
					TipoMaterial tp = new TipoMaterial();
					tp.setNombre((String)campoConsulta.getSelectedItem());
					tp.setDescripcion(campoDescripcion.getText());
					if(conMaterial.actualizarTipoMaterial(tp)>=1)
					{
						guicatalogarModi.vectoresParaComboBox();
						guicatalogarModi.actualizarTipoMaterial();
						JOptionPane.showMessageDialog(null, "Se modifico el Tipo de Material Correctamente");
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "No se pudo modificar el Tipo de Material","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		}		
	}
	
	private class ManejadorJTextField implements KeyListener{

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

		public void keyReleased(KeyEvent e) {			
		}

		public void keyTyped(KeyEvent e) {			
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
	
	private class ManejadorCombo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==campoConsulta){
				int index = campoConsulta.getSelectedIndex();
				if(index>0){
					pos_tp = index-1;
					campoNombre.setText(vtp.get(pos_tp).getNombre());
					campoNombre.setEditable(true);
					campoDescripcion.setText(vtp.get(pos_tp).getDescripcion());
					campoDescripcion.setEditable(true);
					botonModificarTipo.setEnabled(true);
				}else{
					pos_tp = 0;
					campoNombre.setText("");
					campoNombre.setEditable(true);
					campoDescripcion.setText("");
					campoDescripcion.setEditable(true);
					
					botonModificarTipo.setEnabled(false);
					
				}
			}
		}
	}

	public static void main(String[] args) {
		GuiModificarTipoMaterial a = new GuiModificarTipoMaterial(new GuiCatalogar("admin"));
		a.setVisible(true);
	}

}
