package Consultas.Gui;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Principal.Gui.GuiAdministrador;
import Principal.Gui.GuiCatalogador;
import Principal.Gui.GuiUsuarioNormal;
import Utilidades.Estilos;

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
	DefaultListModel modeloLista;
	Vector<Consulta> vectorConsulta;
	
	JButton botonSiguiente, botonAtras;
	int posicionResultado;
	int cantidadTotalResultados;
	int cantidadMostrar;
	public static int TIPOCONSULTA;
	int flag = 0;
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
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Resultado Consultas");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
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
		
		botonSiguiente = new JButton("Siguiente");
		botonAtras = new JButton("Atras");
		botonSiguiente.addActionListener(new ManejadorBoton());
		if(cantidadMostrar>cantidadTotalResultados) botonSiguiente.setEnabled(false);
		botonAtras.addActionListener(new ManejadorBoton());
		botonAtras.setEnabled(false);
		
	}
	
	private void initLista(){
		listaResultado = new JList();
		listaResultado.addListSelectionListener(new ManejadorLista());		
		//listaResultado.setPreferredSize(new Dimension(245,200));
		modeloLista = new DefaultListModel();
		agregarResultadoSiguiente(cantidadMostrar);
	}
	
	public void agregarResultadoSiguiente(int cantidad){
		int posicionFinal = posicionResultado+cantidad; 
		for(int i = posicionResultado; i < posicionFinal; i++ ){
			if(i>=cantidadTotalResultados){
				break;
			}
			modeloLista.addElement(vectorConsulta.elementAt(i));
		}
		listaResultado.setModel(modeloLista);
		posicionResultado=posicionFinal;
	}
	
	public void agregarResultadoAtras(int cantidad){
		int posicionInicial = posicionResultado-cantidad*2; 		
		for(int i = posicionInicial; i < posicionResultado-cantidad; i++ ){
			if(i>=cantidadTotalResultados){
				break;
			}
			modeloLista.addElement(vectorConsulta.elementAt(i));
		}
		listaResultado.setModel(modeloLista);
		posicionResultado=posicionResultado-cantidad;
	}
	
	private class ManejadorBoton implements ActionListener {

		
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()==botonSiguiente){
				if(posicionResultado<cantidadTotalResultados){
					modeloLista.removeAllElements();
					agregarResultadoSiguiente(cantidadMostrar);
					}
				if(posicionResultado>=cantidadTotalResultados){
					botonSiguiente.setEnabled(false);
				}
				botonAtras.setEnabled(true);
				flag = 0;
			}
			if(e.getSource()==botonAtras){
				modeloLista.removeAllElements();
				agregarResultadoAtras(cantidadMostrar);
				if(posicionResultado==cantidadMostrar){
					botonAtras.setEnabled(false);
				}
				botonSiguiente.setEnabled(true);
				flag = 0;
			}
		}
		
		
	}

	private class ManejadorLista implements ListSelectionListener {

		
		public void valueChanged(ListSelectionEvent e) {
			System.out.println("**********  "+flag);
			if (flag == 0) {
				int documentoElegido = listaResultado.getSelectedIndex();
				if (documentoElegido >= 0) {
					ControladorConsulta conConsulta = new ControladorConsulta();
					Consulta documentoConsultar = (Consulta) modeloLista
							.getElementAt(documentoElegido);					
					Documento d =
					conConsulta.obtenerDatosDocumento(documentoConsultar.getIdDocumento());
					
					int tu = GuiConsultaBasica.TIPOUSUARIO;
					System.out.println(tu);
					if(tu == 0)
					{	
						conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(), "anonimo");
					}else if(tu == 1)
					{	
						conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(), GuiUsuarioNormal.LOGIN);					
					}else if(tu == 2)
					{
						conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(), GuiCatalogador.LOGIN);						
					}else if(tu == 3)
					{
						conConsulta.insertarConsultaDocumentoUsuario(d.getId_doc(), GuiAdministrador.LOGIN);						
					}
					
					GuiConsultaBasica.vistaDocumento = new GuiVistaDocumento(d);
					GuiConsultaBasica.ponerDescripcion();
					
				}
				flag = 1;
			} else {
				flag = 0;
			}
			listaResultado.clearSelection();
		}

	}
}
