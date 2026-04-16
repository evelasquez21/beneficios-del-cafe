package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "observaciones_agricultor")
public class ObservacionesAgricultor {
    @Id
    @Column(name = "id_obss_agricultor")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_agricultor", referencedColumnName = "id_agricultor", nullable = false)
    private Agricultor agricultor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obs_agri", referencedColumnName = "id_obs_agri", nullable = false)
    private ObservacionAgricultor obsAgri;

    @Column(name = "fecha_observacion")
    private LocalDateTime fechaObservacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
    }

    public ObservacionAgricultor getObsAgri() {
        return obsAgri;
    }

    public void setObsAgri(ObservacionAgricultor obsAgri) {
        this.obsAgri = obsAgri;
    }

    public LocalDateTime getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(LocalDateTime fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }
}
