package Reportes.Controlador;


import java.util.HashMap;
import java.util.Map;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;


import Reportes.Dao.DaoReportes;
import Utilidades.TableDataSource;

public class ControladorReportes 
{
	public JasperPrint reporteUsuariosAgrupados(String atributoUsuario, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupados(atributoUsuario);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupados(atributoUsuario, tituloReporte, tableData);
	}
	
	public JasperPrint reporteUsuariosAgrupados(String atributoUsuario, String tituloReporte, String cualFecha, String fechaI, String fechaF) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupados(atributoUsuario, cualFecha, fechaI, fechaF);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupados(atributoUsuario, tituloReporte, tableData);
	}
	
	private JasperPrint procesarDatosReporteUsuariosAgrupados(String atributoUsuario, String tituloReporte, TableDataSource tableData) throws JRException
	{
		boolean opcionReporte;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		if(atributoUsuario.equals("vinculo_univalle"))
		{
			parametros.put("opcion", "Tipo");
			parametros.put("convencion_title", "Convención para Tipos de usuarios");
			parametros.put("convencion", "1:Administrador 2:Catalogador 3:Normal");
			tableData.setColumnName(6, "opcion"); //6 es tipo
			opcionReporte = true;
			
		}else if(atributoUsuario.equals("tipo"))
		{
			parametros.put("opcion", "Vinculo con Univalle");
			tableData.setColumnName(5, "opcion"); //5 es vinculo_univalle
			opcionReporte = true;
		}else
		{
			opcionReporte = false;
		}
		
		JasperReport reporte;
		if(opcionReporte)
		{
			reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupados_1.jasper");
		}else
		{
			reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupados_2.jasper");
		}
		
        JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteUsuariosAgrupadosTotales(String atributoUsuario, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupadosTotales(atributoUsuario);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupadosTotales(tituloReporte, atributoUsuario, tableData);
	}
	
	public JasperPrint reporteUsuariosAgrupadosTotales(String atributoUsuario, String tituloReporte, String cualFecha, String fechaI, String fechaF)throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupadosTotales(atributoUsuario,cualFecha, fechaI, fechaF);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupadosTotales(tituloReporte, atributoUsuario, tableData);
	}
	
	private JasperPrint procesarDatosReporteUsuariosAgrupadosTotales(String tituloReporte,String atributoUsuario, TableDataSource tableData) throws JRException
	{
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("grupo", atributoUsuario);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteAreasAgrupadas(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultarAreasConocimientoAgrupadas();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteAreasAgrupadas.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteAreasAgrupadasTotales(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultarAreasConocimientoAgrupadasTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteAreasAgrupadasTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosArea(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadoAreas.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosTipo(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosTipo();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadoTipo.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosFormato(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosFormato();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadoFormato.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*public Vector<String> consultarUsuariosAgrupados(String atributoUsuario){
		DaoReportes	daoReportes = new DaoReportes();
		Vector<String> usuariosAgrupados = daoReportes.consultaUsuariosAgrupados(atributoUsuario);
		
		return usuariosAgrupados;
	}
	
	public Vector<String> consultarAreasAgrupadasAreaPadre(){
		DaoReportes	daoReportes = new DaoReportes();
		Vector<String> areasAgrupadas = daoReportes.consultaAreaAgrupados();
		
		return areasAgrupadas;
	}
	
	public Reporte consultarAreasOrdenadas(String atributo)
	{
		DaoReportes daoReportes = new DaoReportes();
		Reporte reporte = daoReportes.consultarAreasOrdenadas(atributo);
		
		return reporte;
	}
	
	public Reporte consultarUsuariosOrdenados(String atributo, String fechaPrimera, String fechaSegunda, String atributoFecha){
		DaoReportes	daoReportes = new DaoReportes();
		
		Reporte reporte = daoReportes.consultaUsuariosOrdenados(atributo, fechaPrimera, fechaSegunda, atributoFecha);
		
		
		return reporte;
	}
	
	public Reporte consultarUsuariosOrdenadosTotales(String atributo, String fechaPrimera, String fechaSegunda, String atributoFecha){
		DaoReportes	daoReportes = new DaoReportes();
		
		Reporte reporte = daoReportes.consultaUsuariosOrdenadosTotales(atributo, fechaPrimera, fechaSegunda, atributoFecha);		
		
		return reporte;
	}
	
	public Reporte consultarAreasOrdenadasTotales(String atributo)
	{
		DaoReportes	daoReportes = new DaoReportes();
		Reporte reporte = daoReportes.consultarAreasOrdenadasTotales(atributo);		
		
		return reporte;
	}*/
	
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
	
	public static void main(String arg[])
	{
		ControladorReportes c = new ControladorReportes();
		try
		{
			//c.generarReporte("recursos/documento_areas.pdf", c.reporteDocumentosAgrupadosArea("Reporte Documento Areas"));
			//c.generarReporte("recursos/documento_tipo.pdf", c.reporteDocumentosAgrupadosTipo("Reporte Documento Tipos"));
			c.generarReporte("recursos/documento_formato.pdf", c.reporteDocumentosAgrupadosFormato("Reporte Documento Formato"));
		}catch(JRException e)
		{
			e.printStackTrace();
		}
	}

}
