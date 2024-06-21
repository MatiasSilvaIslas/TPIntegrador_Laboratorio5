package frgp.utn.edu.ar.negocioImp;

import java.util.List;

import frgp.utn.edu.ar.dao.IdaoUsuario;
import frgp.utn.edu.ar.daoImp.DaoUsuarioImp;
import frgp.utn.edu.ar.entidad.Usuario;
import frgp.utn.edu.ar.negocio.IUsuarioNegocio;

public class UsuarioNegocioImp implements IUsuarioNegocio{
		private IdaoUsuario daoUsuario;
		
		public UsuarioNegocioImp()
		{
			
		}
		public UsuarioNegocioImp(DaoUsuarioImp daoUsuario)
		{
			this.daoUsuario = daoUsuario;
		}
		
		public IdaoUsuario getDaoUsuario() {
			return daoUsuario;
		}
		public void setDaoUsuario(IdaoUsuario daoUsuario) {
			this.daoUsuario = daoUsuario;
		}
		
		public boolean Add(Usuario user) {
			return daoUsuario.Add(user);
		}

		public Usuario ReadOne(String nombreUsuario) {
			return daoUsuario.ReadOne(nombreUsuario);
		}

		public List<Usuario> ReadAll() {
			return daoUsuario.ReadAll();
		}

		public boolean Exist(String nombreUsuario) {
			return daoUsuario.Exist(nombreUsuario);
		}

		public boolean Update(Usuario usuario) {
			return daoUsuario.Update(usuario);
		}

		public boolean Delete(Usuario usuario) {
			return daoUsuario.Delete(usuario);
		}

		public IdaoUsuario getDao() {
			return daoUsuario;
		}

		public void setDao(IdaoUsuario dao) {
			this.daoUsuario = dao;
		}
}
