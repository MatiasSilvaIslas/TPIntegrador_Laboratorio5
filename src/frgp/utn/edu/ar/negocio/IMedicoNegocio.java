package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Medico;

public interface IMedicoNegocio {
	public boolean Add(Medico medico);
	public Medico ReadOne(int legajo);
	public List<Medico> ReadAll();
	public boolean Exist(int legajo);
	public boolean Update(Medico medico);
	public boolean Delete(Medico medico);
}
