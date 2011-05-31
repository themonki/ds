package Reportes.Controlador;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;

import Reportes.Logica.GenerarReporte;
import Reportes.Logica.Reporte;

 
import Reportes.Dao.DaoReportes;
import Utilidades.TableDataSource;

public class ControladorReportes 
{
	/*public Vector<String> consultarUsuariosAgrupados(String atributoUsuario){
		DaoReportes	daoReportes = new DaoReportes();
		Vector<String> usuariosAgrupados = daoReportes.consultaUsuariosAgrupados(atributoUsuario);
		
		return usuariosAgrupados;
	}*/
	
	public JasperPrint reporteUsuariosAgrupados(String atributoUsuario, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupados(atributoUsuario);
		//System.out.println(tableData.toString());
		
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupados2.jasper");
				
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		if(atributoUsuario.equals("vinculo_univalle"))
		{
			parametros.put("opcion", "Tipo");
			tableData.setColumnName(6, "opcion"); //6 es tipo
			
		}else
		{
			parametros.put("opcion", "Vinculo con Univalle");
			tableData.setColumnName(5, "opcion"); //5 es vinculo_univalle
		}
		
        JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        daoReportes = null;
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteUsuariosAgrupados(String atributoUsuario, String tituloReporte, String cualFecha, String fechaI, String fechaF) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupados(atributoUsuario, cualFecha, fechaI, fechaF);
		//System.out.println(tableData.toString());
		
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupados2.jasper");
				
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		if(atributoUsuario.equals("vinculo_univalle"))
		{
			parametros.put("opcion", "Tipo");
			tableData.setColumnName(6, "opcion"); //6 es tipo
			
		}else
		{
			parametros.put("opcion", "Vinculo con Univalle");
			tableData.setColumnName(5, "opcion"); //5 es vinculo_univalle
		}
		
        JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        daoReportes = null;
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;

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
	
	public void generarReporte(String rutaFinal, JasperPrint print)
	{
		JRExporter exporter = new JRPdfExporter(); 
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaFinal)); 
        
        try
        {
        	exporter.exportReport();
        }catch(JRException e)
        {
        	System.out.println("Exception generada en Controlador Reporte,pruebaRepote");
        	e.printStackTrace();
        }
	}

}
