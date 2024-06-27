package frgp.utn.edu.ar.entidad;

import java.io.Serializable;
import java.sql.Date;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import javax.persistence.*;


@Entity
@Table(name = "Medicos")
public class Medico implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToOne(cascade = {CascadeType.ALL})
    @JoinColumn(name = "especialidad_id")
    private Especialidad especialidad;

    @Column(name = "legajo", unique = true)
    private int legajo;

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "apellido")
    private String apellido;

    @Column(name = "sexo")
    private char sexo;

    @Column(name = "fecha_nacimiento")
    private Date fechaNacimiento;

    @Column(name = "direccion")
    private String direccion;

    @ManyToOne
    @JoinColumn(name = "localidad_id", nullable = false)
    private Localidad localidad;

    @Column(name = "correo_electronico")
    private String correoElectronico;

    @Column(name = "telefono")
    private String telefono;

    @Column(name = "activo")
    private boolean activo;

    @ElementCollection
    @CollectionTable(name = "medico_dias", joinColumns = @JoinColumn(name = "medico_id"))
    @Column(name = "dia")
    private Set<String> diasDeTrabajo;

    @ElementCollection
    @CollectionTable(name = "medico_horarios", joinColumns = @JoinColumn(name = "medico_id"))
    @Column(name = "horario")
    private Set<String> horariosDeTrabajo;

    // Getters y setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Especialidad getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(Especialidad especialidad) {
        this.especialidad = especialidad;
    }

    public int getLegajo() {
        return legajo;
    }

    public void setLegajo(int legajo) {
        this.legajo = legajo;
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

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getTelefono() {
        return telefono;
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

    public Set<String> getDiasDeTrabajo() {
        return diasDeTrabajo;
    }

    public void setDiasDeTrabajo(Set<String> diasDeTrabajo) {
        this.diasDeTrabajo = diasDeTrabajo;
    }

    public Set<String> getHorariosDeTrabajo() {
        return horariosDeTrabajo;
    }

    public void setHorariosDeTrabajo(Set<String> horariosDeTrabajo) {
        this.horariosDeTrabajo = horariosDeTrabajo;
    }

    @Override
    public String toString() {
        return "Datos del medico \n Legajo: " + legajo + "\n Nombre: " + nombre + "\n Apellido: " + apellido
                + "\n Especialidad: " + especialidad.getNombre() + "\n Sexo: " + sexo + "\n Fecha de nacimiento: "
                + fechaNacimiento + "\n Dirección: " + direccion + "\n Localidad: " + localidad + "\n Correo electronico: "
                + correoElectronico + "\n Telefono: " + telefono + "\n Usuario y contraseña: " + usuario.getUser() + " "
                + usuario.getPass() + "\n Dias de trabajo: " + diasDeTrabajo + "\n Horarios de trabajo: " + horariosDeTrabajo;
    }

    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern).matcher(emailAddress).matches();
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Medico medico = (Medico) o;
        return Objects.equals(id, medico.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
