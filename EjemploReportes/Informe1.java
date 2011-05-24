import java.io.*;

import java.sql.*;
import java.net.*;
import java.util.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import com.itextpdf.text.html.*;

public class Informe1{
	
	private String imagen = "logo.jpg";
	private Connection db;
	private boolean connected;
	private Vector resultados;
	private String path = "";
	private Document informe;
	private final String[] columnas = {"NombreUsuario","PartidasJugadas","PartidasPerdidas","Puntuacion"};
	String encabezado="Battleship \n Reporte Definido por el usuario";
	private Vector imagenBD;
	private int tamanoRS = 0;
	
	public Informe1(String ruta){
		
		//try{			
			//Class.forName("com.mysql.jdbc.Driver");
			path = ruta;
		/*}
		catch (ClassNotFoundException cnfe){
			System.out.print("Problema");
		}*/
		resultados = new Vector();
		imagenBD = new Vector();
	}
	
	public void crearPDF() throws FileNotFoundException, DocumentException, MalformedURLException, IOException{
		Font font = new Font(Font.getFamily("HELVETICA") , 25,Font.ITALIC);
		
		informe = new Document();
		PdfWriter.getInstance(informe, new FileOutputStream(path));
		informe.open();
		Paragraph enc = new Paragraph("\n \n");
		
		informe.addTitle("Informe General");
		Image logo = Image.getInstance(imagen); 
		logo.scalePercent(30);
		Chunk imgLogo = new Chunk(logo,0,-15);
		enc.add(imgLogo);
		enc.add(new Phrase(encabezado,font));
		enc.add(new Phrase("\n \n \n \n"));
		informe.add(enc);
	}
	
	/*
	
	public void conectarABD() throws SQLException{
		db = DriverManager.getConnection("jdbc:mysql://mysql:3306/carocc", "carocc", "carocc");
		connected = true;
	}
	
	public void desconectarBD() throws SQLException{
		db.close();
		connected = false;
	}
	
	public void buscarEnTablas(String columna, String criterio) throws SQLException{
		
		String valido = null;
		String consulta = "";
		Statement stmt = db.createStatement();
		if(criterio == null && columna.equals("TOP10")){
			consulta= "SELECT * FROM Warman ORDER BY Puntuacion DESC LIMIT 10; ";
		}
		else if(criterio == null && columna.equals("TOP-10")){
			consulta = "SELECT * FROM Warman ORDER BY Puntuacion ASC LIMIT 10; ";
		}
		else{
			consulta = "SELECT * FROM Warman WHERE " + 	columna + " " + criterio +"; ";
		}
		ResultSet rs = stmt.executeQuery(consulta);
		
		
		while(rs.next()){
			tamanoRS++;
			for(int cuenta = 0; cuenta<columnas.length; cuenta++){
				valido = rs.getString(columnas[cuenta]);
				resultados.addElement(valido);
			}
			System.out.println(rs.getBlob("Imagen"));
			imagenBD.addElement(rs.getBlob("Imagen"));
		}
		rs.close();
	}*/
	
	public void escribirTabla() throws DocumentException, SQLException, IOException{
		
		PdfPTable table = new PdfPTable(5);
		PdfPTable cabeceras = new PdfPTable(5);
		
		PdfPCell[] celdasCabeceras = {new PdfPCell(new Phrase("Nombre")),
									  new PdfPCell(new Phrase("Partidas Jugadas")),
									  new PdfPCell(new Phrase("Partidas Perdidas")),
									  new PdfPCell(new Phrase("Puntuacion")),
									  new PdfPCell(new Phrase("Imagen"))};
		
		for(int cuenta = 0; cuenta < 5; cuenta++){
			celdasCabeceras[cuenta].setBorderWidthLeft(0f);
			celdasCabeceras[cuenta].setBorderWidthTop(0f);
			celdasCabeceras[cuenta].setBorderWidthRight(0f);
			cabeceras.addCell(celdasCabeceras[cuenta]);
		}
		
		table.setWidthPercentage(100);
		
		int counter = 0;
		for(int i = 0; i<20; i++){
			for(int cuenta = 0; cuenta < columnas.length; cuenta++){
				
				//PdfPCell celda = new PdfPCell(new Phrase((String)resultados.elementAt(counter)));
				PdfPCell celda = new PdfPCell(new Phrase("Hola"));
				if(cuenta == 0){
					celda.setBackgroundColor(BaseColor.ORANGE);
				}
				celda.setBorderWidthTop(2f);
				table.addCell(celda);
				counter++;
			}
			//java.awt.Image rpta = null;
			//Blob imagenTemp = (Blob)imagenBD.elementAt(i);
			
			/*Image imgFinal = null;
			if(imagenTemp == null){
				imgFinal = Image.getInstance("null.gif");
			}else{
				rpta = javax.imageio.ImageIO.read(imagenTemp.getBinaryStream());
				imgFinal = Image.getInstance(rpta, java.awt.Color.WHITE);
			}*/
			//table.addCell(imgFinal);
			table.addCell(new PdfPCell(new Phrase ("Otro Hola")));
			
		}
        table.completeRow();
        cabeceras.setWidthPercentage(100);
        informe.add(cabeceras);
        informe.add(table);
        informe.close();
	}
	/*public void imprimir(){
		for(int cuenta = 0; cuenta<resultados.size(); cuenta++){
			System.out.println((String)resultados.elementAt(cuenta));
		}
	}*/
	
	public void generar(String campo, String criterio){
		try{
			//conectarABD();
			//buscarEnTablas(campo, criterio);
			crearPDF();
			escribirTabla();
			//desconectarBD();
			javax.swing.JOptionPane.showMessageDialog(null,"Reporte Generado con Exito","OK",javax.swing.JOptionPane.INFORMATION_MESSAGE);

		}catch(DocumentException e){
			System.out.print("Error de Documento");
			
		}catch(FileNotFoundException e){
			e.printStackTrace();
			System.out.print("Error archivo no encontrado");
		}catch(SQLException e){
			javax.swing.JOptionPane.showMessageDialog(null, "No se puede Accesar la Base de Datos", "ERROR",  javax.swing.JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}catch(IOException e){
			System.err.println("Error en Flujos");
		}
		//imprimir();
	}

}
