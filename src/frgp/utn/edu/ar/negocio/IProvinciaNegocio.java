package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Provincia;

public interface IProvinciaNegocio {
	public Provincia ReadOne(int id);
	public boolean Exist(int id);
	public List<Provincia> ReadAll();
}
