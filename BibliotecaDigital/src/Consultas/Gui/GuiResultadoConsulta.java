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
	
	//Resultados
	JList listaResultado;
	DefaultListModel modeloLista;
	Vector<Consulta> vectorConsulta;
	
	JButton botonRegresar, botonSiguiente, botonAtras;
	int posicionResultado;
	int cantidadTotalResultados;
	int cantidadMostrar;
	
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
		
		panel2.add(botonRegresar, restriccionBotones);
		panel2.add(botonAtras, restriccionBotones);
		panel2.add(botonSiguiente,restriccionBotones);
		
		
		JPanel panel = new JPanel();
		panel.setLayout(new BorderLayout());
		panel.add(scrollResultado, BorderLayout.CENTER);
		panel.add(panel2, BorderLayout.SOUTH);
		
		this.setViewportView(panel);		
		//------------------------------------------
		setSize(900,900);
		setVisible(true);
		
	}
	
	private void initBotones(){
		
		botonRegresar = new JButton("Regresar");
		botonSiguiente = new JButton("Siguiente");
		botonAtras = new JButton("Atras");
		botonRegresar.addActionListener(new ManejadorBoton());
		botonSiguiente.addActionListener(new ManejadorBoton());
		if(cantidadMostrar>cantidadTotalResultados) botonSiguiente.setVisible(false);
		botonAtras.addActionListener(new ManejadorBoton());
		botonAtras.setVisible(false);
		
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
		for(int i = posicionInicial; i < posicionResultado-10; i++ ){
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
			if(e.getSource()==botonRegresar){
				
			}
			if(e.getSource()==botonSiguiente){
				modeloLista.removeAllElements();
				agregarResultadoSiguiente(cantidadMostrar);
				if(posicionResultado>cantidadTotalResultados){
					botonSiguiente.setVisible(false);
				}
				botonAtras.setVisible(true);
				
			}
			if(e.getSource()==botonAtras){
				modeloLista.removeAllElements();
				agregarResultadoAtras(cantidadMostrar);
				if(posicionResultado==cantidadMostrar){
					botonAtras.setVisible(false);
				}
				botonSiguiente.setVisible(true);
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
					// Documento d =
					// conConsulta.obtenerDatosDocumento(documentoConsultar.getIdDocumento());//devuelve
					// el documento
				}
				flag = 1;
			} else {
				flag = 0;
			}
		}

	}

	
	public static void main(String []args){
		JFrame m = new JFrame();
		Container c = m.getContentPane();
		Vector <String> va=new  Vector <String>();
		va.add("autor");
		va.add("autor2");
		Consulta co = new Consulta("1","doc",va);
		Vector<Consulta> v =new Vector<Consulta>();
		for(int i = 0 ; i <10; i++)
			v.add(co);
		Consulta co2 = new Consulta("2","doc2",va);
		for(int i = 0 ; i <10; i++)
			v.add(co2);
		Consulta co3 = new Consulta("3","doc3",va);
		for(int i = 0 ; i <3; i++)
			v.add(co3);
		c.add(new GuiResultadoConsulta(v, 10));
		m.setSize(700, 600);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}

}
