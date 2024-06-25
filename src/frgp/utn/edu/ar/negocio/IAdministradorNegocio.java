package frgp.utn.edu.ar.negocio;

import frgp.utn.edu.ar.entidad.Administrador;

public interface IAdministradorNegocio {
	public Administrador ReadOne(int id);
	public boolean Exist(int id);
}
