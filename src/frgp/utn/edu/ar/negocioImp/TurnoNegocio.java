package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import frgp.utn.edu.ar.dao.IdaoTurno;
import frgp.utn.edu.ar.daoImp.DaoTurno;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.negocio.ITurnoNegocio;

public class TurnoNegocio implements ITurnoNegocio {

	//Necesario para spring core
	private IdaoTurno daoTurno;
	
	public TurnoNegocio()
	{
		
	}
	public TurnoNegocio(DaoTurno daoTurno)
	{
		this.daoTurno = daoTurno;
	}
	
	public IdaoTurno getDaoTurno() {
		return daoTurno;
	}
	public void setDaoTurno(IdaoTurno daoTurno) {
		this.daoTurno = daoTurno;
	}
	
	//Hasta aqu : Necesario para spring core
	
	public boolean Add(Turno turno) {
		return daoTurno.Add(turno);
	}

	public Turno ReadOne(int id_turno) {
		return daoTurno.ReadOne(id_turno);
	}

	public List<Turno> ReadAll() {
		return daoTurno.ReadAll();
	}

	public boolean Exist(int id_turno) {
		return daoTurno.Exist(id_turno);
	}

	public boolean Update(Turno turno) {
		return daoTurno.Update(turno);
	}

	public boolean Delete(Turno turno) {
		return daoTurno.Delete(turno);
	}

	public IdaoTurno getDao() {
		return daoTurno;
	}

	public void setDao(IdaoTurno dao) {
		this.daoTurno = dao;
	}
}

