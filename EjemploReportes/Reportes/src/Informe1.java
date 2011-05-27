import java.io.*;

import java.net.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.StringTokenizer;
import java.util.Vector;

import javax.swing.text.AbstractDocument.ElementEdit;

import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;

public class Informe1{
	
	private String imagen = "logo3dpajaroGrande.png";
	private String path = null;
	private Document informe;	
	private String encabezado=null;
	
	/*Datos*/
	
	private Vector<String> vectorOrderBy = new Vector<String>();
	private Vector<String> vectorColumnas = new Vector<String>();
	private Vector<String> vectorRegistros = new Vector<String>(); 
	
	public Informe1(String ruta){
			path = ruta;
	}
	
	public void crearPDF() throws FileNotFoundException, DocumentException, MalformedURLException, IOException{
		
		Font font = new Font(Font.FontFamily.COURIER , 24,Font.BOLD);
		Font fontFecha = new Font(Font.FontFamily.COURIER, 12, Font.NORMAL);
		
		informe = new Document(PageSize.A4,50,50,50,50);
		PdfWriter.getInstance(informe, new FileOutputStream(path));
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
		
		/*Ingresando datos para tabla*/
		vectorOrderBy.add("Administrador");
		vectorOrderBy.add("Catalogador");
		vectorOrderBy.add("Usuario normal");
		
		vectorColumnas.add("login");
		vectorColumnas.add("nombre1");
		vectorColumnas.add("apellido1");
		vectorColumnas.add("vinculo_univalle");
		
		vectorRegistros.add("admin|biblioteca|eisc|Estudiante de pregrado");
		vectorRegistros.add("");
		vectorRegistros.add("maria|maria|cruz|Estudiante de pregrado");
		vectorRegistros.add("felipex|luis|vargas|Estudiante de pregrado");
		vectorRegistros.add("clrl|cristian|rios|Estudiante de pregrado");
		vectorRegistros.add("");
		vectorRegistros.add("monki|edgar|moncada|Estudiante de pregrado");
		vectorRegistros.add("alberto|alberto|gonzales|Estudiante de pregrado");
		vectorRegistros.add("carlos|carlos|valderrama|Estudiante de pregrado");
		vectorRegistros.add("camilo|camilo|suarez|Egresado");
		vectorRegistros.add("laura|laura|rodrigues|Egresado");
		vectorRegistros.add("marcela|marcela|lozano|Profesor activo");
		
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

	public void generar(String tituloReporte){
		try{
			this.encabezado= tituloReporte;
			crearPDF();
			escribirTabla();
			javax.swing.JOptionPane.showMessageDialog(null,"Reporte Generado con Exito","OK",javax.swing.JOptionPane.INFORMATION_MESSAGE);

		}catch(DocumentException e){
			System.out.print("Error de Documento");
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.print("Error archivo no encontrado");
		}catch(IOException e){
			System.err.println("Error en Flujos");
		}
	}

	/*Clase para colocar el numero de paginas del reporte*/
	
	private class Paginas extends PdfPageEventHelper
	{
		  PdfTemplate total;
		  
		  public void onOpenDocument(PdfWriter writer, Document document) {
			  writer.getDirectContent().createTemplate(50,12);
		  }
	}
}
