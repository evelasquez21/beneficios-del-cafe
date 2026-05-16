package gt.umg.progra3.cafecito.model.pesaje;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "pesajes")
public class Pesaje {
    @Id
    @Column(name = "id_pesaje")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "no_cuenta")
    private Long cuenta;
    @Column(name = "placa_transporte")
    private String placaTransporte;
    @Column(name = "cui_transportista")
    private Long cuiTransportista;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_medida", referencedColumnName = "abrev", nullable = false)
    private UnidadMedida unidadMedida;
    @Column(name = "peso_obtenido")
    private float pesoObtenido;


    @OneToMany(mappedBy = "pesaje", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<AsignacionObsPesaje> observaciones = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getCuenta() {
        return cuenta;
    }

    public void setCuenta(Long cuenta) {
        this.cuenta = cuenta;
    }

    public String getPlacaTransporte() {
        return placaTransporte;
    }

    public void setPlacaTransporte(String placaTransporte) {
        this.placaTransporte = placaTransporte;
    }

    public Long getCuiTransportista() {
        return cuiTransportista;
    }

    public void setCuiTransportista(Long cuiTransportista) {
        this.cuiTransportista = cuiTransportista;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public float getPesoObtenido() {
        return pesoObtenido;
    }

    public void setPesoObtenido(float pesoObtenido) {
        this.pesoObtenido = pesoObtenido;
    }

    public Set<AsignacionObsPesaje> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Set<AsignacionObsPesaje> observaciones) {
        this.observaciones = observaciones;
    }
}
