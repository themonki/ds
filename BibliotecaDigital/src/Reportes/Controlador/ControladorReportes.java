package Reportes.Controlador;


import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

import javax.swing.JTable;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRTableModelDataSource;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import Reportes.Dao.DaoReportes;
import Utilidades.TableDataSource;

public class ControladorReportes 
{
/* ***********************Reportes relacionados con usuarios************** */
	
	/*atributoUsuarios pueder ser genero, tipo de usuario,vinvulo con univalle o nivel escolaridad */
	/*OK*/
	public JasperPrint reporteUsuariosAgrupados(String atributoUsuario, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupados(atributoUsuario);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupados(atributoUsuario, tituloReporte, tableData);
	}
	
	/*OK*/
	public JasperPrint reporteUsuariosAgrupados(String atributoUsuario, String cualFecha, String fechaI, String fechaF, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupados(atributoUsuario, cualFecha, fechaI, fechaF);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupados(atributoUsuario, tituloReporte, tableData);
	}
	
	/*OK*/
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
	
	/*OK*/
	public JasperPrint reporteUsuariosAgrupadosTotales(String atributoUsuario, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupadosTotales(atributoUsuario);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupadosTotales(tituloReporte, atributoUsuario, tableData);
	}
	
	/*OK*/
	public JasperPrint reporteUsuariosAgrupadosTotales(String atributoUsuario, String cualFecha, String fechaI, String fechaF, String tituloReporte)throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAgrupadosTotales(atributoUsuario,cualFecha, fechaI, fechaF);
		daoReportes = null;
		return procesarDatosReporteUsuariosAgrupadosTotales(tituloReporte, atributoUsuario, tableData);
	}
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
	public JasperPrint reporteUsuariosAnioTotales(String tipoAnio, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes del total de usuarios agrupados por un tipo de año
		 * y retorna un reporte mostrando todos los usuarios en todos los años con
		 * todos los meses que se encuentren en el sistema.
		 * @param tipoAnio - String puede ser fecha_nacimiento o fecha_registro, usado para
		 * descriminar por que año se agrupara la vista del reporte.
		 * @pram tituloReporte - String el titulo usado para el reporte.
		 * @return JasperPrint que contiene el reporte y podrá ser usado para imprimirlo.
		 * @author Leonardo Ríos
		 * */
		
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaUsuariosAnioTotal(tipoAnio);
		daoReportes = null;
		return procesarDatosReporteUsuariosFechaTotales(tituloReporte, tipoAnio, tableData);
	}
	
	/*OK*/
	public JasperPrint reporteUsuariosAnioTotales(String tipoAnio, String anioI, String anioF, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes del total de usuarios agrupados por un tipo de año
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
		TableDataSource tableData = daoReportes.consultaUsuariosAnioTotal(tipoAnio, anioI, anioF);
		daoReportes = null;
		return procesarDatosReporteUsuariosFechaTotales(tituloReporte, tipoAnio, tableData);
	}
	
	/*OK*/
	public JasperPrint reporteUsuariosAnioMesTotales(String tipoAnio, String mesI, String mesF, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes del total de usuarios agrupados por un tipo de año
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
		TableDataSource tableData = daoReportes.consultaUsuariosAnioMesTotal(tipoAnio, mesI, mesF);
		daoReportes = null;
		return procesarDatosReporteUsuariosFechaTotales(tituloReporte, tipoAnio, tableData);
	}
	
	/*OK*/
	public JasperPrint reporteUsuariosAnioMesTotales(String tipoAnio, String anioI, String anioF, String mesI, String mesF, String tituloReporte) throws JRException
	{
		/**
		 * Método que permite generar reportes del total de usuarios agrupados por un tipo de año
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
		TableDataSource tableData = daoReportes.consultaUsuariosAnioMesTotal(tipoAnio, anioI, anioF, mesI, mesF);
		daoReportes = null;
		return procesarDatosReporteUsuariosFechaTotales(tituloReporte, tipoAnio, tableData);
	}
	
	/*OK*/
	private JasperPrint procesarDatosReporteUsuariosFechaTotales(String tituloReporte, String tipoAnio, TableDataSource tableData) throws JRException
	{
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("fecha", tipoAnio);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteUsuariosAgrupadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
/* **************reporte relacionados con areas ************************** */
	/*OK*/
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
	
	/*OK*/
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
	/*OK*/
	public JasperPrint reporteDocumentosAgrupadosArea(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Autor(es)");
		parametros.put("opcionA" , "Área: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosAgrupadosTipo(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosTipo();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Autor(es)");
		parametros.put("opcionA" , "Tipo: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosAgrupadosFormato(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosFormato();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Autor(es)");
		parametros.put("opcionA" , "Formato: ");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupado.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosAgrupadosAutor(String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosAgrupadosAutor();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcionA" , "Autor: ");
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/*OK*/
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
	
	/* ********reporte relacionado con descarga documentos* *******************/
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFecha(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFecha();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFecha(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFecha(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaAnio(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaAnio();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año Descarga: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_1.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaAnio(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaAnio(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año Descarga: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_1.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaMes(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaMes();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes Descarga: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaMes(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaMes(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes Descarga: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosArea(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Descargas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosArea(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosArea(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Descargas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosUsuario(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosUsuario();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosUsuario(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosUsuario(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Descargas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	
			/*totales para descarga*/
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Día de Descarga ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Día de Descarga ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaAnioTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaAnioTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año de Descarga ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosFechaAnioTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaAnioTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año de Descarga ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosFechaMesTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaMesTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes de Descarga ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosDescargadosFechaMesTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosFechaMesTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes de Descarga ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosAreaTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosAreaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Area Conocimiento");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosAreaTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosAreaTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Area Cocnocimiento");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosUsuarioTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosUsuarioTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Login Usuario");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosDescargadosUsuarioTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{

		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosDescargadosUsuarioTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Login Usuario ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	
	/* ***************reporte relacionados con consulta documentos* *********/
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFecha(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFecha();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFecha(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFecha(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaAnio(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaAnio();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año Consulta: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_1.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaAnio(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaAnio(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año Consulta: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_1.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaMes(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaMes();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes Consulta: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaMes(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaMes(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes Consulta: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocDescargaConsultaFecha_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosArea(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Consultas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosArea(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosArea(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Fecha");
		parametros.put("uso", "Consultas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosUsuario(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosUsuario();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosUsuario(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosUsuario(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("uso", "Consultas: ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosDescargados_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	
			/*totales para consulta*/
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Día de Consulta ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Día de Consulta ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaAnioTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaAnioTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año de Consulta ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosFechaAnioTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaAnioTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Año de Consulta ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosFechaMesTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaMesTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes de Consulta ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosConsultadosFechaMesTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosFechaMesTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Mes de Consulta ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosAreaTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosAreaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Area Conocimiento");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosAreaTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosAreaTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Area Conocimiento");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosUsuarioTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosUsuarioTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Login Usuario");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosConsultadosUsuarioTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosConsultadosUsuarioTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Login Usuario");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/* ************reporte relacionado con catalogo de documentos************ */
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFecha(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFecha();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaFecha_1.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFecha(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFecha(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaFecha_1.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaAnio(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaAnio();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaFecha_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaAnio(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaAnio(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaFecha_2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaMes(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaMes();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaFecha_4.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaMes(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaMes(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaFecha_4.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}

	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosArea(String introduccion,String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosArea();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaArea.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosArea(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosArea(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaArea.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*Ok*/
	public JasperPrint reporteDocumentosCatalogadosUsuario(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosUsuario();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaUsuario.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosUsuario(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosUsuario(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocCatalogaUsuario.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
			/*totales catalogados*/
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Día de Catalogación ");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Día de Catalogación");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaAnioTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaAnioTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
		parametros.put("opcion", "Año de Catalogación");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosFechaAnioTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaAnioTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
		parametros.put("opcion", "Año de Catalogación");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosCatalogadosFechaMesTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaMesTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
		parametros.put("opcion", "Mes de Catalogación");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	public JasperPrint reporteDocumentosCatalogadosFechaMesTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosFechaMesTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("introduccion", introduccion);
		parametros.put("opcion", "Mes de Catalogación");
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocConsultadosCatalogadosDescargadosFechaTotales2.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}

	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosAreaTotales(String introduccion,String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosAreaTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Area Conocimiento");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosAreaTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosAreaTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Area Conocimiento");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosUsuarioTotales(String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosUsuarioTotales();
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Login Usuario");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}
	
	/*OK*/
	public JasperPrint reporteDocumentosCatalogadosUsuarioTotales(String fechaI, String fechaF, String introduccion, String tituloReporte) throws JRException
	{
		DaoReportes daoReportes = new DaoReportes();
		TableDataSource tableData = daoReportes.consultaDocumentosCatalogadosUsuarioTotales(fechaI, fechaF);
		daoReportes = null;
		
		Map<String, String> parametros = new HashMap<String, String>();
		parametros.put("titulo", tituloReporte);
		parametros.put("opcion", "Login Usuario");
		parametros.put("introduccion", introduccion);
				
		JasperReport reporte = (JasperReport) JRLoader.loadObject("recursos/reporteDocAgrupadosTotales.jasper");
		JRTableModelDataSource table = new JRTableModelDataSource(tableData);
        JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, table);
        
        parametros = null;
        tableData = null;
        table = null;
        reporte = null;
        
        return jasperPrint;
	}

/* ********************metodos utilitarios *********************************/
	public void generarReporte(String rutaFinal, JasperPrint print)
	{
		/*JRExporter exporter = new JRPdfExporter(); 
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaFinal)); 
        
        try
        {
        	exporter.exportReport();
        }catch(JRException e)
        {
        	System.out.println("Exception generada en Controlador Reporte,pruebaRepote");
        	e.printStackTrace();
        }*/
		
		JasperViewer.viewReport(print, false);
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
	
	/*Metodo usado en la gui de reportesSQL*/
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
	
	
	
	/*public static void main(String arg[])
	{
		ControladorReportes c = new ControladorReportes();
		try
		{
			//System.out.println(c.consultaGenerica("select * from usuario"));
			
//			c.generarReporte("recursos/reportes/documento_areas.pdf", c.reporteDocumentosAgrupadosArea("Reporte Documento Areas"));
//			c.generarReporte("recursos/reportes/documento_tipo.pdf", c.reporteDocumentosAgrupadosTipo("Reporte Documento Tipo"));
//			c.generarReporte("recursos/reportes/documento_formato.pdf", c.reporteDocumentosAgrupadosFormato("Reporte Documento Formato"));
//			c.generarReporte("recursos/reportes/documento_autor.pdf", c.reporteDocumentosAgrupadosAutor("Reporte Documento Autores"));	
//			c.generarReporte("recursos/reportes/documento_formato_total.pdf", c.reporteDocumentosAgrupadosFormatoTotales("Reporte Documento Formato Totales"));
//			c.generarReporte("recursos/reportes/documento_tipo_total.pdf", c.reporteDocumentosAgrupadosTipoTotales("Reporte Documento Tipo Totales"));
//			c.generarReporte("recursos/reportes/documento_area_total.pdf", c.reporteDocumentosAgrupadosAreaTotales("Reporte Documento Area Totales"));
//			c.generarReporte("recursos/reportes/documento_autor_total.pdf", c.reporteDocumentosAgrupadosAutorTotales("Reporte Documento Autor Totales"));
			
//			c.generarReporte("recursos/reportes/usuario_escolaridad.pdf", c.reporteUsuariosAgrupados("nivel_escolaridad", "Reporte Usuarios Nivel Escolaridad"));
//			c.generarReporte("recursos/reportes/usuario_escolaridad_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupados("nivel_escolaridad", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Nivel Escolaridad"));
//			c.generarReporte("recursos/reportes/usuario_escolaridad_intervalo_registro.pdf", c.reporteUsuariosAgrupados("nivel_escolaridad", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Nivel Escolaridad"));
//			c.generarReporte("recursos/reportes/usuario_genero.pdf", c.reporteUsuariosAgrupados("genero", "Reporte Usuarios Genero"));
//			c.generarReporte("recursos/reportes/usuario_genero_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupados("genero", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Genero"));
//			c.generarReporte("recursos/reportes/usuario_genero_intervalo_registro.pdf", c.reporteUsuariosAgrupados("genero", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Genero"));
//			c.generarReporte("recursos/reportes/usuario_tipo.pdf", c.reporteUsuariosAgrupados("tipo", "Reporte Usuarios Tipo"));
//			c.generarReporte("recursos/reportes/usuario_tipo_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupados("tipo", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Tipo"));
//			c.generarReporte("recursos/reportes/usuario_tipo_intervalo_registro.pdf", c.reporteUsuariosAgrupados("tipo", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Tipo"));
//			c.generarReporte("recursos/reportes/usuario_vinculo.pdf", c.reporteUsuariosAgrupados("vinculo_univalle", "Reporte Usuarios Vinculo Univalle"));
//			c.generarReporte("recursos/reportes/usuario_vinculo_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupados("vinculo_univalle", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Vinculo Univalle"));
//			c.generarReporte("recursos/reportes/usuario_vinculo_intervalo_registro.pdf", c.reporteUsuariosAgrupados("vinculo_univalle", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Vinculo Univalle"));
//			c.generarReporte("recursos/reportes/usuario_escolaridad_total.pdf", c.reporteUsuariosAgrupadosTotales("nivel_escolaridad", "Reporte Usuarios Nivel Escolaridad Total"));
//			c.generarReporte("recursos/reportes/usuario_escolaridad_total_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupadosTotales("nivel_escolaridad", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Nivel Escolaridad Total"));
//			c.generarReporte("recursos/reportes/usuario_escolaridad_total_intervalo_registro.pdf", c.reporteUsuariosAgrupadosTotales("nivel_escolaridad", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Nivel Escolaridad Total"));
//			c.generarReporte("recursos/reportes/usuario_genero_total.pdf", c.reporteUsuariosAgrupadosTotales("genero", "Reporte Usuarios Genero Total"));
//			c.generarReporte("recursos/reportes/usuario_genero_total_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupadosTotales("genero", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Genero Total"));
//			c.generarReporte("recursos/reportes/usuario_genero_total_intervalo_registro.pdf", c.reporteUsuariosAgrupadosTotales("genero", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Genero Total"));
//			c.generarReporte("recursos/reportes/usuario_tipo_total.pdf", c.reporteUsuariosAgrupadosTotales("tipo", "Reporte Usuarios Tipo Total"));
//			c.generarReporte("recursos/reportes/usuario_tipo_total_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupadosTotales("tipo", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Tipo Total"));
//			c.generarReporte("recursos/reportes/usuario_tipo_total_intervalo_registro.pdf", c.reporteUsuariosAgrupadosTotales("tipo", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Tipo Total"));
//			c.generarReporte("recursos/reportes/usuario_vinculo_total.pdf", c.reporteUsuariosAgrupadosTotales("vinculo_univalle", "Reporte Usuarios Vinculo Univalle Totales"));
//			c.generarReporte("recursos/reportes/usuario_vinculo_total_intervalo_nacimiento.pdf", c.reporteUsuariosAgrupadosTotales("vinculo_univalle", "fecha_nacimiento","1991-03-10","2001-05-21","Reporte Usuarios Vinculo Univalle Total"));
//			c.generarReporte("recursos/reportes/usuario_vinculo_total_intervalo_registro.pdf", c.reporteUsuariosAgrupadosTotales("vinculo_univalle", "fecha_registro","1991-03-10","2001-05-21","Reporte Usuarios Vinvulo Univalle Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento.pdf", c.reporteUsuariosAnio("fecha_nacimiento", "Reporte Usuarios por Fecha de Nacimiento"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_intervalo_anio.pdf", c.reporteUsuariosAnio("fecha_nacimiento", "1989","2003","Reporte Usuarios por Fecha de Nacimiento"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_intervalo_mes.pdf", c.reporteUsuariosAnioMes("fecha_nacimiento","05","11", "Reporte Usuarios por Fecha de Nacimiento"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_intervalo_anio_mes.pdf", c.reporteUsuariosAnioMes("fecha_nacimiento","1989","2003","05","11", "Reporte Usuarios por Fecha de Nacimiento"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_total.pdf", c.reporteUsuariosAnioTotales("fecha_nacimiento", "Reporte Usuarios por Fecha de Nacimiento Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_total_intervalo_anio.pdf", c.reporteUsuariosAnioTotales("fecha_nacimiento", "1989","2003","Reporte Usuarios por Fecha de Nacimiento Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_total_intervalo_mes.pdf", c.reporteUsuariosAnioMesTotales("fecha_nacimiento","05","11", "Reporte Usuarios por Fecha de Nacimiento Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_nacimiento_total_intervalo_anio_mes.pdf", c.reporteUsuariosAnioMesTotales("fecha_nacimiento","1989","2003","05","11", "Reporte Usuarios por Fecha de Nacimiento Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro.pdf", c.reporteUsuariosAnio("fecha_registro", "Reporte Usuarios por Fecha de Registro"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_intervalo_anio.pdf", c.reporteUsuariosAnio("fecha_registro", "1989","2003","Reporte Usuarios por Fecha de Registro"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_intervalo_mes.pdf", c.reporteUsuariosAnioMes("fecha_registro","05","11", "Reporte Usuarios por Fecha de Registro"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_intervalo_anio_mes.pdf", c.reporteUsuariosAnioMes("fecha_registro","1989","2003","05","11", "Reporte Usuarios por Fecha de Registro"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_total.pdf", c.reporteUsuariosAnioTotales("fecha_registro", "Reporte Usuarios por Fecha de Registro Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_total_intervalo_anio.pdf", c.reporteUsuariosAnioTotales("fecha_registro", "1989","2003","Reporte Usuarios por Fecha de Registro Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_total_intervalo_mes.pdf", c.reporteUsuariosAnioMesTotales("fecha_registro","05","11", "Reporte Usuarios por Fecha de Registro Total"));
//			c.generarReporte("recursos/reportes/usuario_fecha_registro_total_intervalo_anio_mes.pdf", c.reporteUsuariosAnioMesTotales("fecha_registro","1989","2003","05","11", "Reporte Usuarios por Fecha de Registro Total"));
			
//			c.generarReporte("recursos/reportes/areas.pdf", c.reporteAreasAgrupadas("Reporte Areas"));
//			c.generarReporte("recursos/reportes/areas_totales.pdf", c.reporteAreasAgrupadasTotales("Reporte Areas Totales"));
			
//			c.generarReporte("recursos/reportes/descargado_fecha.pdf", c.reporteDocumentosDescargadosFecha("introduccion.","Reporte Documentos Descargados por Fecha"));
//			c.generarReporte("recursos/reportes/descargado_fecha_intervalo.pdf", c.reporteDocumentosDescargadosFecha("1989-02-15","2010-01-12","introduccion.","Reporte Documentos Descargados por Fecha"));
//			c.generarReporte("recursos/reportes/descargado_fecha_anio.pdf", c.reporteDocumentosDescargadosFechaAnio("introduccion.","Reporte Documentos Descargados por Fecha"));
//			c.generarReporte("recursos/reportes/descargado_fecha_anio_intervalo.pdf", c.reporteDocumentosDescargadosFechaAnio("1989-02-15","2010-01-12","introduccion.","Reporte Documentos Descargados por Fecha"));
//			c.generarReporte("recursos/reportes/descargado_fecha_mes.pdf", c.reporteDocumentosDescargadosFechaMes("introduccion.","Reporte Documentos Descargados por Fecha"));
//			c.generarReporte("recursos/reportes/descargado_fecha_mes_intervalo.pdf", c.reporteDocumentosDescargadosFechaMes("1989-02-15","2010-01-12","introduccion.","Reporte Documentos Descargados por Fecha"));
//			c.generarReporte("recursos/reportes/descargado_area.pdf", c.reporteDocumentosDescargadosArea("introduccion.","Reporte Documentos Descargados por Área"));
//			c.generarReporte("recursos/reportes/descargado_area_intervalo.pdf", c.reporteDocumentosDescargadosArea("1989-02-15","2010-01-12","introduccion.","Reporte Documentos Descargados por Área"));
//			c.generarReporte("recursos/reportes/descargado_usuario.pdf", c.reporteDocumentosDescargadosUsuario("introduccion.","Reporte Documentos Descargados por Usuario"));
//			c.generarReporte("recursos/reportes/descargado_usuario_intervalo.pdf", c.reporteDocumentosDescargadosUsuario("1989-02-15","2010-01-12","introduccion.","Reporte Documentos Descargados por Usuario"));
//			c.generarReporte("recursos/reportes/consultado_fecha.pdf", c.reporteDocumentosConsultadosFecha("introduccion.","Reporte Documentos Consultados por Fecha"));
//			c.generarReporte("recursos/reportes/consultado_fecha_intervalo.pdf", c.reporteDocumentosConsultadosFecha("2011-05-20","2011-05-30","introduccion.","Reporte Consultados por Fecha"));
//			c.generarReporte("recursos/reportes/consultado_fecha_anio.pdf", c.reporteDocumentosConsultadosFechaAnio("introduccion.","Reporte Documentos Consultados por Fecha"));
//			c.generarReporte("recursos/reportes/consultado_fecha_anio_intervalo.pdf", c.reporteDocumentosConsultadosFechaAnio("2011-05-20","2011-05-30","introduccion.","Reporte Consultados por Fecha"));
//			c.generarReporte("recursos/reportes/consultado_fecha_mes.pdf", c.reporteDocumentosConsultadosFechaMes("introduccion.","Reporte Documentos Consultados por Fecha"));
//			c.generarReporte("recursos/reportes/consultado_fecha_mes_intervalo.pdf", c.reporteDocumentosConsultadosFechaMes("2011-05-20","2011-05-30","introduccion.","Reporte Consultados por Fecha"));
//			c.generarReporte("recursos/reportes/consultado_area.pdf", c.reporteDocumentosConsultadosArea("introduccion.","Reporte Documentos Consultados por Área"));
//			c.generarReporte("recursos/reportes/consultado_area_intervalo.pdf", c.reporteDocumentosConsultadosArea("2011-05-20","2011-05-30","introduccion.","Reporte Documentos Consultados por Área"));
//			c.generarReporte("recursos/reportes/consultado_usuario.pdf", c.reporteDocumentosConsultadosUsuario("introduccion.","Reporte Documentos Consultados por Usuario"));
//			c.generarReporte("recursos/reportes/consultado_usuario_intervalo.pdf", c.reporteDocumentosConsultadosUsuario("1989-02-15","2010-01-12","introduccion.","Reporte Documentos Consultados por Usuario"));
			
//			c.generarReporte("recursos/reportes/catalogado_fecha.pdf", c.reporteDocumentosCatalogadosFecha("Esto es una introducción", "Reporte Documentos Catalogados por Fecha"));
//			c.generarReporte("recursos/reportes/catalogado_fecha_intervalo.pdf", c.reporteDocumentosCatalogadosFecha("1999-02-23","2011-05-03","Esto es una introducción", "Reporte Documentos Catalogados por Fecha"));
//			c.generarReporte("recursos/reportes/catalogado_fecha_anio.pdf", c.reporteDocumentosCatalogadosFechaAnio("Esto es una introducción", "Reporte Documentos Catalogados por Fecha Año"));
//			c.generarReporte("recursos/reportes/catalogado_fecha_intervalo_anio.pdf", c.reporteDocumentosCatalogadosFechaAnio("1999-02-01","2011-04-05","Esto es una introducción", "Reporte Documentos Catalogados por Fecha Año"));
//			c.generarReporte("recursos/reportes/catalogado_fecha_mes.pdf", c.reporteDocumentosCatalogadosFechaMes("Esto es una introducción", "Reporte Documentos Catalogados por Fecha Mes"));
//			c.generarReporte("recursos/reportes/catalogado_fecha_intervalo_mes.pdf", c.reporteDocumentosCatalogadosFechaMes("1999-02-03","2011-05-05","Esto es una introducción", "Reporte Documentos Catalogados por Fecha Mes"));
//			c.generarReporte("recursos/reportes/catalogado_area.pdf", c.reporteDocumentosCatalogadosArea("Esto es una introducción", "Reporte Documentos Catalogados por Área"));
//			c.generarReporte("recursos/reportes/catalogado_area_intervalo.pdf", c.reporteDocumentosCatalogadosArea("1993-03-18","2011-09-08","Esto es una introducción", "Reporte Documentos Catalogados por Área"));
			c.generarReporte("recursos/reportes/catalogado_usuario.pdf", c.reporteDocumentosCatalogadosUsuario("Esto es una introducción", "Reporte Documentos Catalogados por Usaurio"));
//			c.generarReporte("recursos/reportes/catalogado_usuario_intervalo.pdf", c.reporteDocumentosCatalogadosUsuario("1993-03-18","2011-09-08","Esto es una introducción", "Reporte Documentos Catalogados por Usuario"));
			
//			c.generarReporte("", c.reporteDocumentosDescargadosAreaTotales("introduccion", "Reporte Documentos Descargados por Area Total"));
//			c.generarReporte("", c.reporteDocumentosDescargadosAreaTotales("1989-02-03","2005-03-14","introduccion", "Reporte Documentos Descargados por Area Total"));
//			c.generarReporte("", c.reporteDocumentosDescargadosUsuarioTotales("introduccion", "Reporte Documentos Descargadospor Usuario Total"));
//			c.generarReporte("", c.reporteDocumentosDescargadosUsuarioTotales("1989-02-03","2005-03-14","introduccion", "Reporte Documentos Descargados por Usuario Total"));
			
//			c.generarReporte("", c.reporteDocumentosConsultadosAreaTotales("introduccion", "Reporte Documentos consultados por Area Total"));
//			c.generarReporte("", c.reporteDocumentosConsultadosAreaTotales("1989-02-03","2005-03-14","introduccion", "Reporte Documentos consultados por Area Total"));
//			c.generarReporte("", c.reporteDocumentosConsultadosUsuarioTotales("introduccion", "Reporte Documentos Consultados por Usuario Total"));
//			c.generarReporte("", c.reporteDocumentosConsultadosUsuarioTotales("1989-02-03","2005-03-14","introduccion", "Reporte Documentos Consultados por Usuario Total"));
			
//			c.generarReporte("", c.reporteDocumentosCatalogadosAreaTotales("introduccion", "Reporte Documentos Catalogados por Area Total"));
//			c.generarReporte("", c.reporteDocumentosCatalogadosAreaTotales("1989-02-03","2005-03-14","introduccion", "Reporte Documentos Catalogados por Area Total"));
//			c.generarReporte("", c.reporteDocumentosCatalogadosUsuarioTotales("introduccion", "Reporte Documentos Catalogados por Usuario Total"));
//			c.generarReporte("", c.reporteDocumentosCatalogadosUsuarioTotales("1989-02-03","2005-03-14","introduccion", "Reporte Documentos Catalogados por Usuario Total"));
			
			throw new JRException("no se");
		}catch(JRException e)
		{
			e.printStackTrace();
		}
	}*/

}

