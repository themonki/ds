package Consultas.Gui;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.TitledBorder;

import Utilidades.Estilos;

import Consultas.Logica.Consulta;

public class GuiResultadoConsulta extends JScrollPane{
	
	//Resultados
	JList listaResultado;
	DefaultListModel modeloLista;
	Vector<Consulta> vectorConsulta;
	
	JButton botonRegresar, botonSiguiente, botonAtras;
	int posicionResultado;
	int cantidadTotalResultados
	
	GuiResultadoConsulta(){}
	
	GuiResultadoConsulta(Vector<Consulta> vectorConsulta){
		
		TitledBorder borde;
		borde = BorderFactory.createTitledBorder(BorderFactory
				.createEtchedBorder(Estilos.colorBorder, Estilos.colorLightBorder), "Resultado Consultas");
		borde.setTitleColor(Estilos.colorTitulo);
		borde.setTitleFont(Estilos.fontTitulo);
		borde.setTitleJustification(TitledBorder.LEFT);
		setBorder(borde);
		
		JPanel panel = new JPanel(new FlowLayout());
		listaResultado = new JList();
		listaResultado.setPreferredSize(new Dimension(245,400));
		modeloLista = new DefaultListModel();
		this.vectorConsulta = vectorConsulta;
		cantidadTotalResultados=vectorConsulta.size();
		posicionResultado=0;
		
		panel.add(listaResultado);
		this.setViewportView(panel);
		
		setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		
		//------------------------------------------
		setSize(900,900);
		setVisible(true);
	}
	
	public void agregarResultados(){
		int posicionFinal = posicionResultado+10; 
		if((posicionFinal)> cantidadTotalResultados){
			posicionFinal=cantidadTotalResultados;
		}
		
		for(int i = posicionResultado; i < posicionFinal; i++ ){
			Consulta consulta = vectorConsulta.elementAt(i);
			String mostrarDatos = consulta.getTituloDocuemto() + " Autor(es): "+
									consulta.getNombreAutorDocumento();
			modeloLista.addElement(mostrarDatos);			
		}
		
	}
	
	public static void main(String []args){
		JFrame m = new JFrame();
		Container c = m.getContentPane();
		c.add(new GuiResultadoConsulta());
		m.setSize(400, 600);
		m.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		m.setVisible(true);
	}

}
