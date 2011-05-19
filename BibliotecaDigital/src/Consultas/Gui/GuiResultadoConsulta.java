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
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

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
		//------------------------------------------
		/*setSize(900,900);
		setVisible(true);*/
		
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
			}
			if(e.getSource()==botonAtras){
				modeloLista.removeAllElements();
				agregarResultadoAtras(cantidadMostrar);
				if(posicionResultado==cantidadMostrar){
					botonAtras.setEnabled(false);
				}
				botonSiguiente.setEnabled(true);
			}
		}
		
		
	}

	private class ManejadorLista implements ListSelectionListener {

		int flag = 0;
		public void valueChanged(ListSelectionEvent e) {
			if (flag == 0) {
				int documentoElegido = listaResultado.getSelectedIndex();
				if (documentoElegido >= 0) {
					ControladorConsulta conConsulta = new ControladorConsulta();
					Consulta documentoConsultar = (Consulta) modeloLista
							.getElementAt(documentoElegido);
					conConsulta.insertarConsultaDocumentoUsuario("","");
					Documento d =
					conConsulta.obtenerDatosDocumento(documentoConsultar.getIdDocumento());
					
					
					GuiConsultaBasica.vistaDocumento = new GuiVistaDocumento(d);
					GuiConsultaBasica.ponerDescripcion();
					
				}
				flag = 1;
			} else {
				flag = 0;
				//listaResultado.clearSelection();
			}
			listaResultado.clearSelection();
		}

	}

	public static void volverRestaurarSeleccionado(){
		//this.listaResultado.setSelectedIndex(-1);
	}
	/*public static void main(String []args){
		JFrame m = new JFrame();
		Container c = m.getContentPane();
		Vector <String> va=new  Vector <String>();
		va.add("autor");
		va.add("autor2");
		
		Vector<Consulta> v =new Vector<Consulta>();
		for(int i = 0 ; i <10; i++){
			Consulta co = new Consulta("1","doc "+i,va);
			v.add(co);
		}
			
		
		for(int i = 0 ; i <13; i++){
			Consulta co2 = new Consulta("2","doc2 "+i,va);
			v.add(co2);
		}
			
		
		for(int i = 0 ; i <3; i++){
			Consulta co3 = new Consulta("3","doc3 "+i,va);
			v.add(co3);
		}
			
		c.add(new GuiResultadoConsulta(v, 4));
		m.setSize(700, 600);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}
/**/
}
