package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Especialidades")
public class Especialidad implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
	private int id;
	
	@Column(name = "especialidad")
	private String nombreEspecialidad;
	
	@Column(name = "activo")
	private boolean activo;
	
	@OneToMany(mappedBy = "especialidad", fetch = FetchType.EAGER)
	private Set<Medico> medicos;

	public Especialidad() {
	}

	public Especialidad(int id, String nombre, Set<Medico> medicos, boolean activo) {
		super();
		this.id = id;
		this.nombreEspecialidad = nombre;
		this.medicos = medicos;
		this.activo = activo;
	}

	public Especialidad(String nombreEspecialidad, boolean activo) {
        this.nombreEspecialidad = nombreEspecialidad;
        this.activo = activo;
    }
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombreEspecialidad;
	}

	public void setNombre(String nombre) {
		this.nombreEspecialidad = nombre;
	}

	public Set<Medico> getMedicos() {
		return medicos;
	}

	public void setMedicos(Set<Medico> medicos) {
		this.medicos = medicos;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Especialidad [id=" + id + ", nombreEspecialidad=" + nombreEspecialidad + ", activo=" + activo
				+ ", medicos=" + medicos + "]";
	}
}
