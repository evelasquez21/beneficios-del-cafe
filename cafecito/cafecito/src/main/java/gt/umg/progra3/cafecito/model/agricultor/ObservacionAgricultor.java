package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "observacion_agricultor")
public class ObservacionAgricultor {
    @Id
    @Column(name = "id_obs_agri")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String descripcion;

    @OneToMany(mappedBy = "obsAgri")
    private Set<ObservacionesAgricultor> observaciones = new HashSet<>();

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

    public Set<ObservacionesAgricultor> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Set<ObservacionesAgricultor> observaciones) {
        this.observaciones = observaciones;
    }
}
