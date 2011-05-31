package Reportes.Logica;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.ExceptionConverter;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPageEventHelper;
import com.itextpdf.text.pdf.PdfTemplate;
import com.itextpdf.text.pdf.PdfWriter;

import Reportes.Controlador.ControladorReportes;
import Reportes.Logica.Reporte;

public class GenerarReporte {
	
	private String imagen = "recursos/logo3dpajaroGrande.png";
	private String path = null;
	private Document informe;	
	private String encabezado=null;
	
	/*Datos*/
	
	private Vector<String> vectorOrderBy = null;
	private Vector<String> vectorColumnas = null;
	private Vector<String> vectorRegistros = null; 
	
	public GenerarReporte(Reporte reporte, String ruta){
			path = ruta;
			vectorOrderBy = reporte.getVectorOrderBy();
			vectorColumnas = reporte.getVectorColumnas();
			vectorRegistros = reporte.getVectorRegistros();
			encabezado = reporte.getEncabezado();
			
			try {
				crearPDF();
				escribirTabla();
				javax.swing.JOptionPane.showMessageDialog(null,"Su reporte se ha generado con exito.","OK",javax.swing.JOptionPane.INFORMATION_MESSAGE);
			} catch (FileNotFoundException e) {				
				e.printStackTrace();
				javax.swing.JOptionPane.showMessageDialog(null,"Reporte no se ha generado.","OK",javax.swing.JOptionPane.ERROR_MESSAGE);
			} catch (MalformedURLException e) {
				e.printStackTrace();
				javax.swing.JOptionPane.showMessageDialog(null,"Reporte no se ha generado.","OK",javax.swing.JOptionPane.ERROR_MESSAGE);
			} catch (DocumentException e) {
				e.printStackTrace();
				javax.swing.JOptionPane.showMessageDialog(null,"Reporte no se ha generado.","OK",javax.swing.JOptionPane.ERROR_MESSAGE);
			} catch (IOException e) {
				e.printStackTrace();
				javax.swing.JOptionPane.showMessageDialog(null,"Reporte no se ha generado.","OK",javax.swing.JOptionPane.ERROR_MESSAGE);
			}
			
			
	}
	
	public void crearPDF() throws FileNotFoundException, DocumentException, MalformedURLException, IOException{
		
		Font font = new Font(Font.FontFamily.COURIER , 24,Font.BOLD);
		Font fontFecha = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
		
		informe = new Document(PageSize.A4, 50, 50, 50, 50);
		PdfWriter writer = PdfWriter.getInstance(informe, new FileOutputStream(path));
		writer.setPageEvent(new PaginaEvent());
		informe.open();
		
		Image logo = Image.getInstance(imagen);
		logo.setAlignment(Element.ALIGN_RIGHT);
		logo.scalePercent(70);
		informe.add(logo);
		
		Paragraph enc = new Paragraph(encabezado, font);
		enc.setAlignment(Element.ALIGN_CENTER);
		informe.add(enc);
		
		Date fechaActual = new Date();
		SimpleDateFormat formato = new SimpleDateFormat("EEEEEEE',' dd 'de' MMMMM 'del' yyyy");
		Paragraph fecha= new Paragraph(formato.format(fechaActual), fontFecha);
		fecha.setAlignment(Element.ALIGN_CENTER);
		fecha.setSpacingAfter(10);
		informe.add(fecha);
		
		
	}
	
