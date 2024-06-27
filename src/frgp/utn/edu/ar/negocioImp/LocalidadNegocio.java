package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import frgp.utn.edu.ar.dao.IdaoLocalidad;
import frgp.utn.edu.ar.daoImp.DaoLocalidad;
import frgp.utn.edu.ar.entidad.Localidad;
import frgp.utn.edu.ar.negocio.ILocalidadNegocio;

public class LocalidadNegocio implements ILocalidadNegocio{
	private IdaoLocalidad daoLocalidad;
	
	public LocalidadNegocio()
	{
		
	}
	public LocalidadNegocio(DaoLocalidad daoLocalidad)
	{
		this.daoLocalidad = daoLocalidad;
	}
	
	public IdaoLocalidad getDaoLocalidad() {
		return daoLocalidad;
	}
	
	public void setDaoLocalidad(IdaoLocalidad daoLocalidad) {
		this.daoLocalidad = daoLocalidad;
	}
	
	public Localidad ReadOne(int id) {
		return daoLocalidad.ReadOne(id);
	}
	
	public boolean Exist(int id) {
		return daoLocalidad.Exist(id);
	}
	
	public List<Localidad> ReadAll() {
		return daoLocalidad.ReadAll();
	}
	
	public List<Localidad> getLocalidadesByProvincia(int provinciaId) {
		return daoLocalidad.getLocalidadesByProvincia(provinciaId);
	}
	
}
