import javax.swing.*;
import javax.swing.filechooser.*;
import java.io.*;

public class GenerarReporte extends JFrame{

	public GenerarReporte(String columna, String criterio){
		JFileChooser guardador= new JFileChooser();
		guardador.setDialogType(JFileChooser.SAVE_DIALOG);
		guardador.setDragEnabled(true);
		guardador.setAcceptAllFileFilterUsed(false);
		FileNameExtensionFilter filtroPdf = new FileNameExtensionFilter("*.pdf", "pdf");
		guardador.setFileFilter(filtroPdf);
		int opcion = guardador.showSaveDialog(null);
		
		if(opcion == JFileChooser.APPROVE_OPTION){
			File f = guardador.getSelectedFile();
			String ruta = f.getPath();
			String rutaFinal = ruta+".pdf";
			Informe1 informe = new Informe1(rutaFinal);
			informe.generar(columna,criterio);

		}
		if(opcion == JFileChooser.CANCEL_OPTION){
			JOptionPane.showMessageDialog(null,"No se he generado ningun Roporte","Cancelado",JOptionPane.INFORMATION_MESSAGE);
		}
		
	}
	
	public static void main(String args[]){
		
		new GenerarReporte("","");
		
	}

}