	public void escribirTabla() throws DocumentException, IOException{

		
		/*Organizar Cabecera*/
		PdfPTable encabezadoTabla = new PdfPTable(vectorColumnas.size());
		encabezadoTabla.setHorizontalAlignment(Element.ALIGN_CENTER);
		encabezadoTabla.getDefaultCell().setBackgroundColor(BaseColor.RED);
		
		Font fontEnc = new Font(Font.FontFamily.COURIER, 10, Font.BOLD, BaseColor.WHITE);
		for(int i=0;i<vectorColumnas.size();i++)
		{
			Paragraph columna = new Paragraph(vectorColumnas.elementAt(i), fontEnc);
			encabezadoTabla.addCell(columna);
		}
		
		encabezadoTabla.setWidthPercentage(90);
		Font fontOrder = new Font(Font.FontFamily.COURIER, 12, Font.BOLD);
		Font fontValor = new Font(Font.FontFamily.COURIER, 10, Font.NORMAL);
		for(int i=0;i<vectorOrderBy.size();i++)
		{
			Paragraph order = new Paragraph(vectorOrderBy.elementAt(i), fontOrder);
			order.setAlignment(Element.ALIGN_LEFT);
			order.setSpacingAfter(10);
			informe.add(order);
			
			//se agrega el nombre de las columnas
			informe.add(encabezadoTabla);
			
			PdfPTable table = new PdfPTable(vectorColumnas.size());
			for(int j=0; j<vectorRegistros.size();j++)
			{
				if(vectorRegistros.elementAt(j).equals(""))
				{
					vectorRegistros.remove(j);
					break;
				}
				
				StringTokenizer stk = new StringTokenizer(vectorRegistros.elementAt(j),"|");
				while(stk.hasMoreElements())
				{
					String valorString = stk.nextToken();
					Paragraph valor = new Paragraph(valorString,fontValor);
					table.addCell(valor);
					
				}

				vectorRegistros.remove(j);
				j--;
				
			}
			
			table.setSpacingAfter(10);
			table.setWidthPercentage(90);
			informe.add(table);
		
		}
		
        informe.close();
	}

	
	/*Clase para colocar el numero de paginas del reporte*/
	
	private class PaginaEvent extends PdfPageEventHelper
	{
		  PdfTemplate total;
		  
		  public void onOpenDocument(PdfWriter writer, Document document) {
			  total=writer.getDirectContent().createTemplate(30, 16);
		  }
		  
		  public void onEndPage(PdfWriter writer, Document document) {
			  PdfPTable pagina = new PdfPTable(3);
	            try {
	                pagina.setWidths(new int[]{40,20, 2});
	                pagina.setTotalWidth(480);
	                pagina.setLockedWidth(true);
	                pagina.getDefaultCell().setFixedHeight(20);
	                pagina.getDefaultCell().setBorder(Rectangle.TOP);
	                pagina.addCell(new Phrase("Biblioteca digital EISC",
	                		new Font(Font.FontFamily.COURIER, 12, Font.BOLD)));
	                pagina.getDefaultCell().setHorizontalAlignment(Element.ALIGN_RIGHT);
	                pagina.addCell(String.format("Pagina %d de", writer.getPageNumber()));
	                PdfPCell cell = new PdfPCell(Image.getInstance(total));
	                cell.setBorder(Rectangle.TOP);
	                pagina.addCell(cell);
	                pagina.writeSelectedRows(0, -1, 50, 50, writer.getDirectContent());
	            }
	            catch(DocumentException de) {
	                throw new ExceptionConverter(de);
	            }
		  }
		  
		  public void onCloseDocument(PdfWriter writer, Document document) {
	            ColumnText.showTextAligned(total, Element.ALIGN_LEFT,
	                    new Phrase(String.valueOf(writer.getPageNumber() - 1)),
	                    2, 2, 0);
	        }
		  
	}
	
	/*public static void main(String[] args)
	{
		ControladorReportes controlador = new ControladorReportes();
		Reporte reporte = controlador.consultarUsuariosOrdenados("genero");
		reporte.setEncabezado("UN REPORTE OMG!!");
		GenerarReporte genera = new GenerarReporte(reporte, "C:/Documents and Settings/ANDREA/Desktop/ReporteCodigo.pdf");
	}*/
	
	public static void pruebaReporte(String rutaFinal, JasperPrint print)
	{
		JRExporter exporter = new JRPdfExporter(); 
        exporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File(rutaFinal)); 
        
        try
        {
        	exporter.exportReport();
        }catch(JRException e)
        {
        	System.out.println("Exception generada en GenerarReporte,pruebaRepote");
        	e.printStackTrace();
        }
	}
	
}
