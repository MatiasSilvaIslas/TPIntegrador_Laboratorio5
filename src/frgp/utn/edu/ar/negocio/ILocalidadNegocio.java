package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Localidad;

public interface ILocalidadNegocio {

	public Localidad ReadOne(int id);
	public boolean Exist(int id);
	public List<Localidad> ReadAll();
	public List<Localidad> getLocalidadesByProvincia(int provinciaId);
}
