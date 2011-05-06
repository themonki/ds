package Usuarios.Gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
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
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.UIManager;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import Usuarios.Controlador.ControladorUsuario;
import Usuarios.Logica.Usuario;

public class GuiConsultarUsuarios extends JScrollPane{
	
	private static final long serialVersionUID = 1L;
	JLabel login, nombre;
	JTextField campoLogin, campoNombre;
	JScrollPane scrolResultados, scrolUsuario;
	JButton consultar;
	JPanel panelResultado, panelOpciones, panelPrincipal;
	
	//Resultados
	JList resultadoLista;
	DefaultListModel modeloLista;
	Vector<Usuario> usuariosVector;
	
	//Estilos.
	//-------------------------------fuentes letras-------------------------
	Font fontLabels = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 17);
	Font fontSubtitulos = new Font("Book Antiqua",Font.BOLD, 15);
	Font fontTitulo = new Font("Book Antiqua",Font.BOLD+ Font.ITALIC, 25);
	
	//-------------------------------Color letras----------------------------
	Color colorTitulo = new Color(0,50,0);
	Color colorSubtitulo= new Color(0,50,10);
	Color colorLabels= new Color(0,60,0);	
	
	public GuiConsultarUsuarios(){
		iniComponents();
	}
	
	private void iniComponents(){
		
		//InicializarLabels
		login = inicializarLabel("Login: ");
		nombre = inicializarLabel("Nombre: ");
		
		//InicializarJTextField
		campoLogin = new JTextField(10);
		campoNombre= new JTextField(15);
		
		//Inicializar Boton
		consultar = new JButton("CONSULTAR");
		consultar.addActionListener(new ManejadorBoton());
		
		panelOpciones = new JPanel(new GridBagLayout());
		GridBagConstraints restriccionesEtiquetas = new GridBagConstraints();
		GridBagConstraints restriccionesCampos = new GridBagConstraints();
		restriccionesEtiquetas.anchor = GridBagConstraints.WEST;
		restriccionesEtiquetas.gridx=0;
		restriccionesEtiquetas.gridy=0;
		restriccionesEtiquetas.insets = new Insets(4, 2, 2, 2);
		restriccionesCampos.anchor = GridBagConstraints.WEST;
		restriccionesCampos.gridx = 1;
		restriccionesCampos.gridy=0;
		restriccionesCampos.insets = new Insets(4, 0, 2, 2);
		panelOpciones.add(login, restriccionesEtiquetas);
		panelOpciones.add(campoLogin,restriccionesCampos);
		restriccionesEtiquetas.gridy=1;
		restriccionesCampos.gridy=1;
		panelOpciones.add(nombre, restriccionesEtiquetas);
		panelOpciones.add(campoNombre, restriccionesCampos);
		GridBagConstraints restriccionBoton = new GridBagConstraints();
		restriccionBoton.gridy=2;
		restriccionBoton.insets= new Insets(4,2,2,2);
		restriccionBoton.anchor = GridBagConstraints.CENTER;
		restriccionBoton.gridwidth=2;
		panelOpciones.add(consultar, restriccionBoton);
		
		
		panelResultado = new JPanel();
		TitledBorder borde = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow), "::Resultados::");
		borde.setTitleColor(colorSubtitulo);
		borde.setTitleFont(fontSubtitulos);
		borde.setTitleJustification(TitledBorder.LEFT);
		panelResultado.setBorder(borde);
		
		
		
		panelPrincipal = new JPanel();
		TitledBorder bordePrincipal = BorderFactory.createTitledBorder(BorderFactory.createLineBorder(Color.yellow), "::CONSULTAR USUARIOS::");
		bordePrincipal.setTitleColor(colorSubtitulo);
		bordePrincipal.setTitleFont(fontSubtitulos);
		bordePrincipal.setTitleJustification(TitledBorder.LEFT);
		panelPrincipal.setLayout(new BorderLayout());
		panelPrincipal.setBorder(bordePrincipal);
		
		
		panelPrincipal.add(panelOpciones, BorderLayout.NORTH);
		
		
		this.getViewport().add(panelPrincipal);		
	}
	private JLabel inicializarLabel(String titulo){
		JLabel label = new JLabel(titulo, JLabel.LEFT);
		label.setFont(fontLabels);
		label.setForeground(colorLabels);
		return label;
	}
	
	private class ManejadorBoton implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent e) {
			if(e.getSource()== consultar){
				
				ControladorUsuario controlador = new ControladorUsuario();
				Usuario usuarioEncontrado = controlador.consultarUsuario(campoLogin.getText());
				if(usuarioEncontrado.getLogin() != null)
				{	usuariosVector = new Vector<Usuario>();
					usuariosVector.add(usuarioEncontrado);
					resultadoLista = new JList();
					modeloLista = new DefaultListModel();
					resultadoLista.setModel(modeloLista);
					resultadoLista.addListSelectionListener(new ManejadorLista());
					resultadoLista.setPreferredSize(new Dimension(245,400));
					
					for(int i=0;i<usuariosVector.size();i++){
						
						modeloLista.addElement(usuariosVector.elementAt(i));
					}
					
					panelPrincipal.add(panelResultado, BorderLayout.CENTER);
					panelPrincipal.updateUI();
					
					
					//PARA CUANDO SE CREE LA LISTA RESULTADO.
					scrolResultados = new JScrollPane(resultadoLista);
					panelResultado.add(scrolResultados);
				}else{
					panelPrincipal.add(panelResultado, BorderLayout.CENTER);
					panelPrincipal.updateUI();
					panelResultado.add(new JLabel("No hay Resultados"));
					
				}	
			}
		}		
	}
	
	private class ManejadorLista implements ListSelectionListener{

		@Override
		public void valueChanged(ListSelectionEvent e) {
			int usuarioElegido = resultadoLista.getSelectedIndex();
			
			scrolUsuario = new GuiRegistroModificar((Usuario) modeloLista.getElementAt(usuarioElegido), 2);
			scrolUsuario.setPreferredSize(new Dimension(300,400));
			panelPrincipal.remove(panelResultado);
			panelPrincipal.add(panelResultado, BorderLayout.WEST);
			panelPrincipal.add(scrolUsuario, BorderLayout.CENTER);
			panelPrincipal.updateUI();
		}
		
	}
	
	public static void main (String args []){
		
		try
		{	
			UIManager.setLookAndFeel("com.nilo.plaf.nimrod.NimRODLookAndFeel"); 
		}
		catch (Exception e){e.printStackTrace();}
		
		JFrame ventana;
		ventana = new JFrame();
		GuiConsultarUsuarios miPanel = new GuiConsultarUsuarios();
		ventana.getContentPane().add(miPanel);
		ventana.setVisible(true);
		ventana.setSize(300,450);		
		ventana.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	}

}
