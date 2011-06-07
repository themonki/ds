/*
 * ControladorDocumento.java
 * 
 * Clase que permite la comunicación entre la aplicación y la base de datos
 * en el envió de información que este relacionada con los documentos digi-
 * tales de la biblioteca digital involucrando todas las clases en el paqu-
 * ete y subpaquetes de Documento.
 * 
 *
 * JAVA version "1.6.0"
 * 
 * 
 * Autor:     Edgar Andres Moncada
 * Version:   4.0
 */
package Documento.Controlador;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Date;
import java.util.Vector;
import javax.swing.JOptionPane;

import Documento.Dao.DaoDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;

/**
 * Clase que permite la comunicación entre la Clase {@link Documento.Dao.DaoDocumento DaoDocumento} y las Clases
 * de aplicación: {@link Documento.Gui.GuiCatalogar GuiCatalogar}, 
 * y {@link Documento.Gui.GuiModificarDoc GuiModificarDoc} 
 * para el intercambio de datos.
 * @author Edgar Andres Moncada
 *
 */
public class ControladorDocumento {

	/**
	 * Metodo que recibe los datos parametro por parametro para catalogar un documento 
	 * y lo envia al metodo del dao respectivo
	 * @param id - String con la llave del documento
	 * @param idioma - String con el idioma del documento
	 * @param derechos - String con los derechos de autor del documento
	 * @param descripcion - String con la descripcion del documento
	 * @param software - String con el software recomendado 
	 * @param resolucion - String con la resolucion del documento
	 * @param editorial - String con la editoral del documento
	 * @param formato - String con el formato del documento
	 * @param titulo_principal - String con el titulo principal del documento
	 * @param titulo_secundario - String con el titulo secundario o traducido del documento
	 * @param link - String con el enlace del documento
	 * @param creacion - String con la fecha de creacion del documento
	 * @param publicacion - String con la fecha de publicacion del documento
	 * @param catalogacion - String con la fecha de catalogacion del documento
	 * @param login - String con la llave del usuario catalogador del documento
	 * @param tipo - String con el tipo de material del documento
	 * @return 1 si se catalogo correctamente el documento, -1 de ser lo contrario
	 * @author Edgar Andres Moncada Taborda
	 */
	public int insertarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login, String tipo) {		
		
		//se supone que login ya debe llegar con lowercase, y no hace falta en id, creacion, publicacion ni
		//catalogacion, y no se puede en link.
		
		Documento d = new Documento();
		int value;
		d.setId_doc(id);
		d.setIdioma(idioma);
		d.setDerechosDeAutor(derechos);
		d.setDescripcion(descripcion);
		d.setSoftware_recomentado(software);
		d.setResolucion(resolucion);
		d.setEditorial(editorial);
		d.setFormato(formato);
		d.setTituloppal(titulo_principal);
		d.setTitulo_secundario(titulo_secundario);
		d.setUrl(link);
		Date f_creacion = Date.valueOf(creacion);// formato yyyy-mm-dd
		d.setFecha_creacion(f_creacion);
		Date f_publicacion = Date.valueOf(publicacion);// formato yyyy-mm-dd
		d.setFecha_publicacion(f_publicacion);
		Date F_catalogacion = Date.valueOf(catalogacion);// formato yyyy-mm-dd
		d.setFechaDeCatalogacion(F_catalogacion);
		d.setCatalogadorLogin(login);
		d.setTipoMaterial(tipo);
		value = insertarDocumento(d);
		// por seguridad
		d = null;
		return value;
	}

	/**
	 * Metodo que recibe los datos del Documento para catalogar un documento 
	 * y lo envia al metodo del dao respectivo
	 * @param d - documento con los datos a catalogar
	 * @return 1 si se catalogo correctamente el documento, -1 de ser lo contrario
	 * @author Edgar Andres Moncada Taborda
	 */
	public int insertarDocumento(Documento d) {		
		//d.setIdioma(d.getIdioma().toLowerCase());
		//d.setDerechosDeAutor(d.getDerechosDeAutor().toLowerCase());
		d.setDescripcion(d.getDescripcion().toLowerCase());
		d.setSoftware_recomentado(d.getSoftware_recomentado().toLowerCase());
		d.setResolucion(d.getResolucion().toLowerCase());
		d.setEditorial(d.getEditorial().toLowerCase());
		//d.setFormato(d.getFormato().toLowerCase());
		d.setTituloppal(d.getTituloppal().toLowerCase());
		d.setTitulo_secundario(d.getTitulo_secundario().toLowerCase());
		d.setTipoMaterial(d.getTipoMaterial().toLowerCase());
		
		if(d.getResolucion().equals("")){
			d.setResolucion("sin resolucion");
		}
		if(d.getSoftware_recomentado().equals("")){
			d.setSoftware_recomentado("ninguno");
		}
		DaoDocumento daoDoc = new DaoDocumento();
		int value = daoDoc.guardarDocumento(d);

		System.out.println("Se inserto el documento");
		daoDoc = null;
		return value;
	}

	/**
	 * Metodo que recibe los datos parametro por parametro para modificar un documento 
	 * y lo envia al metodo del dao respectivo
	 * @param id - String con la llave del documento
	 * @param idioma - String con el idioma del documento
	 * @param derechos - String con los derechos de autor del documento
	 * @param descripcion - String con la descripcion del documento
	 * @param software - String con el software recomendado 
	 * @param resolucion - String con la resolucion del documento
	 * @param editorial - String con la editoral del documento
	 * @param formato - String con el formato del documento
	 * @param titulo_principal - String con el titulo principal del documento
	 * @param titulo_secundario - String con el titulo secundario o traducido del documento
	 * @param link - String con el enlace del documento
	 * @param creacion - String con la fecha de creacion del documento
	 * @param publicacion - String con la fecha de publicacion del documento
	 * @param catalogacion - String con la fecha de catalogacion del documento
	 * @param login - String con la llave del usuario catalogador del documento
	 * @param tipo - String con el tipo de material del documento
	 * @return 1 si se modifico correctamente el documento, -1 de ser lo contrario
	 * @author Edgar Andres Moncada Taborda
	 */
	public int modificarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login, String tipo) {		
				
		Documento d = new Documento();		
		d.setId_doc(id);
		d.setIdioma(idioma);
		d.setDerechosDeAutor(derechos);
		d.setDescripcion(descripcion);
		d.setSoftware_recomentado(software);
		d.setResolucion(resolucion);
		d.setEditorial(editorial);
		d.setFormato(formato);
		d.setTituloppal(titulo_principal);
		d.setTitulo_secundario(titulo_secundario);
		d.setUrl(link);
		Date f_creacion = Date.valueOf(creacion);// formato yyyy-mm-dd
		d.setFecha_creacion(f_creacion);
		Date f_publicacion = Date.valueOf(publicacion);// formato yyyy-mm-dd
		d.setFecha_publicacion(f_publicacion);
		Date F_catalogacion = Date.valueOf(catalogacion);// formato yyyy-mm-dd
		d.setFechaDeCatalogacion(F_catalogacion);
		d.setCatalogadorLogin(login);
		d.setTipoMaterial(tipo);
		int value = modificarDocumento(d);
		d = null;
		return value;
	}
	/**Metodo que recibe los datos del documento para modificar un documento 
	 * y lo envia al metodo del dao respectivo
	 * @param d - Documento con los datos a modificar
	 * @return 1 si se modifico correctamente el documento, -1 de ser lo contrario
	 * @author Edgar Andres Moncada Taborda
	 */
	public int modificarDocumento(Documento d) {
		//se pasan a minuscula
		//d.setIdioma(d.getIdioma().toLowerCase());
		//d.setDerechosDeAutor(d.getDerechosDeAutor().toLowerCase());
		d.setDescripcion(d.getDescripcion().toLowerCase());
		d.setSoftware_recomentado(d.getSoftware_recomentado().toLowerCase());
		d.setResolucion(d.getResolucion().toLowerCase());
		d.setEditorial(d.getEditorial().toLowerCase());
		//d.setFormato(d.getFormato().toLowerCase());
		d.setTituloppal(d.getTituloppal().toLowerCase());
		d.setTitulo_secundario(d.getTitulo_secundario().toLowerCase());
		d.setTipoMaterial(d.getTipoMaterial().toLowerCase());
		if(d.getResolucion().equals("")){
			d.setResolucion("sin resolucion");
		}
		if(d.getSoftware_recomentado().equals("")){
			d.setSoftware_recomentado("ninguno");
		}
		DaoDocumento daoDoc = new DaoDocumento();
		int value = daoDoc.modificarDocumento(d);
		System.out.println("Se modifico el documento");
		daoDoc = null;
		return value;
	}

