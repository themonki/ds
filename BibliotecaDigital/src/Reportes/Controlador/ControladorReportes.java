package Reportes.Controlador;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Vector;

 
import Reportes.Dao.DaoReportes;

public class ControladorReportes 
{
	public Vector<String> consultarUsuariosAgrupados(String atributoUsuario){
		DaoReportes	daoReportes = new DaoReportes();
		Vector<String> usuariosAgrupados = daoReportes.consultaUsuariosAgrupados(atributoUsuario);
		
		return usuariosAgrupados;
	}
	public Vector<String> consultarAreasAgrupadasAreaPadre(){
		DaoReportes	daoReportes = new DaoReportes();
		Vector<String> areasAgrupadas = daoReportes.consultaAreaAgrupados();
		
		return areasAgrupadas;
	}
	
	

}
