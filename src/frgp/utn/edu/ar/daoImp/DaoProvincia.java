package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoProvincia;
import frgp.utn.edu.ar.entidad.Provincia;

public class DaoProvincia implements IdaoProvincia{
	private Conexion conexion;

	public DaoProvincia() {
	
	}

	public DaoProvincia(Conexion conexion) {
		this.conexion = conexion;
	}
	
	public Provincia ReadOne(int id)
	{
		conexion = new Conexion();
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Provincia provincia=(Provincia)session.get(Provincia.class,id);
        //conexion.cerrarSession();
        return provincia;
	}
	
	public boolean Exist(int id)
	{
		conexion = new Conexion();
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Provincia provincia=(Provincia)session.get(Provincia.class,id);
        if(provincia!=null)
        	return true;
        
        return false;
	}
	
	public List<Provincia> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Provincia> provincias = session.createQuery("FROM Provincia").list();
        return provincias;
	}
	
	//Agrego los gettes y setters para Spring Core

	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}
	
}
