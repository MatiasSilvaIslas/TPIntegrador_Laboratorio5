package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Session;
import frgp.utn.edu.ar.dao.IdaoEspecialidad;
import frgp.utn.edu.ar.entidad.Especialidad;


public class DaoEspecialidad implements IdaoEspecialidad{
	private Conexion conexion;
	
	public DaoEspecialidad() {
	}
	
	public DaoEspecialidad(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean Add(Especialidad especialidad)
	{
		boolean estado = true;
	    conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.save(especialidad);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Especialidad savedEspecialidad = (Especialidad) session.get(Especialidad.class, especialidad.getId());
	        
	        if (savedEspecialidad == null) {
	            estado = false;
	        }
	        
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    }
	    
	    return estado;
	}
	
	public Especialidad ReadOne(int id)
	{
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Especialidad especialidad=(Especialidad)session.get(Especialidad.class,id);
        return especialidad;
	}

	public boolean Exist(int id)
	{
		Session session= conexion.abrirConexion();
		session.beginTransaction();
		Especialidad especialidad=(Especialidad)session.get(Especialidad.class,id);
        if(especialidad!=null)
        	return true;
        
        return false;
	}
	
	public boolean ExistByName(String nombreEspecialidad) {
	    conexion = new Conexion();
	    Session session = conexion.abrirConexion();
	    session.beginTransaction();
	    
	    // HQL usando el nombre de la propiedad de la entidad, no el nombre de la columna de la base de datos
	    String hql = "SELECT COUNT(e) FROM Especialidad e WHERE e.nombreEspecialidad = :nombreEspecialidad";
	    Long count = (Long) session.createQuery(hql)
	                               .setParameter("nombreEspecialidad", nombreEspecialidad)
	                               .uniqueResult();
	    
	    session.getTransaction().commit();
	    session.close();
	    
	    return count > 0;
	}


	
	public boolean Update(Especialidad especialidad)
	{
		boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.update(especialidad);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	         session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Especialidad saved = (Especialidad) session.get(Especialidad.class, especialidad.getId());
	        
	        if (saved.equals(especialidad) == false) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    }
	    
		return estado;
	}
	
	public boolean Delete(Especialidad especialidad) 
	{	
		boolean estado = true;
		conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.delete(especialidad);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Especialidad saved = (Especialidad) session.get(Especialidad.class, especialidad.getId());
	        
	        if (saved != null) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	    }
	    
		return estado;
	}

	public List<Especialidad> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Especialidad> especialidad = session.createQuery("FROM Especialidad").list();
        return especialidad;
	}

	//Agrego los gettes y setters para Spring Core
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

}
