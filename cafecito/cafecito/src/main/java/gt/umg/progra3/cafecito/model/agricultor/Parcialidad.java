package gt.umg.progra3.cafecito.model.agricultor;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parcialidad")
public class Parcialidad {
    @Id
    @Column(name = "id_parcialidad")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "no_cuenta")
    private Long noCuenta;
    // Relación Transporte
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "transporte_placa", referencedColumnName = "placa")
    private Transporte transporte;

    // Relación Transportista
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_transportista", referencedColumnName = "id_transportista")
    private  Transportista transportista;

    // Relación Pesaje Maestro
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_pesaje_maestro", referencedColumnName = "id_pesaje_maestro")
    private PesajeMaestro pesajeMaestro;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abrev", referencedColumnName = "abrev", nullable = false)
    private UnidadMedida unidadMedida;
    private float peso;
    @Column(name = "fecha_envio")
    private LocalDateTime fechaEnvio;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transporte getTransporte() {
        return transporte;
    }

    public void setTransporte(Transporte transporte) {
        this.transporte = transporte;
    }

    public Transportista getTransportista() {
        return transportista;
    }

    public void setTransportista(Transportista transportista) {
        this.transportista = transportista;
    }

    public PesajeMaestro getPesajeMaestro() {
        return pesajeMaestro;
    }

    public void setPesajeMaestro(PesajeMaestro pesajeMaestro) {
        this.pesajeMaestro = pesajeMaestro;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public Long getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(Long noCuenta) {
        this.noCuenta = noCuenta;
    }

    public LocalDateTime getFechaEnvio() {
        return fechaEnvio;
    }

    public void setFechaEnvio(LocalDateTime fechaEnvio) {
        this.fechaEnvio = fechaEnvio;
    }
}
