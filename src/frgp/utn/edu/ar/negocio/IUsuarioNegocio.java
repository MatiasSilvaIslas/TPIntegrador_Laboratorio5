package frgp.utn.edu.ar.negocio;

import java.util.List;

import frgp.utn.edu.ar.entidad.Usuario;

public interface IUsuarioNegocio {
	public boolean Add(Usuario user);
	public Usuario ReadOne(String nombreUsuario);
	public List<Usuario> ReadAll();
	public boolean Exist(String nombreUsuario);
	public boolean Update(Usuario usuario);
	public boolean Delete(Usuario usuario);
}
