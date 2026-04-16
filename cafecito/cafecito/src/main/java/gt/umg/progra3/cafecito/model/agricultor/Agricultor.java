package gt.umg.progra3.cafecito.model.agricultor;

import gt.umg.progra3.cafecito.model.Persona;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "agricultores")
public class Agricultor{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_agricultor")
    private Long id;
    private Long nit;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;
    @OneToOne
    @JoinColumn(name = "cui", referencedColumnName = "cui", unique = true)
    private Persona persona;

    @OneToMany(mappedBy = "agricultor", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ObservacionesAgricultor> observaciones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getNit() {
        return nit;
    }

    public void setNit(Long nit) {
        this.nit = nit;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Set<ObservacionesAgricultor> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Set<ObservacionesAgricultor> observaciones) {
        this.observaciones = observaciones;
    }
}
