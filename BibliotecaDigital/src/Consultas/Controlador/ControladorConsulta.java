package Consultas.Controlador;

import Documento.Controlador.ControladorDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Controlador.ControladorAreaConocimiento;
import GestionDocumento.Controlador.ControladorAutor;
import GestionDocumento.Controlador.ControladorPalabraClave;

public class ControladorConsulta {
//metodo que llama a otros controladores para obtener todos los datos de un documento dado su llave
	public Documento obtenerDatosDocumento(String id_documento){
		ControladorDocumento conDoc = new ControladorDocumento();
		ControladorAreaConocimiento conArea= new ControladorAreaConocimiento();
		ControladorAutor conAutor = new ControladorAutor();
		ControladorPalabraClave conPalabra = new ControladorPalabraClave();
		Documento d = conDoc.obtenerDocumento(id_documento);
		d.setAreas(conArea.obtenerAreasDocumento(id_documento));
		d.setAutores(conAutor.obtenerAutoresDocumento(id_documento));
		d.setPalabrasClave(conPalabra.obtenerPalabrasClaveDocumento(id_documento));
		
		return d;
	}
}
