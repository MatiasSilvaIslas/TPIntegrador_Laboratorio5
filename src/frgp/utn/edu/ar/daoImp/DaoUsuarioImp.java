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
	        
	        // Forzar la sincronizaci�n de la sesi�n con la base de datos
	        session.flush();
	        
	        // Confirmar la transacci�n
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se agreg� a la base de datos
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
	        String hql = "FROM Usuario u WHERE u.user = :nombreUsuario and u.activo = true";
	        System.out.println(hql+"---- "+nombreUsuario);
	        // Ejecutando la consulta y obteniendo el resultado

	        usuario = (Usuario) session.createQuery(hql)
	                                  .setParameter("nombreUsuario", nombreUsuario)
										.uniqueResult();
	        System.out.println(usuario+"---- "+nombreUsuario);
	        session.getTransaction().commit();
	    } catch (Exception e) {

	        e.printStackTrace();
	    } /*finally {
	        if (session != null && session.isOpen()) {
	            session.close();
	        }
	    }*/

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
	        
	        // Forzar la sincronizaci�n de la sesi�n con la base de datos
	        session.flush();
	        
	        // Confirmar la transacci�n
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se actualiz� correctamente en la base de datos
	        Usuario savedUser = (Usuario) session.get(Usuario.class, usuario.getId());
	        
	        if (savedUser == null || !savedUser.equals(usuario)) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        estado = false; // Actualizaci�n fallida
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
	        
	        // Actualizar el estado del usuario a inactivo (borrado l�gico)
	        usuario.setActivo(false);
	        session.update(usuario);
	        
	        // Forzar la sincronizaci�n de la sesi�n con la base de datos
	        session.flush();
	        
	        // Confirmar la transacci�n
	        session.getTransaction().commit();
	        
	        // Verificar si el objeto se actualiz� correctamente en la base de datos
	        Usuario updatedUser = (Usuario) session.get(Usuario.class, usuario.getId());
	        
	        if (updatedUser == null || !updatedUser.isActivo()) {
	            estado = false;
	        }
	    } catch (Exception e) {
	        if (session != null) {
	            session.getTransaction().rollback();
	        }
	        estado = false; // Borrado l�gico fallido
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
