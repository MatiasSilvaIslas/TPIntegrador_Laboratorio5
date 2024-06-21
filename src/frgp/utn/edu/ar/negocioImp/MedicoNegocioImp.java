package frgp.utn.edu.ar.negocioImp;

import java.util.List;
import frgp.utn.edu.ar.negocio.IMedicoNegocio;
import frgp.utn.edu.ar.dao.IdaoMedico;
import frgp.utn.edu.ar.daoImp.DaoMedicoImp;
import frgp.utn.edu.ar.entidad.Medico;

public class MedicoNegocioImp implements IMedicoNegocio{
	private IdaoMedico daoMedico;
	
	public MedicoNegocioImp()
	{
		
	}
	public MedicoNegocioImp(DaoMedicoImp daoMedico)
	{
		this.daoMedico = daoMedico;
	}
	
	public IdaoMedico getDaoMedico() {
		return daoMedico;
	}
	
	public void setDaoMedico(IdaoMedico daoMedico) {
		this.daoMedico = daoMedico;
	}
	
	public boolean Add(Medico medico) {
		return daoMedico.Add(medico);
	}

	public Medico ReadOne(int legajo) {
		return daoMedico.ReadOne(legajo);
	}

	public List<Medico> ReadAll() {
		return daoMedico.ReadAll();
	}

	public boolean Exist(int legajo) {
		return daoMedico.Exist(legajo);
	}

	public boolean Update(Medico medico) {
		return daoMedico.Update(medico);
	}

	public boolean Delete(Medico medico) {
		return daoMedico.Delete(medico);
	}

	public IdaoMedico getDao() {
		return daoMedico;
	}

	public void setDao(IdaoMedico dao) {
		this.daoMedico = dao;
	}
}
