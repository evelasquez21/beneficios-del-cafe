package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observacion_transporte")
public class ObservacionTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_obs_transporte")
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "obsTransporte")
    private Set<ObservacionesTransporte> observaciones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Set<ObservacionesTransporte> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Set<ObservacionesTransporte> observaciones) {
        this.observaciones = observaciones;
    }
}
