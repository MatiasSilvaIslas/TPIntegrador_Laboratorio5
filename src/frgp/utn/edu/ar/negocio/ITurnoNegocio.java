package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Turno;


public interface ITurnoNegocio {

	public boolean Add(Turno turno);
	public Turno ReadOne(int id_turno);
	public List<Turno> ReadAll();
	public boolean Exist(int id_turno);
	public boolean Update(Turno usuario);
	public boolean Delete(Turno usuario);
}