package gt.umg.progra3.cafecito.model.pesaje;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observacion_pesaje")
public class ObservacionPesaje {
    @Id
    @Column(name = "id_obs_pesaje")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "observacion")
    private Set<AsignacionObsPesaje> observaciones = new HashSet<>();

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

    public Set<AsignacionObsPesaje> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Set<AsignacionObsPesaje> observaciones) {
        this.observaciones = observaciones;
    }
}
