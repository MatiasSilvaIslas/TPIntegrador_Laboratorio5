package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Entity
@Table(name = "turnos")
public class Turno implements Serializable{
	/*Medico, Paciente, fecha, hora, observacion, estado (pendiente, presente, ausente).*/
	private static final long serialVersionUID = 1L;
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_turno")
	private int id_turno;
	
	@Column(name = "fecha", columnDefinition = "DATE")
    private Date fecha;

    @Column(name = "hora", columnDefinition = "TIME")
    private Time hora;
    
	private String observacion;
	@Column(name = "estado")
    @Enumerated(EnumType.STRING)
	private Estado estado;
	
	@Column(name = "activo")
	private boolean activo;
	
	@ManyToOne(cascade= {CascadeType.ALL})
	@JoinColumn(name="id")
	private Medico medico;
	
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="paciente_id")
	private Paciente paciente;
	
	public Turno() {
	}
	
	public Turno(Date fecha, Time hora, String observacion, Estado estado, Medico medico, Paciente paciente, boolean activo) {
		super();
		this.fecha = fecha;
		this.hora = hora;
		this.observacion = observacion;
		this.estado = estado;
		this.medico = medico;
		this.paciente = paciente;
		this.activo = activo;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public int getId_turno() {
		return id_turno;
	}
	public Medico getMedico() {
		return medico;
	}
	public void setMedico(Medico medico) {
		this.medico = medico;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public Time getHora() {
		return hora;
	}
	public void setHora(Time hora) {
		this.hora = hora;
	}
	public String getObservacion() {
		return observacion;
	}
	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}
	public Estado getEstado() {
		return estado;
	}
	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return	"-------------------------------- \n" + "Datos del turno: \n" + "Turno numero:" + id_turno +  "\n Fecha: " + fecha +  "\n Hora: " + hora +  "\n Observaciones: " + observacion +  "\n Estado: " + estado +  
				"\n Paciente: " + paciente.getDNI() + " " + paciente.getApellido() + " " + paciente.getNombre() +  "\n Medico: " + medico.getApellido() + " "+ medico.getNombre()+ " " + "medico.getEspecialidad().getNombre()";
	}
	
}
