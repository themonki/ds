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
import GestionDocumento.Controlador.ControladorPalabraClave;
import GestionDocumento.Logica.PalabraClave;
import Utilidades.Button;
import Utilidades.Estilos;

public class GuiModificarPalabraClave extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	JLabel consultar, nombre, descripcion;
	JTextField campoNombre;
	JTextArea campoDescripcion;
	JComboBox campoConsulta;
	Button botonModificarPalabra;
	Vector <PalabraClave> vpc;
	int pos_pc;
	ManejadorCombo manejadorCombo;
	JPanel panel1, panel, panel2, panel3;
	
	GuiCatalogar guicatalogarModi ;

	public GuiModificarPalabraClave() {
		super(":::Modificar Palabra Clave:::");
		setIconImage(new ImageIcon("recursos/iconos/add.png").getImage());
		initComponents();
	}
	
	public GuiModificarPalabraClave(GuiCatalogar guicatalogarModi) {
		super(":::Modificar Palabra Clave:::");
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
			    .createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "::Modificar Palabra Clave::");
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
		
		panel2.add(consultar);
		panel2.add(campoConsulta);
		//panel2.add(nombre);
		//panel2.add(campoNombre);
		panel3.add(panel2);

		JScrollPane scroll = new JScrollPane(campoDescripcion);
		
		panel5.add(scroll);

		panel.add(botonModificarPalabra);

		panel4.add(descripcion, BorderLayout.NORTH);
		panel4.add(panel5, BorderLayout.CENTER);
		panel4.add(panel, BorderLayout.SOUTH);

		setLayout(new GridBagLayout());
		panelPrincipal.add(panel3, BorderLayout.CENTER);
		panelPrincipal.add(panel4, BorderLayout.SOUTH);
		add(panelPrincipal);
		setSize(600, 300);
		setResizable(false);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		//centrar en la pantalla
		Dimension screenSize = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation((screenSize.width)/2-getWidth()/2,(screenSize.height)/2-getHeight()/2);
		setVisible(true);
	}

	private void iniciarCampos() {
		ManejadorJTextField manejador  = new ManejadorJTextField();
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
		botonModificarPalabra = new Button("Modificar Palabra");
		botonModificarPalabra.addActionListener(new ManejadorBoton());
		botonModificarPalabra.setEnabled(false);
		
		ControladorPalabraClave conPalabra = new ControladorPalabraClave();
		vpc = conPalabra.obtenerPalabrasClave();
		int cantidad = vpc.size();
		campoConsulta.addItem("");
		for(int i = 0; i < cantidad; i++){
			campoConsulta.addItem(vpc.get(i).getNombre());
		}

	}

	private void iniciarLabels() {
		
		consultar = new JLabel ("Seleccione la Palabra Clave ");
		nombre = new JLabel("  Palabra Clave: ");
		descripcion = new JLabel("Descripcion.", JLabel.CENTER);
		
		consultar.setFont(Estilos.fontLabels);
		descripcion.setFont(Estilos.fontLabels);
		nombre.setFont(Estilos.fontLabels);
		
		consultar.setForeground(Estilos.colorLabels);
		descripcion.setForeground(Estilos.colorLabels);
		nombre.setForeground(Estilos.colorLabels);
	}
	
	private class ManejadorBoton implements ActionListener {
		
		public void actionPerformed(ActionEvent arg0) {
			
			
			if(validarDatos()){
				String mensaje="Esta a punto de modificar la palabra clave:\n";
				mensaje+=campoNombre.getText()+"\n";
				mensaje+="con la descripcion: "+ campoDescripcion.getText()+"\n";
				mensaje+="¿esta completamente seguro de que desea modificarla?";
				int value = JOptionPane.showConfirmDialog(null, mensaje, "Confirmar Modifcar Palabra CLave",
						JOptionPane.YES_NO_OPTION);
				//1 para no y 0 para si		
				if(value==0){
					ControladorPalabraClave conPalabra = new ControladorPalabraClave();
					PalabraClave pc = new PalabraClave();
					pc.setNombre((String)campoConsulta.getSelectedItem());
					pc.setDescripcion(campoDescripcion.getText());
					conPalabra.actualizarPalabraClave(pc);
					if(conPalabra.actualizarPalabraClave(pc)>=1)
					{
						guicatalogarModi.vectoresParaComboBox();
						guicatalogarModi.actualizarPalabras();
						JOptionPane.showMessageDialog(null, "Se Modifico la Palabra Clave correctamente");
						dispose();
					}else{
						JOptionPane.showMessageDialog(null, "No se pudo actualizar la Palabra Clave","ERROR", JOptionPane.ERROR_MESSAGE);
					}
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
	
	private class ManejadorCombo implements ActionListener{
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==campoConsulta){
				int index = campoConsulta.getSelectedIndex();
				if(index>0){
					pos_pc = index-1;
					campoNombre.setText(vpc.get(pos_pc).getNombre());
					campoNombre.setEditable(true);
					campoDescripcion.setText(vpc.get(pos_pc).getDescripcion());
					campoDescripcion.setEditable(true);
					botonModificarPalabra.setEnabled(true);
				}else{
					pos_pc = 0;
					campoNombre.setText("");
					campoNombre.setEditable(true);
					campoDescripcion.setText("");
					campoDescripcion.setEditable(true);
					
					botonModificarPalabra.setEnabled(false);
					
				}
			}
		}
	}
	
	public static void main(String []args){
		GuiModificarPalabraClave a = new GuiModificarPalabraClave(new GuiCatalogar("admin"));
		a.setVisible(true);
	}
}
