package Usuarios.Gui;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JSpinner;
import javax.swing.JTextField;


@SuppressWarnings({ "serial" })
public class GuiRegistroModificar extends JFrame{

	JTextField loginTF, campoLoginTF, campoRespuestaSecreta, campoNombre1, campoNombre2, campoApellido1, campoApellido2, campoEmail, campoNivelEscolaridad;
	
	JLabel password, verificacionPassword, preguntaSecreta, respuestaSecreta, nombre1, nombre2, apellido1, apellido2, genero, fechaNacimiento, email, nivelEscolaridad, vinculoUnivalle, perfilLabel, estadoLabel, areasInteres;
	
	JPasswordField campoPassword,campoVerificacionPassword;
	
	JComboBox campoPreguntaSecreta, campoGenero, campoVinculoUnivalle, campoPerfil, campoEstado, campoAreasInteres;
	
	JSpinner campoFechaNacimiento;
	
	JPanel panelAreasInteres, panelPrincipalA, panelPrincipalB;
	
	JButton registrar, modificar;
	
	JScrollPane scrollPanelPrincipalA;
	
	int modo; // Indica si es modo registro 1  o modo modificar 2.
	
	GuiRegistroModificar(int modo){
		this.modo=modo;
		initComponents();
	}
	
	public void initComponents(){
		if(modo==1)
			super.setTitle("::Registro::");
		if(modo==2)
			super.setTitle("::Modificar Usuario::");
		super.setIconImage(new ImageIcon("/recursos/LOGO.png").getImage());
		
		if(modo==1 || modo==2){
			
			//Inicializar Labels 
			nombre1= new JLabel("Primer nombre: "); 
			nombre2= new JLabel("Segundo nombre: "); 
			apellido1= new JLabel("Primer apellido: "); 
			apellido2= new JLabel("Segundo apellido: "); 
			genero= new JLabel("Género: "); 
			fechaNacimiento= new JLabel("Fecha de nacimiento: "); 
			email= new JLabel("email: "); 
			nivelEscolaridad= new JLabel("Nivel Escolaridad: "); 
			vinculoUnivalle= new JLabel("Vinculo con Univalle: "); 
			areasInteres= new JLabel("Areas de Interés: ");
			
		}
		password= new JLabel();
		verificacionPassword= new JLabel(); 
		preguntaSecreta= new JLabel();
		respuestaSecreta= new JLabel();		
		perfilLabel= new JLabel(); 
		estadoLabel= new JLabel(); 
		this.setLayout(new BorderLayout());
	}
	
	
	


}
