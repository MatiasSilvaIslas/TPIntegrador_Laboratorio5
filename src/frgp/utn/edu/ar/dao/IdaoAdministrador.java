package frgp.utn.edu.ar.dao;

import frgp.utn.edu.ar.entidad.Administrador;

public interface IdaoAdministrador {
	public Administrador ReadOne(int id);
	public boolean Exist(int id);
}
