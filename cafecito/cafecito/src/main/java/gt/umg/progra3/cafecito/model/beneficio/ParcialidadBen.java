package gt.umg.progra3.cafecito.model.beneficio;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "parcialidad_beneficio")
public class Parcialidad {
    @Id
    @Column(name = "id_parcialidad")
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_cuenta", referencedColumnName = "id_cuenta")
    private Cuenta cuenta;
    @Column(name = "transporte_placa")
    private String transporte;

    @Column(name = "id_transportista")
    private Long idTransportista;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_medida", referencedColumnName = "abrev", nullable = false)
    private UnidadMedida unidadMedida;
    private float peso;
    @Column(name = "fecha_recepcion")
    private LocalDateTime fechaRecepcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public String getTransporte() {
        return transporte;
    }

    public void setTransporte(String transporte) {
        this.transporte = transporte;
    }

    public Long getIdTransportista() {
        return idTransportista;
    }

    public void setIdTransportista(Long idTransportista) {
        this.idTransportista = idTransportista;
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

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}
