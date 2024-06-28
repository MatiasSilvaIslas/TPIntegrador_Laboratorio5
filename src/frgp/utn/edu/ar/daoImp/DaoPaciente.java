package frgp.utn.edu.ar.daoImp;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;

import frgp.utn.edu.ar.dao.IdaoPaciente;
import frgp.utn.edu.ar.entidad.Paciente;

public class DaoPaciente implements IdaoPaciente{

	private Conexion conexion;
	
	public DaoPaciente() {
	}
	
	public DaoPaciente(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean Add(Paciente paciente)
	{
		boolean estado = true;
	    conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.save(paciente);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Paciente savedPaciente = (Paciente) session.get(Paciente.class, paciente.getId());
	        
	        if (savedPaciente == null) {
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

	 public Paciente ReadOne(int dni) {
	        Session session = null;
	        Paciente paciente = null;
	        
	        try {
	            session = conexion.abrirConexion();
	            session.beginTransaction();
	            
	            // Consulta HQL para obtener el paciente por DNI
	            String hql = "FROM Paciente p WHERE p.dni = :dni";
	            paciente = (Paciente) session.createQuery(hql)
	                                         .setParameter("dni", dni)
	                                         .uniqueResult();
	            
	            session.getTransaction().commit();
	            
	        } catch(Exception e) {
	            if (session != null && session.getTransaction().isActive()) {
	                session.getTransaction().rollback();
	            }
	            e.printStackTrace();
	        } finally {
	            if (session != null && session.isOpen()) {
	                session.close();
	            }
	        }
	        
	        return paciente;
	    }

	public boolean Exist(int dni)
	{
		boolean existe = false;
	    Session session = null;
	    
	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Consulta HQL para verificar la existencia de un médico por legajo
	        String hql = "SELECT COUNT(p) FROM Paciente p WHERE p.dni = :dni";
	        Long count = (Long) session.createQuery(hql)
	                                  .setParameter("dni", dni)
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

	
	public boolean Update(Paciente paciente)
	{
		boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.update(paciente);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	         session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Paciente savedUser = (Paciente) session.get(Paciente.class, paciente.getId());
	        
	        if (savedUser.equals(paciente) == false) {
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
	
	public boolean Delete(Paciente paciente) 
	{	
		boolean estado = true;
		conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        paciente.setActivo(false);
	        session.update(paciente);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Paciente savedPac = (Paciente) session.get(Paciente.class, paciente.getId());
	        
	        if (savedPac != null) {
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

	public List<Paciente> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Paciente> pacientes = session.createQuery("FROM Paciente").list();
        List<Paciente> pacientesActivos = new ArrayList<>();
        for (Paciente paciente : pacientes) {
            if (paciente.isActivo()) {
                pacientesActivos.add(paciente);
            }
        }
        
        return pacientesActivos;
	}

	//Agrego los gettes y setters para Spring Core
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

}
