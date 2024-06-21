package frgp.utn.edu.ar.entidad;


import java.io.Serializable;

import javax.persistence.*;


@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "user",unique = true)
	private String user;
	private String pass;
	
	@Column(name = "activo")
	private boolean activo;
	
	@OneToOne(mappedBy="usuario",fetch=FetchType.EAGER)
	private Medico medico;

	
	public Medico getMedico() {
		return medico;
	}

	public void setMedico(Medico medico) {
		this.medico = medico;
	}

	public Usuario()
	{}

	public Usuario(String user, String pass, boolean activo) {
		super();
		this.user = user;
		this.pass = pass;
		this.activo = activo;
	}
	
	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}


	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


	@Override
	public String toString() {
		String mensaje= "Usuario [user=" + user + ", pass=" + pass + ", docente=";
		if(medico!=null)
			mensaje+=medico.getLegajo()+" "+medico.getNombre()+" "+medico.getApellido() +" Usuario: "+medico.getUsuario().getUser() + "]";
		return mensaje;
	}

}
