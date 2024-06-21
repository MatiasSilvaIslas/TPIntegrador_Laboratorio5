package frgp.utn.edu.ar.dao;

import java.util.List;

import frgp.utn.edu.ar.entidad.Usuario;

public interface IdaoUsuario {
	public boolean Add(Usuario user);
	public Usuario ReadOne(String nombreUsuario);
	public List<Usuario> ReadAll();
	public boolean Exist(String nombreUsuario);
	public boolean Update(Usuario usuario);
	public boolean Delete(Usuario usuario);
}
