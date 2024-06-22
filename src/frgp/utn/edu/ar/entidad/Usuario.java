package frgp.utn.edu.ar.entidad;


import java.io.Serializable;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "usuarios")
public class Usuario implements Serializable{

	 private static final long serialVersionUID = 1L;

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(name = "id")
	    private int id;

	    @Column(name = "user", unique = true)
	    private String user;

	    private String pass;

	    @Column(name = "activo")
	    private boolean activo;

	    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
	    private Medico medico;

	    @OneToOne(mappedBy = "usuario", fetch = FetchType.EAGER)
	    private Administrador admin;

	    public Medico getMedico() {
	        return medico;
	    }

	    public void setMedico(Medico medico) {
	        this.medico = medico;
	    }

	    public Administrador getAdmin() {
	        return admin;
	    }

	    public void setAdmin(Administrador admin) {
	        this.admin = admin;
	    }

	    public Usuario() {}

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
	        String mensaje = "Usuario [user=" + user + ", pass=" + pass + ", ";
	        if (medico != null) {
	            mensaje += "Medico: " + medico.getLegajo() + " " + medico.getNombre() + " " + medico.getApellido();
	        } else if (admin != null) {
	            mensaje += "Admin: " + admin.getNombre();
	        }
	        return mensaje + "]";
	    }

}
