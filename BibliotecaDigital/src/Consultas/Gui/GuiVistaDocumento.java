package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.sql.Date;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import Consultas.Controlador.ControladorConsulta;
import Consultas.Logica.Consulta;
import Documento.Controlador.ControladorDocumento;
import Documento.Gui.GuiModificarDoc;
import Documento.Logica.Documento;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;
import Principal.Gui.GuiAdministrador;
import Principal.Gui.GuiCatalogador;
import Principal.Gui.GuiPrincipal;
import Principal.Gui.GuiUsuarioNormal;
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
	JLabel autores;
	JLabel palabrasClave;
	JLabel areas;
	
	JLabel volver;
	
	

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
	JPanel panelAreas;
	JPanel panelPalabrasClave;
	JPanel panelAutores;

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
	
	// De acuerdo al usuario que halla intentado consultar podremos saber si puede descargar editar un documento.
	int tipoUsuario ;
	int flag;

	public GuiVistaDocumento(Documento doc) {

		super();
		documento = doc;
		tipoUsuario= GuiConsultaBasica.TIPOUSUARIO;
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder,
						Estilos.colorLightBorder), "::Descripcion Documento::");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);

		flag =0;
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

		panel.add(volver,restriccionEtiquetas);
		panel.add(icono, restriccionCampo);

		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;
		panel.add(tituloPrincipal, restriccionEtiquetas);
		panel.add(campoTituloPrincipal, restriccionCampo);

		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel.add(autores,restriccionEtiquetas);
		panel.add(panelAutores, restriccionCampo);

		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;
		
		
		
		panel.add(areas, restriccionEtiquetas);
		panel.add(panelAreas, restriccionCampo);

		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;
		
		panel.add(palabrasClave, restriccionEtiquetas);
		panel.add(panelPalabrasClave, restriccionCampo);
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

		if(documento.getFormato().equals("jpg")){
	
			panel.add(resolucion, restriccionEtiquetas);		
			panel.add(campoResolucion, restriccionCampo);		
			posEtiquetas++;			
			restriccionEtiquetas.gridy = posEtiquetas;		
			restriccionCampo.gridy = posEtiquetas;
		}
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

	
	
		
		
		JPanel panel2 = new JPanel(new BorderLayout());
		panelAccionesDocumento = new JPanel(new FlowLayout(50,200,50));

		panelAccionesDocumento.add(etiquetaDescargar);
	
		panelAccionesDocumento.add(etiquetaEditarDocumento);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		panel2.add(panel, BorderLayout.NORTH);
		panel2.add(panelAccionesDocumento, BorderLayout.CENTER);
		//panel2.setBorder(borde);
		setBorder(borde);
		// add(panel);
		this.setViewportView(panel2);

		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		//setPreferredSize(new Dimension(500, 600));
		// ------------------------------------------
		/*setSize(300, 300);
		setVisible(true);*/
	}

	private void iniciarLabels() {
		icono = new JLabel();
		ManejadorEtiquetas manejador = new ManejadorEtiquetas();
		if(documento.getFormato().equals("pdf"))
		{
	
			icono.setIcon(new ImageIcon("recursos/iconos/pdf.png"));
		}else if(documento.getFormato().equals("doc"))
		{
			
			icono.setIcon(new ImageIcon("recursos/iconos/doc.png"));
		}else if(documento.getFormato().equals("jpg"))
		{
			
			icono.setIcon(new ImageIcon("recursos/iconos/jpg.png"));
		}else if(documento.getFormato().equals("odt"))
		{
			
			icono.setIcon(new ImageIcon("recursos/iconos/odt.png"));
		}else
		{
			icono.setIcon(new ImageIcon("recursos/iconos/file.png"));
		}
		
		
		System.out.println(documento.getAreas().get(0).getNombre());
		
		panelAreas = new JPanel();
		
		Vector<AreaConocimiento> areasDocumento = documento.getAreas();
		for(int i=0;i< areasDocumento.size();i++)
		{
			JLabel aux = new JLabel(areasDocumento.get(i).getNombre());
			aux.addMouseListener(manejador);
			aux.setForeground(Color.BLUE);
			aux.addMouseListener(manejador);
			panelAreas.add(aux);
			
			
		}
		
		panelAutores = new JPanel();
		Vector<Autor> autoresDocumento = documento.getAutores();
		for(int i=0;i< autoresDocumento.size();i++)
		{
			
			JLabel aux = new JLabel(autoresDocumento.get(i).getApellido());
			aux.addMouseListener(manejador);
			aux.setForeground(Color.BLUE);
			aux.addMouseListener(manejador);
			panelAutores.add(aux);
			
		}
		panelPalabrasClave = new JPanel();
		Vector<PalabraClave> palabrasClaveDocumento= documento.getPalabrasClave();
		for(int i=0;i< palabrasClaveDocumento.size();i++)
		{
			JLabel aux = new JLabel(palabrasClaveDocumento.get(i).getNombre());
			aux.addMouseListener(manejador);
			aux.setForeground(Color.BLUE);
			aux.addMouseListener(manejador);
			panelPalabrasClave.add(aux);
			
			
		}
		
		
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
		fechaCreacion = new JLabel("Fecha de Creación");

		areas = new JLabel("Areas de conocimiento");
		autores = new JLabel("Autor(es)");
		palabrasClave = new JLabel("Palabras clave");
		
		volver = new JLabel("Volver");
		volver.setIcon(new ImageIcon("recursos/iconos/volver.png"));
		volver.addMouseListener(manejador);
		volver.setForeground(Color.BLUE);
		
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
	
		etiquetaDescargar = new JLabel("Descargar");
		etiquetaDescargar.addMouseListener(manejador);
		etiquetaEditarDocumento = new JLabel("Editar Documento");
		etiquetaEditarDocumento.addMouseListener(manejador);

		etiquetaDescargar.setIcon(new ImageIcon("recursos/iconos/downloadfile.png"));
		etiquetaEditarDocumento
				.setIcon(new ImageIcon("recursos/iconos/editar.png"));
		// Se oculta la opcion de editar documento para usuarios no registrados y para usuarios normales.
		if(tipoUsuario == 1 || tipoUsuario == 0){
		
			etiquetaEditarDocumento.setVisible(false);
		}
		etiquetaDescargar.setForeground(Color.BLUE);
		etiquetaEditarDocumento.setForeground(Color.BLUE);

	}

	public class ManejadorEtiquetas implements MouseListener {

		@Override
		public void mouseClicked(MouseEvent evento) {
			
			if(evento.getSource() == etiquetaDescargar)
			{
				
				if(tipoUsuario == 0)
				{
					
					int opcion = JOptionPane.showConfirmDialog(null," Lo sentimos para desargar el documento debe registarse ¿Desea hacerlo?");
			
					if(opcion == 0)
					{
						//JOptionPane.showMessageDialog(null,"Cambiar al panel de registro de usuario (aun no esta )");
						
						
						if(GuiResultadoConsulta.TIPOCONSULTA == 1)
						{
					
							GuiPrincipal.cambiarPanelRegistro();
							GuiConsultaBasica.restaurarTodo();
						}
						else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
						{
							GuiPrincipal.cambiarPanelRegistroAvanzado();
							GuiConsultaAvanzada.restaurarTodo();
						}
					}else
					{
						//JOptionPane.showMessageDialog(null,"No hacer nada");
					}
					//System.out.print(""+opcion);
				}else if(tipoUsuario == 1 || tipoUsuario == 2 ||tipoUsuario == 3 )
				{
					String urlDestino, urlFuente=documento.getUrl();
					JFileChooser manager = new JFileChooser();
					manager.setDialogTitle("Seleccionar destino");
					manager.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
					int returnVal = manager.showSaveDialog(new JFrame());
					if (returnVal == JFileChooser.APPROVE_OPTION) {//si selecciona guardar
						File file = manager.getSelectedFile();
						urlDestino = file.getAbsolutePath();
						
						ControladorDocumento conDoc = new ControladorDocumento();
						conDoc.descargarDocumento(urlFuente, urlDestino);
						
					}
					
					//JOptionPane.showMessageDialog(null,"Empezando a descargar archivo");
					
					
					
				}
				
				
				
				
				
			}else if(evento.getSource() == etiquetaEditarDocumento)
			{
			
				if(GuiConsultaBasica.TIPOUSUARIO == 2)
				{
					GuiCatalogador.panelModificarDoc = new GuiModificarDoc(GuiCatalogador.LOGIN, documento);
					
					if(GuiResultadoConsulta.TIPOCONSULTA == 1)
					{
						GuiCatalogador.cambiarPanelEditarDocumento();
						GuiConsultaBasica.restaurarTodo();
					}
					else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
					{
				
						GuiCatalogador.cambiarPanelEditarDocumentoAvanzado();
						GuiConsultaAvanzada.restaurarTodo();
					}
					
					
				}else if(GuiConsultaBasica.TIPOUSUARIO == 3)
				{
					GuiAdministrador.panelModificarDoc = new GuiModificarDoc(GuiAdministrador.LOGIN, documento);
					
					if(GuiResultadoConsulta.TIPOCONSULTA == 1)
					{
						GuiAdministrador.cambiarPanelEditarDocumento();
						GuiConsultaBasica.restaurarTodo();
					}
					else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
					{
						GuiAdministrador.cambiarPanelEditarDocumentoAvanzado();
						GuiConsultaAvanzada.restaurarTodo();
					}
				}
					
				
				
				
				
					
				
				
				
			}else if (evento.getSource() == volver)
			{
				if(GuiResultadoConsulta.TIPOCONSULTA == 1){
				
			
					GuiConsultaBasica.restaurar();
				
				
				}else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
				{
					
					GuiConsultaAvanzada.restaurar();
				}
			}else {

				JLabel etiquetaAConsultar = (JLabel) evento.getSource();
				
				if (etiquetaAConsultar.getParent() == panelAreas) {
					if (flag == 0) {
					//	JOptionPane.showMessageDialog(null, "Consultar areas: "+etiquetaAConsultar.getText());

						if(GuiResultadoConsulta.TIPOCONSULTA == 1)
						{
							consultar(etiquetaAConsultar.getText());	
						}else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
						{
							consultarAvanzado(etiquetaAConsultar.getText());	
						}
						
						flag = 1;
					} else {
						flag = 0;
					}
				} else if (etiquetaAConsultar.getParent() == panelAutores) {
					if (flag == 0) {
					//	JOptionPane
						//		.showMessageDialog(null, "Consultar autores: "+etiquetaAConsultar.getText());

					
						if(GuiResultadoConsulta.TIPOCONSULTA == 1)
						{
							consultar(etiquetaAConsultar.getText());	
						}else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
						{
							consultarAvanzado(etiquetaAConsultar.getText());	
						}
						
						flag = 1;
					} else {
						flag = 0;
					}
				} else if (etiquetaAConsultar.getParent() == panelPalabrasClave) {
					if (flag == 0) {
						//JOptionPane.showMessageDialog(null,
						//		"Consultar palabras clave: "+etiquetaAConsultar.getText());
						
						if(GuiResultadoConsulta.TIPOCONSULTA == 1)
						{
							consultar(etiquetaAConsultar.getText());	
						}else if(GuiResultadoConsulta.TIPOCONSULTA == 2)
						{
							consultarAvanzado(etiquetaAConsultar.getText());	
						}	

						flag = 1;
					} else {
						flag = 0;
					}
				}

			}
			
			
			
		}

		@Override
		public void mouseEntered(MouseEvent e) {
		
			 JLabel a =   (JLabel) e.getSource();
			 a.setCursor(new Cursor(Cursor.HAND_CURSOR));
			
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
	

	
	private static void consultar(String parametro)
	{
		ControladorConsulta controlador = new ControladorConsulta();
		//mira que se hace con loque retorna
		boolean seleccionBusquedaCompeta = true;
		
		Vector<Consulta> vector = new Vector<Consulta>();
		
		if(!parametro.equals("")){
			vector = null;
			vector = controlador.consultaGeneral(parametro, seleccionBusquedaCompeta);					
		}else{
			JOptionPane.showMessageDialog(null, "Por favor ingrese parametros para la busqueda",
					"No ahi parametros", JOptionPane.ERROR_MESSAGE);
		}
		
		
		int cantidad = 10;
		System.out.println(vector);
		GuiConsultaBasica.resultadoConsulta = new GuiResultadoConsulta(vector,cantidad);
		GuiConsultaBasica.campoConsulta.setText(parametro);
		GuiResultadoConsulta.TIPOCONSULTA = 1;
		GuiConsultaBasica.restaurar();
		//GuiConsultaBasica.panel.add(GuiConsultaBasica.resultadoConsulta, BorderLayout.CENTER);
		GuiConsultaBasica.panel.updateUI();
		if(vector.size() <=0 && !parametro.equals("")){
			
			JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
			
		}	
		
	}
	
	private static void consultarAvanzado(String parametro)
	{
		ControladorConsulta controlador = new ControladorConsulta();
		//mira que se hace con loque retorna
		boolean seleccionBusquedaCompeta = true;
		
		Vector<Consulta> vector = new Vector<Consulta>();
		
		if(!parametro.equals("")){
			vector = null;
			vector = controlador.consultaGeneral(parametro, seleccionBusquedaCompeta);					
		}else{
			JOptionPane.showMessageDialog(null, "Por favor ingrese parametros para la busqueda",
					"No ahi parametros", JOptionPane.ERROR_MESSAGE);
		}
		
		
		int cantidad = 10;
		System.out.println(vector);
		GuiConsultaBasica.resultadoConsulta = new GuiResultadoConsulta(vector,cantidad);
		GuiConsultaBasica.campoConsulta.setText(parametro);
		GuiResultadoConsulta.TIPOCONSULTA = 1;
		GuiConsultaBasica.panel.add(GuiConsultaBasica.resultadoConsulta, BorderLayout.CENTER);
		GuiConsultaAvanzada.restaurarTodo();
		
		if(GuiConsultaAvanzada.TIPOUSUARIO == 0)
		{
			GuiPrincipal.cambiarAvanzadaInicio();
		}
		else if(GuiConsultaAvanzada.TIPOUSUARIO == 1)
		{
			GuiUsuarioNormal.cambiarAvanzadaInicio();
		}else if(GuiConsultaAvanzada.TIPOUSUARIO == 2)
		{
			GuiCatalogador.cambiarAvanzadaInicio();
		}else if(GuiConsultaAvanzada.TIPOUSUARIO == 3)
		{
			GuiAdministrador.cambiarAvanzadaInicio();
		}
		GuiConsultaBasica.panel.updateUI();
		if(vector.size() <=0 && !parametro.equals("")){
			
			JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
			
		}	
		
	}
	public static void main(String[] args) {
		
	
		
	
		
		JFrame ventana = new JFrame();
		Container a = ventana.getContentPane();

		Documento doc = new Documento("112", "Ingles", "Ni idea",
				"Aplicacion de la teoria de grafos en la busqueda del camino mas corto entre dos host" ,"Adobe Reader", "1024*800",
				"No tiene" ,"pdf","Enrutamiento de paquetes",
				"Routers basicos", "aca.pdf", new Date(555555550),
				 new Date(1121540), new Date(1555555550),
				"marianito", "Articulo",
				new Vector<Autor>(), new Vector<AreaConocimiento>(),
				new Vector<PalabraClave>());
		ControladorConsulta cd = new ControladorConsulta();
		Documento doc2 = cd.obtenerDatosDocumento("10000");
		
		a.add(new GuiVistaDocumento(doc2));
		ventana.setSize(450, 450);
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.setVisible(true);

	}
}
