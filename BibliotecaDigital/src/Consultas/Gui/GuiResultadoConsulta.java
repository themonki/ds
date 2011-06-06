 /**
 * GuiResultadoConsulta.java
 * 
 * Clase que representa la interfaz que permite mostrar los resul-
 * tados que arrojan las consultas general y avanzada de los do-
 * cumentos digitales de la Biblioteca Digital.
 * 
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Edgar Andres Moncada
 * Version:   4.0
 */
package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Vector;
import java.util.Hashtable;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListCellRenderer;
import javax.swing.border.TitledBorder;

import Principal.Gui.GuiAdministrador;
import Principal.Gui.GuiCatalogador;
import Principal.Gui.GuiUsuarioNormal;
import Usuarios.Gui.GuiNovedades;
import Utilidades.Estilos;
import Utilidades.Button;

import Consultas.Logica.Consulta;
import Consultas.Controlador.*;
import Documento.Logica.Documento;

public class GuiResultadoConsulta extends JScrollPane{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	//Resultados
	JList listaResultado;
	Vector<Consulta> vectorConsulta;	
	Button botonSiguiente, botonAtras;
	int posicionResultado, cantidadTotalResultados, cantidadMostrar;
	public static int TIPO_CONSULTA;
	RenderLista rl;//formato de la lista
	TitledBorder borde;
	
	
	public GuiResultadoConsulta(){}
	
	public GuiResultadoConsulta(Vector<Consulta> vectorConsulta, int cantidadResultados){
		
		cantidadMostrar=cantidadResultados;
		this.vectorConsulta = vectorConsulta;
		cantidadTotalResultados=vectorConsulta.size();
		posicionResultado=0;
	
		iniComponents();		
	}
	

	
	private void iniComponents(){
		//***/
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "");
		borde.setTitleColor(Estilos.colorSubtitulo);
		borde.setTitleFont(Estilos.fontSubtitulos);
		borde.setTitleJustification(TitledBorder.LEFT);
		setBorder(borde);
		//******/
		
		initBotones();
		initLista();
		
		JScrollPane scrollResultado = new JScrollPane(listaResultado);
		scrollResultado.setSize(new Dimension(245,200));
		
		JPanel panel2 = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionBotones= new GridBagConstraints();
		restriccionBotones.insets = new Insets(5,40,5,5);
		
		panel2.add(botonAtras, restriccionBotones);
		panel2.add(botonSiguiente,restriccionBotones);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scrollResultado, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);
		
		this.setViewportView(panel);		
		
	}
	
	private void initBotones(){
		
		botonSiguiente = new Button("Siguiente");
		botonAtras = new Button("Atras");
		botonSiguiente.addActionListener(new ManejadorBoton());
		if(cantidadMostrar>=cantidadTotalResultados) botonSiguiente.setEnabled(false);
		botonAtras.addActionListener(new ManejadorBoton());
		botonAtras.setEnabled(false);		
	}
	
	private void initLista(){
		listaResultado = new JList();
		listaResultado.setFixedCellHeight(40);
		listaResultado.setMaximumSize(new Dimension(100, 100));
		rl=new RenderLista();
		listaResultado.addMouseListener(rl);
		listaResultado.addMouseMotionListener(rl);
		agregarResultadoSiguiente(cantidadMostrar);
	}
	
	public void agregarResultadoSiguiente(int cantidad){
		int posicionFinal = posicionResultado+cantidad;
		rl.limpiarElementos();
		int i;
		Vector<Consulta> mostrar = new Vector<Consulta> ();
		for(i = posicionResultado; i < posicionFinal; i++ ){
			if(i>=cantidadTotalResultados){
				i=cantidadTotalResultados;
				break;
			}
			mostrar.add(vectorConsulta.elementAt(i));
		}
		rl.agregarElementos(mostrar);
		listaResultado.setListData(mostrar);
		listaResultado.setCellRenderer(rl);
		if(cantidadTotalResultados==0)//no ahi resultados
		{
			borde.setTitle("No Hay Resultados");
		}else{
		borde.setTitle("Mostrando desde "+ (posicionResultado+1)+
				" hasta "+ i +" de "+cantidadTotalResultados+ " resultados");
		}
		posicionResultado=posicionFinal;
		this.updateUI();
	}
	
	public void agregarResultadoAtras(int cantidad){
		int posicionInicial = posicionResultado-cantidad*2; 
		rl.limpiarElementos();
		Vector<Consulta> mostrar = new Vector<Consulta> ();
		for(int i = posicionInicial; i < posicionResultado-cantidad; i++ ){
			mostrar.add(vectorConsulta.elementAt(i));
		}
		rl.agregarElementos(mostrar);
		listaResultado.setListData(mostrar);
		listaResultado.setCellRenderer(rl);
		posicionResultado=posicionResultado-cantidad;
		borde.setTitle("");
		this.updateUI();
		borde.setTitle("Mostrando desde "+ (posicionInicial+1)+" hasta "+ posicionResultado +
				" de "+cantidadTotalResultados+ " resultados");
	}
	
	private class ManejadorBoton implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==botonSiguiente){
				if(posicionResultado<cantidadTotalResultados){
					agregarResultadoSiguiente(cantidadMostrar);
					}
				if(posicionResultado>=cantidadTotalResultados){
					botonSiguiente.setEnabled(false);
				}
				botonAtras.setEnabled(true);
			}
			if(e.getSource()==botonAtras){
				agregarResultadoAtras(cantidadMostrar);
				if(posicionResultado==cantidadMostrar){
					botonAtras.setEnabled(false);
				}
				botonSiguiente.setEnabled(true);
			}
		}
		
		
	}
