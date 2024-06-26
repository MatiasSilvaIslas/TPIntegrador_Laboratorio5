package frgp.utn.edu.ar.dao;

import java.util.List;
import frgp.utn.edu.ar.entidad.Turno;

public interface IdaoTurno {
	
	public boolean Add(Turno turno);
	public Turno ReadOne(int id_turno);
	public List<Turno> ReadAll();
	public boolean Exist(int id_turno);
	public boolean Update(Turno turno);
	public boolean Delete(Turno turno);

}