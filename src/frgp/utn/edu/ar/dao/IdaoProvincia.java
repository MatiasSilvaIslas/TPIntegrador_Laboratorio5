package frgp.utn.edu.ar.dao;

import java.util.List;
import frgp.utn.edu.ar.entidad.Provincia;;

public interface IdaoProvincia {
	public Provincia ReadOne(int id);
	public boolean Exist(int id);
	public List<Provincia> ReadAll();

}