//**********INSERTAR LAS AREAS, AUTORES Y PALABRASCLAVE*************************************************
//INSERTANDO AREAS CONOCIMIENTO
	/**
	 * Metodo que inserta los datos para almacenar las areas de conocimiento a las que
	 * pertenece el documento y llama al metodo del dao respectivo
	 * @param d - documento la llave del documento y las areas de conocimiento a las que pertenece
	 * @return int con el numero de areas de conocimiento insertadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarDocumentoAreas(Documento d) {
		DaoDocumento daoDoc = new DaoDocumento();
		Vector <AreaConocimiento> vac = d.getAreas();
		Vector <String> area_ids = new Vector<String>();
		int value, cantidad = vac.size();
		if(cantidad<1){return -1;}//sino ahi nada no se inserta
		for(int i =0; i < cantidad; i++ ){
			
			area_ids.add(vac.get(i).getIdArea());
		}
		value = daoDoc.guardarDocumentoAreas(d.getId_doc(), area_ids);
		return value;
	}
	/**
	 * Metodo que inserta los datos del documento, recibiendo el vector con los identificadores
	 * de las  y la llave del documento y llama al metodo dao respectivo
	 * @param area_ids - String con las areas de conocimiento del documento
	 * @param id_doc - String con la llave del documento
	 * @return el numero de areas de conocimiento insertadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarDocumentoAreas(Vector<String> area_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		if(area_ids.size()<1){return -1;}//sino ahi nada no se inserta
		value = daoDoc.guardarDocumentoAreas(id_doc, area_ids);		
		return value;
	}
//INSERTANDO PALABRAS CLAVE	
	/**
	 * Metodo que inserta los datos para almacenar las palabras clave a las que
	 * pertenece el documento y llama al metodo del dao respectivo
	 * @param d - documento la llave del documento y las palabras clave a las que pertenece
	 * @return int con el numero de palabras clave insertadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarDocumentoPalabrasClave(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector <PalabraClave> vac = d.getPalabrasClave();
		Vector <String> palabras_ids = new Vector<String>();
		int value, cantidad = vac.size();
		if(cantidad<1){return -1;}//sino ahi nada no se inserta
		for(int i =0; i < cantidad; i++ ){
			
			palabras_ids.add(vac.get(i).getNombre());
		}
		value = daoDoc.guardarDocumentoPalabrasClave(d.getId_doc(), palabras_ids);
		return value;
	}
	
	/**
	 * Metodo que inserta las palabras clave de un documento, recibiendo el vector con los identificadores
	 * de las palabras y la llave del documento y llama al metodo dao respectivo
	 * @param palabras_ids - String con las palabras clave del documento
	 * @param id_doc - String con la llave del documento
	 * @return el numero de palabras clave insertadas correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarDocumentoPalabrasClave(Vector<String> palabras_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		if(palabras_ids.size()<1){return -1;}//sino ahi nada no se inserta
		value = daoDoc.guardarDocumentoPalabrasClave(id_doc, palabras_ids);
		return value;
	}
//INSERTANDO AUTORES	
	/**
	 * Metodo que inserta los datos para almacenar los autores  del documento
	 *  y llama al metodo del dao respectivo
	 * @param d - documento la llave del documento y los autores
	 * @return int con el numero de autores insertados correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarDocumentoAutores(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector <Autor> vac = d.getAutores();
		Vector <String> autores_ids = new Vector<String>();
		int value, cantidad = vac.size();
		if(cantidad<1){return -1;}//sino ahi nada no se inserta
		for(int i =0; i < cantidad; i++ ){
			
			autores_ids.add(vac.get(i).getId());
		}
		value = daoDoc.guardarDocumentoAutores(d.getId_doc(), autores_ids);
		return value;
	}
	
	/**
	 * Metodo que inserta los autores de un documento, recibiendo el vector con los identificadores
	 * del autor y la llave del documento y llama al metodo dao respectivo
	 * @param autores_ids - String con los autores del documento
	 * @param id_doc - String con la llave del documento
	 * @return el numero de autores insertados correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int insertarDocumentoAutores(Vector<String> autores_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		if(autores_ids.size()<1){return -1;}//sino ahi nada no se inserta
		value = daoDoc.guardarDocumentoAutores(id_doc, autores_ids);
		return value;
	}
//*****************************************************************************************************	
	/**
	 * Metodo que recibe un documento con todos los datos necesarios para catalogar
	 * @param d - Documento con todos los datos
	 * @return 1 si se catalogo el documento correctamente, -1 de ser lo contrario
	 */
	public int catalogarDocumento(Documento d){
		if(verificarInsertarDocumento(d)){
			int value=this.insertarDocumento(d);
			if(value<1){return -1;};
			DaoDocumento daoDoc = new DaoDocumento();
			String id_doc = daoDoc.obtenerLoginDocumento();
			d.setId_doc(id_doc);
			this.insertarDocumentoAreas(d);
			this.insertarDocumentoPalabrasClave(d);
			this.insertarDocumentoAutores(d);
			return 1;			
		}else return -1;
	}
	
	/**
	 * Metodo que recibe un documento con los datos y los identificadores de las areas de conocimiento, 
	 * autores y palabras clave y llama al metodo dao respectivo para que lo catalogue
	 * @param d - Documento con los datos basicos de la tabla documento
	 * @param areas_ids - Vector<String> con las llaves de las areas de conocimeinto
	 * @param autores_ids - Vector<String> con las llaves de los autores
	 * @param palabras_ids - Vector<String> con las llaves de las palabras claves
	 * @return el numero de autores insertados correctamente, 
	 * -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int catalogarDocumento(Documento d, Vector<String> areas_ids,
			Vector<String> autores_ids, Vector<String> palabras_ids ){
		Vector <AreaConocimiento> vac= new Vector <AreaConocimiento>();
		Vector <Autor> va = new Vector <Autor>();
		Vector <PalabraClave> vpc =new Vector <PalabraClave>();
		for(int i = 0; i < areas_ids.size(); i++){
			AreaConocimiento ac = new AreaConocimiento();
			ac.setIdArea(areas_ids.get(i));
			vac.add(ac);
		}
		for(int i = 0; i < autores_ids.size(); i++){
			Autor a = new Autor();
			a.setId(autores_ids.get(i));
			va.add(a);
		}
		for(int i = 0; i < palabras_ids.size(); i++){
			PalabraClave pc = new PalabraClave();
			pc.setNombre(palabras_ids.get(i));
			vpc.add(pc);
		}
		d.setAutores(va);
		d.setAreas(vac);
		d.setPalabrasClave(vpc);
		
		return this.catalogarDocumento(d);		
		/*DaoDocumento daoDoc = new DaoDocumento();		
		int value = this.insertarDocumento(d);
		if(value<1){return -1;}
		String id_doc = daoDoc.obtenerLoginDocumento();//se obtiene el logindel documento q se acbo de catalogar
		this.insertarDocumentoAreas(areas_ids, id_doc);
		this.insertarDocumentoPalabrasClave(palabras_ids, id_doc);
		this.insertarDocumentoAutores(autores_ids, id_doc);
		return 1;/**/
		
	}
	/**
	 * Metodo para almacenar en el repositorio los documentos catalogados,
	 * si el nombre del archivo existe no lo sobreescribe y retorna el path relativo
	 * del archivo
	 * @param url - String con el path absoluto dell documento a catalogar y almacenar en el repositorio
	 * @return String con la direccion relativa en el repositorio
	 * @author Edgar Andres Moncada
	 */
	public String copiarDocumento(String url){
		File src = new File(url), carp_dest=  new File("repositorio/");
		File dst= new File(carp_dest.getName()+"/"+src.getName());
		if(dst.exists()) {return dst.getPath();} // si ya existe en el repositorio no se puede catalogar
		InputStream in;
		OutputStream out;
		carp_dest.mkdir();
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dst);
			
			 byte[] buf = new byte[1024]; 
		        int len; 
		        while ((len = in.read(buf)) > 0) { 
		            out.write(buf, 0, len); 
		        } 
		        in.close(); 
		        out.close();
		        return dst.getPath();
		
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return "";
	}
	
	/**
	 * Metodo usado para verificar los datos al catalogar el documento
	 * @param d - Documento con los datos a verificar
	 * @return boolean, true si los datos son correctos, false de ser lo contrario y  muestra un mensaje
	 * con el error
	 * @author Edgar Andres Moncada
	 */
	public boolean verificarInsertarDocumento(Documento d){
		boolean estado =true;
		String mensaje="";
		if(d.getTituloppal().equals("")){
			mensaje+="*Debe proporcionar un Titulo Principal al documento\n";
			estado=false;
		}
		if(d.getDescripcion().equals("")){
			mensaje+="*Debe proporcionar una descripcion o resumen del Documento\n";
			estado=false;
		}		
		if(d.getAreas().size()==0){
			mensaje+="*Debe proporcionar por lo menos un Area de Conocimiento\n";
			estado=false;
		}
		if(d.getAutores().size()==0){
			mensaje+="*Debe proporcionar por lo menos un Autor\n";
			estado=false;
		}
		if(!comprobarFormato(d.getFormato(), d.getUrl())){
			mensaje+="*El formato "+d.getFormato()+" no coincide con la extencion del archivo\n";
			estado=false;
		}
		//lo ultimo en comprobar
		if (d.getUrl().equals("")){
			mensaje+="*Debe proporcionar una ruta para guardar el Documento\n";
			estado=false;
		}else{
			if(estado){//si todo lo demas esta bien entonces puede copiar al repositorio 
				d.setUrl(this.copiarDocumento(d.getUrl()));//copia el link al repositorio sino existe ya
				if(this.comprobarEnlace(d)){//se compureba en la bd si existe el link del documento
					mensaje+="*Parece que este documento ya fue catalogado, por favor compruebelo\n" +
							"relalizando una consulta o cambie el nombre del documento a catalogar";
					estado=false;
				}
			}
		}
		if(!estado){JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);}	
		return estado;
	}	
	/**
	 * Metodo que comprueba si un enlace ya existe en la base de datos llamando al metodo
	 * dao correspondiente
	 * @param d - Documento con el enlace a buscar
	 * @return bolean, true si el enlace ya esta, false de lo contrario
	 * @author Edgar Andres Moncada
	 */
	public boolean comprobarEnlace(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		return daoDoc.comprobarURL(d.getUrl());
	}	
	/**
	 * Metodo que retorna el documento con la llave id_documento, sin areas, autor y palabras
	 * @param id_documento
	 * @return Documento con los datos del documento que coincidan con la llave id_documento
	 * @author Edgar Andres Moncada
	 */
	public Documento obtenerDocumento(String id_documento){
		DaoDocumento daoDoc = new DaoDocumento();
		Documento d = daoDoc.consultarDatosDocumento(id_documento);
		return d;
	}
	
	/**
	 * Metodo para la descarga de uno de los documentos catalogados, si existe cambia el nombre
	 * del documento
	 * @param urlFuente - String con el enlace en el repositorio
	 * @param urlDestino - String con el enlace carpeta destino
	 * @return String con el path y el nombre del documento a descargar
	 * @author Edgar Andres Moncada
	 */
	public String descargarDocumento(String urlFuente, String urlDestino){
		File src = new File(urlFuente);
		File dst= new File(urlDestino+"/"+src.getName());//es una carpeta
		File nuevoDst = new File(urlDestino+"/"+src.getName());
		int contador = 2;
		while(true){
			if(nuevoDst!=null && nuevoDst.exists()){
				String nuevoNombre = obtenerNombre(dst,"("+contador+")");
				nuevoDst = new File(urlDestino+"/"+nuevoNombre);
				contador++;
			}else {
				dst=null;
				dst=new File(nuevoDst.getPath());;
				nuevoDst=null;
				break;
			}		
		}
		InputStream in;
		OutputStream out;		
		try {
			in = new FileInputStream(src);
			out = new FileOutputStream(dst);
			
			 byte[] buf = new byte[1024]; 
		        int len; 
		        while ((len = in.read(buf)) > 0) { 
		            out.write(buf, 0, len); 
		        } 
		        in.close(); 
		        out.close();
		        return dst.getAbsolutePath();
		
		} catch (FileNotFoundException e) {
			System.out.println(e.toString());
		} catch (IOException e) {
			System.out.println(e.toString());
		}
		return "";
	}
	
	/**
	 * Metodo para cambiar el nombre de un archivo, debe de tener extencion
	 * @param archivo - String con el nombre del documento a descargar
	 * @param agregar - String con el paramatero a adicionar al documento para cambiarle el nombre
	 * @return String el nombre del nuevo documento
	 * @author Edgar Andres Moncada
	 */
	protected String obtenerNombre(File archivo, String agregar){
		String name = archivo.getName();
		int longitud = name.length();
		for(int i = longitud-1; i > 0; i--){
			if(name.charAt(i)=='.'){
				String nombre2 = name.substring(0, i);
				nombre2+= agregar + name.substring(i);
				return nombre2;
			}
		}
		System.out.println("No encuentra la extension");
		return null;
	}
	
