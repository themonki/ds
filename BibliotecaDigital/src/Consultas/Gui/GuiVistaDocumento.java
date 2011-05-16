package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;


import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

import Documento.Logica.Documento;

public class GuiVistaDocumento extends JScrollPane{
	
	JLabel tituloPrincipal, tituloSecundario, idioma, editorial, derechosAutor, descripcion,
	resolucion, formato, softwareRecomendado, fechaPublicacion, fechaCreacion;
	
	JTextField campoTituloPrincipal, campoTituloSecundario, campoIdioma, campoEditorial,
	campoDerechosAutor, campoDescripcion, campoResolucion, campoFormato, campoSoftwareRecomendado,
	campoFechaPublicacion, campoFechaCreacion;
	JButton botonDescargar;
	
	GuiVistaDocumento(Documento doc){
		
		super();
		tituloPrincipal=new JLabel("Titulo Principal");
		tituloSecundario=new JLabel("Titulo Secundario o Traduccion");
		idioma=new JLabel("Idioma");
		editorial=new JLabel("Editorial");
		derechosAutor=new JLabel("Derechos de Autor");
		descripcion=new JLabel("Descripcion");		
		resolucion=new JLabel("Resolucion");
		formato=new JLabel("Formato");
		softwareRecomendado=new JLabel("Software Recomendado para Editar");
		fechaPublicacion=new JLabel("Fecha de Publicacion");
		fechaCreacion=new JLabel("Fecha de Catalogación");
		
		campoTituloPrincipal=new JTextField();
		campoTituloSecundario=new JTextField();
		campoIdioma=new JTextField();
		campoEditorial=new JTextField();
		campoDerechosAutor=new JTextField();
		campoDescripcion=new JTextField();
		campoResolucion=new JTextField();
		campoFormato=new JTextField();
		campoSoftwareRecomendado=new JTextField();
		campoFechaPublicacion=new JTextField();
		campoFechaCreacion=new JTextField();
		
		campoTituloPrincipal.setEditable(false);
		campoTituloSecundario.setEditable(false); 
		campoIdioma.setEditable(false);
		campoEditorial.setEditable(false);
		campoDerechosAutor.setEditable(false);
		campoDescripcion.setEditable(false);
		campoResolucion.setEditable(false);
		campoFormato.setEditable(false);
		campoSoftwareRecomendado.setEditable(false);
		campoFechaPublicacion.setEditable(false);
		campoFechaCreacion.setEditable(false);
		
		botonDescargar=new JButton("Descargar");
		
		JPanel panel = new JPanel();
		panel.setLayout(new GridBagLayout());
		GridBagConstraints restriccionEtiquetas = new GridBagConstraints();
		GridBagConstraints restriccionCampo = new GridBagConstraints();
		
		restriccionEtiquetas.insets= new Insets(10,10,0,0);// espacios entre componentes
		restriccionEtiquetas.anchor=GridBagConstraints.WEST;//alinear a la izquierda
		int posEtiquetas = 0;
		
		restriccionCampo.ipadx = 360;      		
		restriccionCampo.weightx = 10.0;
		restriccionCampo.gridwidth = 2;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(1,40,1,0);
		
		restriccionEtiquetas.gridy=posEtiquetas;		
		panel.add(tituloPrincipal, restriccionEtiquetas);
		panel.add(campoTituloPrincipal, restriccionCampo);
		
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(tituloSecundario, restriccionEtiquetas);
		panel.add(campoTituloSecundario, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(idioma, restriccionEtiquetas);
		panel.add(campoIdioma, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(editorial, restriccionEtiquetas);
		panel.add(campoEditorial, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(derechosAutor, restriccionEtiquetas);
		panel.add(campoDerechosAutor, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;		
		
		panel.add(resolucion, restriccionEtiquetas);
		panel.add(campoResolucion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(formato, restriccionEtiquetas);
		panel.add(campoFormato, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(softwareRecomendado, restriccionEtiquetas);
		panel.add(campoSoftwareRecomendado, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(fechaPublicacion, restriccionEtiquetas);
		panel.add(campoFechaPublicacion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(fechaCreacion, restriccionEtiquetas);
		panel.add(campoFechaCreacion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		panel.add(descripcion, restriccionEtiquetas);
		panel.add(campoDescripcion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy=posEtiquetas;
		restriccionCampo.gridy=posEtiquetas;
		
		//panel.add(descargar);
		JPanel panel2 = new JPanel(new BorderLayout());
		panel2.add(panel);
		//add(panel);
		this.setViewportView(panel2);
		
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//------------------------------------------
		setSize(900,900);
		setVisible(true);
	}
	
	
	public static void main (String []args){
		JFrame ventana = new JFrame();
		Container a = ventana.getContentPane();
		
		a.add(new GuiVistaDocumento(new Documento()));
		ventana.setSize(600, 800);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);
		
	}
}


