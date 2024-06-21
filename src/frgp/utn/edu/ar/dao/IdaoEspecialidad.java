package frgp.utn.edu.ar.dao;

import java.util.List;

import frgp.utn.edu.ar.entidad.Especialidad;

public interface IdaoEspecialidad {
	public boolean Add(Especialidad especialidad);
	public Especialidad ReadOne(int id);
	public List<Especialidad> ReadAll();
	public boolean Exist(int id);
	public boolean ExistByName(String nombre);
	public boolean Update(Especialidad especialidad);
	public boolean Delete(Especialidad especialidad);
}
