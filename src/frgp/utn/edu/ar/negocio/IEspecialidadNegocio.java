package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Especialidad;

public interface IEspecialidadNegocio {

	public boolean Add(Especialidad especialidad);
	public Especialidad ReadOne(int id);
	public List<Especialidad> ReadAll();
	public boolean Exist(int legajo);
	public boolean Update(Especialidad especialidad);
	public boolean Delete(Especialidad especialidad);
}
