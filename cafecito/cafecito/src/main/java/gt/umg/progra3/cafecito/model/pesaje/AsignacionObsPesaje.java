package gt.umg.progra3.cafecito.model.pesaje;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "observaciones_pesaje")
public class AsignacionObsPesaje {
    @Id
    @Column(name = "id_AObs_pesaje")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pesaje", nullable = false)
    private Pesaje pesaje;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obs_pesaje", nullable = false)
    private ObservacionPesaje observacion;

    @Column(name = "fecha_observacion")
    private LocalDateTime fechaObservacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pesaje getPesaje() {
        return pesaje;
    }

    public void setPesaje(Pesaje pesaje) {
        this.pesaje = pesaje;
    }

    public ObservacionPesaje getObservacion() {
        return observacion;
    }

    public void setObservacion(ObservacionPesaje observacion) {
        this.observacion = observacion;
    }

    public LocalDateTime getFechaObservacion() {
        return fechaObservacion;
    }

    public void setFechaObservacion(LocalDateTime fechaObservacion) {
        this.fechaObservacion = fechaObservacion;
    }
}
