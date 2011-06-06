package Reportes.Controlador;


import java.lang.annotation.Documented;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;

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
/* ***********************Reportes relacionados con usuarios************** */
	
	/*atributoUsuarios pueder ser genero, tipo de usuario,vinvulo con univalle o nivel escolaridad */
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
	
	
	public JasperPrint reporteUsuariosAnio(String tipoAnio, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes de usuario agrupados por un tipo de año
		 * y retorna un reporte mostrando todos los usuarios en todos los años con
		 * todos los meses que se encuentren en el sistema.
		 * @param tipoAnio - String puede ser fecha_nacimiento o fecha_registro, usado para
		 * descriminar por que año se agrupara la vista del reporte.
		 * @pram tituloReporte - String el titulo usado para el reporte.
		 * @return JasperPrint que contiene el reporte y podrá ser usado para imprimirlo.
		 * @author Leonardo Ríos
		 * */
		
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAnio(tipoAnio);
		daoReportes = null;
		return procesarDatosReporteUsuariosFecha(tituloReporte, tableData);
	}
	
	public JasperPrint reporteUsuariosAnio(String tipoAnio, String anioI, String anioF, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes de usuario agrupados por un tipo de año
		 * que esta restringido entre anioI y anioF, retorna un reporte mostrando los usuarios
		 * que se encentren en el sistema entre los años indicados con sus respectivos meses.
		 * @param tipoAnio - String puede ser nacimiento o registro, usado para
		 * descriminar por que año se agrupara la vista del reporte.
		 * @param anioI - String para formar un intervalo de año, es el año inicial, cuatro caracteres.
		 * @param anioF - String para formar un intervalo de año, es el año final, cuatro caracteres.
		 * @pram tituloReporte - String el titulo usado para el reporte.
		 * @return JasperPrint que contiene el reporte y podrá ser usado para imprimirlo.
		 * @author Leonardo Ríos
		 * */
		
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAnio(tipoAnio, anioI, anioF);
		daoReportes = null;
		return procesarDatosReporteUsuariosFecha(tituloReporte, tableData);
	}
	
	public JasperPrint reporteUsuariosAnioMes(String tipoAnio, String mesI, String mesF, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes de usuario agrupados por un tipo de año
		 * y cada año tiene sus meses restringidos entre mesI y mesF
		 * retorna un reporte mostrando los usuarios que se encentren en el sistema en todos los años 
		 * con sus meses restringidos por los mese indicados.
		 * @param tipoAnio - String puede ser fecha_nacimiento o fecha_registro, usado para
		 * descriminar por que año se agrupara la vista del reporte.
		 * @param mesI - String para formar el intervalo de mes en cada año, es el mes inicial, dos caracteres.
		 * @param mesF - String para formar el intervalo de mes en cada año, es el mes final, dos caracteres.
		 * @pram tituloReporte - String el titulo usado para el reporte.
		 * @return JasperPrint que contiene el reporte y podrá ser usado para imprimirlo.
		 * @author Leonardo Ríos
		 * */

		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAnioMes(tipoAnio, mesI, mesF);
		daoReportes = null;
		return procesarDatosReporteUsuariosFecha(tituloReporte, tableData);
	}
	
	public JasperPrint reporteUsuariosAnioMes(String tipoAnio, String anioI, String anioF, String mesI, String mesF, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes de usuario agrupados por un tipo de año
		 * que esta restringido entre anioI y anioF, y cada año tiene sus meses restringidos entre mesI y mesF
		 * retorna un reporte mostrando los usuarios que se encentren en el sistema entre los años y los
		 * meses indicados indicados.
		 * @param tipoAnio - String puede ser fecha_nacimiento o fecha_registo, usado para
		 * descriminar por que año se agrupara la vista del reporte.
		 * @param anioI - String para formar un intervalo de año, es el año inicial, cuatro caracteres.
		 * @param anioF - String para formar un intervalo de año, es el año final, cuatro caracteres.
		 * @param mesI - String para formar el intervalo de mes en cada año, es el mes inicial, dos caracteres.
		 * @param mesF - String para formar el intervalo de mes en cada año, es el mes final, dos caracteres.
		 * @pram tituloReporte - String el titulo usado para el reporte.
		 * @return JasperPrint que contiene el reporte y podrá ser usado para imprimirlo.
		 * @author Leonardo Ríos
		 * */
		
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAnioMes(tipoAnio, anioI, anioF, mesI, mesF);
		daoReportes = null;
		return procesarDatosReporteUsuariosFecha(tituloReporte, tableData);
	}
	
	private JasperPrint procesarDatosReporteUsuariosFecha(String tituloReporte, TableDataSource tableData) throws JRException
	{
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupadosFecha.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
/* **************reporte relacionados con areas ************************** */
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

/* ****************reportes relacionados con documentos **************************** */
	public JasperPrint reporteDocumentosAgrupadosArea(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Autor(es)");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
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
		parametros.put("opcion", "Autor(es)");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
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
		parametros.put("opcion", "Autor(es)");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosAutor(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosAutor();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		//parametros.put("opcion", "Areas de Conocimiento");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosFormatoTotales(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosFormatoTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Documentos agrupados por Formato");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosTipoTotales(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosTipoTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Documentos agrupados por Tipo");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosAreaTotales(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosAreaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Documentos agrupados por Área");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosAgrupadosAutorTotales(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosAutorTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Documentos agrupados por Autor");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosFecha(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFecha();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosFecha(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFecha(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosArea(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Descargas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosArea(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosArea(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Descargas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosUsuario(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosUsuario();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosUsuario(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosUsuario(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosFecha(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFecha();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosFecha(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFecha(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosArea(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Consultas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosArea(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosArea(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Consultas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosUsuario(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosUsuario();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosUsuario(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosUsuario(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosCatalogadosFecha(String tituloReporte) throws JRException
	{
		return null;
	}
	
	public JasperPrint reporteDocumentosCatalogadosFecha(String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		return null;
	}
	
	public JasperPrint reporteDocumentosCatalogadosArea(String tituloReporte) throws JRException
	{
		return null;
	}
	
	public JasperPrint reporteDocumentosCatalogadosArea(String fechaI, String fechaF, String tiutloReporte) throws JRException
	{
		return null;
	}
	
	public JasperPrint reporteDocumetnosCatalogadosUsuario(String tituloReporte) throws JRException
	{
		return null;
	}
	
	public JasperPrint reporteDocumentosCatalogadosUsuario(String fechaI, String fechaF, String tituloReprte) throws JRException
	{
		return null;
	}
	
/* ********************metodos utilitarios *********************************/
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
	
	
	
	/*
	 * Metodo que retorna el nombre de las tablas de la base de datos proyecto.
	 * 
	 * */
	
	public Vector<String> obtenerNombreTablas()
	{
		DaoReportes daoReportes = new DaoReportes();
		Vector<String> tablas = daoReportes.obtenerNombreTablas();
		
		return tablas;
	}
	
	/*metodo aparentemente no usado*/
	public Vector<String> obtenerNombreTablas(String nombreTabla)
	{
		DaoReportes daoReportes = new DaoReportes();
		Vector<String> atributos = daoReportes.obtenerAtributosTabla(nombreTabla);
		
		return atributos;
	}
	
	public JTable consultaGenerica(String consultaSql)
	{
		DaoReportes daoReportes = new DaoReportes();
		JTable tabla = daoReportes.consultaGenerica(consultaSql);
		return tabla;
	}
	
	
	
	public static void main(String arg[])
	{
		ControladorReportes c = new ControladorReportes();
		try
		{
			//System.out.println(c.consultaGenerica("select * from usuario"));
			//c.generarReporte("recursos/documento_areas.pdf", c.reporteDocumentosAgrupadosArea("Reporte Documento Areas"));
			//c.generarReporte("recursos/documento_tipo.pdf", c.reporteDocumentosAgrupadosTipo("Reporte Documento Tipo"));
			//c.generarReporte("recursos/documento_formato.pdf", c.reporteDocumentosAgrupadosFormato("Reporte Documento Formato"));
			//c.generarReporte("recursos/documento_autor.pdf", c.reporteDocumentosAgrupadosAutor("Reporte Documento Autores"));	
			//c.generarReporte("recursos/documento_formato_total.pdf", c.reporteDocumentosAgrupadosFormatoTotales("Reporte Documento Formato Totales"));
			//c.generarReporte("recursos/documento_tipo_total.pdf", c.reporteDocumentosAgrupadosTipoTotales("Reporte Documento Tipo Totales"));
			//c.generarReporte("recursos/documento_area_total.pdf", c.reporteDocumentosAgrupadosAreaTotales("Reporte Documento Area Totales"));
			//c.generarReporte("recursos/documento_autor_total.pdf", c.reporteDocumentosAgrupadosAutorTotales("Reporte Documento Autor Totales"));
			
			//c.generarReporte("recursos/usuario_escolaridad.pdf", c.reporteUsuariosAgrupados("nivel_escolaridad", "Reporte Usuarios Nivel Escolaridad"));
			//c.generarReporte("recursos/usuario_genero.pdf", c.reporteUsuariosAgrupados("genero", "Reporte Usuarios Genero"));
			//c.generarReporte("recursos/usuario_tipo.pdf", c.reporteUsuariosAgrupados("tipo", "Reporte Usuarios Tipo"));
			//c.generarReporte("recursos/usuario_vinculo.pdf", c.reporteUsuariosAgrupados("vinculo_univalle", "Reporte Usuarios Vinculo Univalle"));
			//c.generarReporte("recursos/usuario_escolaridad_total.pdf", c.reporteUsuariosAgrupadosTotales("nivel_escolaridad", "Reporte Usuarios Nivel Escolaridad Total"));
			//c.generarReporte("recursos/usuario_genero_total.pdf", c.reporteUsuariosAgrupadosTotales("genero", "Reporte Usuarios Genero Total"));
			//c.generarReporte("recursos/usuario_tipo_total.pdf", c.reporteUsuariosAgrupadosTotales("tipo", "Reporte Usuarios Tipo Total"));
			//c.generarReporte("recursos/usuario_vinculo_total.pdf", c.reporteUsuariosAgrupadosTotales("vinculo_univalle", "Reporte Usuarios Vinculo Univalle Totales"));
			//c.generarReporte("recursos/usuario_fecha_nacimiento.pdf", c.reporteUsuariosAnio("fecha_nacimiento", "Reporte Usuarios por Fecha de Nacimiento"));
			//c.generarReporte("recursos/usuario_fecha_registro.pdf", c.reporteUsuariosAnio("fecha_registro", "Reporte Usuarios por Fecha de Registro"));
			
			//c.generarReporte("recursos/areas.pdf", c.reporteAreasAgrupadas("Reporte Areas"));
			//c.generarReporte("recursos/areas_totales.pdf", c.reporteAreasAgrupadasTotales("Reporte Areas Totales"));
			
			//c.generarReporte("recursos/descargado_fecha.pdf", c.reporteDocumentosDescargadosFecha("Reporte Documentos Descargados por Fecha"));
			//c.generarReporte("recursos/descargado_fecha_intervalo.pdf", c.reporteDocumentosDescargadosFecha("1989-02-15","2010-01-12","Reporte Documentos Descargados por Fecha"));
			//c.generarReporte("recursos/descargado_area.pdf", c.reporteDocumentosDescargadosArea("Reporte Documentos Descargados por Área"));
			//c.generarReporte("recursos/descargado_area_intervalo.pdf", c.reporteDocumentosDescargadosArea("1989-02-15","2010-01-12","Reporte Documentos Descargados por Área"));
			//c.generarReporte("recursos/descargado_usuario.pdf", c.reporteDocumentosDescargadosUsuario("Reporte Documentos Descargados por Usuario"));
			//c.generarReporte("recursos/descargado_usuario_intervalo.pdf", c.reporteDocumentosDescargadosUsuario("1989-02-15","2010-01-12","Reporte Documentos Descargados por Usuario"));
			//c.generarReporte("recursos/consultado_fecha.pdf", c.reporteDocumentosConsultadosFecha("Reporte Documentos Consultados por Fecha"));
			//c.generarReporte("recursos/consultado_fecha_intervalo.pdf", c.reporteDocumentosConsultadosFecha("2011-05-20","2011-05-30","Reporte Consultados Descargados por Fecha"));
			//c.generarReporte("recursos/consultado_area.pdf", c.reporteDocumentosConsultadosArea("Reporte Documentos Consultados por Área"));
			//c.generarReporte("recursos/consultado_area_intervalo.pdf", c.reporteDocumentosConsultadosArea("2011-05-20","2011-05-30","Reporte Documentos Consultados por Área"));
			//c.generarReporte("recursos/consultado_usuario.pdf", c.reporteDocumentosConsultadosUsuario("Reporte Documentos Consultados por Usuario"));
			//c.generarReporte("recursos/consutlado_usuario_intervalo.pdf", c.reporteDocumentosConsultadosUsuario("1989-02-15","2010-01-12","Reporte Documentos Consultados por Usuario"));
			
			throw new JRException("no se");
		}catch(JRException e)
		{
			e.printStackTrace();
		}
	}

}
