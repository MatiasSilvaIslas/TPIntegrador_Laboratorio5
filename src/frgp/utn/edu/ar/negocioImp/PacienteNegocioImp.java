package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import frgp.utn.edu.ar.dao.IdaoPaciente;
import frgp.utn.edu.ar.daoImp.DaoPaciente;
import frgp.utn.edu.ar.entidad.Paciente;
import frgp.utn.edu.ar.negocio.IPacienteNegocio;

public class PacienteNegocioImp implements IPacienteNegocio{
	private IdaoPaciente daoPaciente;
	
	public PacienteNegocioImp()
	{
		
	}
	public PacienteNegocioImp(DaoPaciente daoPaciente)
	{
		this.daoPaciente = daoPaciente;
	}
	
	public IdaoPaciente getDaoPaciente() {
		return daoPaciente;
	}
	public void setDaoPaciente(IdaoPaciente daoPaciente) {
		this.daoPaciente = daoPaciente;
	}
	
	public boolean Add(Paciente paciente) {
		return daoPaciente.Add(paciente);
	}

	public Paciente ReadOne(int dni) {
		return daoPaciente.ReadOne(dni);
	}

	public List<Paciente> ReadAll() {
		return daoPaciente.ReadAll();
	}

	public boolean Exist(int dni) {
		return daoPaciente.Exist(dni);
	}

	public boolean Update(Paciente Paciente) {
		return daoPaciente.Update(Paciente);
	}

	public boolean Delete(Paciente Paciente) {
		return daoPaciente.Delete(Paciente);
	}

	public IdaoPaciente getDao() {
		return daoPaciente;
	}

	public void setDao(IdaoPaciente dao) {
		this.daoPaciente = dao;
	}

}
