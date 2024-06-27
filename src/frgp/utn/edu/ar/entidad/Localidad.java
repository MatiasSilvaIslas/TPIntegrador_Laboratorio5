package frgp.utn.edu.ar.entidad;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import java.io.Serializable;

@Entity
@Table(name = "Localidades")
public class Localidad implements Serializable{
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_localidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "codProvincia", nullable = false)
    private Provincia provincia;

    @Column(nullable = false)
    private String localidad;

    // Getters and setters
    public int getId() {
        return id_localidad;
    }

    public void setId(int id) {
        this.id_localidad = id;
    }

    public Provincia getProvincia() {
        return provincia;
    }

    public void setProvincia(Provincia provincia) {
        this.provincia = provincia;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }
}
