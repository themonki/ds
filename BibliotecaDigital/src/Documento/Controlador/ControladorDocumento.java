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

import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Documento.Dao.DaoDocumento;
import Documento.Logica.Documento;
import GestionDocumento.Logica.AreaConocimiento;
import GestionDocumento.Logica.Autor;
import GestionDocumento.Logica.PalabraClave;

public class ControladorDocumento {

	public int insertarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login) {		
		
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
		value = insertarDocumento(d);
		// por seguridad
		d = null;
		return value;
	}

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
		
		if(d.getTitulo_secundario().equals("")){
			d.setTitulo_secundario("sin titulo secundario");
		}
		DaoDocumento daoDoc = new DaoDocumento();
		int value = daoDoc.guardarDocumento(d);

		System.out.println("Se inserto el documento");
		daoDoc = null;
		return value;
	}

	public int modificarDocumento(String id, String idioma, String derechos,
			String descripcion, String software, String resolucion,
			String editorial, String formato, String titulo_principal,
			String titulo_secundario, String link, String creacion,
			String publicacion, String catalogacion, String login) {		
				
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

		int value = modificarDocumento(d);
		// por seguridad
		d = null;
		return value;
	}
	// /////////
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
		
		DaoDocumento daoDoc = new DaoDocumento();
		int value = daoDoc.modificarDocumento(d);
		System.out.println("Se modifico el documento");
		daoDoc = null;
		return value;
	}

//**********INSERTAR LAS AREAS, AUTORES Y PALABRASCLAVE*************************************************
//INSERTANDO AREAS CONOCIMIENTO
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
	public int insertarDocumentoAreas(Vector<String> area_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		if(area_ids.size()<1){return -1;}//sino ahi nada no se inserta
		value = daoDoc.guardarDocumentoAreas(id_doc, area_ids);		
		return value;
	}
//INSERTANDO PALABRAS CLAVE	
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
	
	public int insertarDocumentoPalabrasClave(Vector<String> palabras_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		if(palabras_ids.size()<1){return -1;}//sino ahi nada no se inserta
		value = daoDoc.guardarDocumentoPalabrasClave(id_doc, palabras_ids);
		return value;
	}
//INSERTANDO AUTORES	
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
	
	public int insertarDocumentoAutores(Vector<String> autores_ids, String id_doc){
		DaoDocumento daoDoc = new DaoDocumento();
		int value;
		if(autores_ids.size()<1){return -1;}//sino ahi nada no se inserta
		value = daoDoc.guardarDocumentoAutores(id_doc, autores_ids);
		return value;
	}
//*****************************************************************************************************	
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

//el path relativo del archivo,si el archivo ya existe en el repositorio (mismo nombre) no se vuelve 
//a copiar devolviendo la misma direccion
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
	public boolean comprobarEnlace(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		return daoDoc.comprobarURL(d.getUrl());
	}
//metodo que retorna el documento con la llave id_documento, sin areas, autor y palabras	
	public Documento obtenerDocumento(String id_documento){
		DaoDocumento daoDoc = new DaoDocumento();
		Documento d = daoDoc.consultarDatosDocumento(id_documento);
		return d;
	}
	
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
	
	public int modificarDocumentoAreas(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector<String> ids_areas = new Vector<String>();
		Vector<AreaConocimiento> vac  =d.getAreas();
		for(int i = 0; i< vac.size();i++){
			ids_areas.add(vac.get(i).getIdArea());
		}
		return daoDoc.actualizarDocumentoAreas(d.getId_doc(), ids_areas);
	}
	public int modificarDocumentoPalabrasClave(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector<String> ids_palabras = new Vector<String>();
		Vector<PalabraClave> vpc  =d.getPalabrasClave();
		for(int i = 0; i< vpc.size();i++){
			ids_palabras.add(vpc.get(i).getNombre());
		}
		return daoDoc.actualizarDocumentoPalabrasClave(d.getId_doc(), ids_palabras);
	}
	public int modificarDocumentoAutores(Documento d){
		DaoDocumento daoDoc = new DaoDocumento();
		Vector<String> ids_autores = new Vector<String>();
		Vector<Autor> va  =d.getAutores();
		for(int i = 0; i< va.size();i++){
			ids_autores.add(va.get(i).getId());
		}
		return daoDoc.actualizarDocumentoAutores(d.getId_doc(), ids_autores);
	}
	
	
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
