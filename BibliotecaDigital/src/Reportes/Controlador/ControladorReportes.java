package Reportes.Controlador;


import java.util.Vector;

import Reportes.Logica.GenerarReporte;
import Reportes.Logica.Reporte;

 
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
	public Reporte consultarUsuariosOrdenados(String atributo){
		DaoReportes	daoReportes = new DaoReportes();
		
		Reporte reporte = daoReportes.consultaUsuariosOrdenados(atributo);
		
		
		return reporte;
	}
	public Reporte consultarUsuariosOrdenadosTotales(String atributo){
		DaoReportes	daoReportes = new DaoReportes();
		
		Reporte reporte = daoReportes.consultaUsuariosOrdenadosTotales(atributo);		
		
		return reporte;
	}
	

}
