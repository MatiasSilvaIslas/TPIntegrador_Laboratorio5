package frgp.utn.edu.ar.daoImp;

import java.sql.Date;
import java.util.List;
import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoTurno;
import frgp.utn.edu.ar.daoImp.Conexion;
import frgp.utn.edu.ar.entidad.Turno;
import frgp.utn.edu.ar.entidad.Turno;



public class DaoTurno implements IdaoTurno{
	
private Conexion conexion;
	
	public DaoTurno() {
	}
	
	public DaoTurno(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean Add(Turno turno)
	{
		boolean estado = true;
	    conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.merge(turno);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Turno savedTurno = (Turno) session.get(Turno.class, turno.getId_turno());
	        
	        if (savedTurno == null) {
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
	
	public Turno ReadOne(int Id_turno)
	{
		Session session= conexion.abrirConexion();
		session.beginTransaction();
        Turno turno=(Turno)session.get(Turno.class,Id_turno);
        return turno;
	}

	public boolean Exist(int Id_turno)
	{
		Session session= conexion.abrirConexion();
		session.beginTransaction();
        Turno turno=(Turno)session.get(Turno.class,Id_turno);
        if(turno!=null)
        	return true;
        
        return false;
	}

	
	public boolean Update(Turno turno)
	{
		boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.merge(turno);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	         session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Turno savedTurno = (Turno) session.get(Turno.class, turno.getId_turno());
	        
	        if (savedTurno.equals(turno) == false) {
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
	
	public boolean Delete(Turno turno) 
	{	
		boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();

	        // Actualizar el estado activo a false usando una consulta HQL directa
	        int rowCount = session.createQuery("UPDATE Turno SET activo = false WHERE id_turno = :id_turno")
	            .setParameter("id_turno", turno.getId_turno())
	            .executeUpdate();

	        // Verificar si se actualizó exactamente un registro
	        if (rowCount == 1) {
	            estado = true; // Actualización exitosa
	        }

	        // Confirmar la transacción
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        estado = false; // Borrado lógico fallido
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }

	    return estado;
	}

	public List<Turno> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Turno> usuarios = session.createQuery("FROM Turno").list();
        return usuarios;
	}

	//Agrego los gettes y setters para Spring Core
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

}