package frgp.utn.edu.ar.negocioImp;

import java.util.List;
import frgp.utn.edu.ar.negocio.IEspecialidadNegocio;
import frgp.utn.edu.ar.dao.IdaoEspecialidad;
import frgp.utn.edu.ar.daoImp.DaoEspecialidad;
import frgp.utn.edu.ar.entidad.Especialidad;

public class EspecialidadNegocioImp implements IEspecialidadNegocio{
	private IdaoEspecialidad daoEspecialidad;
	
	public EspecialidadNegocioImp()
	{
		
	}
	public EspecialidadNegocioImp(DaoEspecialidad daoEspecialidad)
	{
		this.daoEspecialidad = daoEspecialidad;
	}
	
	public IdaoEspecialidad getDaoEspecialidad() {
		return daoEspecialidad;
	}
	
	public void setDaoEspecialidad(IdaoEspecialidad daoEspecialidad) {
		this.daoEspecialidad = daoEspecialidad;
	}
	
	public boolean Add(Especialidad Especialidad) {
		return daoEspecialidad.Add(Especialidad);
	}

	public Especialidad ReadOne(int id) {
		return daoEspecialidad.ReadOne(id);
	}

	public List<Especialidad> ReadAll() {
		return daoEspecialidad.ReadAll();
	}

	public boolean Exist(int id) {
		return daoEspecialidad.Exist(id);
	}

	public boolean ExistByName(String nombre) {
        return daoEspecialidad.ExistByName(nombre);
    }
	
	public boolean Update(Especialidad Especialidad) {
		return daoEspecialidad.Update(Especialidad);
	}

	public boolean Delete(Especialidad Especialidad) {
		return daoEspecialidad.Delete(Especialidad);
	}

	public IdaoEspecialidad getDao() {
		return daoEspecialidad;
	}

	public void setDao(IdaoEspecialidad dao) {
		this.daoEspecialidad = dao;
	}
}
