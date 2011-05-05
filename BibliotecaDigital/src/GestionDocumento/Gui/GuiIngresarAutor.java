package GestionDocumento.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;

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

public class GuiIngresarAutor extends JFrame {
	JLabel nombre, apellido, indicacion, correoElectronico, acronimo;
	JTextField campoNombre, campoApellido, campoCorreoElectronico,
			campoAcronimo;
	JComboBox campoAreaPadre;
	JButton botonIngresarAutor;

	GuiIngresarAutor() {
		initComponents();
	}

	public void initComponents() {
		JPanel panel = new JPanel();
		JPanel panel2 = new JPanel(new GridBagLayout());
		JPanel panel3 = new JPanel();
		// --------------------------------------------------------
		panel2.setBorder(BorderFactory.createLineBorder(Color.yellow));
		panel.setBorder(BorderFactory.createLineBorder(Color.yellow));
		// ---------------------------------------------------------
		iniciarLabels();
		// ---------------------------------------------------------
		iniciarCampos();
		// ----------------------------------------------------------

		GridBagConstraints restriccionCampo = new GridBagConstraints(), restriccionEtiquetas = new GridBagConstraints();

		restriccionCampo.ipadx = 10;
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(5, 40, 5, 0);

		restriccionEtiquetas.gridy = 0;
		restriccionEtiquetas.gridx = 0;

		restriccionEtiquetas.anchor = GridBagConstraints.WEST;
		restriccionCampo.anchor = GridBagConstraints.WEST;

		panel2.add(nombre, restriccionEtiquetas);
		panel2.add(campoNombre, restriccionCampo);

		restriccionEtiquetas.gridy = 1;
		restriccionCampo.gridy = 1;

		panel2.add(apellido, restriccionEtiquetas);
		panel2.add(campoApellido, restriccionCampo);

		restriccionEtiquetas.gridy = 2;
		restriccionCampo.gridy = 2;

		panel2.add(acronimo, restriccionEtiquetas);
		panel2.add(campoAcronimo, restriccionCampo);

		restriccionEtiquetas.gridy = 3;
		restriccionCampo.gridy = 3;

		panel2.add(correoElectronico, restriccionEtiquetas);
		panel2.add(campoCorreoElectronico, restriccionCampo);

		panel3.add(panel2);

		panel.add(botonIngresarAutor);

		setLayout(new BorderLayout());
		add(panel3, BorderLayout.CENTER);
		add(indicacion, BorderLayout.NORTH);
		add(panel, BorderLayout.SOUTH);

		setSize(470, 280);
		setVisible(true);
	}

	private void iniciarCampos() {
		campoNombre = new JTextField(20);
		campoApellido = new JTextField(20);
		campoCorreoElectronico = new JTextField(17);
		campoAcronimo = new JTextField(12);

		campoAreaPadre = new JComboBox();
		botonIngresarAutor = new JButton("Ingresar Autor");

	}

	private void iniciarLabels() {
		Font font1 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 17);
		Font font3 = new Font("Book Antiqua", Font.BOLD + Font.ITALIC, 25);

		Color colorletras = new Color(0, 60, 0);

		indicacion = new JLabel("Registrar  Autor", JLabel.CENTER);
		nombre = new JLabel("Nombre  :");
		apellido = new JLabel("Apellido :");
		correoElectronico = new JLabel("Correo Electronico :");
		acronimo = new JLabel("Acronimo :");

		indicacion.setFont(font3);
		apellido.setFont(font1);
		nombre.setFont(font1);
		acronimo.setFont(font1);
		correoElectronico.setFont(font1);

		indicacion.setForeground(colorletras);
		apellido.setForeground(colorletras);
		nombre.setForeground(colorletras);
		acronimo.setForeground(colorletras);
		correoElectronico.setForeground(colorletras);

	}

	public static void main(String args[]) {

		try {

			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel");

		} catch (Exception e) {
			e.printStackTrace();
		}

		GuiIngresarAutor ventana;
		ventana = new GuiIngresarAutor();
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	}

}