package frgp.utn.edu.ar.negocioImp;

import frgp.utn.edu.ar.dao.IdaoAdministrador;
import frgp.utn.edu.ar.daoImp.DaoAdministrador;
import frgp.utn.edu.ar.entidad.Administrador;
import frgp.utn.edu.ar.negocio.IAdministradorNegocio;

public class AdministradorNegocio implements IAdministradorNegocio{
private IdaoAdministrador daoAdministrador;
	
	public AdministradorNegocio()
	{
		
	}
	public AdministradorNegocio(DaoAdministrador daoAdministrador)
	{
		this.daoAdministrador = daoAdministrador;
	}
	
	public IdaoAdministrador getDaoAdministrador() {
		return daoAdministrador;
	}
	
	public void setDaoAdministrador(IdaoAdministrador daoAdministrador) {
		this.daoAdministrador = daoAdministrador;
	}
	
	public Administrador ReadOne(int id) {
		return daoAdministrador.ReadOne(id);
	}
	
	public boolean Exist(int id) {
		return daoAdministrador.Exist(id);
	}
}
