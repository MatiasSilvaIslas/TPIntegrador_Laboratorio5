package frgp.utn.edu.ar.daoImp;

import java.util.List;

import org.hibernate.Hibernate;
import org.hibernate.Session;
import frgp.utn.edu.ar.dao.IdaoMedico;
import frgp.utn.edu.ar.entidad.Medico;


public class DaoMedicoImp implements IdaoMedico{
	private Conexion conexion;
	
	public DaoMedicoImp() {
	}
	
	public DaoMedicoImp(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean Add(Medico medico)
	{
		boolean estado = true;
	    conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.merge(medico);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Medico savedMedico = (Medico) session.get(Medico.class, medico.getId());
	        
	        if (savedMedico == null) {
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
	
	public Medico ReadOne(int legajo) {
	    Session session = null;
	    Medico medico = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();

	        // Consulta HQL con LEFT JOIN FETCH para obtener el objeto Medico por legajo
	        String hql = "SELECT DISTINCT m FROM Medico m " +
	                     "LEFT JOIN FETCH m.diasDeTrabajo " +
	                     "LEFT JOIN FETCH m.horariosDeTrabajo " +
	                     "LEFT JOIN FETCH m.especialidad " +
	                     "LEFT JOIN FETCH m.usuario " +
	                     "WHERE m.legajo = :legajo";
	        medico = (Medico) session.createQuery(hql)
	                                 .setParameter("legajo", legajo)
	                                 .uniqueResult();


	        if (medico != null) {
	            // Cargamos las colecciones por separado
	            Hibernate.initialize(medico.getDiasDeTrabajo());
	            Hibernate.initialize(medico.getHorariosDeTrabajo());
	        }
	        
	        session.getTransaction().commit();

	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }

	    return medico;
	}



	public boolean Exist(int legajo) {
	    boolean existe = false;
	    Session session = null;
	    
	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Consulta HQL para verificar la existencia de un médico por legajo
	        String hql = "SELECT COUNT(m) FROM Medico m WHERE m.legajo = :legajo";
	        Long count = (Long) session.createQuery(hql)
	                                  .setParameter("legajo", legajo)
	                                  .uniqueResult();
	        
	        if (count != null && count > 0) {
	            existe = true;
	        }
	        
	        session.getTransaction().commit();
	        
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null) {
	            session.close();
	        }
	    }
	    
	    return existe;
	}


	
	public boolean Update(Medico medico)
	{
		boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();

	        // Guardar el objeto
	        session.merge(medico);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	         session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Medico savedUser = (Medico) session.get(Medico.class, medico.getId());
	        
	        if (savedUser.equals(medico) == false) {
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
	
	public boolean Delete(Medico medico) {
	    boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();

	        // Actualizar el estado activo a false usando una consulta HQL directa
	        int rowCount = session.createQuery("UPDATE Medico SET activo = false WHERE legajo = :legajo")
	            .setParameter("legajo", medico.getLegajo())
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

	public List<Medico> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Medico> medicos = session.createQuery("FROM Medico").list();
        return medicos;
	}

	//Agrego los gettes y setters para Spring Core
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

}
