package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "observaciones_transporte")
public class ObservacionesTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_obss_transporte")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "placa", referencedColumnName = "placa", nullable = false)
    private Transporte transporte;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_obs_transporte", referencedColumnName = "id_obs_transporte", nullable = false)
    private ObservacionTransporte obsTransporte;

    @Column(name = "fecha_observacion")
    private LocalDateTime fechaObservacion;
}
