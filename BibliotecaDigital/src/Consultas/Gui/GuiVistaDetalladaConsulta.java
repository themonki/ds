 /**
 * GuiVistaDetalladaConsulta.java
 * 
 * Clase que representa la interfaz que permite mostrar el deta-
 * lle de un documento seleccionado despues de realizar una con-
 * sulta de los documentos digitales de la Biblioteca Digital, 
 * y dependiendo del perfil permite la descarga y/o modificación
 * de dicho documento.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:  
 * Version:   4.0
 */
package Consultas.Gui;

import java.awt.BorderLayout;

import java.awt.Color;

import java.awt.Cursor;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
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
import Usuarios.Gui.GuiNovedades;
import Utilidades.Estilos;



public class GuiVistaDetalladaConsulta extends JScrollPane {

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


	
	Documento documento;
	
	// De acuerdo al usuario que halla intentado consultar podremos saber si puede descargar editar un documento.
	int tipoUsuario ;
	int flag;
	static boolean CONSULTANDO_AUTORES = false;

	public GuiVistaDetalladaConsulta(Documento doc) {

		super();
		documento = doc;
		if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
		{
			tipoUsuario= GuiConsultaBasica.TIPO_USUARIO;
		}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
		{
			tipoUsuario= GuiConsultaAvanzada.TIPO_USUARIO;
		}else if(GuiResultadoConsulta.TIPO_CONSULTA == 3)
		{
			tipoUsuario= GuiNovedades.TIPO_USUARIO;
		}
		
		
		
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

		restriccionEtiquetas.insets = new Insets(1, 20, 10, 50);// espacios entre
		restriccionEtiquetas.gridy=0;
		restriccionEtiquetas.gridx=0;
		restriccionEtiquetas.anchor = GridBagConstraints.WEST;// alinear a la
																// izquierda
		restriccionCampo.anchor = GridBagConstraints.WEST;// alinear a la
		
		
		int posEtiquetas = 0;

		restriccionCampo.ipadx = 50;
		restriccionCampo.gridx = 1;
		restriccionCampo.gridy = 0;
		restriccionCampo.insets = new Insets(1, 50, 10, 50);

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


		panel.add(tituloSecundario, restriccionEtiquetas);
		panel.add(campoTituloSecundario, restriccionCampo);
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

		panel.add(fechaCreacion, restriccionEtiquetas);
		panel.add(campoFechaCreacion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;
		
		
		panel.add(fechaPublicacion, restriccionEtiquetas);
		panel.add(campoFechaPublicacion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		

		panel.add(descripcion, restriccionEtiquetas);
		panel.add(campoDescripcion, restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;

		
	
		
		
		

		panel.add(etiquetaDescargar, restriccionEtiquetas);	
		panel.add(etiquetaEditarDocumento,restriccionCampo);
		posEtiquetas++;
		restriccionEtiquetas.gridy = posEtiquetas;
		restriccionCampo.gridy = posEtiquetas;


		//panel2.setBorder(borde);
		setBorder(borde);
		// add(panel);
		this.setViewportView(panel);

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
		
		
		
		
		panelAreas = new JPanel(new GridLayout());
		panelAreas.setLayout(new BoxLayout(panelAreas, BoxLayout.Y_AXIS));
		
		Vector<AreaConocimiento> areasDocumento = documento.getAreas();
		for(int i=0;i< areasDocumento.size();i++)
		{
			JLabel aux = new JLabel(areasDocumento.get(i).getNombre());
			aux.setFont(Estilos.fontSubrayados);
			aux.setForeground(Estilos.colorSubtitulo);
			aux.addMouseListener(manejador);
			aux.setForeground(Color.blue);
			aux.addMouseListener(manejador);
			panelAreas.add(aux);
			
			
		}
		
		panelAutores = new JPanel();
		
		panelAutores.setLayout(new BoxLayout(panelAutores, BoxLayout.Y_AXIS));
		
		Vector<Autor> autoresDocumento = documento.getAutores();
		for(int i=0;i< autoresDocumento.size();i++)
		{
			
			JLabel aux = new JLabel(autoresDocumento.get(i).getNombre()+" "+autoresDocumento.get(i).getApellido());
			aux.setFont(Estilos.fontSubtitulos);
			aux.setForeground(Estilos.colorSubtitulo);
			aux.addMouseListener(manejador);
			
			aux.setForeground(Color.BLUE);
			aux.addMouseListener(manejador);
			aux.setAlignmentY(JLabel.WEST);
			panelAutores.add(aux);
			
		}
		panelPalabrasClave = new JPanel();
		
		panelPalabrasClave.setLayout(new BoxLayout(panelPalabrasClave, BoxLayout.Y_AXIS));
		
		Vector<PalabraClave> palabrasClaveDocumento= documento.getPalabrasClave();
		for(int i=0;i< palabrasClaveDocumento.size();i++)
		{
			JLabel aux = new JLabel(palabrasClaveDocumento.get(i).getNombre());
			aux.setFont(Estilos.fontSubtitulos);
			aux.setForeground(Estilos.colorSubtitulo);
			aux.addMouseListener(manejador);
			aux.setForeground(Color.BLUE);
			aux.addMouseListener(manejador);
			panelPalabrasClave.add(aux);
			
			
		}
		
		
		// etiquetas
		tituloPrincipal = new JLabel("Titulo Principal :");
		tituloPrincipal.setFont(Estilos.fontLabels);
		tituloPrincipal.setForeground(Estilos.colorLabels2);
		
		tituloSecundario = new JLabel("Titulo Secundario o Traduccion :");
		tituloSecundario.setFont(Estilos.fontLabels);
		tituloSecundario.setForeground(Estilos.colorLabels2);
		
		idioma = new JLabel("Idioma :");
		idioma.setFont(Estilos.fontLabels);
		idioma.setForeground(Estilos.colorLabels2);
		
		editorial = new JLabel("Editorial :");
		editorial.setFont(Estilos.fontLabels);
		editorial.setForeground(Estilos.colorLabels2);
		
		derechosAutor = new JLabel("Derechos de Autor :");
		derechosAutor.setFont(Estilos.fontLabels);
		derechosAutor.setForeground(Estilos.colorLabels2);
		
		descripcion = new JLabel("Descripcion :");
		descripcion.setFont(Estilos.fontLabels);
		descripcion.setForeground(Estilos.colorLabels2);
		
		resolucion = new JLabel("Resolucion :");
		resolucion.setFont(Estilos.fontLabels);
		resolucion.setForeground(Estilos.colorLabels2);
		
		formato = new JLabel("Formato :");
		formato.setFont(Estilos.fontLabels);
		formato.setForeground(Estilos.colorLabels2);
		
		softwareRecomendado = new JLabel("Software Recomendado para Editar :");
		softwareRecomendado.setFont(Estilos.fontLabels);
		softwareRecomendado.setForeground(Estilos.colorLabels2);
		
		fechaPublicacion = new JLabel("Fecha de Publicacion :");
		fechaPublicacion.setFont(Estilos.fontLabels);
		fechaPublicacion.setForeground(Estilos.colorLabels2);
		
		fechaCreacion = new JLabel("Fecha de Creación :");
		fechaCreacion.setFont(Estilos.fontLabels);
		fechaCreacion.setForeground(Estilos.colorLabels2);

		areas = new JLabel("Areas de conocimiento :");
		areas.setFont(Estilos.fontLabels);
		areas.setForeground(Estilos.colorLabels2);
		
		autores = new JLabel("Autor(es) :");
		autores.setFont(Estilos.fontLabels);
		autores.setForeground(Estilos.colorLabels2);
		
		palabrasClave = new JLabel("Palabras clave :");
		palabrasClave.setFont(Estilos.fontLabels);
		palabrasClave.setForeground(Estilos.colorLabels2);
		
		
		volver = new JLabel("Volver");
		volver.setFont(Estilos.fontLabels);
		volver.setForeground(Estilos.colorLabels2);
	
		volver.setIcon(new ImageIcon("recursos/iconos/volver.png"));
		volver.addMouseListener(manejador);
		volver.setForeground(Color.BLUE);
		
		// Donde se mostraran los resultados.
		campoTituloPrincipal = new JLabel();
		campoTituloPrincipal.setText(documento.getTituloppal());
		campoTituloPrincipal.setFont(Estilos.fontSubtitulos);
		//campoTituloPrincipal.setForeground(Color.black);
		
		campoTituloSecundario =new JLabel();
		campoTituloSecundario.setText(documento.getTitulo_secundario());
		campoTituloSecundario.setFont(Estilos.fontSubtitulos);
		//campoTituloSecundario.setForeground(Estilos.colorSubtitulo);
		
		campoIdioma = new JLabel();
		campoIdioma.setText(documento.getIdioma());
		campoIdioma.setFont(Estilos.fontSubtitulos);
		//campoIdioma.setForeground(Estilos.colorSubtitulo);
		
		
		
		campoEditorial = new JLabel();
		campoEditorial.setText(documento.getEditorial());
		campoEditorial.setFont(Estilos.fontSubtitulos);
		//campoEditorial.setForeground(Estilos.colorSubtitulo);
		
		
		campoDerechosAutor = new JLabel();
		campoDerechosAutor.setText(documento.getDerechosDeAutor());
		campoDerechosAutor.setFont(Estilos.fontSubtitulos);
	//	campoDerechosAutor.setForeground(Estilos.colorSubtitulo);
		
		campoDescripcion = new JLabel();
		campoDescripcion.setText(documento.getDescripcion());
		campoDescripcion.setFont(Estilos.fontSubtitulos);
		//campoDescripcion.setForeground(Estilos.colorSubtitulo);
		
		campoResolucion = new JLabel();
		campoResolucion.setText(documento.getResolucion());
		campoResolucion.setFont(Estilos.fontSubtitulos);
	//	campoResolucion.setForeground(Estilos.colorSubtitulo);
		
		campoFormato = new JLabel();
		campoFormato.setText(documento.getFormato());
		campoFormato.setFont(Estilos.fontSubtitulos);
		//campoFormato.setForeground(Estilos.colorSubtitulo);
		
		campoSoftwareRecomendado = new JLabel();
		campoSoftwareRecomendado.setText(documento.getSoftware_recomentado());
		campoSoftwareRecomendado.setFont(Estilos.fontSubtitulos);
		//campoSoftwareRecomendado.setForeground(Estilos.colorSubtitulo);
		
		campoFechaPublicacion = new JLabel();
		campoFechaPublicacion.setText(""+documento.getFecha_publicacion());
		campoFechaPublicacion.setFont(Estilos.fontSubtitulos);
		//campoFechaPublicacion.setForeground(Estilos.colorSubtitulo);
		
		campoFechaCreacion = new JLabel();
		campoFechaCreacion.setText(""+documento.getFecha_creacion());
		campoFechaCreacion.setFont(Estilos.fontSubtitulos);
		///campoFechaCreacion.setForeground(Estilos.colorSubtitulo);

		// Etiquetas funcionales de acuerdo al tipo de usuario;
	
		etiquetaDescargar = new JLabel("Descargar");
		etiquetaDescargar.setFont(Estilos.fontLabels);
		etiquetaDescargar.setForeground(Estilos.colorLabels);
		
		etiquetaDescargar.addMouseListener(manejador);
		etiquetaEditarDocumento = new JLabel("Editar Documento");
		etiquetaEditarDocumento.setFont(Estilos.fontLabels);
		etiquetaEditarDocumento.setForeground(Estilos.colorLabels);
		etiquetaEditarDocumento.addMouseListener(manejador);

		etiquetaDescargar.setIcon(new ImageIcon("recursos/iconos/downloadfile.png"));
		etiquetaEditarDocumento
				.setIcon(new ImageIcon("recursos/iconos/editar.png"));
		// Se oculta la opcion de editar documento para usuarios no registrados y para usuarios normales.
		if(tipoUsuario == 3 || tipoUsuario == 0){
		
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
					
					int opcion = JOptionPane.showConfirmDialog(null, " Lo sentimos para descargar el documento debe registarse, ¿Desea hacerlo?", "Descarga Documento",JOptionPane.YES_NO_OPTION);
			
					if(opcion == 0)
					{
						//JOptionPane.showMessageDialog(null,"Cambiar al panel de registro de usuario (aun no esta )");
						
						
						if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
						{
					
							GuiPrincipal.cambiarPanelRegistro();
							GuiConsultaBasica.restaurarTodo();
						}
						else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
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
						String descarga = conDoc.descargarDocumento(urlFuente, urlDestino);
						
						if(!descarga.equals(""))
						{
							int tu =tipoUsuario;
							if (tu == 3) {
								ControladorConsulta conCon = new ControladorConsulta();
								conCon.insertarDescargaDocumento(documento.getId_doc(), GuiUsuarioNormal.LOGIN);
							} else if (tu == 2) {
								ControladorConsulta conCon = new ControladorConsulta();
								conCon.insertarDescargaDocumento(documento.getId_doc(), GuiCatalogador.LOGIN);
							} else if (tu == 1) {
								ControladorConsulta conCon = new ControladorConsulta();
								conCon.insertarDescargaDocumento(documento.getId_doc(), GuiAdministrador.LOGIN);
							}else{
								System.out.println("Error al insertar Descarga no se conoce el login");
							}
							
							JOptionPane.showMessageDialog(null,"Se descargo correctamente el archivo en la siguiente ubicacion: \n "+descarga);
						}
						
					}
					
					
					
					
					
				}
				
				
				
				
				
			}else if(evento.getSource() == etiquetaEditarDocumento)
			{
			
				if(GuiConsultaBasica.TIPO_USUARIO == 2)
				{
					GuiCatalogador.panelModificarDoc = new GuiModificarDoc(documento);
					
					if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
					{
						GuiCatalogador.cambiarPanelEditarDocumento();
						GuiConsultaBasica.restaurarTodo();
					}
					else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
					{
				
						GuiCatalogador.cambiarPanelEditarDocumentoAvanzado();
						GuiConsultaAvanzada.restaurarTodo();
					}
					else if(GuiResultadoConsulta.TIPO_CONSULTA == 3)
					{
				
						GuiCatalogador.cambiarPanelEditarDocumentoNovedades();
						GuiNovedades.restaurar();
					}
					
					
				}else if(GuiConsultaBasica.TIPO_USUARIO == 1)
				{
					GuiAdministrador.PANEL_MODIFICAR_DOCUMENTO = new GuiModificarDoc(documento);
					
					if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
					{
						GuiAdministrador.cambiarPanelEditarDocumento();
						GuiConsultaBasica.restaurarTodo();
					}
					else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
					{
						GuiAdministrador.cambiarPanelEditarDocumentoAvanzado();
						GuiConsultaAvanzada.restaurarTodo();
					}
					else if(GuiResultadoConsulta.TIPO_CONSULTA == 3)
					{
						GuiAdministrador.cambiarPanelEditarDocumentoNovedades();
						GuiNovedades.restaurar();
					}
				}
					
				
				
				
				
					
				
				
				
			}else if (evento.getSource() == volver)
			{
				if(GuiResultadoConsulta.TIPO_CONSULTA == 1){
				
			
					GuiConsultaBasica.restaurar();
				
				
				}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
				{
					
					GuiConsultaAvanzada.restaurar();
				}
				else if(GuiResultadoConsulta.TIPO_CONSULTA == 3)
				{
					
					GuiNovedades.restaurar();
				}
			}else {

				JLabel etiquetaAConsultar = (JLabel) evento.getSource();
				
				if (etiquetaAConsultar.getParent() == panelAutores) {
					CONSULTANDO_AUTORES = true;
				}else
				{
					CONSULTANDO_AUTORES = false;
				}
				
				if (flag == 0) {
					

					if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
					{
						consultar(etiquetaAConsultar.getText());	
					}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
					{
						consultarAvanzado(etiquetaAConsultar.getText());	
					}else if(GuiResultadoConsulta.TIPO_CONSULTA == 3)
					{
						consultarNovedades(etiquetaAConsultar.getText());	
					}
					
					flag = 1;
				} else {
					flag = 0;
				}
				
				/*if (etiquetaAConsultar.getParent() == panelAreas) {
					if (flag == 0) {
					//	JOptionPane.showMessageDialog(null, "Consultar areas: "+etiquetaAConsultar.getText());

						if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
						{
							consultar(etiquetaAConsultar.getText());	
						}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
						{
							consultarAvanzado(etiquetaAConsultar.getText());	
						}
						
						flag = 1;
					} else {
						flag = 0;
					}
				} else if (etiquetaAConsultar.getParent() == panelAutores) {
					if (flag == 0) {
					
						CONSULTANDO_AUTORES = true;

					
						if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
						{
							consultar(etiquetaAConsultar.getText());	
						}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
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
						
						if(GuiResultadoConsulta.TIPO_CONSULTA == 1)
						{
							consultar(etiquetaAConsultar.getText());	
						}else if(GuiResultadoConsulta.TIPO_CONSULTA == 2)
						{
							consultarAvanzado(etiquetaAConsultar.getText());	
						}	

						flag = 1;
					} else {
						flag = 0;
					}
				}*/

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
	

		boolean seleccionBusquedaCompeta;
		if(CONSULTANDO_AUTORES == true)
		{
			seleccionBusquedaCompeta = false;
			
		}else{

		seleccionBusquedaCompeta = true;
	
		}
		
		
		
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
		GuiConsultaBasica.PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(vector,cantidad);
		GuiConsultaBasica.CAMPO_CONSULTA.setText(parametro);
		GuiResultadoConsulta.TIPO_CONSULTA = 1;
		GuiConsultaBasica.restaurar();
		
		GuiConsultaBasica.PANEL_PRINCIPAL.updateUI();
		if(vector.size() <=0 && !parametro.equals("")){
			
			JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
			
		}	
		
	}
	
	private static void consultarNovedades(String parametro)
	{
		ControladorConsulta controlador = new ControladorConsulta();
		//mira que se hace con loque retorna
		boolean seleccionBusquedaCompeta;
		if(CONSULTANDO_AUTORES == true)
		{
			seleccionBusquedaCompeta = false;
			
		}else{

		seleccionBusquedaCompeta = true;
	
		}
		
		
		
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
		GuiConsultaBasica.PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(vector,cantidad);
		GuiConsultaBasica.CAMPO_CONSULTA.setText(parametro);
		GuiResultadoConsulta.TIPO_CONSULTA = 1;
		GuiConsultaBasica.PANEL_PRINCIPAL.add(GuiConsultaBasica.PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER);
		GuiNovedades.restaurar();
	
		if(GuiNovedades.TIPO_USUARIO == 3)
		{
			GuiUsuarioNormal.cambiarNovedadesInicio();
		}else if(GuiNovedades.TIPO_USUARIO == 2)
		{
			GuiCatalogador.cambiarNovedadesInicio();
		}else if(GuiNovedades.TIPO_USUARIO == 1)
		{
			GuiAdministrador.cambiarNovedadesInicio();
		}
		GuiConsultaBasica.PANEL_PRINCIPAL.updateUI();
		if(vector.size() <=0 && !parametro.equals("")){
			
			JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
			
		}	
		
	}
	

	private static void consultarAvanzado(String parametro)
	{
		ControladorConsulta controlador = new ControladorConsulta();
		//mira que se hace con loque retorna
		boolean seleccionBusquedaCompeta;
		if(CONSULTANDO_AUTORES == true)
		{
			seleccionBusquedaCompeta = false;
			
		}else{

		seleccionBusquedaCompeta = true;
	
		}
		
		
		
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
		GuiConsultaBasica.PANEL_RESULTADO_CONSULTA = new GuiResultadoConsulta(vector,cantidad);
		GuiConsultaBasica.CAMPO_CONSULTA.setText(parametro);
		GuiResultadoConsulta.TIPO_CONSULTA = 1;
		GuiConsultaBasica.PANEL_PRINCIPAL.add(GuiConsultaBasica.PANEL_RESULTADO_CONSULTA, BorderLayout.CENTER);
		GuiConsultaAvanzada.restaurarTodo();
		
		if(GuiConsultaAvanzada.TIPO_USUARIO == 0)
		{
			GuiPrincipal.cambiarAvanzadaInicio();
		}
		else if(GuiConsultaAvanzada.TIPO_USUARIO == 3)
		{
			GuiUsuarioNormal.cambiarAvanzadaInicio();
		}else if(GuiConsultaAvanzada.TIPO_USUARIO == 2)
		{
			GuiCatalogador.cambiarAvanzadaInicio();
		}else if(GuiConsultaAvanzada.TIPO_USUARIO == 1)
		{
			GuiAdministrador.cambiarAvanzadaInicio();
		}
		GuiConsultaBasica.PANEL_PRINCIPAL.updateUI();
		if(vector.size() <=0 && !parametro.equals("")){
			
			JOptionPane.showMessageDialog(null, "La consulta no arrojo resultados");
			
		}	
		
	}
	

}
