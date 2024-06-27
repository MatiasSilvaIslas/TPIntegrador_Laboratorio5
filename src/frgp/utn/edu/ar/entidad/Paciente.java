package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import java.sql.Date;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.*;

@Entity
@Table(name = "Pacientes")
public class Paciente implements Serializable {
	private static final long serialVersionUID=1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "paciente_id")
    private int id;
	
	@OneToOne(cascade= {CascadeType.ALL}) 
	@JoinColumn(name="turno_id")
	private Turno turno; 
	
	@OneToMany(mappedBy = "paciente", fetch = FetchType.EAGER)
	private Set<Turno> turnos;

    @Column(name = "dni",unique = true)
    private int dni;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "sexo")
    private char sexo;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "telefono")
    private String telefono;
    
    @Column(name = "direccion")
    private String direccion;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "localidad_id", nullable = false)
    private Localidad localidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provincia_id", nullable = false)
    private Provincia provincia;
    
    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "activo")
	private boolean activo;
    
    public Set<Turno> getTurno() {
		return turnos;
	}
	
	public void setTurno(Set<Turno> turnos) {
		this.turnos = turnos;
	}
    
    public Paciente() {} 
	
	public Paciente(int dni, String nombre, String apellido,
			char sexo, Date fechaNacimiento, String direccion, Localidad localidad, Provincia provincia, String correoElectronico,
			String telefono, boolean activo) {
		super();
		
		this.dni = dni;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.fechaNacimiento = fechaNacimiento;
		this.telefono = telefono;
		this.direccion = direccion;
		this.localidad = localidad;
		this.provincia = provincia;
		this.correoElectronico = correoElectronico;			
		this.activo = activo;
	}

	public int getDNI() {
		return dni;
	}

	public void setDNI(int dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public char getSexo() {
		return sexo;
	}

	public void setSexo(char sexo) {
		this.sexo = sexo;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public Localidad getLocalidad() {
		return localidad;
	}

	public void setLocalidad(Localidad localidad) {
		this.localidad = localidad;
	}
	
	public Provincia getProvincia() {
		return provincia;
	}

	public void setProvincia(Provincia provincia) {
		this.provincia = provincia;
	}

	public String getCorreoElectronico() {
		return correoElectronico;
	}

	public void setCorreoElectronico(String correoElectronico) {
		this.correoElectronico = correoElectronico;
	}

	public String getTelefono() {
		return telefono;
	}

	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	@Override
	public String toString() {
		return "Paciente [DNI=" + dni + ", nombre=" + nombre + "TURNO=" + turnos.toString() + ", apellido=" + apellido + ", sexo=" + sexo
				+ ", fechaNacimiento=" + fechaNacimiento + ", direccion=" + direccion + ", localidad=" + localidad
				+ ", provincia=" + provincia + ", correoElectronico=" + correoElectronico + ", telefono=" + telefono + "]";
	}
	
	public static boolean patternMatches(String emailAddress, String regexPattern) {
	    return Pattern.compile(regexPattern)
	      .matcher(emailAddress)
	      .matches();
	}

}