//*********************************************MODIFICAR
	
	/**
	 * Metodo para la modificacion de las areas de conocimiento de un documento, llamando
	 * al metodo dao correspondiente
	 * @param d - Documento con las areas de conocimiento a modificar
	 * @return int con el numero de areas de conocimiento modificadas
	 * @author Edgar Andres Moncada
	 */
	public int modificarDocumentoAreas(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector<String> ids_areas = new Vector<String>();
		Vector<AreaConocimiento> vac  =d.getAreas();
		for(int i = 0; i< vac.size();i++){
			ids_areas.add(vac.get(i).getIdArea());
		}
		return daoDoc.actualizarDocumentoAreas(d.getId_doc(), ids_areas);
	}
	/**
	 * Metodo para la modificacion de las palabras clave de un documento, llamando
	 * al metodo dao correspondiente
	 * @param d - Documento con las palabras clave a modificar
	 * @return int con el numero de palabras clave modificadas
	 * @author Edgar Andres Moncada
	 */
	public int modificarDocumentoPalabrasClave(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector<String> ids_palabras = new Vector<String>();
		Vector<PalabraClave> vpc  =d.getPalabrasClave();
		for(int i = 0; i< vpc.size();i++){
			ids_palabras.add(vpc.get(i).getNombre());
		}
		return daoDoc.actualizarDocumentoPalabrasClave(d.getId_doc(), ids_palabras);
	}
	/**
	 * Metodo para la modificacion de los autores de un documento, llamando
	 * al metodo dao correspondiente
	 * @param d - Documento con los autores a modificar
	 * @return int con el numero los autores modificados
	 * @author Edgar Andres Moncada
	 */
	public int modificarDocumentoAutores(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector<String> ids_autores = new Vector<String>();
		Vector<Autor> va  =d.getAutores();
		for(int i = 0; i< va.size();i++){
			ids_autores.add(va.get(i).getId());
		}
		return daoDoc.actualizarDocumentoAutores(d.getId_doc(), ids_autores);
	}
	
	
	/**Metodo que recibe el los datos del documento a modificar y llama al metodo dao respectivo
	 * para almacenarlo
	 * @param d - Documento con todos los datos y las areas de conocimiento, autores y palabras clave
	 * @return 1 si se modifico sactisfactoriamente el documento, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int modificarDatosDocumento(Documento d){
		if(verificarModificarDocumento(d)){
			int value=this.modificarDocumento(d);
			if(value<1){return -1;};
			this.modificarDocumentoAreas(d);
			this.modificarDocumentoPalabrasClave(d);
			this.modificarDocumentoAutores(d);
			return 1;			
		}else return -1;
	}
	/**
	 * Metodo que obtiene los datos del documento y los identificadores de las areas de conocimiento,
	 *  las palabras clave y los autores  y los inserta llamando al metodo dao respectivo
	 * @param d - Documento con los datos a modificar
	 * @param areas_ids - Vector<String> con las areas de conocimiento a modificar
	 * @param autores_ids - Vector<String> con los auotres a modificar
	 * @param palabras_ids - Vector<String> con las palabras clave a modificar
	 * @return 1 si se modifico sactisfactoriamente el documento, -1 de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public int modificarDatosDocumento(Documento d, Vector<String> areas_ids,
			Vector<String> autores_ids, Vector<String> palabras_ids ){
		
		Vector <AreaConocimiento> vac= new Vector <AreaConocimiento>();
		Vector <Autor> va = new Vector <Autor>();
		Vector <PalabraClave> vpc =new Vector <PalabraClave>();
		for(int i = 0; i < areas_ids.size(); i++){
			AreaConocimiento ac = new AreaConocimiento();
			ac.setIdArea(areas_ids.get(i));
			vac.add(ac);
		}
		for(int i = 0; i < autores_ids.size(); i++){
			Autor a = new Autor();
			a.setId(autores_ids.get(i));
			va.add(a);
		}
		for(int i = 0; i < palabras_ids.size(); i++){
			PalabraClave pc = new PalabraClave();
			pc.setNombre(palabras_ids.get(i));
			vpc.add(pc);
		}
		d.setAutores(va);
		d.setAreas(vac);
		d.setPalabrasClave(vpc);
		
		return this.modificarDatosDocumento(d);
	}
	
	/**
	 * Metodo para verificar los datos a modificar del documento
	 * @param d - Documento con los datos a validar
	 * @return boolean, true si los datos estan correctamente, de lo contrario falso y muestra
	 * un mensaje con el error
	 * @author Edgar Andres Moncada
	 */
	public boolean verificarModificarDocumento(Documento d){
		boolean estado =true;
		String mensaje="";
		if(d.getTituloppal().equals("")){
			mensaje+="*Debe proporcionar un Titulo Principal al documento\n";
			estado=false;
		}
		if(d.getDescripcion().equals("")){
			mensaje+="*Debe proporcionar una descripcion o resumen del Documento\n";
			estado=false;
		}		
		if(d.getAreas().size()==0){
			mensaje+="*Debe proporcionar por lo menos un Area de Conocimiento\n";
			estado=false;
		}
		if(d.getAutores().size()==0){
			mensaje+="*Debe proporcionar por lo menos un Autor\n";
			estado=false;
		}

		if(!estado){JOptionPane.showMessageDialog(null, mensaje, "Error", JOptionPane.ERROR_MESSAGE);}	
		return estado;
	}
	
	/**
	 * Metodo para que el formato del archivo corresponda con el formato dado
	 * @param formato - String con el formato del documento
	 * @param nombre - String con el nombre del documento
	 * @return boolean, true si el formato y la extencion coinciden, false de ser lo contrario
	 * @author Edgar Andres Moncada
	 */
	public boolean comprobarFormato(String formato, String nombre){
		String name="";
		int longitud = nombre.length();
		for(int i = longitud-1; i > 0; i--){
			if(nombre.charAt(i)=='.'){;
				name = nombre.substring(i+1);
				break;
			}
		}
		name.toLowerCase();
		if(formato.equals("otro")){
			if(!name.equals("doc")&&!name.equals("docx")&&!name.equals("jpg")
					&&!name.equals("jepg")&&!name.equals("odt")&&!name.equals("pdf")){
				return true;
			}
		}
		
		if(formato.equals("jpg")){
			if(name.equals(formato)||name.equals("jpeg")){
				return true;
			}
		}
		if(formato.equals("doc")){
			if(name.equals(formato)||name.equals("docx")){
				return true;
			}
		}
		if(name.equals(formato)){
			return true;
		}
		
		return false;
	}

}
