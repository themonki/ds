package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Documento.Logica.Documento;
import Utilidades.Estilos;

public class GuiVistaDocumento extends JScrollPane {

	private static final long serialVersionUID = 1L;

	// Etiquetas con los datos correspondientes al documento.
	JLabel tituloPrincipal;
	JLabel tituloSecundario;
	JLabel idioma;
	JLabel editorial;
	JLabel derechosAutor;
	JLabel descripcion;
	JLabel resolucion;
	JLabel formato;
	JLabel softwareRecomendado;
	JLabel fechaPublicacion;
	JLabel fechaCreacion;

	// Campos para el contenido de cada uno de los datos correspondientes al
	// documento.
	JLabel campoTituloPrincipal;
	JLabel campoTituloSecundario;
	JLabel campoIdioma;
	JLabel campoEditorial;
	JLabel campoDerechosAutor;
	JLabel campoDescripcion;
	JLabel campoResolucion;
	JLabel campoFormato;
	JLabel campoSoftwareRecomendado;
	JLabel campoFechaPublicacion;
	JLabel campoFechaCreacion;

	// Panel general que contiene todos los elementos
	JPanel panel;
	// Boton que permite descargar en caso de ser un usuario normal,
	// administrador o catalogador.
	JLabel etiquetaDescargar;
	JLabel etiquetaEditarDocumento;

	JLabel icono;

	// Panel para las acciones especiales
	JPanel panelAccionesDocumento;
	
	Documento documento;

	GuiVistaDocumento(Documento doc) {

		super();
		documento = doc;
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder,
						Estilos.colorLightBorder), "::Descripcion Documento::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);

		panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints restriccionEtiquetas = new GridBagConstraints();
		GridBagConstraints restriccionCampo = new GridBagConstraints();

		restriccionEtiquetas.insets = new Insets(10, 10, 0, 0);// espacios entre
																// componentes
		restriccionEtiquetas.anchor = GridBagConstraints.WEST;// alinear a la
																// izquierda
		int posEtiquetas = 0;

		restriccionCampo.ipadx = 50;
		restriccionCampo.weightx = 10;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(1, 50, 1, 0);

		iniciarLabels();

		panel.add(icono, restriccionCampo);

		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;
		panel.add(tituloPrincipal, restriccionEtiquetas);
		panel.add(campoTituloPrincipal, restriccionCampo);

		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(tituloSecundario, restriccionEtiquetas);
		panel.add(campoTituloSecundario, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(idioma, restriccionEtiquetas);
		panel.add(campoIdioma, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(editorial, restriccionEtiquetas);
		panel.add(campoEditorial, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(derechosAutor, restriccionEtiquetas);
		panel.add(campoDerechosAutor, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(resolucion, restriccionEtiquetas);
		panel.add(campoResolucion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(formato, restriccionEtiquetas);
		panel.add(campoFormato, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(softwareRecomendado, restriccionEtiquetas);
		panel.add(campoSoftwareRecomendado, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(fechaPublicacion, restriccionEtiquetas);
		panel.add(campoFechaPublicacion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(fechaCreacion, restriccionEtiquetas);
		panel.add(campoFechaCreacion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(descripcion, restriccionEtiquetas);
		panel.add(campoDescripcion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		// panel.add(descargar);
		JPanel panel2 = new JPanel(new BorderLayout());
		panelAccionesDocumento = new JPanel();

		panelAccionesDocumento.add(etiquetaDescargar);
		panelAccionesDocumento.add(etiquetaEditarDocumento);

		panel2.add(panel, BorderLayout.NORTH);
		panel2.add(panelAccionesDocumento, BorderLayout.CENTER);
		panel2.setBorder(borde);
		// add(panel);
		this.setViewportView(panel2);

		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

		// ------------------------------------------
		setSize(450, 250);
		setVisible(true);
	}

	private void iniciarLabels() {
		icono = new JLabel();
		icono.setIcon(new ImageIcon("recursos/iconos/Pdf.gif"));
		// etiquetas
		tituloPrincipal = new JLabel("Titulo Principal");
		tituloSecundario = new JLabel("Titulo Secundario o Traduccion");
		idioma = new JLabel("Idioma");
		editorial = new JLabel("Editorial");
		derechosAutor = new JLabel("Derechos de Autor");
		descripcion = new JLabel("Descripcion");
		resolucion = new JLabel("Resolucion");
		formato = new JLabel("Formato");
		softwareRecomendado = new JLabel("Software Recomendado para Editar");
		fechaPublicacion = new JLabel("Fecha de Publicacion");
		fechaCreacion = new JLabel("Fecha de Catalogación");

		// Donde se mostraran los resultados.
		campoTituloPrincipal = new JLabel();
		campoTituloPrincipal.setText(documento.getTituloppal());
		campoTituloSecundario =new JLabel();
		campoTituloSecundario.setText(documento.getTitulo_secundario());
		campoIdioma = new JLabel();
		campoIdioma.setText(documento.getIdioma());
		
		
		campoEditorial = new JLabel();
		campoEditorial.setText(documento.getEditorial());
		campoDerechosAutor = new JLabel();
		campoDerechosAutor.setText(documento.getDerechosDeAutor());
		campoDescripcion = new JLabel();
		campoDescripcion.setText(documento.getDescripcion());
		campoResolucion = new JLabel();
		campoResolucion.setText(documento.getResolucion());
		campoFormato = new JLabel();
		campoFormato.setText(documento.getFormato());
		campoSoftwareRecomendado = new JLabel();
		campoSoftwareRecomendado.setText(documento.getSoftware_recomentado());
		campoFechaPublicacion = new JLabel();
		campoFechaPublicacion.setText(""+documento.getFecha_publicacion());
		campoFechaCreacion = new JLabel();
		campoFechaCreacion.setText(""+documento.getFecha_creacion());

		// Etiquetas funcionales de acuerdo al tipo de usuario;
		ManejadorEtiquetas manejador = new ManejadorEtiquetas();
		etiquetaDescargar = new JLabel("Descargar");
		etiquetaDescargar.addMouseListener(manejador);
		etiquetaEditarDocumento = new JLabel("Editar Documento");
		etiquetaEditarDocumento.addMouseListener(manejador);

		etiquetaDescargar.setIcon(new ImageIcon("recursos/iconos/add.png"));
		etiquetaEditarDocumento
				.setIcon(new ImageIcon("recursos/iconos/add.png"));
		etiquetaEditarDocumento.setVisible(false);
		etiquetaDescargar.setForeground(Color.BLUE);
		etiquetaEditarDocumento.setForeground(Color.BLUE);

	}

	public class ManejadorEtiquetas implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent e) {
			JOptionPane.showMessageDialog(null,
					"Empezando a descargar el documento");
			etiquetaEditarDocumento.setVisible(true);
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseExited(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mousePressed(MouseEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void mouseReleased(MouseEvent e) {
			// TODO Auto-generated method stub

		}

	}

	public static void main(String[] args) {
		JFrame ventana = new JFrame();
		Container a = ventana.getContentPane();

		a.add(new GuiVistaDocumento(new Documento()));
		ventana.setSize(450, 450);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);

	}
}
