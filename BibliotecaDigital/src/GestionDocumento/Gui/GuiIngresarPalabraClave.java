package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.UIManager;
import GestionDocumento.Controlador.*;
public class GuiIngresarPalabraClave extends JFrame {

	JLabel nombre, descripcion, indicacion;
	JTextField campoNombre;
	JTextArea campoDescripcion;
	JButton botonIngresarPalabra;

	JPanel panel1, panel, panel2, panel3;

	public GuiIngresarPalabraClave() {
		initComponents();
	}

	public void initComponents() {
		// ---------------------------------------------------------------
		JPanel panel5 = new JPanel(new FlowLayout()), panel4 = new JPanel(
				new BorderLayout()), panel = new JPanel();
		JPanel panel2 = new JPanel(new GridLayout(1, 1, 10, 10));
		JPanel panel3 = new JPanel();
		// ----------------------------------------------------------------
		panel2.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel5.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel.setBorder(BorderFactory.createLineBorder(Color.yellow));
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
		setVisible(true);
	}

	private void iniciarCampos() {
		campoNombre = new JTextField(15);
		campoDescripcion = new JTextArea(5, 20);
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
			ControladorPalabraClave conPalabra = new ControladorPalabraClave();
			conPalabra.insertarPalabraClave(campoNombre.getText(), campoDescripcion.getText());
			dispose();
		}		
	}


	public static void main(String args[]) {

		try {

			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}

		GuiIngresarPalabraClave ventana;
		ventana = new GuiIngresarPalabraClave();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}