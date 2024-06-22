package frgp.utn.edu.ar.daoImp;

import java.util.List;
import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import frgp.utn.edu.ar.dao.IdaoUsuario;
import frgp.utn.edu.ar.entidad.Usuario;

public class DaoUsuarioImp implements IdaoUsuario{
	private Conexion conexion;
	
	public DaoUsuarioImp() {
	}
	
	public DaoUsuarioImp(Conexion conexion) {
		this.conexion = conexion;
	}

	public boolean Add(Usuario user)
	{
		boolean estado = true;
	    conexion = new Conexion();
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.save(user);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agregó a la base de datos
	        Usuario savedUser = (Usuario) session.get(Usuario.class, user.getId());
	        
	        if (savedUser == null) {
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
	
	public Usuario ReadOne(String nombreUsuario) {
	    Session session = null;
	    Usuario usuario = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();

	        // Construyendo la consulta HQL
	        String hql = "FROM Usuario u WHERE u.user = :nombreUsuario";
	        
	        // Ejecutando la consulta y obteniendo el resultado
	        usuario = (Usuario) session.createQuery(hql)
	                                  .setParameter("nombreUsuario", nombreUsuario)
	                                  .uniqueResult();
	        
	        session.getTransaction().commit();
	    } catch (Exception e) {
	        if (session != null && session.getTransaction().isActive()) {
	            session.getTransaction().rollback();
	        }
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    
	    return usuario;
	}


	public boolean Exist(String nombreUsuario) {
        conexion = new Conexion();
        Session session = conexion.abrirConexion();
        session.beginTransaction();
        String hql = "SELECT COUNT(*) FROM Usuario u WHERE u.user = :nombreUsuario";
        Long count = (Long) session.createQuery(hql)
                                  .setParameter("nombreUsuario", nombreUsuario)
                                  .uniqueResult();
        session.getTransaction().commit();
        session.close();
        return count > 0;
    }

	
	public boolean Update(Usuario usuario) {
	    boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Guardar el objeto
	        session.update(usuario);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se actualizó correctamente en la base de datos
	        Usuario savedUser = (Usuario) session.get(Usuario.class, usuario.getId());
	        
	        if (savedUser == null || !savedUser.equals(usuario)) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        estado = false; // Actualización fallida
	        e.printStackTrace();
	    } finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }
	    
	    return estado;
	}


	public boolean Delete(Usuario usuario) {    
	    boolean estado = true;
	    Session session = null;

	    try {
	        session = conexion.abrirConexion();
	        session.beginTransaction();
	        
	        // Actualizar el estado del usuario a inactivo (borrado lógico)
	        usuario.setActivo(false);
	        session.update(usuario);
	        
	        // Forzar la sincronización de la sesión con la base de datos
	        session.flush();
	        
	        // Confirmar la transacción
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se actualizó correctamente en la base de datos
	        Usuario updatedUser = (Usuario) session.get(Usuario.class, usuario.getId());
	        
	        if (updatedUser == null || !updatedUser.isActivo()) {
	            estado = false;
	        }
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


	public List<Usuario> ReadAll() {		
		conexion = new Conexion();
	    Session session = conexion.abrirConexion();
        session.beginTransaction();
        List<Usuario> usuarios = session.createQuery("FROM Usuario").list();
        return usuarios;
	}
	
	 public Usuario autenticarUsuario(String nombreUsuario, String password) {
	        Usuario usuario = ReadOne(nombreUsuario);
	        if (usuario != null && usuario.getPass().equals(password)) {
	            return usuario;
	        }
	        return null;
	    }

	//Agrego los gettes y setters para Spring Core
	
	public Conexion getConexion() {
		return conexion;
	}

	public void setConexion(Conexion conexion) {
		this.conexion = conexion;
	}

}
