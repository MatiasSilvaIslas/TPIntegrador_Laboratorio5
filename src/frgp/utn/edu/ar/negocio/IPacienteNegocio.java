package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Paciente;

public interface IPacienteNegocio {
	public boolean Add(Paciente user);
	public Paciente ReadOne(int dni);
	public List<Paciente> ReadAll();
	public boolean Exist(int dni);
	public boolean Update(Paciente Paciente);
	public boolean Delete(Paciente Paciente);
}
