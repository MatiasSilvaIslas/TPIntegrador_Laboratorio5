package frgp.utn.edu.ar.daoImp;

import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoAdministrador;
import frgp.utn.edu.ar.entidad.Administrador;
import frgp.utn.edu.ar.entidad.Usuario;

public class DaoAdministrador implements IdaoAdministrador {
	private Conexion conexion;

	public DaoAdministrador() {
	
	}

	public DaoAdministrador(Conexion conexion) {
		this.conexion = conexion;
	}
	
	public Administrador ReadOne(int id)
	{
		conexion = new Conexion();
		Session session= conexion.abrirConexion();
		session.beginTransaction();
        Administrador admin=(Administrador)session.get(Administrador.class,id);
        //conexion.cerrarSession();
        return admin;
	}
	
	public boolean Exist(int id)
	{
		conexion = new Conexion();
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Administrador admin=(Administrador)session.get(Administrador.class,id);
       // conexion.cerrarSession();
        if(admin!=null)
        	return true;
        
        return false;
	}
}
