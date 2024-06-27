package frgp.utn.edu.ar.dao;

import java.util.List;
import frgp.utn.edu.ar.entidad.Localidad;

public interface IdaoLocalidad {
	public Localidad ReadOne(int id);
	public boolean Exist(int id);
	public List<Localidad> ReadAll();
	public List<Localidad> getLocalidadesByProvincia(int provinciaId);
}
