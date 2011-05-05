package GestionDocumento.Controlador;

import java.util.Vector;
import GestionDocumento.Dao.DaoTipoMaterial;
import GestionDocumento.Logica.TipoMaterial;
/*
 * Yerminson Gonzalez
 * */
public class ControladorTipoMaterial {
	
	

	public int insertarTipoMaterial(String nombre,String descripcion)
	{
		TipoMaterial tipoMaterial = new TipoMaterial(nombre,descripcion);
		DaoTipoMaterial daoTipoMaterial = new DaoTipoMaterial();
		int numFilas = daoTipoMaterial.guardarTipoMaterial(tipoMaterial);
		tipoMaterial = null;
		daoTipoMaterial = null;
		return numFilas;
	}
	
	public TipoMaterial obtenerTipoMaterial(String nombre)
	{
		DaoTipoMaterial daoTipoMaterial = new DaoTipoMaterial();
		TipoMaterial tipoMaterial = daoTipoMaterial.consultarTipoMaterial(nombre);
		daoTipoMaterial = null;
		return tipoMaterial;
	}
	
	public Vector<TipoMaterial> obtenerTiposMateriales()
	{
		DaoTipoMaterial daoTioMaterial = new DaoTipoMaterial();
		Vector<TipoMaterial> todosTiposMateriales = daoTioMaterial.consultarTodosTipoMaterial();
		daoTioMaterial = null;
		return todosTiposMateriales;
	}

}