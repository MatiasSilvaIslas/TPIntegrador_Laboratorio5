package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoLocalidad;
import frgp.utn.edu.ar.entidad.Localidad;
import frgp.utn.edu.ar.entidad.Usuario;

public class DaoLocalidad implements IdaoLocalidad{
	private Conexion conexion;

	public DaoLocalidad() {
	
	}

	public DaoLocalidad(Conexion conexion) {
		this.conexion = conexion;
	}
	
	public Localidad ReadOne(int id)
	{
		conexion = new Conexion();
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Localidad localidad=(Localidad)session.get(Localidad.class,id);
        //conexion.cerrarSession();
        return localidad;
	}
	
	public boolean Exist(int id)
	{
		conexion = new Conexion();
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Localidad localidad=(Localidad)session.get(Localidad.class,id);
        if(localidad!=null)
        	return true;
        
        return false;
	}
	
	public List<Localidad> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Localidad> localidad = session.createQuery("FROM Localidad").list();
        return localidad;
	}
	
	//Agrego los gettes y setters para Spring Core
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}
	
	public List<Localidad> getLocalidadesByProvincia(int provinciaId) {
		Session session = null;
	    List<Localidad> localidades = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();

	        String hql = "FROM Localidad l WHERE l.provincia.id = :provinciaId";

	        // Crear la consulta y establecer el parámetro
	        localidades = session.createQuery(hql)
	                            .setParameter("provinciaId", provinciaId).list();
	        session.getTransaction().commit();
	    } catch (Exception e) {

	        e.printStackTrace();
	    } /*finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }*/

	    return localidades;
    }
}
