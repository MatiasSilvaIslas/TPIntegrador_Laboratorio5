package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import frgp.utn.edu.ar.dao.IdaoProvincia;
import frgp.utn.edu.ar.daoImp.DaoProvincia;
import frgp.utn.edu.ar.entidad.Provincia;
import frgp.utn.edu.ar.negocio.IProvinciaNegocio;

public class ProvinciaNegocio implements IProvinciaNegocio{
	private IdaoProvincia daoProvincia;
	
	public ProvinciaNegocio()
	{
		
	}
	public ProvinciaNegocio(DaoProvincia daoProvincia)
	{
		this.daoProvincia = daoProvincia;
	}
	
	public IdaoProvincia getDaoProvincia() {
		return daoProvincia;
	}
	
	public void setDaoProvincia(IdaoProvincia daoProvincia) {
		this.daoProvincia = daoProvincia;
	}
	
	public Provincia ReadOne(int id) {
		return daoProvincia.ReadOne(id);
	}
	
	public boolean Exist(int id) {
		return daoProvincia.Exist(id);
	}
	
	public List<Provincia> ReadAll() {
		return daoProvincia.ReadAll();
	}
	
}