//clase que se encargara de como se mostraran ls elementos de la lista
	private class RenderLista extends JLabel implements ListCellRenderer, MouseListener, MouseMotionListener{

		/**
		 * 
		 */
		private static final long serialVersionUID = 1L;
		Hashtable<Object, ImageIcon> elementos;
		ImageIcon icono = new ImageIcon("recursos/book.gif");

		public Component getListCellRendererComponent(JList list, Object value,
				int index, boolean isSelected, boolean cellHasFocus) {
			
			if (elementos.get(value) != null) {
				setIcon(icono);
				setText("" + value);
				this.updateUI();
				if (isSelected) {
					this.setFont(new Font("Verdana", Font.BOLD, 16));
				}else {
					setIcon(elementos.get(value));
					setFont(null);
					this.updateUI();
				}
			}
			return this;
		}

		public RenderLista() {
			elementos = new Hashtable<Object, ImageIcon>();
		}

		public void agregarElementos(Vector<Consulta> vc) {
			for (int i = 0; i < vc.size(); i++) {
				elementos.put(vc.get(i), new ImageIcon());
			}
		}
		public void limpiarElementos() {
			elementos.clear();
		}
		//////////////////////////////7
		public void mouseClicked(MouseEvent e) {
			int documentoElegido = listaResultado.getSelectedIndex();
			if (documentoElegido >= 0) {
				ControladorConsulta conConsulta = new ControladorConsulta();
				Consulta documentoConsultar = (Consulta) listaResultado
						.getSelectedValue();
				Documento d = conConsulta
						.obtenerDatosDocumento(documentoConsultar
								.getIdDocumento());

				int tu = 0;
				if(TIPO_CONSULTA  == 1)
				{
					tu= GuiConsultaBasica.TIPO_USUARIO;
				}else if(TIPO_CONSULTA  == 2)
				{
					tu = GuiConsultaAvanzada.TIPO_USUARIO;
				}else if(TIPO_CONSULTA  == 3)
				{
					tu = GuiNovedades.TIPO_USUARIO;
				}


				if (tu == 0) {
					conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(),
							"anonimo");
				} else if (tu == 3) {
					conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(),
							GuiUsuarioNormal.LOGIN);
				} else if (tu == 2) {
					conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(),
							GuiCatalogador.LOGIN);
				} else if (tu == 1) {
					conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(),
							GuiAdministrador.LOGIN);
				}
		
				
				if (TIPO_CONSULTA == 1) {

					GuiConsultaBasica.PANEL_VISTA_DETALLADA_CONSULTA = new GuiVistaDetalladaConsulta(d);
					GuiConsultaBasica.ponerDescripcion();

				} else if (TIPO_CONSULTA == 2) {
					GuiConsultaAvanzada.PANEL_VISTA_DETALLADA_CONSULTA = new GuiVistaDetalladaConsulta(
							d);
					
					GuiConsultaAvanzada.ponerDescripcion();

				} else if (TIPO_CONSULTA == 3) {
					
					GuiNovedades.PANEL_VISTA_DETALLADA_CONSULTA = new GuiVistaDetalladaConsulta(d);
					GuiNovedades.ponerDescripcion();
				

				}

			}
			listaResultado.clearSelection();
		}

		public void mouseEntered(MouseEvent e) {
		}

		public void mouseExited(MouseEvent arg0) {
			listaResultado.clearSelection();
		}

		public void mousePressed(MouseEvent arg0) {
		}

		public void mouseReleased(MouseEvent arg0) {
		}
//////////////////////////////////
		public void mouseDragged(MouseEvent arg0) {
		}

		public void mouseMoved(MouseEvent e) {
			int index = listaResultado.locationToIndex(e.getPoint());
			listaResultado.setSelectedIndex(index);
		}
		

	}
}
